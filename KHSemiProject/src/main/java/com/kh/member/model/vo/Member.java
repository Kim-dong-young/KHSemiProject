package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberNickName;
	private int exp;
	private String memberImg;
	private String joinDate;
	private int checkContinueCnt;
	private String status;
	private String introduce;
	private String memberemail;
	private String address;
	private String phone;
	
	public Member() {
		super();
	}

	public Member(int memberNo, String memberId, String memberPwd, String memberNickName, int exp, String memberImg,
			String joinDate, int checkContinueCnt, String status, String introduce, String memberemail , String address, String phone) {
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
		this.memberemail = memberemail;
		this.address = address;
		this.phone = phone;
	}

	public Member(String memberId, String memberPwd, String memberNickName) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNickName = memberNickName;
	}
	
	public Member(String memberId, String memberNickName, String memberemail, String address, String phone) {
		super();
		this.memberId = memberId;
		this.memberNickName = memberNickName;
		this.memberemail = memberemail;
		this.address = address;
		this.phone = phone;
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

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
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

	public String getEmail() {
		return memberemail;
	}

	public void setEmail(String memberemail) {
		this.memberemail = memberemail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd
				+ ", memberNickName=" + memberNickName + ", exp=" + exp + ", memberImg=" + memberImg + ", joinDate="
				+ joinDate + ", checkContinueCnt=" + checkContinueCnt + ", status=" + status + ", introduce="
				+ introduce + ", memberemail=" + memberemail + ", address=" + address + ", phone=" + phone + "]";
	}

	
	
	
}
