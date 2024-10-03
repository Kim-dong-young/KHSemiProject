package com.kh.createQuiz.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.createQuiz.model.vo.CreateQuiz;

public class CreateQuizDao {


 public void insertQuiz(Connection conn, CreateQuiz quiz)  {
     String query = "INSERT INTO QUIZ (QUIZ_number, QUIZ_title, QUIZ_explanation, MEMBER_number, CATEGORY_number, THUMBNAIL) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
         pstmt.setInt(1, quiz.getQUIZ_NUMBER());
         pstmt.setString(2, quiz.getQUIZ_TITLE());
         pstmt.setString(3, quiz.getQUIZ_EXPLANATION());
         pstmt.setInt(4, quiz.getMEMBER_NUMBER());
         pstmt.setInt(5, quiz.getCATEGORY_NUMBER());
         pstmt.setString(6, quiz.getTHUMBNAIL());
         return;
     } catch (SQLException e) {
		e.printStackTrace();
	}
 }


 public int insertQuizTag(Connection conn, CreateQuiz quiz) throws SQLException {
     String query = "INSERT INTO QUIZ_TAG (TAG_name, QUIZ_number) VALUES (?, ?)";
     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
         pstmt.setString(1, quiz.getTAG_NAME());
         pstmt.setInt(2, quiz.getQUIZ_NUMBER());
         return pstmt.executeUpdate();
     }
 }


 public void insertQuizWithTag(Connection conn, CreateQuiz quiz) throws SQLException {
     try {
         conn.setAutoCommit(false);
         insertQuiz(conn, quiz);
         insertQuizTag(conn, quiz);
         conn.commit();
     } catch (SQLException e) {
         conn.rollback();
         throw e;
     } finally {
         conn.setAutoCommit(true);
     }
 }
}
