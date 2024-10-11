package com.kh.playQuiz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;
import com.kh.playQuiz.service.PlayQuizService;

/**
 * Servlet implementation class PlayQuizEndController
 */
public class PlayQuizEndController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayQuizEndController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int cNum = Integer.parseInt(request.getParameter("correctNum"));
		int qNum = Integer.parseInt(request.getParameter("quizNumber"));
		int mNum = 0;
		System.out.println(request.getParameter("memberNo"));
		if(request.getParameter("memberNo") == null || request.getParameter("memberNo").equals("")) {
			mNum = -1;
		} else {
			mNum = Integer.parseInt(request.getParameter("memberNo"));
		}
		System.out.println(cNum);
		System.out.println(qNum);
		System.out.println(mNum);
		if(mNum != -1) {
			new PlayQuizService().MemberAddExp(mNum, qNum);
			Member updateMem = new MemberService().selectMember(mNum);
			request.getSession().setAttribute("loginMember", updateMem);
			int exp = new PlayQuizService().PlayQuizSelectExp(mNum);
			request.setAttribute("exp", exp);
		}
		boolean link = new PlayQuizService().AjaxPlayQuizViewCount(qNum, mNum, cNum);
		System.out.println(link);
		
		int questNo = 1; // 1 : 퀴즈 1개 완료하기
		int isDone = new PlayQuizService().checkDailyQuest(mNum, questNo);
		
		// MEMBER_QUEST_SUCCESS 값 0은 퀘스트완료 X / 보상 획득 X
		// MEMBER_QUEST_SUCCESS 값 1은 퀘스트완료 O / 보상 획득 X
		// MEMBER_QUEST_SUCCESS 값 2는 퀘스트완료 O / 보상 획득 O
		if(isDone == 0) { // 퀘스트 깬적 없을 경우 ( 오늘 첫 로그인 )
			new PlayQuizService().successQuest(mNum, questNo);
		}
		
		request.getRequestDispatcher("templates/QuizEnd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
