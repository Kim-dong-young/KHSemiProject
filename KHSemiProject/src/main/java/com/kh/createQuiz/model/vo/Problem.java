package com.kh.createQuiz.model.vo;

public class Problem {
	private int PROBLEM_number;
	private String PROBLEM_content;
	private int PROBLEM_media_kind;
	private String PROBLEM_media;
	private String PROBLEM_hint;
	private int QUIZ_number;
	public Problem() {
		super();
	}
	public Problem(int pROBLEM_number, String pROBLEM_content, int pROBLEM_media_kind, String pROBLEM_media,
			String pROBLEM_hint, int qUIZ_number) {
		super();
		PROBLEM_number = pROBLEM_number;
		PROBLEM_content = pROBLEM_content;
		PROBLEM_media_kind = pROBLEM_media_kind;
		PROBLEM_media = pROBLEM_media;
		PROBLEM_hint = pROBLEM_hint;
		QUIZ_number = qUIZ_number;
	}
	public int getPROBLEM_number() {
		return PROBLEM_number;
	}
	public void setPROBLEM_number(int pROBLEM_number) {
		PROBLEM_number = pROBLEM_number;
	}
	public String getPROBLEM_content() {
		return PROBLEM_content;
	}
	public void setPROBLEM_content(String pROBLEM_content) {
		PROBLEM_content = pROBLEM_content;
	}
	public int getPROBLEM_media_kind() {
		return PROBLEM_media_kind;
	}
	public void setPROBLEM_media_kind(int pROBLEM_media_kind) {
		PROBLEM_media_kind = pROBLEM_media_kind;
	}
	public String getPROBLEM_media() {
		return PROBLEM_media;
	}
	public void setPROBLEM_media(String pROBLEM_media) {
		PROBLEM_media = pROBLEM_media;
	}
	public String getPROBLEM_hint() {
		return PROBLEM_hint;
	}
	public void setPROBLEM_hint(String pROBLEM_hint) {
		PROBLEM_hint = pROBLEM_hint;
	}
	public int getQUIZ_number() {
		return QUIZ_number;
	}
	public void setQUIZ_number(int qUIZ_number) {
		QUIZ_number = qUIZ_number;
	}
	@Override
	public String toString() {
		return "Problem [PROBLEM_number=" + PROBLEM_number + ", PROBLEM_content=" + PROBLEM_content
				+ ", PROBLEM_media_kind=" + PROBLEM_media_kind + ", PROBLEM_media=" + PROBLEM_media + ", PROBLEM_hint="
				+ PROBLEM_hint + ", QUIZ_number=" + QUIZ_number + "]";
	}

	
}
