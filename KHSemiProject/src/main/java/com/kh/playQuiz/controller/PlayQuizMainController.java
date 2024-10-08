package com.kh.playQuiz.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.kh.playQuiz.model.vo.Problem;
import com.kh.playQuiz.service.PlayQuizService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlayQuizMainController
 */
public class PlayQuizMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayQuizMainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizNumber = Integer.parseInt(request.getParameter("quizNumber"));
		ArrayList<Problem> pList = new PlayQuizService().selectQuizProblem(quizNumber);
		
		request.setAttribute("pList", pList);
		request.getRequestDispatcher("templates/QuizScreen2.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
