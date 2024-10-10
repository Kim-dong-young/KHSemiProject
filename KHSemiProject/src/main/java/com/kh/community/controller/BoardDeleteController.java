package com.kh.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.kh.community.model.vo.Attachment;
import com.kh.community.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardDeleteController
 */
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bService = new BoardService();
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		int cPage = Integer.parseInt(request.getParameter("cpage"));
		int cmtPage = Integer.parseInt(request.getParameter("comment"));

		/*	삭제 순서
		 *  
		 *  우선 게시물에 달린 사진을 서버 경로에서도 삭제해야만 한다.
		 *  
		 * 	1. 게시글에 달린 댓글부터 삭제한다
		 * 	2. 게시글에 달린 좋아요 정보도 삭제한다
		 *  3. 게시글을 삭제한다.
		 *  4. 위의 트랜잭션이 성공했다면, 게시글에 달린 사진도 실제 서버에서 삭제한다.
		 */
		
		ArrayList<Attachment> attachList = bService.selectAttachmentList(boardNo);
		
		boolean isSuccess = bService.deleteBoard(boardNo);
		
		if(isSuccess) {
			
			if(!attachList.isEmpty()) {
				for(Attachment at : attachList) {
					String filePath = request.getServletContext().getRealPath("/" + at.getFilePath() + at.getChangeName());
					File file = new File( filePath );
					
					if(file.exists()) {
						file.delete();
					}
				}
			}
			
			response.sendRedirect("community?cpage=" + cPage);
		} else {
			request.setAttribute("errorMsg", "삭제에 실패하였습니다.");
			response.sendRedirect("board?cpage=" + cPage + "&no="+ boardNo + "&comment=" + cmtPage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
