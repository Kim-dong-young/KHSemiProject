package com.kh.search.model.vo;

public class Quiz {
	private int quiz_number;
	private String quiz_title;
	private String quiz_date;
	private String quiz_modify_date;
	private String member_number;
	private String category_number;
	public Quiz() {
		super();
	}
	public Quiz(int quiz_number, String quiz_title, String quiz_date, String quiz_modify_date, String member_number,
			String category_number) {
		super();
		this.quiz_number = quiz_number;
		this.quiz_title = quiz_title;
		this.quiz_date = quiz_date;
		this.quiz_modify_date = quiz_modify_date;
		this.member_number = member_number;
		this.category_number = category_number;
	}	
	@Override
	public String toString() {
		return "Quiz [quiz_number=" + quiz_number + ", quiz_title=" + quiz_title + ", quiz_date=" + quiz_date
				+ ", quiz_modify_date=" + quiz_modify_date + ", member_number=" + member_number + ", category_number="
				+ category_number + "]";
	}
	public int getQuiz_number() {
		return quiz_number;
	}
	public void setQuiz_number(int quiz_number) {
		this.quiz_number = quiz_number;
	}
	public String getQuiz_title() {
		return quiz_title;
	}
	public void setQuiz_title(String quiz_title) {
		this.quiz_title = quiz_title;
	}
	public String getQuiz_date() {
		return quiz_date;
	}
	public void setQuiz_date(String quiz_date) {
		this.quiz_date = quiz_date;
	}
	public String getQuiz_modify_date() {
		return quiz_modify_date;
	}
	public void setQuiz_modify_date(String quiz_modify_date) {
		this.quiz_modify_date = quiz_modify_date;
	}
	public String getMember_number() {
		return member_number;
	}
	public void setMember_number(String member_number) {
		this.member_number = member_number;
	}
	public String getCategory_number() {
		return category_number;
	}
	public void setCategory_number(String category_number) {
		this.category_number = category_number;
	}
	
	
}
