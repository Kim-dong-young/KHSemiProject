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
	
	public ArrayList<Category> selectUserCategory() {
		Connection conn = getConnection();
		ArrayList<Category> categoryList = new BoardDao().selectUserCategory(conn);
		
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

	public int deleteMemberComment(int commentNo) {
		Connection conn = getConnection();
		int result = new BoardDao().deleteMemberComment(conn, commentNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public ArrayList<Board> selectBoardTabList(PageInfo pageInfo, int tabNo) {
		Connection conn = getConnection();
		ArrayList<Board> boardList;
		
		if(tabNo == 0) {
			boardList = new BoardDao().selectBoardPopList(conn, pageInfo);
		} else {
			boardList = new BoardDao().selectBoardTabList(conn, pageInfo ,tabNo);
		}
		
		close(conn);
		return boardList;
	}

	public int selectBoardTabListCount(int tabNo) {
		Connection conn = getConnection();
		int boardCount;
		
		if(tabNo == 0) {
			boardCount = new BoardDao().selectBoardPopListCount(conn);
		} else {
			boardCount = new BoardDao().selectBoardTabListCount(conn, tabNo);
		}

		close(conn);
		return boardCount;
	}

	public int increaseLike(int memberNo, int boardNo) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		// 조회 결과가 없으면(= 해당 유저가 해당 게시글 좋아요를 안눌렀다면) result1 = 0
		int result1 = bDao.selectLikeMember(conn, memberNo, boardNo);
		int result2 = bDao.increaseLike(conn, memberNo, boardNo);
		int likeCount = 0;
		
		if(result1 == 0 && result2 > 0) {
			commit(conn);
			likeCount = bDao.countBoardLike(conn, boardNo);
		} else if( result2 == -1 ){
			rollback(conn);
			likeCount = result2;
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		return likeCount;
	}

	public ArrayList<Board> selectBoardTop5() {
		Connection conn = getConnection();
		ArrayList<Board> boardList = new BoardDao().selectBoardTop5(conn);

		close(conn);
		return boardList;
	}

	public int searchListCount(String searchText) {
		Connection conn = getConnection();
		int boardCount = new BoardDao().searchListCount(conn, searchText);
		
		close(conn);
		return boardCount;
	}

	public int searchBoardTabCount(int tabNo, String searchText) {
		Connection conn = getConnection();
		int boardCount;
		
		if(tabNo == 0) {
			boardCount = new BoardDao().searchBoardPopListCount(conn, searchText);
		} else {
			boardCount = new BoardDao().searchBoardTabListCount(conn, tabNo, searchText);
		}

		close(conn);
		return boardCount;
	}

	public ArrayList<Board> searchList(PageInfo pageInfo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList = new BoardDao().searchList(conn, pageInfo, searchText);
		
		close(conn);
		return boardList;
	}

	public ArrayList<Board> searchBoardTabList(PageInfo pageInfo, int tabNo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList;
		
		if(tabNo == 0) {
			boardList = new BoardDao().searchBoardPopList(conn, pageInfo, searchText);
		} else {
			boardList = new BoardDao().searchBoardTabList(conn, pageInfo ,tabNo, searchText);
		}
		
		close(conn);
		return boardList;
	}

	public int searchTitleCount(String searchText) {
		Connection conn = getConnection();
		int boardCount = new BoardDao().searchTitleCount(conn, searchText);
		
		close(conn);
		return boardCount;
	}

	public int searchTitleTabCount(int tabNo, String searchText) {
		Connection conn = getConnection();
		int boardCount;
		
		if(tabNo == 0) {
			boardCount = new BoardDao().searchTitlePopListCount(conn, searchText);
		} else {
			boardCount = new BoardDao().searchTitleTabListCount(conn, tabNo, searchText);
		}

		close(conn);
		return boardCount;
	}

	public ArrayList<Board> searchTitleList(PageInfo pageInfo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList = new BoardDao().searchTitleList(conn, pageInfo, searchText);
		
		close(conn);
		return boardList;
	}

	public ArrayList<Board> searchTitleTabList(PageInfo pageInfo, int tabNo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList;
		
		if(tabNo == 0) {
			boardList = new BoardDao().searchTitlePopList(conn, pageInfo, searchText);
		} else {
			boardList = new BoardDao().searchTitleTabList(conn, pageInfo ,tabNo, searchText);
		}
		
		close(conn);
		return boardList;
	}

	public int searchContentCount(String searchText) {
		Connection conn = getConnection();
		int boardCount = new BoardDao().searchContentCount(conn, searchText);
		
		close(conn);
		return boardCount;
	}

	public int searchContentTabCount(int tabNo, String searchText) {
		Connection conn = getConnection();
		int boardCount;
		
		if(tabNo == 0) {
			boardCount = new BoardDao().searchContentPopListCount(conn, searchText);
		} else {
			boardCount = new BoardDao().searchContentTabListCount(conn, tabNo, searchText);
		}

		close(conn);
		return boardCount;
	}

	public ArrayList<Board> searchContentList(PageInfo pageInfo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList = new BoardDao().searchContentList(conn, pageInfo, searchText);
		
		close(conn);
		return boardList;
	}

	public ArrayList<Board> searchContentTabList(PageInfo pageInfo, int tabNo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList;
		
		if(tabNo == 0) {
			boardList = new BoardDao().searchContentPopList(conn, pageInfo, searchText);
		} else {
			boardList = new BoardDao().searchContentTabList(conn, pageInfo ,tabNo, searchText);
		}
		
		close(conn);
		return boardList;
	}

	public int searchWriterCount(String searchText) {
		Connection conn = getConnection();
		int boardCount = new BoardDao().searchWriterCount(conn, searchText);
		
		close(conn);
		return boardCount;
	}

	public int searchWriterTabCount(int tabNo, String searchText) {
		Connection conn = getConnection();
		int boardCount;
		
		if(tabNo == 0) {
			boardCount = new BoardDao().searchWriterPopListCount(conn, searchText);
		} else {
			boardCount = new BoardDao().searchWriterTabListCount(conn, tabNo, searchText);
		}

		close(conn);
		return boardCount;
	}

	public ArrayList<Board> searchWriterList(PageInfo pageInfo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList = new BoardDao().searchWriterList(conn, pageInfo, searchText);
		
		close(conn);
		return boardList;
	}

	public ArrayList<Board> searchWriterTabList(PageInfo pageInfo, int tabNo, String searchText) {
		Connection conn = getConnection();
		ArrayList<Board> boardList;
		
		if(tabNo == 0) {
			boardList = new BoardDao().searchWriterPopList(conn, pageInfo, searchText);
		} else {
			boardList = new BoardDao().searchWritertTabList(conn, pageInfo ,tabNo, searchText);
		}
		
		close(conn);
		return boardList;
	}

	public boolean insertCommentReply(Comment comment) {
		Connection conn = getConnection();
		BoardDao bDao = new BoardDao();
		Boolean isSuccess = false;
		
		int result1 = bDao.insertCommentReply(conn, comment);
		
		int curSeqNo = bDao.selectCommentNo(conn);
		if(curSeqNo > 0) comment.setCommentNo(curSeqNo);
		
		int result2 = bDao.updateParentOrder(conn, comment);
		int result3 = bDao.updateParentCount(conn, comment);
		
		if(result1 > 0 && result2 >= 0 && result3 >= 0) {
			commit(conn);
			isSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		return isSuccess;
	}

	public ArrayList<Comment> selectReplyList(PageInfo cPageInfo, int boardNo, ArrayList<Comment> commentList) {
		Connection conn = getConnection();
		
		ArrayList<Comment> replyList = new BoardDao().selectReplyList(conn, cPageInfo, boardNo);
		
		close(conn);
		return replyList;
	}

	public Comment selectComment(int commentNo) {
		Connection conn = getConnection();
		Comment comment = new BoardDao().selectComment(conn, commentNo);
		
		close(conn);
		return comment;
	}

	public int selectMaxCommentGroupNo(int communityNo) {
		Connection conn = getConnection();
		int result = new BoardDao().selectMaxCommentGroupNo(conn, communityNo);
		
		close(conn);
		return result;
	}


	
	
}
