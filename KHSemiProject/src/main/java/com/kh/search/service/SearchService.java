package com.kh.search.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.PageInfo;
import com.kh.common.ReportInfo;
import com.kh.search.model.dao.QuizDao;
import com.kh.search.model.vo.Quiz;
import com.kh.search.model.vo.Tag;

public class SearchService {

	public int selectQuizCount(int category, int search_type, String search_text, int orderby, ArrayList<String> tagList) {
		Connection conn = JDBCTemplate.getConnection();
		
		int quizCount = new QuizDao().selectQuizCount(conn, category, search_type, search_text, orderby, tagList);
		JDBCTemplate.close(conn);
		return quizCount;
	}

	public ArrayList<Quiz> selectQuiz(PageInfo pi, int category, int search_type, String search_text, int orderby, ArrayList<String> tagList) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Quiz> quiz = new QuizDao().selectQuiz(conn, pi, category, search_type, search_text, orderby, tagList);
		JDBCTemplate.close(conn);
		
		return quiz;
	}

	public ArrayList<Tag> selectTagList(String searchText) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Tag> list = new QuizDao().selectTagList(conn, searchText);
		JDBCTemplate.close(conn);
		return list;
	}
		
	public Quiz detailQuiz(int quiz_number) {
		Connection conn = getConnection();
		Quiz q = new QuizDao().detailQuiz(conn, quiz_number);
		JDBCTemplate.close(conn);
		return q;
	}

	public int quizViewCount(int num) {
		Connection conn = getConnection();
		int result = new QuizDao().quizViewCount(conn, num);
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Tag> TagArray(int num) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Tag> list = new QuizDao().TagArray(conn, num);
		JDBCTemplate.close(conn);
		return list;
	}

	public String quizRateAvg(int num) {
		Connection conn = JDBCTemplate.getConnection();
		String str = new QuizDao().quizRateAvg(conn, num);
		JDBCTemplate.close(conn);
		return str;
	}

	public ArrayList<Quiz> simularQuizList(ArrayList<Tag> tagArr) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Quiz> qArr = new QuizDao().simularQuizList(conn, tagArr);
		JDBCTemplate.close(conn);
		return qArr;
	}
	
	public ArrayList<Quiz> selectTopTenQuiz(String btnValue) {
		Connection conn = getConnection();
		ArrayList<Quiz> list = new QuizDao().selectTopTenQuiz(conn, btnValue);
		
		JDBCTemplate.close(conn);
		return list;
	}
		
    public int markInsert(int quiznum, int memberNum) {
        Connection conn = getConnection();
        int list = 0;
        if(!new QuizDao().markSelect(conn, quiznum, memberNum)) {
            list = new QuizDao().markInsert(conn, quiznum, memberNum);
            if(list > 0) {
                commit(conn);
                list = 1;
            } else {
                rollback(conn);
            }
        } else {
            list = new QuizDao().markDelete(conn, quiznum, memberNum);
            if(list > 0) {
                commit(conn);
                list = 2;
            } else {
                rollback(conn);
            }
        }
        
        
        
        
        JDBCTemplate.close(conn);
        return list;
    }

    public int markSelect(int quizNum, int memberNum) {
        Connection conn = getConnection();
        if(new QuizDao().markSelect(conn, quizNum, memberNum)) {
        	JDBCTemplate.close(conn);
            return 1;
        } else {
        	JDBCTemplate.close(conn);
            return 0;
        }
    }
	
	public ArrayList<Quiz> noSearchMyQuiz(int memberNo, String selectValue, String btnValue) {
		Connection conn = getConnection();
		ArrayList<Quiz> list = new QuizDao().noSearchMyQuiz(conn, memberNo, selectValue, btnValue);
		
		JDBCTemplate.close(conn);
		return list;
	}
		
	public ArrayList<Quiz> searchMyQuiz(int memberNo, String selectValue, String btnValue, String searchValue, String serSelValue) {
		Connection conn = getConnection();
		ArrayList<Quiz> list = new QuizDao().searchMyQuiz(conn, memberNo, selectValue, btnValue, searchValue, serSelValue);
		
		JDBCTemplate.close(conn);
		return list;
	}

	public int selectQuizMaker(int qNum) {
		Connection conn = getConnection();
		int result = new QuizDao().selectQuizMaker(conn, qNum);
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertReportQuiz(ReportInfo reportInfo) {
		Connection conn = getConnection();
		int result = 0;
		int selectResult = new QuizDao().selectReportQuiz(conn, reportInfo);
		
		if(selectResult < 1) { // 조회 결과가 없을 경우에만 신고 테이블에 삽입 ( 중복신고 방지 )
			result = new QuizDao().insertReportQuiz(conn, reportInfo);
		}
		
		close(conn);
		return result;
	}
}
