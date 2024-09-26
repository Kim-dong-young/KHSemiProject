package com.kh.community.controller;

import java.io.IOException;

import com.kh.community.model.vo.Comment;
import com.kh.community.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CommentInsertController
 */
public class CommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNo = request.getParameter("commentWriter");
		String content = request.getParameter("commentContent");
		String communityNo = request.getParameter("no");
		
		String cpage = request.getParameter("cpage");
		String cmtPage = request.getParameter("comment");
		System.out.println(cmtPage);
		
		Comment comment = new Comment();
		comment.setMemberNo(userNo);
		comment.setCommentContent(content);
		comment.setCommunityNo(Integer.parseInt(communityNo));
		
		int result = new BoardService().insertComment(comment);
		
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
