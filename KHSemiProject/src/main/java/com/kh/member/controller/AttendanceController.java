package com.kh.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.kh.member.model.vo.Attendance;
import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class AttendanceController
 */
public class AttendanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("MemberNo"));		
		int atCheck  = new MemberService().attendanceCheck(memberNo);
		int updateTotalAt = new MemberService().totalAttendance(memberNo);
				
		Member updateMem = new MemberService().selectMember(memberNo);
		
		HttpSession session = request.getSession();
		if(atCheck == 0) {
			session.setAttribute("alertMsg", "이미 출석을 하셨습니다.");
		} else {
			session.setAttribute("alertMsg", "출석 완료");	
			session.setAttribute("loginMember", updateMem);
			session.setAttribute("totalAt", updateTotalAt);
			
			int questNo = 10; // 10 : 출석버튼 누르기
			int isDone = new MemberService().checkDailyQuest(memberNo, questNo);
			
			// MEMBER_QUEST_SUCCESS 값 0은 퀘스트완료 X / 보상 획득 X
			// MEMBER_QUEST_SUCCESS 값 1은 퀘스트완료 O / 보상 획득 X
			// MEMBER_QUEST_SUCCESS 값 2는 퀘스트완료 O / 보상 획득 O
			if(isDone == 0) { // 퀘스트 깬적 없을 경우 ( 오늘 첫 로그인 )
				// 로그인 하면 퀘스트 성공
				new MemberService().successQuest(memberNo, questNo);
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/main.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
