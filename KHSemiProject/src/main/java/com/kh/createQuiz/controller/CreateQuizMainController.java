package com.kh.createQuiz.controller;

import java.io.IOException;

import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.service.CreateQuizService;
import com.kh.createQuiz.service.CreateQuizServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CreateQuizMainController 
 */

public class CreateQuizMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreateQuizService quizService = new CreateQuizServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateQuizMainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String quizTitle = request.getParameter("title");
		String quizExplanation = request.getParameter("explanation");
		String categoryNumber = request.getParameter("category");
		String tagName = request.getParameter("tag");

		
		if (categoryNumber == null || categoryNumber.trim().isEmpty()) {
			categoryNumber = "0"; 
		}

		String categoryName = "";

		switch (categoryNumber) {
		case "1":
			categoryName = "유머";
			break;
		case "2":
			categoryName = "예술/문학";
			break;
		case "3":
			categoryName = "세계";
			break;
		case "4":
			categoryName = "역사";
			break;
		case "5":
			categoryName = "언어";
			break;
		case "6":
			categoryName = "과학/자연";
			break;
		case "7":
			categoryName = "스포츠";
			break;
		case "8":
			categoryName = "기타";
			break;
		default:
			categoryName = "미정";
			break;
		}

		
		if (tagName == null || tagName.trim().isEmpty()) {
			tagName = categoryName;
		}

		System.out.println("Title: " + quizTitle);
		System.out.println("Explanation: " + quizExplanation);
		System.out.println("Category Number: " + categoryNumber);
		System.out.println("Tag: " + tagName);

		CreateQuiz quiz = new CreateQuiz();
		quiz.setQUIZ_TITLE(quizTitle);
		quiz.setQUIZ_EXPLANATION(quizExplanation);
		quiz.setCATEGORY_NUMBER(Integer.parseInt(categoryNumber));
		quiz.setTAG_NAME(tagName);

		int result;
		try {
			result = quizService.createQuiz(quiz);
			if (result > 0) {
				response.sendRedirect(request.getContextPath() + "/quiz.cr");
			} else {
				request.setAttribute("errorMsg", "퀴즈 생성에 실패했습니다.");
				response.sendRedirect(request.getContextPath() + "/main.me");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "잘못된 카테고리 번호입니다.");
			response.sendRedirect(request.getContextPath() + "/main.me");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
