package com.kh.createQuiz.service;


import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.createQuiz.model.dao.CreateQuizDao;
import com.kh.createQuiz.model.vo.CreateQuiz;

public class CreateQuizService {
    private CreateQuizDao quizDAO = new CreateQuizDao();

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
