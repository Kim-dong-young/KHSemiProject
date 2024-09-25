package com.kh.member.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Attendance;
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
		
		int result = mDao.attendanceCheck(conn, memberNo);
		
		if(result > 0) {
			return 0;
		} else {
			result = mDao.attendanceInsert(conn, memberNo);
			
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		
		close(conn);
		return result;
	}
	
	public int totalAttendance(int memberNo) {
		Connection conn = getConnection();	
		
		int result = new MemberDao().totalAttendance(conn, memberNo);
		
		close(conn);
		
		return result;
	}
	
	public Member resetAttend(Member loginMember) {
		Connection conn = getConnection();
		
		Member updateMem = null;
		MemberDao mDao = new MemberDao();
		
		int result1 = mDao.deforeAttendCheck(conn, loginMember); 		     // 전날 출석 체크
		int result2 = mDao.attendanceCheck(conn, loginMember.getMemberNo()); // 오늘 출석 체크
		if(result1 + result2 == 0) {
			int result3 = mDao.resetAttend(conn, loginMember);
			
			if(result3 > 0) {
				commit(conn);
				
				updateMem = new MemberDao().selectMember(conn, loginMember.getMemberId());
				return updateMem;
			} else {
				rollback(conn);
			}
		}
		
		return loginMember;
	}
}
