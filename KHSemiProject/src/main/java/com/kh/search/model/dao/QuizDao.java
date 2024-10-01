package com.kh.search.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringJoiner;

import com.kh.common.JDBCTemplate;
import com.kh.common.PageInfo;
import com.kh.search.model.vo.Quiz;
import com.kh.search.model.vo.Tag;

public class QuizDao {
	private Properties prop = new Properties();
	

	public QuizDao() {
		String filePath = QuizDao.class.getResource("/db/sql/Quiz-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectQuizCount(Connection conn, int category, int search_type, String search_text, int orderby, ArrayList<String> tagList) {
		
		int quizCount = 0;
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    // SQL 쿼리 동적 생성
	    StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS COUNT FROM QUIZ ")
	        .append("JOIN CATEGORY USING (CATEGORY_NUMBER) ")
	        .append("JOIN MEMBER USING (MEMBER_NUMBER) ");

	    // 1. 태그가 있는 경우 QUIZ_TAG와 JOIN
	    if (tagList != null && !tagList.isEmpty()) {
	        sql.append("JOIN QUIZ_TAG USING (QUIZ_NUMBER) ");
	    }

	    sql.append("WHERE 1=1 "); // 기본 조건

	    // 2. 카테고리 설정
	    if (category == 0) {
	        sql.append("AND CATEGORY_NUMBER BETWEEN 1 AND 8 ");
	    } else {
	        sql.append("AND CATEGORY_NUMBER = ? ");
	    }

	    // 3. 검색 조건 설정
	    if (search_type == 1) {
	        sql.append("AND QUIZ_TITLE LIKE ? ");
	    } else if (search_type == 2) {
	        sql.append("AND MEMBER_NICKNAME LIKE ? ");
	    }

	    // 4. 태그 필터 추가
	    if (tagList != null && !tagList.isEmpty()) {
	        sql.append("AND TAG_NAME IN (");
	        for (int i = 0; i < tagList.size(); i++) {
	            sql.append("?");
	            if (i < tagList.size() - 1) {
	                sql.append(", ");
	            }
	        }
	        sql.append(") ");
	    }

	    try {
	        pstmt = conn.prepareStatement(sql.toString());

	        int paramIndex = 1;

	        // 카테고리 바인딩
	        if (category != 0) {
	            pstmt.setInt(paramIndex++, category);
	        }

	        // 검색 텍스트 바인딩
	        if (search_type == 1 || search_type == 2) {
	            pstmt.setString(paramIndex++, "%" + search_text + "%");
	        }

	        // 태그 리스트 바인딩
	        if (tagList != null && !tagList.isEmpty()) {
	            for (String tag : tagList) {
	                pstmt.setString(paramIndex++, tag);
	            }
	        }

	        // 쿼리 실행
	        rset = pstmt.executeQuery();

	        if (rset.next()) {
	            quizCount = rset.getInt("COUNT");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }

	    return quizCount;
	}

	public ArrayList<Quiz> selectQuiz(Connection conn, PageInfo pi, int category, int search_type, String search_text, int orderby, ArrayList<String> tagList) {
	    ArrayList<Quiz> list = new ArrayList<>();
	    
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
	    int endRow = startRow + pi.getBoardLimit() - 1;

	    // 기본 SELECT 쿼리
	    String sql = "SELECT * FROM ( " +
	                 "    SELECT A.*, ROWNUM AS RNUM FROM ( " +
	                 "        SELECT Q.QUIZ_NUMBER, Q.QUIZ_TITLE ";

	    // ORDER BY에 따른 쿼리 변경
	    if (orderby == 1) {
	        sql += "        FROM (SELECT QUIZ_NUMBER, COUNT(*) " +
	               "        FROM QUIZ_LOG GROUP BY QUIZ_NUMBER ORDER BY COUNT(*) DESC) QL " +
	               "        JOIN QUIZ Q ON QL.QUIZ_NUMBER = Q.QUIZ_NUMBER ";
	    } else if (orderby == 2) {
	        sql += "        FROM QUIZ Q ";
	    } else if (orderby == 3) {
	        sql += "        FROM (SELECT QUIZ_NUMBER, AVG(QUIZ_RATE_RATING) " +
	               "        FROM QUIZ_RATE GROUP BY QUIZ_NUMBER ORDER BY AVG(QUIZ_RATE_RATING) DESC) QR " +
	               "        JOIN QUIZ Q ON QR.QUIZ_NUMBER = Q.QUIZ_NUMBER ";
	    }

	    // 기본 조인 및 WHERE 절 시작
	    sql += "        JOIN CATEGORY C ON Q.CATEGORY_NUMBER = C.CATEGORY_NUMBER " +
	           "        JOIN MEMBER M ON Q.MEMBER_NUMBER = M.MEMBER_NUMBER ";

	    // 태그 필터링 추가
	    if (tagList != null && !tagList.isEmpty()) {
	        sql += "        JOIN QUIZ_TAG QT ON Q.QUIZ_NUMBER = QT.QUIZ_NUMBER ";
	    }

	    // WHERE 절 필터링
	    sql += "        WHERE 1 = 1 ";  // 기본 WHERE 절

	    // 카테고리 필터링
	    if (category != 0) {
	        sql += "        AND C.CATEGORY_NUMBER = ? ";
	    }

	    // 검색 필터링
	    if (search_text != null && !search_text.trim().isEmpty()) {
	        if (search_type == 1) {
	            sql += "        AND Q.QUIZ_TITLE LIKE ? ";
	        } else if (search_type == 2) {
	            sql += "        AND M.MEMBER_NICKNAME LIKE ? ";
	        }
	    }

	    // 태그 리스트 필터링
	    if (tagList != null && !tagList.isEmpty()) {
	        // 태그 수에 따라 ?를 동적으로 생성
	        StringJoiner sj = new StringJoiner(", ");
	        for (int i = 0; i < tagList.size(); i++) {
	            sj.add("?");
	        }
	        sql += "        AND QT.TAG_NAME IN (" + sj.toString() + ") ";
	        sql += "        GROUP BY Q.QUIZ_NUMBER, Q.QUIZ_TITLE ";
	        sql += "        HAVING COUNT(DISTINCT QT.TAG_NAME) = ? ";
	    } else {
	        sql += "        GROUP BY Q.QUIZ_NUMBER, Q.QUIZ_TITLE ";
	    }

	    // 페이징 및 최종 WHERE 절
	    sql += "    ) A WHERE ROWNUM <= ? " +
	           ") WHERE RNUM >= ?";

	    try {
	        pstmt = conn.prepareStatement(sql);

	        int paramIndex = 1;

	        // 카테고리 바인딩
	        if (category != 0) {
	            pstmt.setInt(paramIndex++, category);
	        }

	        // 검색 텍스트 바인딩
	        if (search_text != null && !search_text.trim().isEmpty()) {
	            pstmt.setString(paramIndex++, "%" + search_text + "%");
	        }

	        // 태그 리스트 바인딩
	        if (tagList != null && !tagList.isEmpty()) {
	            for (String tag : tagList) {
	                pstmt.setString(paramIndex++, tag);
	            }
	            pstmt.setInt(paramIndex++, tagList.size());  // 태그 개수 바인딩
	        }

	        // 페이징 바인딩
	        pstmt.setInt(paramIndex++, endRow);
	        pstmt.setInt(paramIndex++, startRow);

	        // 쿼리 실행
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            Quiz q = new Quiz();
	            q.setQuiz_number(rset.getInt("QUIZ_NUMBER"));
	            q.setQuiz_title(rset.getString("QUIZ_TITLE"));
	            list.add(q);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }
	    return list;
	}

		
		
		
	public ArrayList<Tag> selectTagList(Connection conn, String searchText) {
		ArrayList<Tag> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTagList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%");
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Tag(
						rset.getString("tag_name"),
						rset.getInt("count")
						));
							
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Quiz> selectMyQuiz(Connection conn, int memberNo) {
		ArrayList<Quiz> list = new ArrayList<>();
		System.out.println(memberNo);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMyQuiz");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Quiz q = new Quiz();
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				
				list.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public Quiz detailQuiz(Connection conn, int quiz_number) {
		Quiz q = new Quiz();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("detailQuiz");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quiz_number);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				q.setQuiz_modify_date(rset.getString("quiz_modify_date"));
				q.setMember_name(rset.getString("member_name"));
				q.setCategory_name(rset.getString("category_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return q;
	}

	public int quizViewCount(Connection conn, int num) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("quizViewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Tag> TagArray(Connection conn, int num) {
		ArrayList<Tag> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("TagArray");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Tag t = new Tag();
				t.setQuizTag(rset.getString("tag_name"));
				
				
				list.add(t);
							
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}	
}
