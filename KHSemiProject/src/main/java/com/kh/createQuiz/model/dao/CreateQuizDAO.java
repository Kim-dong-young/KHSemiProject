package com.kh.createQuiz.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.createQuiz.model.vo.CreateQuiz;

public class CreateQuizDAO {

	public void insertQuiz(Connection conn, CreateQuiz quiz) {
		String query = "INSERT INTO QUIZ (QUIZ_number, QUIZ_title, QUIZ_explanation, MEMBER_number, CATEGORY_number, THUMBNAIL) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, quiz.getQUIZ_NUMBER()); // 이 부분의 값이 잘 설정되어 있는지 확인
			pstmt.setString(2, quiz.getQUIZ_TITLE());
			pstmt.setString(3, quiz.getQUIZ_EXPLANATION());
			pstmt.setInt(4, quiz.getMEMBER_NUMBER());
			pstmt.setInt(5, quiz.getCATEGORY_NUMBER());
			pstmt.setString(6, quiz.getTHUMBNAIL());
			pstmt.executeUpdate(); // 이 라인이 없으면 데이터베이스에 값이 저장되지 않음
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
