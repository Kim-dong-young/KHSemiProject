package com.kh.common;

import java.util.HashSet;
import java.util.Random;

public class DailyQuestTemplate {
	public static final int dailyQuestLimit = 3;
	
	/**
	 * @param questRange : 랜덤으로 뽑을 숫자의 범위
	 * @return 중복없는 1 이상 questRange 이하 숫자가 dailyQuestLimit개 담긴 HashSet<Integer> 
	 * 만약 dailyQuestLimit보다 작은 숫자를 넣는다면 null 리턴
	 * @apiNote dailyQuestLimit는 기본 3으로 설정되어있음.
	 */
	public static HashSet<Integer> getRandomQuestNum(int questRange){
		if(questRange < dailyQuestLimit) {
			return null;
		}
		// 퀘스트는 랜덤으로 부여된다.
		// 랜덤값의 범위를 조회하기 위해 퀘스트 목록의 크기를 가져온다.
		HashSet<Integer> questNum = new HashSet<>();
		HashSet<Integer> result = new HashSet<>();
		
		// HashSet에 퀘스트 번호를 넣는다. ( 중복 추출 방지 )
		for(int i=1; i <= questRange; i++) {
			questNum.add(i);
		}
		
		// HashSet에서 랜덤 번호를 꺼내오고, HashSet에서 값 제거
		for(int i=0; i < dailyQuestLimit; i++) {
			int randNum = new Random().nextInt(questNum.size()) + 1;
			result.add(randNum);
			questNum.remove(randNum);
		}
		
		return result;
	}
	
	public static boolean checkQuest(int questNo) {
		
		switch(questNo) {
		case 1: // 퀴즈 1개 완료하기
			break;
		case 2: // 로그인 하기
			break;
		case 3: // 댓글 1개 달기
			break;
		case 4: // 커뮤니티 글 작성
			break;
		case 5: // 경험치 100 획득
			break;
		case 6: // 문제 1개 맞추기
			break;
		case 7: // 문제 만들어보기
			break;
		case 8: // 북마크하기
			break;
		case 9: // 공유하기
			break;
		case 10: // 출석버튼 누르기
			break;
		}
		
		return false;
	}
}
