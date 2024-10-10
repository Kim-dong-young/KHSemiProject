package com.kh.playQuiz.model.vo;

public class Problem {
	private int quiz_number;
	private int problem_number;
	private String problem_content;
	private int problem_media_kind;
	private String problem_hint;
	private int Ptime;
	
	public int getPtime() {
		return Ptime;
	}
	public void setPtime(int ptime) {
		Ptime = ptime;
	}
	public Problem() {
		super();
	}
	public Problem(int quiz_number, int problem_number, String problem_content, int problem_media_kind,
			String problem_media, String problem_hint) {
		super();
		this.quiz_number = quiz_number;
		this.problem_number = problem_number;
		this.problem_content = problem_content;
		this.problem_media_kind = problem_media_kind;
		this.problem_hint = problem_hint;
	}
	public int getQuiz_number() {
		return quiz_number;
	}
	public void setQuiz_number(int quiz_number) {
		this.quiz_number = quiz_number;
	}
	public int getProblem_number() {
		return problem_number;
	}
	public void setProblem_number(int problem_number) {
		this.problem_number = problem_number;
	}
	public String getProblem_content() {
		return problem_content;
	}
	public void setProblem_content(String problem_content) {
		this.problem_content = problem_content;
	}
	public int getProblem_media_kind() {
		return problem_media_kind;
	}
	public void setProblem_media_kind(int problem_media_kind) {
		this.problem_media_kind = problem_media_kind;
	}
	public String getProblem_hint() {
		return problem_hint;
	}
	public void setProblem_hint(String problem_hint) {
		this.problem_hint = problem_hint;
	}
	@Override
	public String toString() {
		return "Problem [quiz_number=" + quiz_number + ", problem_number=" + problem_number + ", problem_content="
				+ problem_content + ", problem_media_kind=" + problem_media_kind + ", problem_hint=" + problem_hint
				+ ", Ptime=" + Ptime + "]";
	}

	
}
