package com.kh.community.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.kh.community.model.vo.Category;
import com.kh.community.service.BoardService;
import com.kh.member.model.vo.Member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardEnrollController
 */
public class BoardEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = ((Member)request.getSession().getAttribute("loginMember")).getMemberNo();
		
		ArrayList<Category> categoryList = new ArrayList<>();
		
		// 1번은 관리자
		if(memberNo == 1) {
			categoryList = new BoardService().selectCategory();
		} else {
			categoryList = new BoardService().selectUserCategory();
		}
		
		request.setAttribute("category", categoryList);
		
		request.setAttribute("pageName", "communityWritePage");
		request.setAttribute("optional", request.getAttribute("tabNo"));
		
		request.getRequestDispatcher("templates/communityWritePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
