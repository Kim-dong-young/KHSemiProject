package com.kh.community.model.vo;

public class Comment {
	private int commentNo;
	private int commentParentNo;
	private int communityNo;
	private int memberNo;
	private String memberName;
	private String commentContent;
	private String commentDate;
	private int commentGroup;
	private int commentDepth;
	private int commentOrder;
	private int commentChildCount;
	private String commentStatus;
	
	public Comment() {
		super();
	}
	
	// 부모 댓글의 정보를 가져오는 생성자
	public Comment(int commentGroup, int commentDepth, int commentOrder, int commentChildCount) {
		super();
		this.commentGroup = commentGroup;
		this.commentDepth = commentDepth;
		this.commentOrder = commentOrder;
		this.commentChildCount = commentChildCount;
	}
	
	public Comment(int commentNo, int commentParentNo, int communityNo, String memberName, int memberNo,
			String commentContent) {
		super();
		this.commentNo = commentNo;
		this.commentParentNo = commentParentNo;
		this.communityNo = communityNo;
		this.memberName = memberName;
		this.memberNo = memberNo;
		this.commentContent = commentContent;
	}

	public Comment(int commentNo, int commentParentNo, int communityNo, int memberNo, String memberName,
			String commentContent, String commentDate) {
		super();
		this.commentNo = commentNo;
		this.commentParentNo = commentParentNo;
		this.communityNo = communityNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
	}

	public Comment(int commentNo, int commentParentNo, int communityNo, int memberNo, String commentContent,
			String commentDate, int commentGroup, int commentDepth, int commentOrder, int commentChildCount,
			String commentStatus) {
		super();
		this.commentNo = commentNo;
		this.commentParentNo = commentParentNo;
		this.communityNo = communityNo;
		this.memberNo = memberNo;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.commentGroup = commentGroup;
		this.commentDepth = commentDepth;
		this.commentOrder = commentOrder;
		this.commentChildCount = commentChildCount;
		this.commentStatus = commentStatus;
	}

	public Comment(int commentNo, int commentParentNo, int communityNo, int memberNo, String memberName,
			String commentContent, String commentDate, int commentGroup, int commentDepth, int commentOrder,
			int commentChildCount, String commentStatus) {
		super();
		this.commentNo = commentNo;
		this.commentParentNo = commentParentNo;
		this.communityNo = communityNo;
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.commentGroup = commentGroup;
		this.commentDepth = commentDepth;
		this.commentOrder = commentOrder;
		this.commentChildCount = commentChildCount;
		this.commentStatus = commentStatus;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getCommentParentNo() {
		return commentParentNo;
	}

	public void setCommentParentNo(int commentParentNo) {
		this.commentParentNo = commentParentNo;
	}

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public int getCommentGroup() {
		return commentGroup;
	}

	public void setCommentGroup(int commentGroup) {
		this.commentGroup = commentGroup;
	}

	public int getCommentDepth() {
		return commentDepth;
	}

	public void setCommentDepth(int commentDepth) {
		this.commentDepth = commentDepth;
	}

	public int getCommentOrder() {
		return commentOrder;
	}

	public void setCommentOrder(int commentOrder) {
		this.commentOrder = commentOrder;
	}

	public int getCommentChildCount() {
		return commentChildCount;
	}

	public void setCommentChildCount(int commentChildCount) {
		this.commentChildCount = commentChildCount;
	}

	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentParentNo=" + commentParentNo + ", communityNo="
				+ communityNo + ", memberNo=" + memberNo + ", memberName=" + memberName + ", commentContent="
				+ commentContent + ", commentDate=" + commentDate + ", commentGroup=" + commentGroup + ", commentDepth="
				+ commentDepth + ", commentOrder=" + commentOrder + ", commentChildCount=" + commentChildCount
				+ ", commentStatus=" + commentStatus + "]";
	}
	
	
}
