package com.kh.createQuiz.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.createQuiz.model.dao.CreateQuizDAO;
import com.kh.createQuiz.model.vo.Answer;
import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.model.vo.Problem;
import com.kh.createQuiz.model.vo.QuizTag;
import com.kh.member.model.dao.MemberDao;


public class CreateQuizServiceImpl implements CreateQuizService {
	private CreateQuizDAO quizDAO = new CreateQuizDAO();
	@Override
	public int createQuiz(CreateQuiz quiz , QuizTag tag) {
		Connection conn = getConnection();

		int result = quizDAO.insertQuiz(conn, quiz);
		int result2 = quizDAO.insertQuizTag(conn, tag);
		
		if (result > 0 && result2 > 0) {
			commit(conn);
			result = quizDAO.selectQuizNo(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}
	

	public int insertProblems(Problem pr, Problem pr2, Problem pr3, Problem pr4, Problem pr5, Answer a, Answer a2,
			Answer a3, Answer a4, Answer a5) {
		Connection conn = getConnection();
		Boolean isSuccess = false;
		int result1 = 0;
		int result2 = 0;
		if (pr.getPROBLEM_content() != null && a.getANSWER_content() != null) {
			System.out.println("들어왔다잇" + pr + "," + a);
			result1 = new CreateQuizDAO().insertProblems(conn, pr);
			result2 = new CreateQuizDAO().insertAnswer(conn, a);

			if (result1 * result2 > 0)
				isSuccess = true;
			else
				isSuccess = false;

		}
		if (pr2.getPROBLEM_content() != null && a2.getANSWER_content() != null) {
			//System.out.println("들어왔다잇" + pr2 + "," + a2);
			result1 = new CreateQuizDAO().insertProblems(conn, pr2);
			result2 = new CreateQuizDAO().insertAnswer(conn, a2);
			
			if (result1 * result2 > 0)
				isSuccess = true;
			else
				isSuccess = false;
		}
		if (pr3.getPROBLEM_content() != null && a3.getANSWER_content() != null) {
			//System.out.println("들어왔다잇" + pr3 + "," + a3);
			result1 = new CreateQuizDAO().insertProblems(conn, pr3);
			result2 = new CreateQuizDAO().insertAnswer(conn, a3);
			
			if (result1 * result2 > 0)
				isSuccess = true;
			else
				isSuccess = false;
		}
		if (pr4.getPROBLEM_content() != null && a4.getANSWER_content() != null) {
			//System.out.println("들어왔다잇" + pr4 + "," + a4);
			result1 = new CreateQuizDAO().insertProblems(conn, pr4);
			result2 = new CreateQuizDAO().insertAnswer(conn, a4);
			
			if (result1 * result2 > 0)
				isSuccess = true;
			else
				isSuccess = false;
		}
		if (pr5.getPROBLEM_content() != null && a5.getANSWER_content() != null) {
			//System.out.println("들어왔다잇" + pr5 + "," + a5);
			result1 = new CreateQuizDAO().insertProblems(conn, pr5);
			result2 = new CreateQuizDAO().insertAnswer(conn, a5);
			
			if (result1 * result2 > 0)
				isSuccess = true;
			else
				isSuccess = false;
		}

		if (isSuccess) {
			commit(conn);
			System.out.println("완료");
		} else {
			System.out.println("실패");
			rollback(conn);
		}
		close(conn);
		return result1 * result2;
	}
	

	public int successQuest(int memberNo, int questNo) {
		Connection conn = getConnection();
		int result = new MemberDao().successQuest(conn, memberNo, questNo);
		
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
		int result = new CreateQuizDAO().checkDailyQuest(conn, memberNo, questNo);
		
		close(conn);
		return result;
	}


	public int updateMemberExp(int memberNo, int questNo, int exp) {
		CreateQuizDAO cQuizDao = new CreateQuizDAO();
		Connection conn = getConnection();
		int result1 = cQuizDao.updateMemberExp(conn, memberNo, exp);
		int result2 = cQuizDao.doneDailyQuest(conn, memberNo, questNo);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}

	public int updateMemberExp(int memberNo, int exp) {
		System.out.println("updateMemberExp 실행됨");
		CreateQuizDAO cQuizDao = new CreateQuizDAO();
		Connection conn = getConnection();
		int result1 = cQuizDao.updateMemberExp(conn, memberNo, exp);
		
		if(result1 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result1;
	}


}