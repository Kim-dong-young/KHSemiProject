package com.kh.playQuiz.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.playQuiz.model.dao.PlayQuizDao;
import com.kh.playQuiz.model.vo.Problem;

public class PlayQuizService {

	public ArrayList<Problem> selectQuizProblem(int quizNumber) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Problem> pList = new PlayQuizDao().selectQuizProblem(conn, quizNumber);
		// TODO Auto-generated method stub
		return pList;
	}

	public boolean AjaxPlayQuizAnswerCheck(int pNum, String answer) {
		Connection conn = JDBCTemplate.getConnection();
		
		
		return new PlayQuizDao().AjaxPlayQuizAnswerCheck(conn, pNum, answer);
	}

	public String getCorrectAnswer(int pNum) {
		Connection conn = JDBCTemplate.getConnection();
		
		
		return new PlayQuizDao().getCorrectAnswer(conn, pNum);
	}

	public String AjaxPlayQuizMedia(int num, int pNum) {
		Connection conn = JDBCTemplate.getConnection();
		
		
		return new PlayQuizDao().AjaxPlayQuizMedia(conn, pNum, num);
	}
	
}
