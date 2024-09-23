package com.kh.community.model.dao;

import static com.kh.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.PageInfo;
import com.kh.community.model.vo.Board;

public class BoardDao {
	
	private Properties prop = new Properties();
	
	public BoardDao() {
		super();
		String filePath = BoardDao.class.getResource("/db/sql/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectListCount(Connection conn) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<Board> selectList(Connection conn, PageInfo pageInfo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1,startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board(
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getString("COMMUNITY_TITLE"),
							rset.getString("TAB_NAME"),
							rset.getInt("COMMUNITY_VIEWCOUNT"),
							rset.getString("COMMUNITY_DATE"),
							rset.getString("MEMBER_NICKNAME"),
							rset.getInt("COMMENT_COUNT")
						);
				
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return boardList;
	}

	public Board selectBoard(Connection conn, int boardNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Board board = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board(
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getString("COMMUNITY_TITLE"),
							rset.getString("COMMUNITY_CONTENT"),
							rset.getInt("COMMUNITY_VIEWCOUNT"),
							rset.getString("COMMUNITY_DATE"),
							rset.getString("MEMBER_NICKNAME"),
							rset.getString("TAB_NAME"),
							rset.getInt("LIKE_COUNT"),
							rset.getInt("COMMENT_COUNT")
							
							/*
							 * int communityNo,
							 * String communityTitle, 
							 * String communityContent, 
							 * int communityViewcount,
							 * String communityDate, 
							 * String memberId, 
							 * String communityTab, 
							 * int likeCount, 
							 * int commentCount
							 */
						);
				System.out.println(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(conn);
		}
		
		return board;
	}
	
}
