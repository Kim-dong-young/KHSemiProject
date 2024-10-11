package com.kh.createQuiz.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.kh.createQuiz.model.vo.CreateQuiz;
import com.kh.createQuiz.service.CreateQuizService;
import com.kh.createQuiz.service.CreateQuizServiceImpl;
import com.kh.member.model.vo.Member;
import com.kh.search.model.vo.Quiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CreateQuizMainController
 */

public class CreateQuizMainController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CreateQuizService quizService = new CreateQuizServiceImpl();

    public CreateQuizMainController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String changeName = null;

        // enctype이 multipart/form-data로 전송되었는지 체크
        if (JakartaServletFileUpload.isMultipartContent(request)) {
            System.out.println("파일 업로드 성공");
            // 1. 파일 용량 제한 (byte)
            int fileMaxSize = 1024 * 1024 * 10; // 10MB
            int requestMaxSize = 1024 * 1024 * 20; // 20MB

            // 2. 파일을 저장할 경로 설정
            String savePath = request.getServletContext().getRealPath("/static/img/THUMBNAIL/");

            // 3. DiskFileItemFactory 객체 생성
            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
            upload.setFileSizeMax(fileMaxSize);
            upload.setSizeMax(requestMaxSize);

            // 요청에서 파일 아이템을 파싱
            List<FileItem> formItems = upload.parseRequest(request);

            // 추가할 데이터
            CreateQuiz cQuiz = new CreateQuiz();
            cQuiz.setMEMBER_NUMBER(((Member) request.getSession().getAttribute("loginMember")).getMemberNo());

            // 반복문을 통해 파일과 파라미터 정보를 획득
            for (FileItem item : formItems) {
                if (item.isFormField()) { // 일반 파라미터
                    switch (item.getFieldName()) {
                    case "title":
                        cQuiz.setQUIZ_TITLE(item.getString(Charset.forName("utf-8")));
                        break;
                    case "explanation":
                        cQuiz.setQUIZ_EXPLANATION(item.getString(Charset.forName("utf-8")));
                        break;
                    case "category":
                        cQuiz.setCATEGORY_NUMBER(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
                        break;
                    case "tag":
                        cQuiz.setTAG_NAME(item.getString(Charset.forName("utf-8")));
                    }
                } else { // 파일 업로드
                    String originName = item.getName();
                    if (originName.length() > 0) { // 파일이 있는 경우
                        String tmpName = "thumbnail_" + System.currentTimeMillis();
                        String type = originName.substring(originName.lastIndexOf("."));
                        changeName = tmpName + type; // 서버에 저장할 파일명

                        File f = new File(savePath, changeName);
                        item.write(f.toPath()); // 지정한 경로에 파일 업로드

                        cQuiz.setTHUMBNAIL("static/img/THUMBNAIL/" + changeName);
                    }
                }
            }

            // 퀴즈 저장
            int result = quizService.createQuiz(cQuiz);
            if (result > 0) {
            	System.out.println(result);
                request.setAttribute("quiz_num", result); 
                request.getRequestDispatcher("templates/CreateQuiz.jsp").forward(request, response);
            } else {
                if (cQuiz != null) {
                    new File(savePath + changeName).delete(); 
                }
                request.setAttribute("alertMsg", "퀴즈 작성 실패");
                response.sendRedirect(request.getContextPath() + "/main.me");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
