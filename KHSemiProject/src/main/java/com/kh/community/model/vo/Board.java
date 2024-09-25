package com.kh.community.model.vo;

public class Board {
	private int communityNo;
	private String communityTitle;
	private String communityContent;
	private String communityTab;
	private int communityViewcount;
	private String communityDate;
	private String memberId;
	private int memberNo;
	private int commentCount;
	private int likeCount;
	
	public Board() {
		super();
	}

	public Board(String memberId, String communityTab, String communityTitle, String communityContent) {
		super();
		this.memberId = memberId;
		this.communityTab = communityTab;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
	}

	public Board(int communityNo, String communityTitle, String communityTab, int communityViewcount,
			String communityDate, String memberId, int commentCount) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityTab = communityTab;
		this.communityViewcount = communityViewcount;
		this.communityDate = communityDate;
		this.memberId = memberId;
		this.commentCount = commentCount;
	}
	
	public Board(int communityNo,String communityTitle, String communityContent, int communityViewcount,
			String communityDate, String memberId, int memberNo, String communityTab, int likeCount) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityViewcount = communityViewcount;
		this.communityDate = communityDate;
		this.memberId = memberId;
		this.memberNo = memberNo;
		this.communityTab = communityTab;
		this.likeCount = likeCount;
	}

	public Board(int communityNo, String communityTitle, String communityContent, String communityTab,
			int communityViewcount, String communityDate, String memberId, int memberNo, int commentCount,
			int likeCount) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityTab = communityTab;
		this.communityViewcount = communityViewcount;
		this.communityDate = communityDate;
		this.memberId = memberId;
		this.memberNo = memberNo;
		this.commentCount = commentCount;
		this.likeCount = likeCount;
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

	public String getCommunityTab() {
		return communityTab;
	}

	public void setCommunityTab(String communityTab) {
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public String toString() {
		return "Board [communityNo=" + communityNo + ", communityTitle=" + communityTitle + ", communityContent="
				+ communityContent + ", communityTab=" + communityTab + ", communityViewcount=" + communityViewcount
				+ ", communityDate=" + communityDate + ", memberId=" + memberId + ", memberNo=" + memberNo
				+ ", commentCount=" + commentCount + ", likeCount=" + likeCount + "]";
	}

}
