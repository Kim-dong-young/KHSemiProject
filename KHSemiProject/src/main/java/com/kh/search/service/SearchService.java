package com.kh.search.service;

import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.PageInfo;
import com.kh.search.model.dao.QuizDao;
import com.kh.search.model.vo.Quiz;
import com.kh.search.model.vo.Tag;

public class SearchService {

	public int selectQuizCount(int category, int search_type, String search_text, int orderby, ArrayList<String> tagList) {
		Connection conn = JDBCTemplate.getConnection();
		
		int quizCount = new QuizDao().selectQuizCount(conn, category, search_type, search_text, orderby, tagList);
		JDBCTemplate.close(conn);
		return quizCount;
	}

	public ArrayList<Quiz> selectQuiz(PageInfo pi, int category, int search_type, String search_text, int orderby, ArrayList<String> tagList) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Quiz> quiz = new QuizDao().selectQuiz(conn, pi, category, search_type, search_text, orderby, tagList);
		JDBCTemplate.close(conn);
		
		return quiz;
	}

	public ArrayList<Tag> selectTagList(String searchText) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Tag> list = new QuizDao().selectTagList(conn, searchText);
		return list;
	}
	
	public ArrayList<Quiz> selectMyQuiz(int memberNo) {
		Connection conn = getConnection();
		ArrayList<Quiz> list = new QuizDao().selectMyQuiz(conn, memberNo);
		
		JDBCTemplate.close(conn);
		return list;
	}
}
