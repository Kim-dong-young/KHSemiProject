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
}
