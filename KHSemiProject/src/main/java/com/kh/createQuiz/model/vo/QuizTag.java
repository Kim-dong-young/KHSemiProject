package com.kh.createQuiz.model.vo;

import java.util.Arrays;

public class QuizTag {
	private String tagName;
	private String[] tagList;
	private int quizNumber;
	
	public QuizTag() {
		super();
	}
	
	public QuizTag(String tagName, int quizNumber) {
		super();
		this.tagName = tagName;
		this.quizNumber = quizNumber;
	}

	public QuizTag(String tagName, String[] tagList, int quizNumber) {
		super();
		this.tagName = tagName;
		this.tagList = tagList;
		this.quizNumber = quizNumber;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String[] getTagList() {
		return tagList;
	}

	public void setTagList(String[] tagList) {
		this.tagList = tagList;
	}

	public int getQuizNumber() {
		return quizNumber;
	}

	public void setQuizNumber(int quizNumber) {
		this.quizNumber = quizNumber;
	}

	@Override
	public String toString() {
		return "QuizTag [tagName=" + tagName + ", tagList=" + Arrays.toString(tagList) + ", quizNumber=" + quizNumber
				+ "]";
	}

	
}
