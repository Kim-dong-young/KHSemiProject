package com.kh.member.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import com.kh.member.model.vo.Quest;


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
	
	public Member updatePwdMember(String memberId, String memberPwd, String updatePwd) {
		Connection conn = getConnection();
		int result = new MemberDao().updatePwdMember(conn, memberId, memberPwd, updatePwd);
		
		Member updateMember = null;
		if (result > 0) {
			commit(conn);
			
			updateMember = new MemberDao().selectMember(conn, memberId);
		} else {
			rollback(conn);
		}
		
		return updateMember;
	}
	
	public int deleteMember(String memberId, String memberPwd) {
		Connection conn = getConnection();
		int result = new MemberDao().deleteMember(conn, memberId, memberPwd);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public Member selectMember(int memberNo) {
		Connection conn = getConnection();	
		Member updateMember = new MemberDao().selectMember(conn, memberNo);
		
		close(conn);
		
		return updateMember;
	}
	
	public int attendanceCheck(int memberNo) {
		Connection conn = getConnection();	
		MemberDao mDao = new MemberDao();
		
		int result = mDao.attendanceCheck(conn, memberNo);

		if(result == 0) {
			int result1 = mDao.attendanceInsert(conn, memberNo);
			int result2 = mDao.updateContinueCount(conn, memberNo);
			
			if(result1 > 0 && result2 > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
			
			close(conn);
			return result1 * result2;
		} 
		
		return 0;
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


	public Member updateMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMember = null;
		if(result > 0) {
			commit(conn);
			
			updateMember = new MemberDao().selectMember(conn, m.getMemberId());
		}else {
			rollback(conn);
		}
		return updateMember;
	}


	public Member updateProfile(Member p) {
		Connection conn = getConnection();
		int result = new MemberDao().updateProfile(conn, p);
		
		Member updateProfile = null;
		if(result > 0) {
			commit(conn);
			
			updateProfile = new MemberDao().selectMember(conn, p.getMemberId());
		}else {
			rollback(conn);
		}
		return null;
	}

	public ArrayList<Quest> getDailyQuest(Member loginMember) {
		Connection conn = getConnection();
		MemberDao mDao = new MemberDao();
		ArrayList<Quest> questList = mDao.selectDailyQuest(conn, loginMember); // 유저의 일일퀘스트를 조회한다.
		
		if(questList.isEmpty()) { // 퀘스트가 없다면 퀘스트를 부여
			int result = 0; // 트랜잭션 커밋 & 롤백 용 변수
			int dailyQuestLimit = 3; // 일일퀘스트의 개수
			
			// 퀘스트는 랜덤으로 부여된다.
			// 랜덤값의 범위를 조회하기 위해 퀘스트 목록의 크기를 가져온다.
			int questCount = mDao.selectQuestCount(conn);
			
			// IntStream.rangeClosed( startIndex, endIndex ) => startIndex ~ endIndex 값 까지 IntStream 반환
			// Boxed 는 원시 타입 스트림을 객체 타입 스트림으로 변환
			// list는 객체형만 담을수 있어서 변환해줘야 한다.
			// toList()로 변환시, immutableCollection 반환, 수정이 불가능하다.
			// collect(Collectors.toList())로 변환해줘야 한다.
			List<Integer> randNum = IntStream.rangeClosed(1, questCount).boxed().collect(Collectors.toList());
			
			// 배열을 랜덤한 순서로 섞음 ( Fisher-Yates shuffle )
			// 이미 뽑은 배열 요소는 다시 선택하지 않음 => 무작위 추출시 무한루프 걱정 X, O(n)의 시간복잡도
			Collections.shuffle(randNum);
			
			// 1 ~ questCount까지 생성된 배열을 무작위로 섞고 일일퀘스트 개수만큼 추출
			// => 중복 퀘스트를 선택할 일이 없다
			List<Integer> selectedQuest = randNum.subList(0, dailyQuestLimit);
			
			for(int questNo : selectedQuest) { // 선택된 퀘스트를 퀘스트 테이블에 추가
				result += mDao.insertDailyQuest(conn, loginMember, questNo);
			}
			
			if(result == dailyQuestLimit) { // dailyQuestLimit 개수만큼 일일퀘스트가 추가 되었다면 커밋
				commit(conn);
				questList = mDao.selectDailyQuest(conn, loginMember); // 유저의 일일퀘스트를 조회한다.
			} else {
				rollback(conn);
			}
		}

		close(conn);
		return questList;
	}
}
