package com.kh.member.model.vo;

import java.sql.Date;

public class Attendance {
	private int dailyNo;
	private Date dailyCheckDate;
	private String member;
	
	public Attendance() {
		super();
	}
	
	public Attendance(int dailyNo, Date dailyCheckDate, String member) {
		super();
		this.dailyNo = dailyNo;
		this.dailyCheckDate = dailyCheckDate;
		this.member = member;
	}

	public int getDailyNo() {
		return dailyNo;
	}

	public void setDailyNo(int dailyNo) {
		this.dailyNo = dailyNo;
	}

	public Date getDailyCheckDate() {
		return dailyCheckDate;
	}

	public void setDailyCheckDate(Date dailyCheckDate) {
		this.dailyCheckDate = dailyCheckDate;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Attendance [dailyNo=" + dailyNo + ", dailyCheckDate=" + dailyCheckDate + ", member=" + member + "]";
	}
	
	
}

