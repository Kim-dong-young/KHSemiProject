package com.kh.common;

public class ReportInfo {
	private int reportNo;
	/*
	 * reportEncNo = 신고 사유 번호
	 * 	  1: 홍보/도배
	 *    2: 음란물
	 *    3: 불법적 내용
	 *    4: 욕설
	 *    5: 혐오발언
	 *    6: 사칭글
	 *    7: 괴롭힘
	 *    8: 기타
	 */
	private int reportEncNo; 	
	private String reportReason;
	private String reportDate;
	private int memberNo; // 신고한 유저의 번호
	private int reportedMemberNo; // 피신고자(신고당한 유저)의 번호
	private int quizNo;
	private int quizCommentNo;
	private int communityNo;
	private int communityCommentNo;
	
	public ReportInfo() {
		super();
	}
	
	public ReportInfo(int reportNo, int reportEncNo, String reportReason, String reportDate, int memberNo,
			int reportedMemberNo, int quizNo, int quizCommentNo, int communityNo, int communityCommentNo) {
		super();
		this.reportNo = reportNo;
		this.reportEncNo = reportEncNo;
		this.reportReason = reportReason;
		this.reportDate = reportDate;
		this.memberNo = memberNo;
		this.reportedMemberNo = reportedMemberNo;
		this.quizNo = quizNo;
		this.quizCommentNo = quizCommentNo;
		this.communityNo = communityNo;
		this.communityCommentNo = communityCommentNo;
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public int getReportEncNo() {
		return reportEncNo;
	}

	public void setReportEncNo(int reportEncNo) {
		this.reportEncNo = reportEncNo;
	}

	public String getReportReason() {
		return reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getReportedMemberNo() {
		return reportedMemberNo;
	}

	public void setReportedMemberNo(int reportedMemberNo) {
		this.reportedMemberNo = reportedMemberNo;
	}

	public int getQuizNo() {
		return quizNo;
	}

	public void setQuizNo(int quizNo) {
		this.quizNo = quizNo;
	}

	public int getQuizCommentNo() {
		return quizCommentNo;
	}

	public void setQuizCommentNo(int quizCommentNo) {
		this.quizCommentNo = quizCommentNo;
	}

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public int getCommunityCommentNo() {
		return communityCommentNo;
	}

	public void setCommunityCommentNo(int communityCommentNo) {
		this.communityCommentNo = communityCommentNo;
	}

	@Override
	public String toString() {
		return "ReportInfo [reportNo=" + reportNo + ", reportEncNo=" + reportEncNo + ", reportReason=" + reportReason
				+ ", reportDate=" + reportDate + ", memberNo=" + memberNo + ", reportedMemberNo=" + reportedMemberNo
				+ ", quizNo=" + quizNo + ", quizCommentNo=" + quizCommentNo + ", communityNo=" + communityNo
				+ ", communityCommentNo=" + communityCommentNo + "]";
	}
	
}
