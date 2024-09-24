package com.kh.member.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;


public class MemberService {
	public Member loginMember(String memberId, String memberPwd) {
		Connection conn = getConnection();
		Member m = new MemberDao().loginMember(conn, memberId, memberPwd);
		
		close(conn);
		return m;
	}
	

	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updateMember = null;
		if (result > 0) {
			commit(conn);
			
			updateMember = new MemberDao().selectMember(conn, userId);
		} else {
			rollback(conn);
		}
		
		return updateMember;
	}
	
	public int attendanceCheck(int memberNo) {
		Connection conn = getConnection();	
		MemberDao mDao = new MemberDao();
		
		int result1 = mDao.attendanceCheck(conn, memberNo);
		int result2;
		
		if(result1 > 0) {
			return 0;
		} else {
			result2 = mDao.attendanceInsert(conn, memberNo);
			
			if(result2 > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		
		close(conn);
		return result2;
	}
}
