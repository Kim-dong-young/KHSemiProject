package com.kh.member.service;

import static com.kh.common.DailyQuestTemplate.dailyQuestLimit;
import static com.kh.common.DailyQuestTemplate.getRandomQuestNum;
import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import com.kh.member.model.vo.Quest;


public class MemberService {
	private MemberDao mDao = new MemberDao();
	
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
		
		close(conn);
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
		
		close(conn);
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
		
		close(conn);
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
		
		close(conn);
		
		return updateProfile;
	}
	
	public int initDailyQuest(Member loginMember) {
		Connection conn = getConnection();
		MemberDao mDao = new MemberDao(); 
		int questCount = mDao.countMemberQuest(conn, loginMember);
		
		if(questCount < 1) { // 멤버가 가진 퀘스트가 없을 경우
			int result = 0; // 트랜잭션 커밋 & 롤백 용 변수
			
			// 퀘스트는 랜덤으로 부여된다.
			// 랜덤값의 범위를 조회하기 위해 퀘스트 목록의 크기를 가져온다.
			int questRange = mDao.selectQuestCount(conn);
			HashSet<Integer> questNum = getRandomQuestNum(questRange);
			
			result = mDao.insertDailyQuest(conn, loginMember, questNum);
			
			if(result == dailyQuestLimit) { // dailyQuestLimit 개수만큼 일일퀘스트가 추가 되었다면 커밋
				commit(conn);
				questCount = result; // 추가된 개수 == 유저가 가지고 있는 퀘스트 개수
			} else {
				rollback(conn);
			}
		}
		
		close(conn);
		return questCount;
	}
	
	public int updateDailyQuest(Member loginMember) {
		Connection conn = getConnection();
		MemberDao mDao = new MemberDao(); 
		int result1 = 0;
		int result2 = 0;
		
		// 하루가 지났는지 확인후, 지났다면 다시 퀘스트를 부여한다.
		Date questDate = mDao.getQuestDate(conn, loginMember);
		Date currentDate = new Date(System.currentTimeMillis());
		
        // 1. Calendar 객체를 사용하여 년, 월, 일을 추출
        Calendar questCal = Calendar.getInstance();
        questCal.setTime(questDate);

        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);

        // 2. 년, 월, 일을 비교
        if (currentCal.get(Calendar.YEAR) == questCal.get(Calendar.YEAR) && // 하루가 지났다면 퀘스트 삭제 후 부여 ( 일자로 비교 )
            currentCal.get(Calendar.DAY_OF_YEAR) - questCal.get(Calendar.DAY_OF_YEAR) >= 1) {
        	
        	int questRange = mDao.selectQuestCount(conn);
			HashSet<Integer> questNum = getRandomQuestNum(questRange);
			
			result1 = mDao.deleteMemberQuest(conn, loginMember);
			result2 = mDao.insertDailyQuest(conn, loginMember, questNum);
			
			// 삭제가 성공하고, dailyQuestLimit 개수만큼 일일퀘스트가 추가 되었다면 커밋
			if(result1 > 0 && result2 == dailyQuestLimit) {
				commit(conn);
			} else {
				rollback(conn);
			}
        }
        
        close(conn);
        return result1 * result2;
	}
	
	public ArrayList<Quest> getDailyQuest(Member loginMember) {
		Connection conn = getConnection();
		ArrayList<Quest> questList = new MemberDao().selectDailyQuest(conn, loginMember); // 유저의 일일퀘스트를 조회한다.

		close(conn);
		return questList;
	}


	public int playedRecode(int memberNo) {
		Connection conn = getConnection();
		int result = new MemberDao().playedRecode(conn, memberNo);
		
		close(conn);
		return result;
	}


	public int successQuest(int memberNo, int questNo) {
		Connection conn = getConnection();
		int result = new MemberDao().successQuest(conn, memberNo, questNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}


	public int checkDailyQuest(int memberNo, int questNo) {
		Connection conn = getConnection();
		int result = new MemberDao().checkDailyQuest(conn, memberNo, questNo);
		
		close(conn);
		return result;
	}


	public int updateMemberExp(int memberNo, int questNo, int exp) {
		Connection conn = getConnection();
		int result1 = mDao.updateMemberExp(conn, memberNo, exp);
		int result2 = mDao.doneDailyQuest(conn, memberNo, questNo);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result1 * result2;
	}
	
	public int attendanceRate(Member m) {
		Connection conn = getConnection();
		int result = 0;
		
		String sysdate = mDao.selectSysdate(conn);
		
		int totalResult;
		
		if(sysdate.equals(m.getJoinDate())) {
			totalResult = 1;
		} else {
			totalResult = mDao.totalDate(conn, m.getMemberNo());
		}
		
		int attendanceResult = mDao.attendanceDate(conn, m.getMemberNo());
		
		if(totalResult > 0 && attendanceResult > 0) {
			double i = (double)attendanceResult / totalResult; 
			result = (int)(i * 100);
		} else {
			double i = (double)attendanceResult / 1;
			result = (int)(i * 100);
		}
		close(conn);
		return result;
	}
	
	public int correctRate(int memberNo) {
		Connection conn = getConnection();
		int result = 0;
		
		int totalResult = mDao.totalQuiz1(conn, memberNo);
		int correctResult = mDao.correctQuiz(conn, memberNo);
		
		if(totalResult > 0 && correctResult > 0) {
			double i = (double)correctResult / totalResult; 
			result = (int)(i * 100);

		}
		close(conn);
		return result;
	}
	
	public int playedQuiz(int memberNo) {
		Connection conn = getConnection();
		int result = 0;
		
		int totalResult = mDao.totalQuiz(conn);
		int playedResult = mDao.playedQuiz(conn, memberNo);
		
		if(totalResult > 0 && playedResult > 0) {
			double i = (double)playedResult / totalResult; 
			result = (int)(i * 100);

		}
		
		close(conn);
		return result;
	}
	
	public String idCheck(String checkId) {
		Connection conn = getConnection();
		int result = mDao.checkId(conn, checkId);
		
		String yesOrNo;
		
		if(result > 0) {
			yesOrNo = "NNNNN";
		} else {
			yesOrNo = "NNNNY";
		}
		
		close(conn);
		return yesOrNo;
	}
}
