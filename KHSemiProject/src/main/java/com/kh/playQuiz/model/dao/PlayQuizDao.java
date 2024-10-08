package com.kh.playQuiz.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.playQuiz.model.vo.Problem;
import com.kh.search.model.dao.QuizDao;

public class PlayQuizDao {
	private Properties prop = new Properties();

	public PlayQuizDao() {
		String filePath = QuizDao.class.getResource("/db/sql/PlayQuiz-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Problem> selectQuizProblem(Connection conn, int quizNumber) {
		
		ArrayList<Problem> pList = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectQuizProblem");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quizNumber);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Problem p = new Problem();
				p.setProblem_number(rset.getInt("problem_number"));
				p.setProblem_content(rset.getString("problem_content"));
				p.setProblem_hint(rset.getString("problem_hint"));
				p.setProblem_media_kind(rset.getInt("problem_media_kind"));
				p.setPtime(rset.getInt("ptime"));
				pList.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pList;
	}

	public boolean AjaxPlayQuizAnswerCheck(Connection conn, int pNum, String answer) {
		boolean result = false;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("AjaxPlayQuizAnswerCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			pstmt.setString(2, answer);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public String getCorrectAnswer(Connection conn, int pNum) {
		String result = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("getCorrectAnswer");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getString("answer_content");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public String AjaxPlayQuizMedia(Connection conn, int pNum, int num) {
		String result = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("AjaxPlayQuizMedia");
		
		
		if(num == 2) {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pNum);
				
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getString("IMGS_FILE_NAME");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		} else {
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pNum);
				
				rset = pstmt.executeQuery();
				if(rset.next()) {
					result = rset.getString("imgs_link_path");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		}
		
		return result;
	}

	public boolean AjaxPlayQuizViewCount(Connection conn, int qNum, int mNum, int cNum) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("AjaxPlayQuizViewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNum);
			pstmt.setInt(2, qNum);
			pstmt.setInt(3, cNum);
			
			
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
