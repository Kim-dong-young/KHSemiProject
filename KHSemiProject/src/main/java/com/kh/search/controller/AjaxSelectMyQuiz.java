package com.kh.search.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.kh.search.model.vo.Quiz;
import com.kh.search.service.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectMyQuiz
 */
public class AjaxSelectMyQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService = new SearchService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxSelectMyQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		String selectValue = request.getParameter("selName");
		String btnValue = request.getParameter("btnName");
		String searchValue = request.getParameter("searchVal");
		String serSelValue = request.getParameter("serSelName");
		
		ArrayList<Quiz> list = null;
		
		if(searchValue == null && serSelValue == null) {
			list = searchService.noSearchMyQuiz(memberNo, selectValue, btnValue);
		} else {
			list = searchService.searchMyQuiz(memberNo, selectValue, btnValue, searchValue, serSelValue);
		}
		
		
		HashMap<String, Object> result = new HashMap<>();
	    result.put("qList", list);
	    result.put("contextPath", request.getContextPath());
		
		response.setContentType("application/json; charset=UTF-8");
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
