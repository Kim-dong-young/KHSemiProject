package com.kh.createQuiz.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateQuizMainController
 */

@WebServlet(name = "create.quiz", urlPatterns= {"/create.quiz"})
public class CreateQuizMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizMainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String quizTitle = request.getParameter("title");
		String quizExplanation = request.getParameter("explanation");
		String quizCategory = request.getParameter("category");
		String quizTag = request.getParameter("tag");
		String[] createQuizArr = request.getParameterValues("createQuiz");
		
		String createQuiz = "";
		if(createQuizArr != null) {
			createQuiz = String.join(",", createQuizArr);
		}
		
		
		
		request.getRequestDispatcher("static/templates/CreateQuizMain.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
