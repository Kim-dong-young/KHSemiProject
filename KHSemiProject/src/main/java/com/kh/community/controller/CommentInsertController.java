package com.kh.community.controller;

import java.io.IOException;

import com.kh.community.model.vo.Comment;
import com.kh.community.service.BoardService;
import com.kh.member.model.vo.Member;

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
		BoardService bService = new BoardService();
		
		int memberNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		String content = request.getParameter("commentContent");
		String communityNo = request.getParameter("no");
		
		String cpage = request.getParameter("cpage");
		String cmtPage = request.getParameter("comment");
		
		Comment comment = new Comment();
		comment.setMemberNo(memberNo);
		comment.setCommentContent(content);
		comment.setCommunityNo(Integer.parseInt(communityNo));
		
		int commentGroupNo = bService.selectMaxCommentGroupNo(Integer.parseInt(communityNo));
		if(commentGroupNo != -1) { // -1은 조회실패를 뜻함
			comment.setCommentGroup(commentGroupNo + 1);
		}
		
		int result = bService.insertComment(comment);
		if(result > 0) {
			int questNo = 3;
			bService.successQuest(memberNo, questNo);
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
