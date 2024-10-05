package com.kh.createQuiz.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.service.CreateQuizService;
import com.kh.createQuiz.service.CreateQuizServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * CreateQuizMainController
 */
@MultipartConfig  
public class CreateQuizMainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CreateQuizService quizService = new CreateQuizServiceImpl();

    public CreateQuizMainController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 기존 파라미터 처리
        String quizTitle = request.getParameter("title");
        String quizExplanation = request.getParameter("explanation");
        String categoryNumber = request.getParameter("category");
        String tagName = request.getParameter("tag");
        
        // 입력된 값 콘솔에 출력
        System.out.println("Quiz Title: " + quizTitle);
        System.out.println("Quiz Explanation: " + quizExplanation);
        System.out.println("Category Number: " + categoryNumber);
        System.out.println("Tag Name: " + tagName);

        if (categoryNumber == null || categoryNumber.trim().isEmpty()) {
            categoryNumber = "0"; 
        }

        String categoryName = getCategoryName(categoryNumber); 

        if (tagName == null || tagName.trim().isEmpty()) {
            tagName = categoryName;
        }

        // 썸네일 업로드
        Part thumbnailPart = request.getPart("thumbnail"); 
        String thumbnailFileName = thumbnailPart.getSubmittedFileName();  

        // 경로 설정
        String uploadPath = getServletContext().getRealPath("/static/uploads");  
        
        // 업로드 폴더가 존재하지 않으면 생성
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();  // 하위 폴더 생성
        }

        // 파일 이름 중복 방지를 위해 UUID 추가
        String uniqueFileName = UUID.randomUUID().toString() + "_" + thumbnailFileName;
        
        // 파일을 서버에 저장
        thumbnailPart.write(uploadPath + File.separator + uniqueFileName);  

        // CreateQuiz 객체에 파라미터 설정
        CreateQuiz quiz = new CreateQuiz();
        quiz.setQUIZ_TITLE(quizTitle);
        quiz.setQUIZ_EXPLANATION(quizExplanation);
        quiz.setCATEGORY_NUMBER(Integer.parseInt(categoryNumber));
        quiz.setTAG_NAME(tagName);
        quiz.setTHUMBNAIL(uniqueFileName);  // 썸네일 파일 이름을 quiz 객체에 설정

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

    //카테고리 이름을 가져오는 메소드
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
