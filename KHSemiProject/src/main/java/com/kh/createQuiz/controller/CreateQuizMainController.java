package com.kh.createQuiz.controller;

import java.io.IOException;
import java.io.InputStream;

import com.kh.createQuiz.service.CreateQuizService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 최대 10MB 파일 업로드 허용
public class CreateQuizMainController extends HttpServlet {

	private CreateQuizService createQuizService;

	@Override
	public void init() throws ServletException {
		super.init();
		createQuizService = new CreateQuizService(); // CreateQuizService 초기화
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 퀴즈 생성 페이지로 포워딩
		request.getRequestDispatcher("/templates/CreateQuizMain.jsp").forward(request, response);
		// Form에서 받은 데이터를 처리
				String title = request.getParameter("title");
				String explanation = request.getParameter("explanation");
				String category = request.getParameter("category");
				String tag = request.getParameter("tag");

				// 파일 데이터 처리
				Part thumbnailPart = request.getPart("thumbnail"); // input file name="thumbnail"
				InputStream thumbnailInputStream = null;
				if (thumbnailPart != null && thumbnailPart.getSize() > 0) {
					thumbnailInputStream = thumbnailPart.getInputStream();
				}

				// Quiz 생성 서비스 호출
				boolean isCreated = createQuizService.createQuiz(title, explanation, category, tag, thumbnailInputStream);

				if (isCreated) {
					// 성공 시 리다이렉트
					response.sendRedirect(request.getContextPath() + "/quiz.cr");
				} else {
					// 실패 시 에러 페이지로 이동
					response.sendRedirect(request.getContextPath() + "/main.me");
				} 
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
