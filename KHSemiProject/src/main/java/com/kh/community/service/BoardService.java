package com.kh.community.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.PageInfo;
import com.kh.community.model.dao.BoardDao;
import com.kh.community.model.vo.Board;
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
		Board board = new BoardDao().selectBoard(conn, boardNo);
		
		close(conn);
		return board;
	}

	public int countBoardComment(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDao().countBoardComment(conn, boardNo);
		
		close(conn);
		return result;
	}

	public ArrayList<Comment> selectCommentList(int boardNo) {
		Connection conn = getConnection();
		ArrayList<Comment> commentList = new BoardDao().selectCommentList(conn, boardNo);
		
		close(conn);
		return commentList;
	}
	
}
