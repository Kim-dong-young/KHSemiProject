package com.kh.search.model.vo;

public class Tag {
	private String quizTag;
	private int quizNumber;
	private int count;

	public Tag() {
		super();
	}

	public Tag(String quizTag) {
		super();
		this.quizTag = quizTag;
	}

	public Tag(String quizTag, int count) {
		super();
		this.quizTag = quizTag;
		this.count = count;
	}

	public Tag(String quizTag, int quizNumber, int count) {
		super();
		this.quizTag = quizTag;
		this.quizNumber = quizNumber;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Tag [quizTag=" + quizTag + ", quizNumber=" + quizNumber + ", count=" + count + "]";
	}

	public String getQuizTag() {
		return quizTag;
	}

	public void setQuizTag(String quizTag) {
		this.quizTag = quizTag;
	}

	public int getQuizNumber() {
		return quizNumber;
	}

	public void setQuizNumber(int quizNumber) {
		this.quizNumber = quizNumber;
	}
}
