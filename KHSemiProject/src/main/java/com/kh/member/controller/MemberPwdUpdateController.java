package com.kh.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class MemberPwdUpdateController
 */
public class MemberPwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("password");
		String updatememberPwd = request.getParameter("new-password");
		
		Member updateMember = new MemberService().updatePwdMember(memberId, memberPwd, updatememberPwd);
		HttpSession session = request.getSession();
		if(updateMember == null) {
			session.setAttribute("alertMsg", "비밀번호 변경에 실패하였습니다.");
//			request.getRequestDispatcher("/KHSemiProject/templates/userset2.jsp").forward(request, response);
		} else {
			
			session.setAttribute("loginMember", updateMember);
			session.setAttribute("alertMsg", "성공적으로 변경을 완료하였습니다.");
		}
		
		response.sendRedirect(request.getContextPath() + "/userset2.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
