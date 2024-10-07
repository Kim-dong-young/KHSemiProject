package com.kh.createQuiz.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.createQuiz.model.dao.CreateQuizDAO;
import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.model.vo.Problem;

public class CreateQuizServiceImpl implements CreateQuizService {
    private CreateQuizDAO quizDAO = new CreateQuizDAO();

    @Override
    public int createQuiz(CreateQuiz quiz) {
        Connection conn = getConnection();
        
        int result = quizDAO.insertQuiz(conn, quiz);
        
        if(result > 0) {
        	commit(conn);
        } else {
        	rollback(conn);
        }
        
        close(conn);
        
        return result;
    }


	public int insertProblems(Problem p) {
		Connection conn = getConnection();
		
		int result = new CreateQuizDAO().insertProblems(conn, p);
		
		if(result >0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
