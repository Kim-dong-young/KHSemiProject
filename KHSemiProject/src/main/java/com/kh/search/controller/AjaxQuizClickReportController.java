package com.kh.search.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.kh.common.ReportInfo;
import com.kh.search.service.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxQuizClickReportController
 */
public class AjaxQuizClickReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxQuizClickReportController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int qNum = Integer.parseInt(request.getParameter("quizNo"));
		int doReportMNum = Integer.parseInt(request.getParameter("reportedMemberNo"));
		int rpNum = Integer.parseInt(request.getParameter("reportNumber"));
		String reportReason = request.getParameter("reportReason");
		
		ReportInfo reportInfo = new ReportInfo(rpNum, reportReason, doReportMNum, new SearchService().selectQuizMaker(qNum));
		reportInfo.setQuizNo(qNum);
		
		int result = new SearchService().insertReportQuiz(reportInfo);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(result, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
