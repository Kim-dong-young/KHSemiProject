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
 * Servlet implementation class MemberProfileController
 */
public class MemberProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberNickName = request.getParameter("memberNickName");
		String Introduce = request.getParameter("Introduce");
		
		Member p = new Member(memberId,memberNickName,Introduce);
		p.setMemberId(memberId);
		p.setMemberNickName(memberNickName);
		p.setIntroduce(Introduce);
		
		Member updateProfile = new MemberService().updateProfile(p);
		
		if(updateProfile == null) {
			request.setAttribute("errorMsg", "프로필 수정에 실패하였습니다.");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateProfile);
			session.setAttribute("alertMsg", "프로필 수정에 성공하였습니다.");
		}
		response.sendRedirect(request.getContextPath() + "/userset.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
