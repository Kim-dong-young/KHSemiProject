package com.kh.community.controller;

import java.io.IOException;

import com.kh.common.ReportInfo;
import com.kh.community.service.BoardService;
import com.kh.member.model.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardReportController
 */
public class BoardReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int boardNo = Integer.parseInt(request.getParameter("communityNo"));
		int memberNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		int reportedMemberNo = Integer.parseInt(request.getParameter("reportedMemberNo"));
		int reportNumber = Integer.parseInt(request.getParameter("reportNumber"));
		String reportReason = request.getParameter("reportReason");
		
		ReportInfo reportInfo = new ReportInfo(reportNumber, reportReason, memberNo, reportedMemberNo);
		reportInfo.setCommunityNo(boardNo);
		
		int result = new BoardService().insertReportBoard(reportInfo);
		
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
