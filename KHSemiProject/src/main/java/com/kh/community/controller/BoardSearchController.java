package com.kh.community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.common.PageInfo;
import com.kh.community.model.dao.BoardDao;
import com.kh.community.model.vo.Board;
import com.kh.community.model.vo.Category;
import com.kh.community.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardSearchController
 */
public class BoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bService = new BoardService();
		String searchText = request.getParameter("searchText");
		String searchOption = request.getParameter("searchOption");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("searchText", searchText);
		map.put("searchOption", searchOption);
		
		/* 게시글 */
		int listCount = 0; // DB에 있는 총 게시글 수
		int currentPage; // 현재 사용자가 요청한 페이지
		int pageBarLimit = 10; // 페이지 하단 페이징 바 개수
		int boardLimit = 10; // 한 페이지 내에 보여질 게시글 최대 수
		
		/* 페이징 바 */
		int maxPage; // 게시글 수를 페이지 단위로 나눴을 때, 가장 마지막 페이지
		int startPage; // 제일 첫 페이지(시작 = 1), 페이징 바의 시작 수
		int endPage; // 페이징 바의 마지막 끝 수
		
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		String tabNo = request.getParameter("tno");
		
		if(searchOption.equals("all")) {
			if(tabNo == null) {
				listCount = bService.searchListCount(searchText);
			} else {
				listCount = bService.searchBoardTabListCount(Integer.parseInt(tabNo), searchText);
			}
		}
		
		// int 나누기 int => int, double로 형변환 후 계산 -> if 나머지 있다면 max Page = 몫 + 1;
		maxPage = (int) Math.ceil( (double)listCount / boardLimit);
		
		startPage = (( (currentPage - 1) / pageBarLimit ) * pageBarLimit) + 1;
		endPage = (startPage + pageBarLimit - 1) > maxPage ? maxPage : (startPage + pageBarLimit - 1);
		
		PageInfo pageInfo = new PageInfo(listCount, currentPage, pageBarLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Board> boardList = new ArrayList<>();
		
		if(searchOption.equals("all")) {
			if(tabNo == null) {
				boardList = bService.searchList(pageInfo, searchText);
			} else {
				boardList = bService.searchBoardTabList(pageInfo, Integer.parseInt(tabNo), searchText);
			}
		}
		
		ArrayList<Category> category = bService.selectCategory();
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		request.setAttribute("category", category);
		
		request.setAttribute("pageName", "communityMainPage");
		request.setAttribute("optional", tabNo);
		
		request.setAttribute("searchText", searchText);
		request.setAttribute("searchOption", searchOption);

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
