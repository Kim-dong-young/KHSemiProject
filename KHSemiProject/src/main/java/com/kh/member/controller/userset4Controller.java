package com.kh.member.controller;

import java.io.IOException;

import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class userset4Controller
 */
public class userset4Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userset4Controller() {
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
		
		int result = new MemberService().deleteMember(memberId,memberPwd);
		
		HttpSession session = request.getSession();
		if(result > 0) {
			session.removeAttribute("loginUser");
			session.setAttribute("alertMsg", "성공적으로 회원탈퇴 되었습니다.");
			
			response.sendRedirect(request.getContextPath());
		}else {
			session.setAttribute("alertMsg", "회원탈퇴에 실패하였습니다.");
			response.sendRedirect(request.getContextPath() + "/userset4.me");
		}
		
		
		request.getRequestDispatcher("templates/userset4.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
