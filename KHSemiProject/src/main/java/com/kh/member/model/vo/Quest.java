package com.kh.member.model.vo;

public class Quest {
	private int memberQuestNo;
	private int questSuccess;
	private String questDate;
	private int memberNo;
	private int questNo;
	private String questContent;
	
	public Quest() {
		super();
	}

	public Quest(int memberQuestNo, int questSuccess, String questDate, int memberNo, int questNo,
			String questContent) {
		super();
		this.memberQuestNo = memberQuestNo;
		this.questSuccess = questSuccess;
		this.questDate = questDate;
		this.memberNo = memberNo;
		this.questNo = questNo;
		this.questContent = questContent;
	}

	public int getMemberQuestNo() {
		return memberQuestNo;
	}

	public void setMemberQuestNo(int memberQuestNo) {
		this.memberQuestNo = memberQuestNo;
	}

	public int getQuestSuccess() {
		return questSuccess;
	}

	public void setQuestSuccess(int questSuccess) {
		this.questSuccess = questSuccess;
	}

	public String getQuestDate() {
		return questDate;
	}

	public void setQuestDate(String questDate) {
		this.questDate = questDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getQuestNo() {
		return questNo;
	}

	public void setQuestNo(int questNo) {
		this.questNo = questNo;
	}

	public String getQuestContent() {
		return questContent;
	}

	public void setQuestContent(String questContent) {
		this.questContent = questContent;
	}

	@Override
	public String toString() {
		return "Quest [memberQuestNo=" + memberQuestNo + ", questSuccess=" + questSuccess + ", questDate=" + questDate
				+ ", memberNo=" + memberNo + ", questNo=" + questNo + ", questContent=" + questContent + "]";
	}
	
}
