package com.kh.member.controller;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DailyQuestCheckController
 */
public class DailyQuestCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DailyQuestCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath(); // contextPath
		int memberNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		int questNo = Integer.parseInt(request.getParameter("qno"));
		
		MemberService mService = new MemberService();
		
		int isDone = mService.checkDailyQuest(memberNo, questNo);
		
		// MEMBER_QUEST_SUCCESS 값 0은 퀘스트완료 X / 보상 획득 X
		// MEMBER_QUEST_SUCCESS 값 1은 퀘스트완료 O / 보상 획득 X
		// MEMBER_QUEST_SUCCESS 값 2는 퀘스트완료 O / 보상 획득 O
		if(isDone == 1) {
			int exp = 100;
			mService.updateMemberExp(memberNo, questNo, exp);
		}
		
		response.sendRedirect(contextPath + "/main.me");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
