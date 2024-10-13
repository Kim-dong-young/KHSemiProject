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
 * Servlet implementation class MemberInsertController
 */
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("sMemberId");
		String memberPwd = request.getParameter("sMemberPwd");
		String nickname = request.getParameter("sMemberNickname");
		
		String contextPath = request.getContextPath(); // contextPath
		String path = request.getParameter("sPath");    // js에서 받아온 pathname
		String checkPath = contextPath + path;          // contextPath + pathname
		
		Member m = new Member(memberId, memberPwd, nickname);
		
		int result = new MemberService().insertMember(m);
		
		HttpSession session = request.getSession();
		
		if(result > 0) {	
			session.setAttribute("alertMsg", "성공적으로 회원가입이 되었습니다.");
		} else {
			session.setAttribute("alertMsg", "회원가입에 실패하였습니다.");
		}
		
		if(checkPath.equals(contextPath)) {
			response.sendRedirect(contextPath);
		} else {
			response.sendRedirect(path);
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
