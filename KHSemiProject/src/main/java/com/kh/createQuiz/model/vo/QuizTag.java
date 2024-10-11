package com.kh.createQuiz.model.vo;

public class QuizTag {
	private String tagName;
	private int quizNumber;
	
	public QuizTag() {
		super();
	}

	public QuizTag(String tagName, int quizNumber) {
		super();
		this.tagName = tagName;
		this.quizNumber = quizNumber;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getQuizNumber() {
		return quizNumber;
	}

	public void setQuizNumber(int quizNumber) {
		this.quizNumber = quizNumber;
	}

	@Override
	public String toString() {
		return "QuizTag [tagName=" + tagName + ", quizNumber=" + quizNumber + ", getTagName()=" + getTagName()
				+ ", getQuizNumber()=" + getQuizNumber() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
