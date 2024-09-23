package com.kh.member.controller;

import jakarta.servlet.ServletException;
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
		
		String memberPwd = request.getParameter("memberPwd");
		String updatememberPwd = request.getParameter("updatememberPwd");
		String updatememberPwdChange = request.getParameter("updatememberPwdChange");
		
		Member updateMember = new MemberService().updatePwdMember(memberPwd, updatememberPwd, updatememberPwdChange);
		
		if(updateMember == null) {
			request.setAttribute("errorMsg", "비밀번호 변경에 실패하였습니다.");
			request.getRequestDispatcher("/KHSemiProject/templates/userset.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", session);
			session.setAttribute("alerMsg", "성공적으로 변경을 완료하였습니다.");
		}
		
		response.sendRedirect(request.getContextPath() + "/update.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
