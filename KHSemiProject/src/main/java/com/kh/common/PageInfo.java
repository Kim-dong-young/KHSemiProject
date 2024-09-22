package com.kh.common;

public class PageInfo {
	/* 게시글 */
	private int listCount; // DB에 있는 총 게시글 수
	private int currentPage; // 현재 사용자가 요청한 페이지
	private int pageBarLimit; // 페이지 하단 페이징 바 개수
	private int boardLimit; // 한 페이지 내에 보여질 게시글 최대 수
	
	/* 페이징 바 */
	private int maxPage; // 게시글 수를 페이지 단위로 나눴을 때, 가장 마지막 페이지
	private int startPage; // 제일 첫 페이지(시작 = 1), 페이징 바의 시작 수
	private int endPage; // 페이징 바의 마지막 끝 수
	
	public PageInfo() {
		super();
	}
	
	public PageInfo(int listCount, int currentPage, int pageBarLimit, int boardLimit, int maxPage, int startPage,
			int endPage) {
		super();
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageBarLimit = pageBarLimit;
		this.boardLimit = boardLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageBarLimit() {
		return pageBarLimit;
	}
	public void setPageBarLimit(int pageBarLimit) {
		this.pageBarLimit = pageBarLimit;
	}
	public int getBoardLimit() {
		return boardLimit;
	}
	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	@Override
	public String toString() {
		return "PageInfo [listCount=" + listCount + ", currentPage=" + currentPage + ", pageBarLimit=" + pageBarLimit
				+ ", boardLimit=" + boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
	
}