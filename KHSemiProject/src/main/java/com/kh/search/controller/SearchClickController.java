package com.kh.search.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.kh.search.model.vo.Tag;
import com.kh.search.service.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchClickController
 */
public class SearchClickController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchClickController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("quiz_number"));
		
		ArrayList<Tag> tagArr = new SearchService().TagArray(num);
		
		request.setAttribute("Quiz", new SearchService().detailQuiz(num));
		request.setAttribute("viewCount", new SearchService().quizViewCount(num));
		request.setAttribute("TagArr", tagArr);
		request.setAttribute("quiz_rate", new SearchService().quizRateAvg(num));
		request.setAttribute("list", new SearchService().simularQuizList(tagArr));
		request.setAttribute("pageName", "searchClickPage"); 
		request.getRequestDispatcher("templates/searchClickPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
