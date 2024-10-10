package com.kh.playQuiz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;
import com.kh.playQuiz.service.PlayQuizService;

/**
 * Servlet implementation class AjaxPlayQuizStarsCheck
 */
public class AjaxPlayQuizStarsCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPlayQuizStarsCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qNum = Integer.parseInt(request.getParameter("qNum"));
		int mNum = Integer.parseInt(request.getParameter("mNum"));
		int result = new PlayQuizService().AjaxPlayQuizStarsCheck(qNum, mNum);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    new Gson().toJson(result, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
