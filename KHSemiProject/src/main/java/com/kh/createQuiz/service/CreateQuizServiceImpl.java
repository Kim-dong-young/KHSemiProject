package com.kh.createQuiz.service;

import static com.kh.common.JDBCTemplate.*;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.createQuiz.model.dao.CreateQuizDAO;
import com.kh.createQuiz.model.vo.CreateQuiz;

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
}
