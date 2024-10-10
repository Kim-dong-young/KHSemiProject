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

public class CreateQuizServiceImpl implements CreateQuizService {
	private CreateQuizDAO quizDAO = new CreateQuizDAO();

	@Override
	public int createQuiz(CreateQuiz quiz) {
		Connection conn = getConnection();

		int result = quizDAO.insertQuiz(conn, quiz);

		if (result > 0) {
			commit(conn);
			
			result = quizDAO.selectQuizNo(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	public int insertProblems(Problem pr, Answer a) {
		Connection conn = getConnection();
		
		System.out.println("들어왔다잇" + pr + "," + a);
		int result1 = new CreateQuizDAO().insertProblems(conn, pr);
		int result2 = new CreateQuizDAO().insertAnswer(conn, a);
		
		if (result1 > 0 && result2 >0) {
			commit(conn);
			System.out.println("완료");
		} else {
			System.out.println("실패");
			rollback(conn);		
		}
		close(conn);
		return result1 * result2;
	}

}