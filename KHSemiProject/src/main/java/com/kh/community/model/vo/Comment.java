package com.kh.community.model.vo;

public class Comment {
	private int commentNo;
	private int commentParentNo;
	private int communityNo;
	private int memberNo;
	private String memberName;
	private String commentContent;
	private String commentDate;
	
	public Comment() {
		super();
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

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentParentNo=" + commentParentNo + ", communityNo="
				+ communityNo + ", memberNo=" + memberNo + ", memberName=" + memberName + ", commentContent="
				+ commentContent + ", commentDate=" + commentDate + "]";
	}
	
	
}
