package com.kh.model.vo;

public class Board {
	private int communityNo;
	private String communityTitle;
	private String communityContent;
	private int communityTab;
	private int communityViewcount;
	private String communityDate;
	private int memberId;
	
	public Board() {
		super();
	}
	
	public Board(int communityNo, String communityTitle, String communityContent, int communityTab,
			int communityViewcount, String communityDate, int memberId) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityTab = communityTab;
		this.communityViewcount = communityViewcount;
		this.communityDate = communityDate;
		this.memberId = memberId;
	}
	
	public int getCommunityNo() {
		return communityNo;
	}
	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}
	public String getCommunityTitle() {
		return communityTitle;
	}
	public void setCommunityTitle(String communityTitle) {
		this.communityTitle = communityTitle;
	}
	public String getCommunityContent() {
		return communityContent;
	}
	public void setCommunityContent(String communityContent) {
		this.communityContent = communityContent;
	}
	public int getCommunityTab() {
		return communityTab;
	}
	public void setCommunityTab(int communityTab) {
		this.communityTab = communityTab;
	}
	public int getCommunityViewcount() {
		return communityViewcount;
	}
	public void setCommunityViewcount(int communityViewcount) {
		this.communityViewcount = communityViewcount;
	}
	public String getCommunityDate() {
		return communityDate;
	}
	public void setCommunityDate(String communityDate) {
		this.communityDate = communityDate;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "Board [communityNo=" + communityNo + ", communityTitle=" + communityTitle + ", communityContent="
				+ communityContent + ", communityTab=" + communityTab + ", communityViewcount=" + communityViewcount
				+ ", communityDate=" + communityDate + ", memberId=" + memberId + "]";
	}
	
}
