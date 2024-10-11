package com.kh.playQuiz.service;

import static com.kh.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.playQuiz.model.dao.PlayQuizDao;
import com.kh.playQuiz.model.vo.Problem;

public class PlayQuizService {

	public ArrayList<Problem> selectQuizProblem(int quizNumber) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Problem> pList = new PlayQuizDao().selectQuizProblem(conn, quizNumber);
		close(conn);
		// TODO Auto-generated method stub
		return pList;
	}

	public boolean AjaxPlayQuizAnswerCheck(int pNum, String answer) {
		Connection conn = JDBCTemplate.getConnection();
		
		boolean result = new PlayQuizDao().AjaxPlayQuizAnswerCheck(conn, pNum, answer); 
		close(conn);
		return result;
	}

	public String getCorrectAnswer(int pNum) {
		Connection conn = JDBCTemplate.getConnection();
		
		String result = new PlayQuizDao().getCorrectAnswer(conn, pNum);
		close(conn);
		return result;
	}

	public String AjaxPlayQuizMedia(int num, int pNum) {
		Connection conn = JDBCTemplate.getConnection();
		
		String result = new PlayQuizDao().AjaxPlayQuizMedia(conn, pNum, num);
		close(conn);
		return result;
	}

	public boolean AjaxPlayQuizViewCount(int qNum, int mNum, int cNum) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = new PlayQuizDao().AjaxPlayQuizViewCount(conn, qNum, mNum, cNum);
		
		if(result) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int AjaxPlayQuizStarsCheck(int qNum, int mNum) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PlayQuizDao().AjaxPlayQuizStarsCheck(conn, qNum, mNum);
		close(conn);
		return result;
	}

	public boolean AjaxPlayQuizStarsConfirm(int qNum, int mNum, int rating) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = new PlayQuizDao().AjaxPlayQuizStarsConfirm(conn, qNum, mNum, rating);
		
		if(result) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public void MemberAddExp(int mNum, int qNum) {
		Connection conn = JDBCTemplate.getConnection();
		boolean result = new PlayQuizDao().MemberAddExp(conn, mNum, qNum);
		
		System.out.println(result);
		if(result) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
	}

	public int PlayQuizSelectExp(int mNum) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new PlayQuizDao().PlayQuizSelectExp(conn, mNum);
		close(conn);
		return result;
	}
	
	public int successQuest(int memberNo, int questNo) {
		Connection conn = getConnection();
		int result = new PlayQuizDao().successQuest(conn, memberNo, questNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}


	public int checkDailyQuest(int memberNo, int questNo) {
		Connection conn = getConnection();
		int result = new PlayQuizDao().checkDailyQuest(conn, memberNo, questNo);
		
		close(conn);
		return result;
	}
	
}
