package com.kh.search.controller;

import java.io.IOException;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;
import com.kh.search.service.SearchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxQuizMarkController
 */
public class AjaxQuizMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxQuizMarkController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int quizNum = Integer.parseInt(request.getParameter("quizNum"));
		int memberNum = Integer.parseInt(request.getParameter("member"));
		
		int mark = new SearchService().markInsert(quizNum, memberNum);
		
		int questNo = 8; // 8 : 북마크하기
		int isDone = new SearchService().checkDailyQuest(memberNum, questNo);
		
		// MEMBER_QUEST_SUCCESS 값 0은 퀘스트완료 X / 보상 획득 X
		// MEMBER_QUEST_SUCCESS 값 1은 퀘스트완료 O / 보상 획득 X
		// MEMBER_QUEST_SUCCESS 값 2는 퀘스트완료 O / 보상 획득 O
		if(isDone == 0) { // 퀘스트 깬적 없을 경우 ( 오늘 첫 로그인 )
			// 로그인 하면 퀘스트 성공
			new SearchService().successQuest(memberNum, questNo);
		}
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(mark, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
