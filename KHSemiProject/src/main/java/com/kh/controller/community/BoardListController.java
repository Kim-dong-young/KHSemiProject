package com.kh.controller.community;

import java.io.IOException;

import com.kh.service.community.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class boardListController
 */
@WebServlet(name = "list.bo", urlPatterns = { "/list.bo" })
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 게시글 */
		int listCount; // DB에 있는 총 게시글 수
		int currentPage; // 현재 사용자가 요청한 페이지
		int pageBarLimit = 10; // 페이지 하단 페이징 바 개수
		int boardLimit = 10; // 한 페이지 내에 보여질 게시글 최대 수
		
		/* 페이징 바 */
		int maxPage; // 게시글 수를 페이지 단위로 나눴을 때, 가장 마지막 페이지
		int startPage; // 제일 첫 페이지(시작 = 1), 페이징 바의 시작 수
		int endPage; // 페이징 바의 마지막 끝 수
		
		listCount = new BoardService().selectListCount();
		
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// int 나누기 int => int, double로 형변환 후 계산 -> if 나머지 있다면 max Page = 몫 + 1;
		maxPage = (int) Math.ceil( (double)listCount / boardLimit);
		
		startPage = ((currentPage / pageBarLimit) * pageBarLimit) + 1;
		endPage = startPage + pageBarLimit - 1;
		
		request.getRequestDispatcher("templates/communityMainPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
