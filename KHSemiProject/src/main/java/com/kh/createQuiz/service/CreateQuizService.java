package com.kh.createQuiz.service;


import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.createQuiz.model.dao.CreateQuizDAO;
import com.kh.createQuiz.model.vo.CreateQuiz;

public class CreateQuizService {
    private CreateQuizDAO quizDAO = new CreateQuizDAO();

    public int createQuiz(CreateQuiz quiz) throws SQLException {
        Connection conn = getConnection();
        try {
            quizDAO.insertQuizWithTag(conn, quiz);
        } finally {
            close(conn);
        }
		return 0;
    }
}
