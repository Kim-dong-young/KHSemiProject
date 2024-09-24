package com.kh.search.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.kh.common.PageInfo;
import com.kh.community.service.BoardService;
import com.kh.search.model.vo.Quiz;
import com.kh.search.service.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * (int category(0 전체 1 유머 ~ 8 기타), int search_type (0 관련없음 1 제목 2 제작자), 
	String search_text(null가능), int orderby ( 1 조회수 2 최신 3 평점 )
 */

/**
 * Servlet implementation class SearchMainController
 */
public class SearchMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//----------------------------페이징 처리------------------------------
		int quizCount; //현재 총 게시글 수 
		int currentPage; //현재 페이지(사용자가 요청한 페이지) 
		int pageLimit = 10; //페이지 하단에 보여질 페이징바의 수 
		int boardLimit = 8; //한 페이지내에 보여질 게시글 최대갯수 
		//위 4개의 값을 기준으로 아래 3개의 값을 구할 수 있음
		
		int maxPage; //가장 마지막 페이지(총 페이지의 수)
		int startPage; //페이징바의 시작 수
		int endPage; //페이징방의 마지막 끝수
		
		//총 게시글 수
		quizCount = new SearchService().selectQuizCount();
		
		//현재 페이지(사용자가 요청한 페이지) 
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		/**
		 * maxPage : 제일 마지막 페이지 수
		 * 
		 * listCount      boardLimit				
		 * 	  100				10			-> 	10
		 * 	  107				10			-> 	10...7
		 * 
		 * 총 게시글 수 / 한페이지에 보여줄 게시글 수 -> 올림
		 */
		
		maxPage = (int)Math.ceil((double)quizCount/boardLimit);
		
		/**
		 * startPage : 페이징바 시작수
		 * 
		 * currentPage		pageLimit		startPage
		 * 		1			   10 				1
		 *		5			   10				1
		 *		11			   10 				2
		 *
		 *startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1
		 */
		
		startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		
		/**
		 * endPage : 페이징바의 끝수
		 * 
		 * startPage : 1 -> 10
		 * startPage : 11 -> 20
		 * startPgge : 21 -> 30
		 */
		
		endPage = startPage + pageLimit - 1;
		
		//startPage가 11면 endPage 20이다(maxPage 13이라면?)
		endPage = endPage > maxPage ? maxPage : endPage;
		
		
		int category = Integer.parseInt(request.getParameter("category"));
		int search_type = Integer.parseInt(request.getParameter("search_type"));
		String search_text = request.getParameter("search_text");
		int orderby = Integer.parseInt(request.getParameter("orderby"));
		
		
		
		
		PageInfo pi = new PageInfo(quizCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Quiz> list = new SearchService().selectQuiz(pi, category, search_type, search_text, orderby);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		System.out.println(pi);
		System.out.println(list);

		request.getRequestDispatcher("templates/searchMainPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
