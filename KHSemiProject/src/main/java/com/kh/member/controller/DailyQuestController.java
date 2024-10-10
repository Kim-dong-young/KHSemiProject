package com.kh.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;
import com.kh.member.model.vo.Quest;
import com.kh.member.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DailyQuestController
 */
public class DailyQuestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DailyQuestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		
		ArrayList<Quest> questList = new MemberService().getDailyQuest(loginMember);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(questList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
