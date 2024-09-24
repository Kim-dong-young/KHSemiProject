package com.kh.search.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.PageInfo;
import com.kh.search.model.dao.QuizDao;
import com.kh.search.model.vo.Quiz;

public class SearchService {

	public int selectQuizCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int quizCount = new QuizDao().selectQuizCount(conn);
		JDBCTemplate.close(conn);
		return quizCount;
	}

	public ArrayList<Quiz> selectQuiz(PageInfo pi, int category, int search_type, String search_text, int orderby) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Quiz> quiz = new QuizDao().selectQuiz(conn, pi, category, search_type, search_text, orderby);
		JDBCTemplate.close(conn);
		
		return quiz;
	}

}
