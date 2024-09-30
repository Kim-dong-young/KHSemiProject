package com.kh.createQuiz.model.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CreateQuizDao {

	private DataSource dataSource;

	// Quiz 삽입 메서드
	public void insertQuiz(String title, String explanation, String category, String tag,
			InputStream thumbnailInputStream) throws SQLException {
		String sql = "INSERT INTO quiz (title, explanation, category, tag, thumbnail) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, title);
			ps.setString(2, explanation);
			ps.setString(3, category);
			ps.setString(4, tag);

			// BLOB 데이터 처리 (이미지 파일)
			if (thumbnailInputStream != null) {
				ps.setBlob(5, thumbnailInputStream);
			} else {
				ps.setNull(5, java.sql.Types.BLOB); // 이미지가 없을 때 처리
			}

			ps.executeUpdate();
		}
	}
}
