package com.kh.community.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.kh.common.ReportInfo;
import com.kh.community.service.BoardService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class CommentReportController
 */
public class CommentReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int memberNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		int reportedMemberNo = Integer.parseInt(request.getParameter("reportedMemberNo"));
		int reportNumber = Integer.parseInt(request.getParameter("reportNumber"));
		String reportReason = request.getParameter("reportReason");
		
		ReportInfo reportInfo = new ReportInfo(reportNumber, reportReason, memberNo, reportedMemberNo);
		reportInfo.setCommunityCommentNo(commentNo);
		
		int result = new BoardService().insertReportComment(reportInfo);
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
