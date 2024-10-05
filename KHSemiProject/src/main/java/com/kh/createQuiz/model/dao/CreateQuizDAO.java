package com.kh.createQuiz.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.createQuiz.model.vo.CreateQuiz;

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
		
		String sql = prop.getProperty("insertQuiz");
		System.out.println(sql);
		try {
			
			System.out.println(quiz.getQUIZ_TITLE());
			System.out.println(quiz.getQUIZ_EXPLANATION());
			System.out.println(quiz.getMEMBER_NUMBER());
			System.out.println(quiz.getCATEGORY_NUMBER());
			System.out.println(quiz.getTHUMBNAIL());
			
			pstmt = conn.prepareStatement(sql);
			
            pstmt.setString(1, quiz.getQUIZ_TITLE());
            pstmt.setString(2, quiz.getQUIZ_EXPLANATION());
            pstmt.setInt(3, quiz.getMEMBER_NUMBER());
            pstmt.setInt(4, quiz.getCATEGORY_NUMBER());
            pstmt.setString(5, quiz.getTHUMBNAIL());
            
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
			return pstmt.executeUpdate(); // 영향을 받은 행 수 반환
		}
	}

	// 퀴즈와 태그 정보를 함께 삽입
	public int insertQuizWithTag(Connection conn, CreateQuiz quiz) throws SQLException {
		try {
			conn.setAutoCommit(false); // 자동 커밋 해제
			insertQuiz(conn, quiz); // 퀴즈 삽입
			insertQuizTag(conn, quiz); // 태그 삽입
			conn.commit(); // 커밋
		} catch (SQLException e) {
			conn.rollback(); // 롤백
			throw e; // 예외 재던짐
		} finally {
			conn.setAutoCommit(true); // 자동 커밋 복구
		}
		return 0;
	}
}
