package com.kh.createQuiz.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.kh.createQuiz.model.vo.Answer;
import com.kh.createQuiz.model.vo.Problem;
import com.kh.createQuiz.service.CreateQuizServiceImpl;
import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

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
		
		int memberNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();

		if (JakartaServletFileUpload.isMultipartContent(request)) {
			int fileMaxSize = 1024 * 1024 * 10; // 10MB
			int requestMaxSize = 1024 * 1024 * 20; // 20MB

			String savePath = request.getServletContext().getRealPath("/static/img/problems/");
			
			//폴더 없으면 생성
			File saveDir = new File(savePath);
			if(!saveDir.exists()) {
				saveDir.mkdirs(); // 경로에 폴더 생성
			}
			
			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
			upload.setFileSizeMax(fileMaxSize);
			upload.setSizeMax(requestMaxSize);

			List<FileItem> formItems = upload.parseRequest(request);

			Problem pr = new Problem();
			Answer a = new Answer();

			Problem pr2 = new Problem();
			Answer a2 = new Answer();

			Problem pr3 = new Problem();
			Answer a3 = new Answer();

			Problem pr4 = new Problem();
			Answer a4 = new Answer();

			Problem pr5 = new Problem();
			Answer a5 = new Answer();

			for (FileItem item : formItems) {
				if (item.isFormField()) {
					switch (item.getFieldName()) {
					case "quiz_number":
						pr.setQUIZ_number(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						pr2.setQUIZ_number(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						pr3.setQUIZ_number(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						pr4.setQUIZ_number(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						pr5.setQUIZ_number(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						break;
					case "pcontent-1":
						pr.setPROBLEM_content(item.getString(Charset.forName("utf-8")));
						break;
					case "ptime-1":
						pr.setPtime(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						break;
					case "phint-1":
						pr.setPROBLEM_hint(item.getString(Charset.forName("utf-8")));
						break;
					case "panswer-1":
						a.setANSWER_content(item.getString(Charset.forName("utf-8")));
						break;
					case "pcontent-2":
						pr2.setPROBLEM_content(item.getString(Charset.forName("utf-8")));
						break;
					case "ptime-2":
						pr2.setPtime(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						break;
					case "phint-2":
						pr2.setPROBLEM_hint(item.getString(Charset.forName("utf-8")));
						break;
					case "panswer-2":
						a2.setANSWER_content(item.getString(Charset.forName("utf-8")));
						break;
					case "pcontent-3":
						pr3.setPROBLEM_content(item.getString(Charset.forName("utf-8")));
						break;
					case "ptime-3":
						pr3.setPtime(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						break;
					case "phint-3":
						pr3.setPROBLEM_hint(item.getString(Charset.forName("utf-8")));
						break;
					case "panswer-3":
						a3.setANSWER_content(item.getString(Charset.forName("utf-8")));
						break;
					case "pcontent-4":
						pr4.setPROBLEM_content(item.getString(Charset.forName("utf-8")));
						break;
					case "ptime-4":
						pr4.setPtime(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						break;
					case "phint-4":
						pr4.setPROBLEM_hint(item.getString(Charset.forName("utf-8")));
						break;
					case "panswer-4":
						a4.setANSWER_content(item.getString(Charset.forName("utf-8")));
						break;
					case "pcontent-5":
						pr5.setPROBLEM_content(item.getString(Charset.forName("utf-8")));
						break;
					case "ptime-5":
						pr5.setPtime(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						break;
					case "phint-5":
						pr5.setPROBLEM_hint(item.getString(Charset.forName("utf-8")));
						break;
					case "panswer-5":
						a5.setANSWER_content(item.getString(Charset.forName("utf-8")));
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
							switch (item.getFieldName()) {
							case "file-1":
								pr.setPROBLEM_media("static/img/problems/" + changeName);
								break;
							case "file-2":
								pr2.setPROBLEM_media("static/img/problems/" + changeName);
								break;
							case "file-3":
								pr3.setPROBLEM_media("static/img/problems/" + changeName);
								break;
							case "file-4":
								pr4.setPROBLEM_media("static/img/problems/" + changeName);
								break;
							case "file-5":
								pr5.setPROBLEM_media("static/img/problems/" + changeName);
								break;
							}

						} catch (Exception e) {
							e.printStackTrace();
							if (changeName != null) {
								new File(savePath + changeName).delete();
							}
						}
					}
				}

			}

			int problemResult = new CreateQuizServiceImpl().insertProblems(pr, pr2, pr3, pr4, pr5,
					a, a2, a3, a4, a5);

			if (problemResult > 0) {
				request.getSession().setAttribute("alertMsg", "문제 작성 완료");
				response.sendRedirect(request.getContextPath() + "/main.me");
				
				int questNo = 7; // 7 : 문제 만들어보기
				int isDone = new CreateQuizServiceImpl().checkDailyQuest(memberNo, questNo);
				
				// MEMBER_QUEST_SUCCESS 값 0은 퀘스트완료 X / 보상 획득 X
				// MEMBER_QUEST_SUCCESS 값 1은 퀘스트완료 O / 보상 획득 X
				// MEMBER_QUEST_SUCCESS 값 2는 퀘스트완료 O / 보상 획득 O
				if(isDone == 0) { // 퀘스트 깬적 없을 경우 ( 오늘 첫 로그인 )
					// 로그인 하면 퀘스트 성공
					new CreateQuizServiceImpl().successQuest(memberNo, questNo);
				}
				
				int exp = 300; // 퀴즈 제작시 경험치 부여
				new CreateQuizServiceImpl().updateMemberExp(memberNo, exp);
				
				request.getSession().setAttribute("loginMember", new MemberService().selectMember(memberNo));
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
