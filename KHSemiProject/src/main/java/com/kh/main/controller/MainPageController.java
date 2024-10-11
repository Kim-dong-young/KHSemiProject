package com.kh.main.controller;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainPageController
 */
public class MainPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pageName", "mainPage");
		MemberService mService = new MemberService();
		
		
		Member m = ((Member)request.getSession().getAttribute("loginMember"));
		
		if(m != null) {
			int rResult = mService.playedRecode(m.getMemberNo());
			int aResult = mService.attendanceRate(m.getMemberNo());
			int cResult = mService.correctRate(m.getMemberNo());
			
			request.setAttribute("optional", m.getExp());
			request.setAttribute("playedRecode", rResult);
			request.setAttribute("attendance", aResult);
			request.setAttribute("correct", cResult);
		}
	
		request.getRequestDispatcher("templates/mainPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
