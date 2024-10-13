package com.kh.createQuiz.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.createQuiz.model.vo.Answer;
import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.model.vo.Problem;
import com.kh.createQuiz.model.vo.QuizTag;

public class CreateQuizDAO {
	private Properties prop = new Properties();

	public CreateQuizDAO() {
		String filePath = CreateQuizDAO.class.getResource("/db/sql/Quiz-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertQuiz(Connection conn, CreateQuiz quiz) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql;

		if (quiz.getTHUMBNAIL() == null) {
			sql = prop.getProperty("insertQuizNotThumb");
		} else {
			sql = prop.getProperty("insertQuiz");
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, quiz.getQUIZ_TITLE());
			pstmt.setString(2, quiz.getQUIZ_EXPLANATION());
			pstmt.setInt(3, quiz.getMEMBER_NUMBER());
			pstmt.setInt(4, quiz.getCATEGORY_NUMBER());

			if (quiz.getTHUMBNAIL() != null) {
				pstmt.setString(5, quiz.getTHUMBNAIL());
			}

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 태그 정보를 데이터베이스에 삽입
	public int insertQuizTag(Connection conn, QuizTag tag) {
		int result = 1; // 태그는 Null일 수도 있다. 따라서 기본값 항상 성공
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQuizTag");
		
		try {
			for(String tagName : tag.getTagList()) {
				if(tagName != null && !tagName.equals("")) {
					pstmt = conn.prepareStatement(sql);
				
					pstmt.setString(1, tagName);
					
					result *= pstmt.executeUpdate();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int insertProblems(Connection conn, Problem p) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProblems");

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, p.getPROBLEM_content());
			pstmt.setString(2, p.getPROBLEM_media());
			pstmt.setString(3, p.getPROBLEM_hint());
			pstmt.setInt(4, p.getQUIZ_number());
			pstmt.setInt(5, p.getPtime());
			
			result = pstmt.executeUpdate();

			// 자동 생성된 problem_number 값 가져오기
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int generatedProblemNumber = rs.getInt(1); // 첫 번째 컬럼에서 가져옴
				p.setPROBLEM_number(generatedProblemNumber); // Problem 객체에 설정
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertAnswer(Connection conn, Answer a) {
		int result = 0;

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAnswer");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getANSWER_content());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;

	}

	public int selectQuizNo(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectQuizNo");

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt("CURRVAL");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public int successQuest(Connection conn, int memberNo, int questNo) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("successQuest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, questNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int checkDailyQuest(Connection conn, int memberNo, int questNo) {
		int result = 0;

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("checkDailyQuest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, questNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("MEMBER_QUEST_SUCCESS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberExp(Connection conn, int memberNo, int exp) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMemberExp");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, exp);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int doneDailyQuest(Connection conn, int memberNo, int questNo) {
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("doneDailyQuest");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, questNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
