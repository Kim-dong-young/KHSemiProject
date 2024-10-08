package com.kh.createQuiz.model.vo;

public class Answer {
	private int ANSWER_number;
	private String ANSWER_content;
	private int PROBLEM_number;

	public Answer() {
		super();
	}

	public Answer(int aNSWER_number, String aNSWER_content, int pROBLEM_number) {
		super();
		ANSWER_number = aNSWER_number;
		ANSWER_content = aNSWER_content;
		PROBLEM_number = pROBLEM_number;
	}

	public int getANSWER_number() {
		return ANSWER_number;
	}

	public void setANSWER_number(int aNSWER_number) {
		ANSWER_number = aNSWER_number;
	}

	public String getANSWER_content() {
		return ANSWER_content;
	}

	public void setANSWER_content(String aNSWER_content) {
		ANSWER_content = aNSWER_content;
	}

	public int getPROBLEM_number() {
		return PROBLEM_number;
	}

	public void setPROBLEM_number(int pROBLEM_number) {
		PROBLEM_number = pROBLEM_number;
	}

	@Override
	public String toString() {
		return "Answer [ANSWER_number=" + ANSWER_number + ", ANSWER_content=" + ANSWER_content + ", PROBLEM_number="
				+ PROBLEM_number + "]";
	}

}
