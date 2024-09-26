package com.kh.community.controller;

import static com.kh.common.JDBCTemplate.close;

import java.sql.SQLException;

public class asdwee {
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, boardNo);
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
}
