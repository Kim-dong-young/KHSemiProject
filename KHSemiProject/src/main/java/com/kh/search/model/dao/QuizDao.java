package com.kh.search.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.PageInfo;
import com.kh.search.model.vo.Quiz;

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

	public int selectQuizCount(Connection conn) {
		
		int quizCount = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQuizCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				quizCount = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return quizCount;
	}

	public ArrayList<Quiz> selectQuiz(Connection conn, PageInfo pi) {
		ArrayList<Quiz> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectQuiz");
		
		try {
			pstmt = conn.prepareStatement(sql);
			/*
			 * currentPage : 1 -> 1~10
			 * currentPage : 2 -> 11~20
			 * currentPage : 3 -> 21~30
			 */
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Quiz q = new Quiz();
				b.setBoardNo(rset.getInt("board_no"));
				b.setCategory(rset.getString("category_name"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardWriter(rset.getString("user_id"));
				b.setCount(rset.getInt("count"));
				b.setCreateDate(rset.getString("create_date"));
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

}
