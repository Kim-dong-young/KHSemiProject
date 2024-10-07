package com.kh.member.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.kh.community.model.vo.Attachment;
import com.kh.community.model.vo.Board;
import com.kh.community.service.BoardService;
import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberProfileController
 */
public class MemberProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(JakartaServletFileUpload.isMultipartContent(request)) {
			// 1.파일 용량 제한(byte)
			int fileMaxSize = 1024 * 1024 * 10; // 10MB
			int requestMaxSize = 1024 * 1024 * 20; // 20MB
			
			// 2. 전달된 파일을 저장시킬 폴더경로 가져오기
			String savePath = request.getServletContext().getRealPath("/static/img/userProfile");
			
			// 3.DiskFileItemFactory(파일을 임시로 저장) 객체 생성
			DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
			
			// JakartaServletFileUpload = http 요청으로 들어온 파일데이터 파싱 -> 개별 FileItem 객체로 변환
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
			
			upload.setFileSizeMax(fileMaxSize);
			upload.setSizeMax(requestMaxSize);
			
			// 요청(request)으로부터 파일 아이템 파싱
			List<FileItem> formItems = upload.parseRequest(request);
			
			// 추가할 데이터
			Board b = new Board();
			ArrayList<Attachment> list = new ArrayList<>();
			Boolean thumbnailChecked = false;
			
			for(FileItem item : formItems) {
				if(item.isFormField()) { // 일반 파라미터일 경우
					switch(item.getFieldName()) {
					case "userNo":
						b.setMemberNo(Integer.parseInt(item.getString(Charset.forName("utf-8"))));
						break;
					case "tab":
						b.setCommunityTab(item.getString(Charset.forName("utf-8")));
						break;
					case "title":
						b.setCommunityTitle(item.getString(Charset.forName("utf-8")));
						break;
					case "content":
						b.setCommunityContent(item.getString(Charset.forName("utf-8")));
						break;
					}
				}else { // 이미지 파일일 경우
					String originName = item.getName();
					
					if(originName.length() > 0) { // 파일을 업로드 했을 경우
						// 고유한 파일명 생성
						String tmpNmae = "boardFile_" + System.currentTimeMillis();
						// 파일 형식 ex1) jpg , png 추출
						String type = originName.substring(originName.lastIndexOf("."));
						// DB에 저장할 파일명
						String changeName = tmpNmae + type;
						
						File f = new File(savePath, changeName);
						item.write(f.toPath()); // 지정된 경로에 파일 업로드
						
						Attachment at = new Attachment();
						at.setOriginName(originName);
						at.setChangeName(changeName);
						at.setFilePath("static/img/userProfile/");
						
						if(!thumbnailChecked) { // 첫번째로 들어온 이미지 파일을 썸네일로 설정
							at.setFileLevel(2); // 2 : 썸네일 이미지
							thumbnailChecked = true;
						}else {
							at.setFileLevel(1); // 1 : 기본 이미지
						}
						
						list.add(at);
					}
				}
			}
			
			int result = new BoardService().insertBoard(b,list);
		}
		
		
		String memberId = request.getParameter("memberId");
		String memberNickName = request.getParameter("memberNickName");
		String Introduce = request.getParameter("Introduce");
		
		Member p = new Member(memberId,memberNickName,Introduce);
		p.setMemberId(memberId);
		p.setMemberNickName(memberNickName);
		p.setIntroduce(Introduce);
		
		Member updateProfile = new MemberService().updateProfile(p);
		
		if(updateProfile == null) {
			request.setAttribute("errorMsg", "프로필 수정에 실패하였습니다.");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateProfile);
			session.setAttribute("alertMsg", "프로필 수정에 성공하였습니다.");
		}
		response.sendRedirect(request.getContextPath() + "/userset.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
