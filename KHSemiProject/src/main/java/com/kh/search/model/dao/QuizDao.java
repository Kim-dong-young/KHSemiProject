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

	public ArrayList<Quiz> selectQuiz(Connection conn, PageInfo pi, int category, int search_type, String search_text, int orderby) {
		ArrayList<Quiz> list = new ArrayList<>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		boolean sqlAnd = false;
		
		String sql = "SELECT QUIZ_NUMBER, QUIZ_TITLE ";
				
		if(orderby == 1) {
			sql += "FROM (SELECT QUIZ_NUMBER, COUNT(*) "
					+ "FROM QUIZ_LOG "
					+ "GROUP BY QUIZ_NUMBER "
					+ "ORDER BY COUNT(*) DESC)"
					+ "JOIN QUIZ USING (QUIZ_NUMBER) ";
		} else if(orderby == 2){
			sql += "FROM QUIZ ";
		} else if(orderby == 3) {
			sql += "FROM (SELECT QUIZ_NUMBER, AVG(QUIZ_RATE_RATING) "
					+ "FROM QUIZ_RATE "
					+ "GROUP BY QUIZ_NUMBER "
					+ "ORDER BY AVG(QUIZ_RATE_RAING) DESC) "
					+ "JOIN QUIZ USING (QUIZ_NUMBER) ";
		}
		
		
		sql += "JOIN CATEGORY USING (CATEGORY_NUMBER) "
		       + "JOIN MEMBER USING (MEMBER_NUMBER) ";
		
		boolean sqlWhere = false;
		
		if(category != 0) {
			if(!sqlWhere) {
				sql += "WHERE ";
				sqlWhere = true;
			}
			sql += "CATEGORY_NUMBER = '" + category + "'";
			sqlAnd = true;
		}
		if(search_text != null) {
			if(sqlAnd) {
				sql += " AND ";
			}
			if(!sqlWhere) {
				sql += "WHERE ";
				sqlWhere = true;
			}
			switch(search_type) {
			case 1:
				sql += "QUIZ_TITLE LIKE '%" + search_text + "%'";
				break;
			case 2:
				sql += "MEMBER_NICKNAME LIKE '%" + search_text + "%'";
				break;
			}
			sqlAnd = true;
		}
		sql += " ORDER BY ";
		switch(orderby) {
		case 1, 3:
			sql += "ROWNUM DESC";
			break;
		case 2:
			sql += "QUIZ_MODIFY_DATE DESC";
		}
		
		try {
			rset = stmt.executeQuery(sql);
			while(rset.next()) {
				Quiz q = new Quiz();
				
				q.setQuiz_number(rset.getInt("quiz_number"));
				q.setQuiz_title(rset.getString("quiz_title"));
				list.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

}
