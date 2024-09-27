package com.kh.search.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

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

	    StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT ROWNUM RNUM, A.* FROM (SELECT QUIZ_NUMBER, QUIZ_TITLE ");
	    
	    // 1. ORDER BY에 따른 쿼리 변경
	    if(orderby == 1) {
	        sql.append("FROM (SELECT QUIZ_NUMBER, COUNT(*) ")
	           .append("FROM QUIZ_LOG GROUP BY QUIZ_NUMBER ORDER BY COUNT(*) DESC) ")
	           .append("JOIN QUIZ USING (QUIZ_NUMBER) ");
	    } else if(orderby == 2){
	        sql.append("FROM QUIZ ");
	    } else if(orderby == 3) {
	        sql.append("FROM (SELECT QUIZ_NUMBER, AVG(QUIZ_RATE_RATING) ")
	           .append("FROM QUIZ_RATE GROUP BY QUIZ_NUMBER ORDER BY AVG(QUIZ_RATE_RATING) DESC) ")
	           .append("JOIN QUIZ USING (QUIZ_NUMBER) ");
	    }

	    // 2. 기본 JOIN 조건 추가
	    sql.append("JOIN CATEGORY USING (CATEGORY_NUMBER) ")
	       .append("JOIN MEMBER USING (MEMBER_NUMBER) ");

	    // 3. 태그 리스트가 있으면 QUIZ_TAG와 JOIN
	    if (tagList != null && !tagList.isEmpty()) {
	        sql.append("JOIN QUIZ_TAG USING (QUIZ_NUMBER) ");
	    }

	    // 4. WHERE 조건 추가
	    boolean sqlWhere = false;
	    
	    if (category != 0) {
	        sql.append("WHERE CATEGORY_NUMBER = ? ");
	        sqlWhere = true;
	    }

	    if (search_text != null && !search_text.trim().isEmpty()) {
	        if (sqlWhere) {
	            sql.append("AND ");
	        } else {
	            sql.append("WHERE ");
	            sqlWhere = true;
	        }
	        if (search_type == 1) {
	            sql.append("QUIZ_TITLE LIKE ? ");
	        } else if (search_type == 2) {
	            sql.append("MEMBER_NICKNAME LIKE ? ");
	        }
	    }

	    if (tagList != null && !tagList.isEmpty()) {
	        if (sqlWhere) {
	            sql.append("AND ");
	        } else {
	            sql.append("WHERE ");
	            sqlWhere = true;
	        }
	        sql.append("TAG_NAME IN (");
	        for (int i = 0; i < tagList.size(); i++) {
	            sql.append("?");
	            if (i < tagList.size() - 1) {
	                sql.append(", ");
	            }
	        }
	        sql.append(") ");
	    }

	    // 5. ORDER BY 구문 추가
	    sql.append("ORDER BY ");
	    if (orderby == 1 || orderby == 3) {
	        sql.append("ROWNUM DESC ");
	    } else if (orderby == 2) {
	        sql.append("QUIZ_NUMBER DESC ");
	    }

	    // 6. 페이징 처리
	    sql.append(") A) WHERE RNUM BETWEEN ? AND ?");

	    try {
	        pstmt = conn.prepareStatement(sql.toString());

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
	        }

	        // 페이징 바인딩
	        pstmt.setInt(paramIndex++, startRow);
	        pstmt.setInt(paramIndex++, endRow);

	        // 쿼리 실행
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            Quiz q = new Quiz();
	            q.setQuiz_number(rset.getInt("quiz_number"));
	            q.setQuiz_title(rset.getString("quiz_title"));
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

}
