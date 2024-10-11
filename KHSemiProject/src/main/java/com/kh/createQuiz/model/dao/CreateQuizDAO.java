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

		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(quiz.getQUIZ_TITLE());
			System.out.println(quiz.getQUIZ_EXPLANATION());
			System.out.println(quiz.getMEMBER_NUMBER());
			System.out.println(quiz.getCATEGORY_NUMBER());
			System.out.println(quiz.getTHUMBNAIL());

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
	public int insertQuizTag(Connection conn, CreateQuiz quiz) throws SQLException {
		String query = "INSERT INTO QUIZ_TAG (TAG_name, QUIZ_number) VALUES (?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, quiz.getTAG_NAME());
			pstmt.setInt(2, quiz.getQUIZ_NUMBER());
			return pstmt.executeUpdate();
		}
	}

	// 퀴즈와 태그 정보를 함께 삽입
	public int insertQuizWithTag(Connection conn, CreateQuiz quiz) throws SQLException {
		try {
			conn.setAutoCommit(false);
			insertQuiz(conn, quiz);
			insertQuizTag(conn, quiz);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
		return 0;
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
				System.out.println("dao : " + result);
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
		System.out.println("updateMemberExp DAO 실행");
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMemberExp");
		System.out.println(sql);
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
		System.out.println("doneDailyQuest DAO 실행");
		int result = 0;

		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("doneDailyQuest");
		System.out.println(sql);
		
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
