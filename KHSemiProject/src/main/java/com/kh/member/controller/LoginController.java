package com.kh.member.controller;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;
import com.kh.common.DailyQuestTemplate;
import com.kh.common.DailyQuestTemplate.*;

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
		
		String contextPath = request.getContextPath(); // contextPath
		String path = request.getParameter("path");    // js에서 받아온 pathname
		String checkPath = contextPath + path;          // contextPath + pathname
		
		System.out.println(checkPath);
		Member loginMember = new MemberService().loginMember(memberId, memberPwd);
		
		HttpSession session = request.getSession();		
		if(loginMember == null) {
			session.setAttribute("alertMsg", "로그인에 실패하였습니다.");
			
			if(checkPath.equals(contextPath)) {
				response.sendRedirect(contextPath);
			} else {
				response.sendRedirect(path);
			}
		} else {
			int totalAt = new MemberService().totalAttendance(loginMember.getMemberNo());
			
			if(loginMember.getCheckContinueCnt() != 0) {
				Member updateMem = new MemberService().resetAttend(loginMember);
				session.setAttribute("loginMember", updateMem);
			} else {
				session.setAttribute("loginMember", loginMember);
			}
			
			int result = 0;
			// 첫 접속 유저에게 퀘스트를 부여한다.
			result = new MemberService().initDailyQuest(loginMember); // 유저가 가진 퀘스트 개수 반환
			
			if(result > 0) { // 퀘스트가 있다면, 하루가 지났는지 검사해서 퀘스트를 교체해준다.
				new MemberService().updateDailyQuest(loginMember);
			}
			
			// 로그인 하면 퀘스트 성공
			new MemberService().successQuest(loginMember.getMemberNo(), 2); // 2 : 로그인 하기
			
			session.setAttribute("totalAt", totalAt);
			
			if(checkPath.equals(contextPath)) {
				response.sendRedirect(contextPath + "/main.me");
			} else {
				response.sendRedirect(path);
			}
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