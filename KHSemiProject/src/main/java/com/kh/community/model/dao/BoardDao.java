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
import com.kh.community.model.vo.Category;
import com.kh.community.model.vo.Comment;

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
							rset.getInt("TAB_NUMBER"),
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
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board(
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getString("COMMUNITY_TITLE"),
							rset.getString("COMMUNITY_CONTENT"),
							rset.getInt("COMMUNITY_VIEWCOUNT"),
							rset.getString("COMMUNITY_DATE"),
							rset.getString("MEMBER_NICKNAME"),
							rset.getInt("MEMBER_NUMBER"),
							rset.getString("TAB_NAME"),
							rset.getInt("TAB_NUMBER"),
							rset.getInt("LIKE_COUNT")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

	public int countBoardComment(Connection conn, int boardNo) {
		int result = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("countBoardComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COMMENT_COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Comment> selectCommentList(Connection conn, PageInfo cPageInfo, int boardNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Comment> commentList = new ArrayList<>();
		
		String sql = prop.getProperty("selectCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (cPageInfo.getCurrentPage() - 1) * cPageInfo.getBoardLimit() + 1;
			int endRow = startRow + cPageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Comment comment = new Comment(
							rset.getInt("COMMUNITY_COMMENT_NUMBER"),
							rset.getInt("COMMUNITY_PARENT_NUMBER"),
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getString("MEMBER_NICKNAME"),
							rset.getInt("MEMBER_NUMBER"),
							rset.getString("COMMUNITY_COMMENT_CONTENT")
						);
				
				commentList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}

	public int increaseViewCount(Connection conn, int communityNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseViewCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Category> selectCategory(Connection conn) {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Category category = new Category();
				
				category.setTabNumber(rset.getInt("tab_number"));
				category.setTabName(rset.getString("tab_name"));
				
				categoryList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return categoryList;
	}
	
	public ArrayList<Category> selectUserCategory(Connection conn) {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectUserCategory");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Category category = new Category();
				
				category.setTabNumber(rset.getInt("tab_number"));
				category.setTabName(rset.getString("tab_name"));
				
				categoryList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return categoryList;
	}

	public int insertBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, b.getCommunityTitle());
			pstmt.setString(2, b.getCommunityContent());
			pstmt.setInt(3, Integer.parseInt(b.getMemberId()));
			pstmt.setInt(4, Integer.parseInt(b.getCommunityTab()));
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertComment(Connection conn, Comment comment) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommunityNo());
			pstmt.setInt(2, comment.getMemberNo());
			pstmt.setString(3, comment.getCommentContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteComment(Connection conn, int boardNo) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteCommunityLike(Connection conn, int boardNo) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCommunityLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBoard(Connection conn, int boardNo) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMemberComment(Connection conn, int commentNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMemberComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectBoardTabList(Connection conn, PageInfo pageInfo, int tabNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("selectBoardTabList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1, tabNo);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board(
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getString("COMMUNITY_TITLE"),
							rset.getString("TAB_NAME"),
							rset.getInt("TAB_NUMBER"),
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

	public int selectBoardTabListCount(Connection conn, int tabNo) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectBoardTabListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, tabNo);
			
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
	
	public int selectBoardPopListCount(Connection conn, int tabNo) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectBoardPopListCount");
		
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

	public ArrayList<Board> selectBoardPopList(Connection conn, PageInfo pageInfo, int tabNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("selectBoardPopList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board(
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getString("COMMUNITY_TITLE"),
							rset.getString("TAB_NAME"),
							rset.getInt("TAB_NUMBER"),
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
	
	public int selectLikeMember(Connection conn, int memberNo, int boardNo) {
		int result = 1;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectLikeMember");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			rset = pstmt.executeQuery();
			if(!rset.next()) { // 조회 결과가 없으면(= 해당 유저가 해당 게시글 좋아요를 안눌렀다면) 0
				result = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int increaseLike(Connection conn, int memberNo, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int countBoardLike(Connection conn, int boardNo) {
		int result = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("countBoardLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			switch(e.getErrorCode()) {
			case 1:
				return -1;
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectBoardTop5(Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("selectBoardTop5");
		
		try {
			pstmt = conn.prepareStatement(sql);
	
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board(
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getString("COMMUNITY_TITLE"),
							rset.getString("TAB_NAME"),
							rset.getInt("TAB_NUMBER"),
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
}
