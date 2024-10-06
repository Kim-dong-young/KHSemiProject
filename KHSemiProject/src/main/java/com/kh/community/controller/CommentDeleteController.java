package com.kh.community.controller;

import java.io.IOException;

import com.kh.community.model.vo.Comment;
import com.kh.community.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentDeleteController
 */
public class CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bService = new BoardService();
		String communityNo = request.getParameter("no");
		String cpage = request.getParameter("cpage");
		String cmtPage = request.getParameter("comment");
		
		int commentNo = Integer.parseInt(request.getParameter("cno"));
		Comment comment = bService.selectComment(commentNo);
		
		int result = 0;
		if(comment.getCommentChildCount() > 0) {
			result = bService.updateCommentStatus(comment);
		} else {
			result = bService.deleteMemberComment(comment);
		}
		
		response.sendRedirect("board?cpage="+ cpage +"&no="+ communityNo +"&comment=" + cmtPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
