package com.kh.search.model.dao;

import static com.kh.common.JDBCTemplate.close;

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

	    // 기본 SELECT 쿼리 시작
	    String sql = "SELECT * FROM ( " +
	                 "    SELECT A.*, ROWNUM AS RNUM FROM ( ";

	    // ORDER BY에 따른 SELECT 절 및 JOIN 절 변경
	    if (orderby == 1) {
	        // 조회수 순 정렬
	        sql += "        SELECT Q.QUIZ_NUMBER, Q.QUIZ_TITLE, Q.THUMBNAIL, NVL(COUNT(QL.QUIZ_LOG_NUMBER), 0) AS VIEW_COUNT ";
	        sql += "        FROM QUIZ Q ";
	        sql += "        LEFT JOIN QUIZ_LOG QL ON Q.QUIZ_NUMBER = QL.QUIZ_NUMBER ";
	    } else if (orderby == 2) {
	        // 최신순 정렬
	        sql += "        SELECT Q.QUIZ_NUMBER, Q.QUIZ_TITLE, Q.THUMBNAIL, Q.QUIZ_DATE ";
	        sql += "        FROM QUIZ Q ";
	    } else if (orderby == 3) {
	        // 평점 순 정렬
	        sql += "        SELECT Q.QUIZ_NUMBER, Q.QUIZ_TITLE, Q.THUMBNAIL, NVL(AVG(QR.QUIZ_RATE_RATING), 0) AS AVG_RATING ";
	        sql += "        FROM QUIZ Q ";
	        sql += "        LEFT JOIN QUIZ_RATE QR ON Q.QUIZ_NUMBER = QR.QUIZ_NUMBER ";
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

	    // GROUP BY 절 구성
	    String groupByClause = "        GROUP BY Q.QUIZ_NUMBER, Q.QUIZ_TITLE, Q.THUMBNAIL";

	    if (orderby == 2) {
	        // 최신순 정렬 시 날짜 추가
	        groupByClause += ", Q.QUIZ_DATE";
	    }

	    // 태그 리스트 필터링
	    if (tagList != null && !tagList.isEmpty()) {
	        // 태그 수에 따라 ?를 동적으로 생성
	        StringJoiner sj = new StringJoiner(", ");
	        for (int i = 0; i < tagList.size(); i++) {
	            sj.add("?");
	        }
	        sql += "        AND QT.TAG_NAME IN (" + sj.toString() + ") ";
	        sql += groupByClause;
	        sql += "        HAVING COUNT(DISTINCT QT.TAG_NAME) = ? ";
	    } else {
	        sql += groupByClause;
	    }

	    // ORDER BY 절 추가
	    if (orderby == 1) {
	        sql += "        ORDER BY VIEW_COUNT DESC ";
	    } else if (orderby == 2) {
	        sql += "        ORDER BY Q.QUIZ_DATE DESC ";
	    } else if (orderby == 3) {
	        sql += "        ORDER BY AVG(NVL(QR.QUIZ_RATE_RATING, 0)) DESC ";
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
	            q.setThumbnail(rset.getString("THUMBNAIL"));
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
				q.setMember_name(rset.getString("member_nickname"));
				q.setCategory_name(rset.getString("category_name"));
				q.setQuiz_explanation(rset.getString("quiz_explanation"));
				q.setThumbnail(rset.getString("THUMBNAIL"));
				
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

	public String quizRateAvg(Connection conn, int num) {
		String str = "";
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("quizRateAvg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				str += rset.getDouble("AVG");		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return str;
	}

	public ArrayList<Quiz> simularQuizList(Connection conn, ArrayList<Tag> tagArr) {
		ArrayList<Quiz> list = new ArrayList<>();

	    if (tagArr == null || tagArr.isEmpty()) {
	        return list; // 태그 리스트가 없으면 빈 리스트를 반환
	    }

	    PreparedStatement pstmt = null;
	    ResultSet rset = null;

	    String sql = "SELECT Q.QUIZ_NUMBER, Q.QUIZ_TITLE, Q.THUMBNAIL, NVL(SUM(QL.QUIZ_LOG_COUNT), 0) AS VIEWS " +
	                 "FROM QUIZ Q " +
	                 "JOIN QUIZ_TAG QT ON Q.QUIZ_NUMBER = QT.QUIZ_NUMBER " +
	                 "LEFT JOIN QUIZ_LOG QL ON Q.QUIZ_NUMBER = QL.QUIZ_NUMBER " +
	                 "WHERE QT.TAG_NAME IN (";

	    // IN 절에 들어갈 '?' 플래이스홀더를 동적으로 생성
	    StringJoiner placeholders = new StringJoiner(", ");
	    for (int i = 0; i < tagArr.size(); i++) {
	        placeholders.add("?");
	    }
	    sql += placeholders.toString() + ") " +
	           "GROUP BY Q.QUIZ_NUMBER, Q.QUIZ_TITLE, Q.THUMBNAIL " +
//	           "HAVING COUNT(DISTINCT QT.TAG_NAME) = ? " +
	           "ORDER BY VIEWS DESC";

	    try {
	        pstmt = conn.prepareStatement(sql);

	        int paramIndex = 1;

	        // 태그 이름들을 IN 절의 '?'에 바인딩
	        for (Tag tag : tagArr) {
	            pstmt.setString(paramIndex++, tag.getQuizTag());
	        }
			/*
			 * pstmt.setInt(paramIndex++, tagArr.size()); // HAVING 절에 태그 개수 바인딩
			 */
	        // 쿼리 실행
	        rset = pstmt.executeQuery();

	        while (rset.next()) {
	            Quiz q = new Quiz();
	            q.setQuiz_number(rset.getInt("QUIZ_NUMBER"));
	            q.setQuiz_title(rset.getString("QUIZ_TITLE"));
	            q.setThumbnail(rset.getString("THUMBNAIL"));
	            list.add(q);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 해제
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }

	    return list;
	}
	
	public ArrayList<Quiz> selectLatestQuiz(Connection conn) {
		ArrayList<Quiz> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLatestQuiz");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Quiz q = new Quiz();
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				q.setThumbnail(rset.getString("thumbnail"));
				
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

    public int markInsert(Connection conn, int quiznum, int memberNum) {
        int b = 0;
        
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("markInsert");
        

        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, memberNum);
            pstmt.setInt(2, quiznum);
            
            b = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        
        
        
        
        return b;
    }

    public boolean markSelect(Connection conn, int quizNum, int memberNum) {
        
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("markSelect");
        boolean init = false;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberNum);
            pstmt.setInt(2, quizNum);
            rset = pstmt.executeQuery();
            init = rset.next();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rset);
            JDBCTemplate.close(pstmt);
        }
        
        return init;
    }

    public int markDelete(Connection conn, int quiznum, int memberNum) {
        int b = 0;
        
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("markDelete");
        

        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, memberNum);
            pstmt.setInt(2, quiznum);
            
            b = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        
        
        
        
        return b;
    }

	public ArrayList<Quiz> selectInquiryQuiz(Connection conn) {
		ArrayList<Quiz> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectinquiryQuiz");
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Quiz q = new Quiz();
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				q.setThumbnail(rset.getString("thumbnail"));
				
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
	
	public ArrayList<Quiz> selectGradeQuiz(Connection conn) {
		ArrayList<Quiz> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectGradeQuiz");
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Quiz q = new Quiz();
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				q.setThumbnail(rset.getString("thumbnail"));
				q.setQuiz_rate(rset.getDouble("quiz_rate_rating"));
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
	
	public ArrayList<Quiz> noSearchMyQuiz(Connection conn, int memberNo, String selectValue, String btnValue) {
		ArrayList<Quiz> list = new ArrayList<>();
		System.out.println(memberNo);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("noSearch" + btnValue + selectValue);
		System.out.println("noSearch" + btnValue + selectValue);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Quiz q = new Quiz();
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				q.setThumbnail(rset.getString("thumbnail"));
				
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
	
	public ArrayList<Quiz> searchMyQuiz(Connection conn,  int memberNo, String selectValue, String btnValue, String searchValue, String serSelValue) {
		ArrayList<Quiz> list = new ArrayList<>();
		System.out.println(memberNo);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("search" + btnValue + selectValue + serSelValue);
		System.out.println("search" + btnValue + selectValue + serSelValue);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, "%"+searchValue+"%");
			
			if(serSelValue.equals("TitleNContent")) {
				pstmt.setString(3, "%"+searchValue+"%");
			}
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Quiz q = new Quiz();
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				q.setThumbnail(rset.getString("thumbnail"));
				
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
}
