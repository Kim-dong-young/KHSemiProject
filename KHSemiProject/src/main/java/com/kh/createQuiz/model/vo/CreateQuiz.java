package com.kh.createQuiz.model.vo;

public class CreateQuiz {
	private int QUIZ_NUMBER;
	private String QUIZ_TITLE;
	private String QUIZ_DATE;
	private String QUIZ_MODIFY_DATE;
	private String QUIZ_EXPLANATION;
	private int MEMBER_NUMBER;
	private int CATEGORY_NUMBER;
	private String THUMBNAIL;
	
	public CreateQuiz() {
		super();
	}

	public CreateQuiz(int qUIZ_NUMBER, String qUIZ_TITLE, String qUIZ_DATE, String qUIZ_MODIFY_DATE,
			String qUIZ_EXPLANATION, int mEMBER_NUMBER, int cATEGORY_NUMBER, String tHUMBNAIL) {
		super();
		QUIZ_NUMBER = qUIZ_NUMBER;
		QUIZ_TITLE = qUIZ_TITLE;
		QUIZ_DATE = qUIZ_DATE;
		QUIZ_MODIFY_DATE = qUIZ_MODIFY_DATE;
		QUIZ_EXPLANATION = qUIZ_EXPLANATION;
		MEMBER_NUMBER = mEMBER_NUMBER;
		CATEGORY_NUMBER = cATEGORY_NUMBER;
		THUMBNAIL = tHUMBNAIL;
	}

	public int getQUIZ_NUMBER() {
		return QUIZ_NUMBER;
	}

	public void setQUIZ_NUMBER(int qUIZ_NUMBER) {
		QUIZ_NUMBER = qUIZ_NUMBER;
	}

	public String getQUIZ_TITLE() {
		return QUIZ_TITLE;
	}

	public void setQUIZ_TITLE(String qUIZ_TITLE) {
		QUIZ_TITLE = qUIZ_TITLE;
	}

	public String getQUIZ_DATE() {
		return QUIZ_DATE;
	}

	public void setQUIZ_DATE(String qUIZ_DATE) {
		QUIZ_DATE = qUIZ_DATE;
	}

	public String getQUIZ_MODIFY_DATE() {
		return QUIZ_MODIFY_DATE;
	}

	public void setQUIZ_MODIFY_DATE(String qUIZ_MODIFY_DATE) {
		QUIZ_MODIFY_DATE = qUIZ_MODIFY_DATE;
	}

	public String getQUIZ_EXPLANATION() {
		return QUIZ_EXPLANATION;
	}

	public void setQUIZ_EXPLANATION(String qUIZ_EXPLANATION) {
		QUIZ_EXPLANATION = qUIZ_EXPLANATION;
	}

	public int getMEMBER_NUMBER() {
		return MEMBER_NUMBER;
	}

	public void setMEMBER_NUMBER(int mEMBER_NUMBER) {
		MEMBER_NUMBER = mEMBER_NUMBER;
	}

	public int getCATEGORY_NUMBER() {
		return CATEGORY_NUMBER;
	}

	public void setCATEGORY_NUMBER(int cATEGORY_NUMBER) {
		CATEGORY_NUMBER = cATEGORY_NUMBER;
	}

	public String getTHUMBNAIL() {
		return THUMBNAIL;
	}

	public void setTHUMBNAIL(String tHUMBNAIL) {
		THUMBNAIL = tHUMBNAIL;
	}

	@Override
	public String toString() {
		return "CreateQuiz [QUIZ_NUMBER=" + QUIZ_NUMBER + ", QUIZ_TITLE=" + QUIZ_TITLE + ", QUIZ_DATE=" + QUIZ_DATE
				+ ", QUIZ_MODIFY_DATE=" + QUIZ_MODIFY_DATE + ", QUIZ_EXPLANATION=" + QUIZ_EXPLANATION
				+ ", MEMBER_NUMBER=" + MEMBER_NUMBER + ", CATEGORY_NUMBER=" + CATEGORY_NUMBER + ", THUMBNAIL="
				+ THUMBNAIL + "]";
	}

	
	
}
