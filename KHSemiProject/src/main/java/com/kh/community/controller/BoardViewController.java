package com.kh.community.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.kh.common.PageInfo;
import com.kh.community.model.vo.Board;
import com.kh.community.model.vo.Category;
import com.kh.community.model.vo.Comment;
import com.kh.community.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardViewController
 */
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bService = new BoardService();
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		Board board = bService.selectBoard(boardNo);
		request.setAttribute("board", board);
		
		String tabNo = request.getParameter("tno");
		request.setAttribute("tno", tabNo);
		
		/* 게시글 */
		int listCount; // DB에 있는 총 게시글 수
		int currentPage; // 현재 사용자가 요청한 페이지
		int pageBarLimit = 10; // 페이지 하단 페이징 바 개수
		int boardLimit = 10; // 한 페이지 내에 보여질 게시글 최대 수
		
		/* 페이징 바 */
		int maxPage; // 게시글 수를 페이지 단위로 나눴을 때, 가장 마지막 페이지
		int startPage; // 제일 첫 페이지(시작 = 1), 페이징 바의 시작 수
		int endPage; // 페이징 바의 마지막 끝 수
		
		if(tabNo == null) {
			listCount = bService.selectListCount();
		} else {
			listCount = bService.selectBoardTabListCount(Integer.parseInt(tabNo));
		}
		
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// int 나누기 int => int, double로 형변환 후 계산 -> if 나머지 있다면 max Page = 몫 + 1;
		maxPage = (int) Math.ceil( (double)listCount / boardLimit);
		
		startPage = (( (currentPage - 1) / pageBarLimit ) * pageBarLimit) + 1;
		endPage = (startPage + pageBarLimit - 1) > maxPage ? maxPage : (startPage + pageBarLimit - 1);
		
		PageInfo pageInfo = new PageInfo(listCount, currentPage, pageBarLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Board> boardList = new ArrayList<>();
		
		if(tabNo == null) {
			boardList = bService.selectList(pageInfo);
		} else {
			boardList = bService.selectBoardTabList(pageInfo, Integer.parseInt(tabNo));
		}
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("boardList", boardList);
		
		/* 댓글 */
		int cCount; // 해당 게시글의 총 댓글 수
		int cCurrentPage; // 현재 사용자가 요청한 페이지
		int cPageBarLimit = 5; // 페이지 하단 페이징 바 개수
		int cLimit = 10; // 한 페이지 내에 보여질 댓글 최대 수
		
		int cMaxPage; // 댓글 수를 페이지 단위로 나눴을 때, 가장 마지막 페이지
		int cStartPage; // 제일 첫 페이지(시작 = 1), 페이징 바의 시작 수
		int cEndPage; // 페이징 바의 마지막 끝 수
		
		cCount = bService.countBoardComment(boardNo);
		
		cCurrentPage = Integer.parseInt(request.getParameter("comment"));
		
		cMaxPage = (int) Math.ceil( (double)cCount / cLimit);
		
		cStartPage = (( (cCurrentPage - 1) / cPageBarLimit ) * cPageBarLimit) + 1;
		
		cEndPage = (cStartPage + cPageBarLimit - 1) > cMaxPage ? 
				cMaxPage : (cStartPage + cPageBarLimit - 1);
		
		PageInfo cPageInfo = new PageInfo(cCount, cCurrentPage, cPageBarLimit, 
				cLimit, cMaxPage, cStartPage, cEndPage);
		
		ArrayList<Comment> commentList = bService.selectCommentList(cPageInfo, boardNo);
		ArrayList<Category> category = bService.selectCategory();
		
		request.setAttribute("commentCount", cCount);
		request.setAttribute("cPageInfo", cPageInfo);
		request.setAttribute("commentList", commentList);
		request.setAttribute("category", category);

		request.getRequestDispatcher("templates/communityViewPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
