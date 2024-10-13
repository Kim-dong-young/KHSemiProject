package com.kh.playQuiz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.kh.playQuiz.service.PlayQuizService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxPlayQuizAnswerCheckController
 */
public class AjaxPlayQuizAnswerCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxPlayQuizAnswerCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pNum = Integer.parseInt(request.getParameter("problem_num"));
	    String answer = request.getParameter("answer");
	    
	    PlayQuizService service = new PlayQuizService();
	    boolean result = service.AjaxPlayQuizAnswerCheck(pNum, answer);
	    String correctAnswer = service.getCorrectAnswer(pNum); // 정답을 가져오는 메소드 추가

	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("correct", result);
	    responseMap.put("correctAnswer", correctAnswer);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    new Gson().toJson(responseMap, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
