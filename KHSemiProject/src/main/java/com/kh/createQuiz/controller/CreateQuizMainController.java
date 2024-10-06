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

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String changeName = null;
    	
    	//enctype이 multipart/form-data로 전송이 되었는지 체크
		if(JakartaServletFileUpload.isMultipartContent(request)) {
			System.out.println("성공");
			//1. 파일용량제한(byte)
			int fileMaxSize = 1024 * 1024 * 10; // 10mb
			int requestMaxSize = 1024 * 1024 * 20; // 20mb
			
			//2. 전달된 파일을 저장시킬 폴더경로가져오기
			String savePath = request.getServletContext().getRealPath("/static/img/THUMBNAIL/");
			
			//3.DiskFileItemFactory(파일을 임시로 저장) 객체 생성
			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
			
			//JakartaServletFileUpload http요청으로 들어온 파일데이터를 파싱 -> 개별FileItem 객체로 변환
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
			
			upload.setFileSizeMax(fileMaxSize);
			upload.setSizeMax(requestMaxSize);
			
			//요청(request)으로부터 파일아이템 파싱
			List<FileItem> formItems = upload.parseRequest(request);

			//추가할 데이터
			CreateQuiz cQuiz = new CreateQuiz();
			
			cQuiz.setMEMBER_NUMBER(((Member)request.getSession().getAttribute("loginMember")).getMemberNo());
			
			//반복문을 통해 파일과 파라미터 정보획득
			for(FileItem item : formItems) {
				System.out.println(item);
				if(item.isFormField()) { //일반파라미터
					switch(item.getFieldName()) {
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
				} else {
					String originName = item.getName(); //업로드 요청한 파일명(오리지널 파일명)
					
					if(originName.length() > 0) { //파일을 업로드 했을 때
						//고유한 파일명 생성
						String tmpName = "quiz_" + System.currentTimeMillis();
						String type = originName.substring(originName.lastIndexOf("."));
						changeName = tmpName + type; //서버에 저장할 파일명
						
						File f = new File(savePath, changeName);
						item.write(f.toPath()); //지정한 경로에 파일 업로드
						
						cQuiz.setTHUMBNAIL("static/img/THUMBNAIL/" + changeName);
					}
				}
			}
			
			int result = quizService.createQuiz(cQuiz);
			
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "사진게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/quiz.cr");
			} else { 
				if(cQuiz != null) {
					 new File(savePath + changeName).delete();
				 }
				 request.setAttribute("alertMsg", "사진게시글 작성 실패");
				 response.sendRedirect(request.getContextPath() + "/main.me");
			}
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
