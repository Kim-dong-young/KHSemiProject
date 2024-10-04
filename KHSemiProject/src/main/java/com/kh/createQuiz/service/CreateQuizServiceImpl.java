package com.kh.createQuiz.service;

import static com.kh.common.JDBCTemplate.close;
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
        try {
            return quizDAO.insertQuizWithTag(conn, quiz);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // 예외 발생 시 0 반환
        } finally {
            close(conn);
        }
    }
}
