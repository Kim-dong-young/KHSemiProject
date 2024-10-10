package com.kh.createQuiz.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.kh.createQuiz.model.vo.Answer;
import com.kh.createQuiz.model.vo.Problem;
import com.kh.createQuiz.service.CreateQuizServiceImpl;
import com.kh.search.model.vo.Quiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class problemsController
 */
@MultipartConfig
public class problemsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public problemsController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("/templates/CreateQuiz.jsp").forward(request, response);
        String changeName = null;
        response.setContentType("application/json; charset=UTF-8");

        if (JakartaServletFileUpload.isMultipartContent(request)) {
            int fileMaxSize = 1024 * 1024 * 10; // 10MB
            int requestMaxSize = 1024 * 1024 * 20; // 20MB

            String savePath = request.getServletContext().getRealPath("/static/img/problems/");

            DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
            JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
            upload.setFileSizeMax(fileMaxSize);
            upload.setSizeMax(requestMaxSize);

            List<FileItem> formItems = upload.parseRequest(request);

            Problem p = new Problem();
            Answer a = new Answer();

            Quiz quiz = (Quiz) request.getAttribute("insertQuiz");
            if (quiz != null) {
                p.setQUIZ_number(quiz.getQuiz_number());
            } else {
                System.out.println("Quiz attribute is null");
                response.sendRedirect(request.getContextPath());
                return;
            }

            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                    case "pno":
                        p.setPROBLEM_number(Integer.parseInt(item.getString()));
                        break;
                    case "pcontent":
                        p.setPROBLEM_content(item.getString());
                        break;
//                    case "pmk":
//                        p.setPROBLEM_media_kind(Integer.parseInt(item.getString()));
//                        break;
                    case "phint":
                        p.setPROBLEM_hint(item.getString());
                        break;
                    case "panswer":
                        a.setANSWER_content(item.getString());
                        break;
                    }
                } else {
                    String originName = item.getName();
                    if (originName.length() > 0) {
                        String tmpName = "problem_" + System.currentTimeMillis();
                        String type = originName.substring(originName.lastIndexOf("."));
                        changeName = tmpName + type;

                        File f = new File(savePath, changeName);
                        try {
                            item.write(f.toPath());
                            p.setPROBLEM_media("static/img/problems/" + changeName);
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (changeName != null) {
                                new File(savePath + changeName).delete();
                            }
                        }
                    }
                }
            }

            int problemResult = new CreateQuizServiceImpl().insertProblems(p);
            int answerResult = new CreateQuizServiceImpl().insertAnswers(a);

            if (problemResult > 0 && answerResult > 0) {
                request.setAttribute("alertMsg", "문제 작성 완료");
                response.sendRedirect(request.getContextPath() + "/problems.co");
            } else {
                if (changeName != null) {
                    new File(savePath + changeName).delete();
                }
                request.setAttribute("alertMsg", "문제 작성 실패");
                response.sendRedirect(request.getContextPath() + "/main.me");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
