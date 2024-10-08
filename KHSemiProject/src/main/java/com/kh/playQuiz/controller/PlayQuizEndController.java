package com.kh.playQuiz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.kh.playQuiz.service.PlayQuizService;

/**
 * Servlet implementation class PlayQuizEndController
 */
public class PlayQuizEndController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayQuizEndController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int cNum = Integer.parseInt(request.getParameter("correctNum"));
		int qNum = Integer.parseInt(request.getParameter("quizNumber"));
		int mNum = 0;
		System.out.println(request.getParameter("memberNo"));
		if(request.getParameter("memberNo") == null || request.getParameter("memberNo").equals("")) {
			mNum = -1;
		} else {
			mNum = Integer.parseInt(request.getParameter("memberNo"));
		}
		System.out.println(cNum);
		System.out.println(qNum);
		System.out.println(mNum);
		if(mNum != -1) {
			new PlayQuizService().MemberAddExp(mNum, qNum);
			int exp = new PlayQuizService().PlayQuizSelectExp(mNum);
			request.setAttribute("exp", exp);
		}
		boolean link = new PlayQuizService().AjaxPlayQuizViewCount(qNum, mNum, cNum);
		System.out.println(link);
		
		
		Math.floor(0.4);
		request.getRequestDispatcher("templates/QuizEnd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
