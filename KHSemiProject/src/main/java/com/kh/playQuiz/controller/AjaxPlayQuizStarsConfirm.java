package com.kh.playQuiz.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;
import com.kh.playQuiz.service.PlayQuizService;

/**
 * Servlet implementation class AjaxPlayQuizStarsConfirm
 */
public class AjaxPlayQuizStarsConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPlayQuizStarsConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qNum = Integer.parseInt(request.getParameter("qNum"));
		int mNum = Integer.parseInt(request.getParameter("mNum"));
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		boolean result = new PlayQuizService().AjaxPlayQuizStarsConfirm(qNum, mNum, rating);
		
		if(result) {
			int questNo = 5; // 5 : 퀴즈 별점 남기기
			int isDone = new PlayQuizService().checkDailyQuest(mNum, questNo);
			
			// MEMBER_QUEST_SUCCESS 값 0은 퀘스트완료 X / 보상 획득 X
			// MEMBER_QUEST_SUCCESS 값 1은 퀘스트완료 O / 보상 획득 X
			// MEMBER_QUEST_SUCCESS 값 2는 퀘스트완료 O / 보상 획득 O
			if(isDone == 0) { // 퀘스트 깬적 없을 경우 ( 오늘 첫 로그인 )
				new PlayQuizService().successQuest(mNum, questNo);
			}
		}
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    new Gson().toJson(result, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
