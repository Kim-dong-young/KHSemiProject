package com.kh.playQuiz.controller;

import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.playQuiz.service.PlayQuizService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxQuizQuestController
 */
public class AjaxQuizQuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxQuizQuestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if( request.getSession().getAttribute("loginMember") != null ) {
			int memberNum = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
			int questNo = 6; // 6 : 문제 하나 맞추기
			int isDone = new PlayQuizService().checkDailyQuest(memberNum, questNo);
			
			// MEMBER_QUEST_SUCCESS 값 0은 퀘스트완료 X / 보상 획득 X
			// MEMBER_QUEST_SUCCESS 값 1은 퀘스트완료 O / 보상 획득 X
			// MEMBER_QUEST_SUCCESS 값 2는 퀘스트완료 O / 보상 획득 O
			if(isDone == 0) { // 퀘스트 깬적 없을 경우 ( 오늘 첫 로그인 )
				// 로그인 하면 퀘스트 성공
				new PlayQuizService().successQuest(memberNum, questNo);
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
