package com.kh.search.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.search.model.dao.QuizDao;

public class SearchService {

	public int selectQuizCount() {
		Connection conn = JDBCTemplate.getConnection();
		
		int quizCount = new QuizDao().selectQuizCount(conn);
		JDBCTemplate.close(conn);
		return 0;
	}

}
