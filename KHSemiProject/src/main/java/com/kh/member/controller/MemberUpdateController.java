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
 * Servlet implementation class MemberUpdateController
 */
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberemail = request.getParameter("memberemail");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Member m = new Member(phone,address,memberemail);
		
		Member updateMem = new MemberService().updateMember(m);
		
		HttpSession session = request.getSession();
		if(updateMem == null) {
			session.setAttribute("alrtMsg", "회원정보 수정에 실패하였습니다.");
		}else {
			session.setAttribute("loginUser", updateMem);
			session.setAttribute("alrtMsg", "회원정보 수정에 성공하였습니다.");
		}
		response.sendRedirect(request.getContextPath() + "/userset3.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
