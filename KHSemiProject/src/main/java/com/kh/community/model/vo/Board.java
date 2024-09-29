package com.kh.community.model.vo;

public class Board {
	private int communityNo;
	private String communityTitle;
	private String communityContent;
	private String communityTab;
	private int communityTabNo;
	private int communityViewcount;
	private String communityDate;
	private String memberId;
	private int memberNo;
	private int commentCount;
	private int likeCount;
	
	public Board() {
		super();
	}
	
	// 게시글 쓸 때, 쓴 게시글 정보 불러오는 생성자
	public Board(String memberId, String communityTab, String communityTitle, String communityContent) {
		super();
		this.memberId = memberId;
		this.communityTab = communityTab;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
	}
	
	// 실시간 인기글 상위5개 게시물 불러오는 생성자
	public Board(int communityNo, String communityTitle, String communityTab, int communityTabNo, String memberId,
			int commentCount) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityTab = communityTab;
		this.communityTabNo = communityTabNo;
		this.memberId = memberId;
		this.commentCount = commentCount;
	}
	
	// 게시글 목록 불러오는 생성자
	public Board(int communityNo, String communityTitle, String communityTab, int communityTabNo,
			int communityViewcount, String communityDate, String memberId, int commentCount) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityTab = communityTab;
		this.communityTabNo = communityTabNo;
		this.communityViewcount = communityViewcount;
		this.communityDate = communityDate;
		this.memberId = memberId;
		this.commentCount = commentCount;
	}

	// 게시글 정보 불러오는 생성자
	public Board(int communityNo,String communityTitle, String communityContent, int communityViewcount,
			String communityDate, String memberId, int memberNo, String communityTab, int communityTabNo, int likeCount) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityViewcount = communityViewcount;
		this.communityDate = communityDate;
		this.memberId = memberId;
		this.memberNo = memberNo;
		this.communityTab = communityTab;
		this.communityTabNo = communityTabNo;
		this.likeCount = likeCount;
	}

	// 전체 생성자
	public Board(int communityNo, String communityTitle, String communityContent, String communityTab,
			int communityTabNo, int communityViewcount, String communityDate, String memberId, int memberNo,
			int commentCount, int likeCount) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityTab = communityTab;
		this.communityTabNo = communityTabNo;
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

	public int getCommunityTabNo() {
		return communityTabNo;
	}

	public void setCommunityTabNo(int communityTabNo) {
		this.communityTabNo = communityTabNo;
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
				+ communityContent + ", communityTab=" + communityTab + ", communityTabNo=" + communityTabNo
				+ ", communityViewcount=" + communityViewcount + ", communityDate=" + communityDate + ", memberId="
				+ memberId + ", memberNo=" + memberNo + ", commentCount=" + commentCount + ", likeCount=" + likeCount
				+ "]";
	}

}
