package com.kh.createQuiz.controller;

import java.io.IOException;

import com.kh.createQuiz.model.vo.Problem;
import com.kh.createQuiz.service.CreateQuizServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class problemsController
 */
public class problemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public problemsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int PROBLEM_number = Integer.parseInt(request.getParameter("pno"));
//		String PROBLEM_content = request.getParameter("pcontent") ;
//		int PROBLEM_media_kind = Integer.parseInt(request.getParameter("pmk"));
//		String PROBLEM_media = request.getParameter("pmedia");
//		String PROBLEM_hint = request.getParameter("phint");
//		
//		Problem p = new Problem();
//		p.setPROBLEM_number(PROBLEM_number);
//		p.setPROBLEM_conment(PROBLEM_content);
//		p.setPROBLEM_media_kind(PROBLEM_media_kind);
//		p.setPROBLEM_media(PROBLEM_media);
//		p.setPROBLEM_hint(PROBLEM_hint);
//		
//		int result = new CreateQuizServiceImpl().insertProblems(p);
//		
//		response.getWriter().print(result);
//		System.out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
