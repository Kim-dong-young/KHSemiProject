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
 * Servlet implementation class AjzxPlayQuizMediaController
 */
public class AjzxPlayQuizMediaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjzxPlayQuizMediaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pNum = Integer.parseInt(request.getParameter("pNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		String link = new PlayQuizService().AjaxPlayQuizMedia(num, pNum);
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    new Gson().toJson(link, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
