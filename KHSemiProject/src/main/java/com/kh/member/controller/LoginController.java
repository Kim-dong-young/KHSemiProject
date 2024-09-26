package com.kh.member.controller;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class loginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");		
		String path = request.getParameter("origin");
		
		System.out.println(path);
		Member loginMember = new MemberService().loginMember(memberId, memberPwd);
		
		HttpSession session = request.getSession();		
		if(loginMember == null) {
			session.setAttribute("alertMsg", "로그인에 실패하였습니다.");
			
			if(path.equals("/KHSemiProject/templates/mainPage.jsp") ) {
				response.sendRedirect(request.getContextPath() + "/main.me");
			} else {
				response.sendRedirect(request.getContextPath());
			}
		} else {
			int totalAt = new MemberService().totalAttendance(loginMember.getMemberNo());
			
			if(loginMember.getCheckContinueCnt() != 0) {
				Member updateMem = new MemberService().resetAttend(loginMember);
				session.setAttribute("loginMember", updateMem);
			} else {
				session.setAttribute("loginMember", loginMember);
			}
			
			session.setAttribute("totalAt", totalAt);
			
			//request.getRequestDispatcher("templates/mainPage.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/main.me");
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
