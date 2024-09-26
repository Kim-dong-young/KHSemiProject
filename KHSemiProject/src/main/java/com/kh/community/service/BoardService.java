package com.kh.community.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.PageInfo;
import com.kh.community.model.dao.BoardDao;
import com.kh.community.model.vo.Board;
import com.kh.community.model.vo.Category;
import com.kh.community.model.vo.Comment;

public class BoardService {

	public int selectListCount() {
		Connection conn = getConnection();
		int boardCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		return boardCount;
	}

	public ArrayList<Board> selectList(PageInfo pageInfo) {
		Connection conn = getConnection();
		ArrayList<Board> boardList = new BoardDao().selectList(conn, pageInfo);
		
		close(conn);
		return boardList;
	}

	public Board selectBoard(int boardNo) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		Board board = bDao.selectBoard(conn, boardNo);
		
		if(board != null) {
			int result = bDao.increaseViewCount(conn, board.getCommunityNo());
			
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		
		close(conn);
		return board;
	}

	public int countBoardComment(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDao().countBoardComment(conn, boardNo);
		
		close(conn);
		return result;
	}

	public ArrayList<Comment> selectCommentList(PageInfo cPageInfo, int boardNo) {
		Connection conn = getConnection();
		ArrayList<Comment> commentList = new BoardDao().selectCommentList(conn, cPageInfo, boardNo);
		
		close(conn);
		return commentList;
	}

	public ArrayList<Category> selectCategory() {
		Connection conn = getConnection();
		ArrayList<Category> categoryList = new BoardDao().selectCategory(conn);
		
		close(conn);
		return categoryList;
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().insertBoard(conn,b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int insertComment(Comment comment) {
		Connection conn = getConnection();
		int result = new BoardDao().insertComment(conn, comment);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public boolean deleteBoard(int boardNo) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		Boolean isSuccess = false;
		
		int result1 = bDao.deleteComment(conn, boardNo);
		int result2 = bDao.deleteCommunityLike(conn, boardNo);
		int result3 = bDao.deleteBoard(conn, boardNo);
		
		if(result1 > -1 && result2 > -1 && result3 > -1) {
			commit(conn);
			isSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		return isSuccess;
	}

}
