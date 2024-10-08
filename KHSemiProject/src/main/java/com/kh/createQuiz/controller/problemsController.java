package com.kh.createQuiz.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.kh.createQuiz.model.vo.Problem;
import com.kh.createQuiz.service.CreateQuizServiceImpl;
import com.kh.search.model.vo.Quiz;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class problemsController
 */
public class problemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public problemsController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String changeName = null;
		response.setContentType("application/json; charset=UTF-8");

		if (JakartaServletFileUpload.isMultipartContent(request)) {
			// 1. 파일 용량 제한 설정
			int fileMaxSize = 1024 * 1024 * 10; // 10MB
			int requestMaxSize = 1024 * 1024 * 20; // 20MB

			// 2. 파일 저장 경로
			String savePath = request.getServletContext().getRealPath("/static/img/problems/");

			// 3. 파일 업로드를 위한 DiskFileItemFactory 생성
			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
			
			upload.setFileSizeMax(fileMaxSize);
			upload.setSizeMax(requestMaxSize);

			// 4. 요청에서 파일과 데이터를 파싱
			List<FileItem> formItems = upload.parseRequest(request);

			Problem p = new Problem();
			p.setQUIZ_number(((Quiz) request.getAttribute("insertQuiz")).getQuiz_number());
			
			//반복문을 통해 파일과 파라미터 정보획득
			for (FileItem item : formItems) {
				if (item.isFormField()) {
					switch (item.getFieldName()) {
					case "pno":
						p.setPROBLEM_number(Integer.parseInt(item.getString()));
						break;
					case "pcontent":
						p.setPROBLEM_content(item.getString());
						break;
					case "pmk":
						p.setPROBLEM_media_kind(Integer.parseInt(item.getString()));
						break;
					case "phint":
						p.setPROBLEM_hint(item.getString());
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
							item.write(f.toPath()); // 파일 저장
							p.setPROBLEM_media("static/img/problems/" + changeName);
						} catch (Exception e) {
							e.printStackTrace();
							if (changeName != null) {
								new File(savePath + changeName).delete(); // 파일 삭제
							}
						}
					}
				}
			}

			// 문제 저장
			int result = new CreateQuizServiceImpl().insertProblems(p);

			// 응답 처리
			if (result > 0) {
				
			} else {
				if (changeName != null) {
					new File(savePath + changeName).delete();
				}
		
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
