package com.kh.createQuiz.model.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CreateQuizDao {

	private DataSource QUIZ;

	// Quiz 삽입 메서드
	public void insertquiz(String title, String explanation, String category, String tag, InputStream thumbnailInputStream) 
			throws SQLException {
		String sql = "INSERT INTO quiz (title, explanation, category, tag, thumbnail) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = QUIZ.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, title);
			pstmt.setString(2, explanation);
			pstmt.setString(3, category);
			pstmt.setString(4, tag);

			// BLOB 데이터 처리 (이미지 파일)
			if (thumbnailInputStream != null) {
				pstmt.setBlob(5, thumbnailInputStream);
			} else {
				pstmt.setNull(5, java.sql.Types.BLOB); // 이미지가 없을 때 처리
			}

			pstmt.executeUpdate();
			conn.commit(); // 필요 시 커밋

		}
	}
}
