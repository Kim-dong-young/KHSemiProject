package com.kh.search.service;

import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

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

	public Quiz detailQuiz(int quiz_number) {
		Connection conn = getConnection();
		Quiz q = new QuizDao().detailQuiz(conn, quiz_number);
		return q;
	}

	public int quizViewCount(int num) {
		Connection conn = getConnection();
		int result = new QuizDao().quizViewCount(conn, num);
		return result;
	}

	public ArrayList<Tag> TagArray(int num) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Tag> list = new QuizDao().TagArray(conn, num);
		return list;
	}

	public String quizRateAvg(int num) {
		Connection conn = JDBCTemplate.getConnection();
		String str = new QuizDao().quizRateAvg(conn, num);
		return str;
	}

	public ArrayList<Quiz> simularQuizList(ArrayList<Tag> tagArr) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Quiz> qArr = new QuizDao().simularQuizList(conn, tagArr);
		return qArr;
	}
	
	public ArrayList<Quiz> selectLatestQuiz() {
		Connection conn = getConnection();
		ArrayList<Quiz> list = new QuizDao().selectLatestQuiz(conn);
		
		JDBCTemplate.close(conn);
		return list;
	}
	
	
	public int markInsert(int quiznum, int memberNum) {
		Connection conn = getConnection();
		int list = 0;
		if(!new QuizDao().markSelect(conn, quiznum, memberNum)) {
			list = new QuizDao().markInsert(conn, quiznum, memberNum);
			if(list > 0) {
				commit(conn);
				list = 1;
			} else {
				rollback(conn);
			}
		} else {
			list = new QuizDao().markDelete(conn, quiznum, memberNum);
			if(list > 0) {
				commit(conn);
				list = 2;
			} else {
				rollback(conn);
			}
		}
		
		
		
		
		JDBCTemplate.close(conn);
		return list;
	}

	public int markSelect(int quizNum, int memberNum) {
		Connection conn = getConnection();
		if(new QuizDao().markSelect(conn, quizNum, memberNum)) {
			return 1;
		} else {
			return 0;
		}
	}
}
