package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberNickName;
	private int exp;
	private String memberImg;
	private Date joinDate;
	private int checkContinueCnt;
	private String status;
	private String introduce;
	
	public Member() {
		super();
	}

	public Member(int memberNo, String memberId, String memberPwd, String memberNickName, int exp, String memberImg,
			Date joinDate, int checkContinueCnt, String status, String introduce) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNickName = memberNickName;
		this.exp = exp;
		this.memberImg = memberImg;
		this.joinDate = joinDate;
		this.checkContinueCnt = checkContinueCnt;
		this.status = status;
		this.introduce = introduce;
	}

	public Member(String memberId, String memberPwd, String memberNickName) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNickName = memberNickName;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getMemberImg() {
		return memberImg;
	}

	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public int getCheckContinueCnt() {
		return checkContinueCnt;
	}

	public void setCheckContinueCnt(int checkContinueCnt) {
		this.checkContinueCnt = checkContinueCnt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd
				+ ", memberNickName=" + memberNickName + ", exp=" + exp + ", memberImg=" + memberImg + ", joinDate="
				+ joinDate + ", checkContinueCnt=" + checkContinueCnt + ", status=" + status + ", introduce="
				+ introduce + "]";
	}
	
	
}
