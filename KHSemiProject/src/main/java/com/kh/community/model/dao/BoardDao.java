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

	/*
	// 이전 댓글 검색 코드
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
	*/

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
			pstmt.setInt(4, comment.getCommentGroup());
			
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

	public int deleteMemberComment(Connection conn, Comment comment) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMemberComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommentNo());
			
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
	
	public int selectBoardPopListCount(Connection conn) {
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

	public ArrayList<Board> selectBoardPopList(Connection conn, PageInfo pageInfo) {
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

	public int searchListCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			
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

	public int searchBoardPopListCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchBoardPopListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			
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

	public int searchBoardTabListCount(Connection conn, int tabNo, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchBoardTabListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, tabNo);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			pstmt.setString(4, searchText);
			
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

	public ArrayList<Board> searchList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			
			pstmt.setInt(4,startRow);
			pstmt.setInt(5, endRow);
			
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

	public ArrayList<Board> searchBoardPopList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchBoardPopList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
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

	public ArrayList<Board> searchBoardTabList(Connection conn, PageInfo pageInfo, int tabNo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchBoardTabList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1, tabNo);
			
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			pstmt.setString(4, searchText);
			
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			
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

	public int searchTitleCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchTitleCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			
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

	public int searchTitlePopListCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchTitlePopListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			
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

	public int searchTitleTabListCount(Connection conn, int tabNo, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchTitleTabListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, tabNo);
			pstmt.setString(2, searchText);
			
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

	public ArrayList<Board> searchTitleList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchTitleList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			
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

	public ArrayList<Board> searchTitlePopList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchTitlePopList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			
			pstmt.setInt(2, startRow);
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

	public ArrayList<Board> searchTitleTabList(Connection conn, PageInfo pageInfo, int tabNo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchTitleTabList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1, tabNo);
			
			pstmt.setString(2, searchText);
			
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
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

	public int searchContentCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchContentCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			
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

	public int searchContentPopListCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchContentPopListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			
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

	public int searchContentTabListCount(Connection conn, int tabNo, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchContentTabListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, tabNo);
			pstmt.setString(2, searchText);
			
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

	public ArrayList<Board> searchContentList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchContentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			
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

	public ArrayList<Board> searchContentPopList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchContentPopList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			
			pstmt.setInt(2, startRow);
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

	public ArrayList<Board> searchContentTabList(Connection conn, PageInfo pageInfo, int tabNo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchContentTabList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1, tabNo);
			
			pstmt.setString(2, searchText);
			
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
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

	public int searchWriterCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchWriterCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			
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

	public int searchWriterPopListCount(Connection conn, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchWriterPopListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			
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

	public int searchWriterTabListCount(Connection conn, int tabNo, String searchText) {
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("searchWriterTabListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, tabNo);
			pstmt.setString(2, searchText);
			
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

	public ArrayList<Board> searchWriterList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchWriterList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			
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

	public ArrayList<Board> searchWriterPopList(Connection conn, PageInfo pageInfo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchWriterPopList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			
			pstmt.setInt(2, startRow);
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

	public ArrayList<Board> searchWritertTabList(Connection conn, PageInfo pageInfo, int tabNo, String searchText) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Board> boardList = new ArrayList<>();
		
		String sql = prop.getProperty("searchWritertTabList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pageInfo.getCurrentPage() - 1) * pageInfo.getBoardLimit() + 1;
			int endRow = startRow + pageInfo.getBoardLimit() - 1;
			
			pstmt.setInt(1, tabNo);
			
			pstmt.setString(2, searchText);
			
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
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

	public int insertCommentReply(Connection conn, Comment comment) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertCommentReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommentParentNo());
			pstmt.setInt(2, comment.getCommunityNo());
			pstmt.setInt(3, comment.getMemberNo());
			pstmt.setString(4, comment.getCommentContent());
			pstmt.setInt(5, comment.getCommentGroup());
			pstmt.setInt(6, comment.getCommentDepth());
			pstmt.setInt(7, comment.getCommentOrder());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Comment> selectCommentList(Connection conn, PageInfo cPageInfo, int boardNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Comment> replyList = new ArrayList<>();
		
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
							rset.getString("COMMUNITY_COMMENT_CONTENT"),
							rset.getInt("COMMENT_GROUP"),
							rset.getInt("COMMENT_DEPTH"),
							rset.getInt("COMMENT_ORDER"),
							rset.getInt("COMMENT_CHILD_COUNT"),
							rset.getString("COMMENT_STATUS")
						);
				
				replyList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return replyList;
	}

	public Comment selectCommentDisplayInfo(Connection conn, int commentNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		Comment comment = null;
		
		String sql = prop.getProperty("selectCommentDisplayInfo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,commentNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				comment = new Comment(
							rset.getInt("COMMENT_GROUP"),
							rset.getInt("COMMENT_DEPTH"),
							rset.getInt("COMMENT_ORDER"),
							rset.getInt("COMMENT_CHILD_COUNT")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return comment;
	}

	public int updateParentOrder(Connection conn, Comment comment) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateParentOrder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommentNo());
			pstmt.setInt(2, comment.getCommentGroup());
			pstmt.setInt(3, comment.getCommentOrder());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateParentCount(Connection conn, Comment comment) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateParentCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommentNo());
			pstmt.setInt(2, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectMaxCommentGroupNo(Connection conn, int communityNo) {
		int result = -1;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectMaxCommentGroupNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, communityNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("MAX_GROUP_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int selectCommentNo(Connection conn) {
		int result = -1;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectCommentNo");
		
		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("SEQ_NO");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public Comment selectComment(Connection conn, int commentNo) {
		Comment comment = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectComment");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, commentNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				comment = new Comment(
							rset.getInt("COMMUNITY_COMMENT_NUMBER"),
							rset.getInt("COMMUNITY_PARENT_NUMBER"),
							rset.getInt("COMMUNITY_NUMBER"),
							rset.getInt("MEMBER_NUMBER"),
							rset.getString("COMMUNITY_COMMENT_CONTENT"),
							rset.getString("COMMUNITY_COMMENT_DATE"),
							rset.getInt("COMMENT_GROUP"),
							rset.getInt("COMMENT_DEPTH"),
							rset.getInt("COMMENT_ORDER"),
							rset.getInt("COMMENT_CHILD_COUNT"),
							rset.getString("COMMENT_STATUS")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return comment;
	}

	public int selectCommentChildCount(Connection conn, int commentNo) {
		int result = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectCommentChildCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentNo);

			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getInt("COMMENT_CHILD_COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public int updateCommentStatus(Connection conn, Comment comment) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateCommentStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCommentOrder(Connection conn, Comment comment) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateCommentOrder");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommentGroup());
			pstmt.setInt(2, comment.getCommentOrder());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateChildCount(Connection conn, Comment comment) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateChildCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteParentComment(Connection conn, Comment comment) {
		int result = -1;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteParentComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getCommentGroup());
			pstmt.setInt(2, comment.getCommunityNo());
			pstmt.setInt(3, comment.getCommentGroup());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
}
