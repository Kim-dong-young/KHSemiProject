package com.kh.community.controller;

import java.io.IOException;

import com.kh.community.service.BoardService;
import com.kh.member.model.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDeleteController
 */
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		int cPage = Integer.parseInt(request.getParameter("cpage"));
		int cmtPage = Integer.parseInt(request.getParameter("comment"));
		
		System.out.println(boardNo);
		System.out.println(cPage);
		System.out.println(cmtPage);
		/*	삭제 순서
		 * 
		 * 	1. 게시글에 달린 댓글부터 삭제한다
		 * 	2. 게시글에 달린 좋아요 정보도 삭제한다
		 *  3. 게시글을 삭제한다
		 */
		
		boolean isSuccess = new BoardService().deleteBoard(boardNo);
		
		if(isSuccess) {
			response.sendRedirect("community?cpage=" + cPage);
		} else {
			request.setAttribute("errorMsg", "삭제에 실패하였습니다.");
			response.sendRedirect("board?cpage=" + cPage + "&no="+ boardNo + "&comment=" + cmtPage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
