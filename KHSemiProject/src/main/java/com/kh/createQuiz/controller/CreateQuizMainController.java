package com.kh.createQuiz.controller;

import java.io.IOException;

import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.service.CreateQuizService;
import com.kh.createQuiz.service.CreateQuizServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;  // **수정된 부분: multipart 처리를 위한 어노테이션 추가**
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;  // **수정된 부분: 파일 업로드 처리를 위한 Part 클래스 사용**

@MultipartConfig  // **수정된 부분: 파일 업로드 처리를 위한 어노테이션 추가**
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
		
		// 기존 파라미터 처리
		String quizTitle = request.getParameter("title");
		String quizExplanation = request.getParameter("explanation");
		String categoryNumber = request.getParameter("category");
		String tagName = request.getParameter("tag");

		if (categoryNumber == null || categoryNumber.trim().isEmpty()) {
			categoryNumber = "0"; 
		}

		String categoryName = getCategoryName(categoryNumber);  // **수정된 부분: 카테고리 이름을 가져오는 메소드 사용**

		if (tagName == null || tagName.trim().isEmpty()) {
			tagName = categoryName;
		}

		// **수정된 부분: 파일 처리 (썸네일 업로드)**
		Part thumbnailPart = request.getPart("thumbnail");  // 파일 파라미터로부터 Part 객체를 가져옴
		String thumbnailFileName = thumbnailPart.getSubmittedFileName();  // 업로드된 파일의 이름을 가져옴

		// **수정된 부분: 파일을 저장할 경로 설정 및 파일 저장**
		String uploadPath = getServletContext().getRealPath("") + "uploads";  
		thumbnailPart.write(uploadPath + "/" + thumbnailFileName);  // 파일을 서버에 저장

		// CreateQuiz 객체에 파라미터 설정
		CreateQuiz quiz = new CreateQuiz();
		quiz.setQUIZ_TITLE(quizTitle);
		quiz.setQUIZ_EXPLANATION(quizExplanation);
		quiz.setCATEGORY_NUMBER(Integer.parseInt(categoryNumber));
		quiz.setTAG_NAME(tagName);
		quiz.setTHUMBNAIL(thumbnailFileName);  // **수정된 부분: 업로드된 파일 이름을 설정**

		// 서비스 호출 및 결과 처리
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

	// **수정된 부분: 카테고리 이름을 가져오는 메소드**
	private String getCategoryName(String categoryNumber) {
		switch (categoryNumber) {
			case "1": return "유머";
			case "2": return "예술/문학";
			case "3": return "세계";
			case "4": return "역사";
			case "5": return "언어";
			case "6": return "과학/자연";
			case "7": return "스포츠";
			case "8": return "기타";
			default: return "미정";
		}
	}
}
