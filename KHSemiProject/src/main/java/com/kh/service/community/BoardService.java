package com.kh.service.community;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.model.dao.community.BoardDao;

public class BoardService {

	public int selectListCount() {
		Connection conn = getConnection();
		int boardCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		return boardCount;
	}
	
}
