package com.kh.search.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;
import com.kh.search.service.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxQuizMarkController
 */
public class AjaxQuizMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxQuizMarkController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizNum = Integer.parseInt(request.getParameter("quizNum"));
		int memberNum = Integer.parseInt(request.getParameter("member"));
		
		int mark = new SearchService().markInsert(quizNum, memberNum);
		
		System.out.println("데이터들어옴");
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(mark, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
