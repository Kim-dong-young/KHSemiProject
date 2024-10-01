package com.kh.community.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.kh.community.model.vo.Comment;
import com.kh.community.service.BoardService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class CommentReplyController
 */
public class CommentReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		String content = request.getParameter("commentContent");
		String communityNo = request.getParameter("no");
		String parentNo = request.getParameter("pno");
		
		String cpage = request.getParameter("cpage");
		String cmtPage = request.getParameter("comment");
		
		Comment comment = new Comment();
		comment.setMemberNo(userNo);
		comment.setCommentContent(content);
		comment.setCommunityNo(Integer.parseInt(communityNo));
		comment.setCommentParentNo(Integer.parseInt(parentNo));
		
		int result = new BoardService().insertCommentReply(comment);
		
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
