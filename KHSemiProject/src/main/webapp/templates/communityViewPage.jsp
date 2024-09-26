<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ 
    page import="com.kh.community.model.vo.Board, 
                com.kh.common.PageInfo, 
                java.util.ArrayList, 
                com.kh.community.model.vo.Board,
                com.kh.community.model.vo.Comment" %>
<% 
    Board currentBoard = (Board)request.getAttribute("board"); 

    PageInfo cPageInfo = (PageInfo)request.getAttribute("cPageInfo");
    ArrayList<Comment> commentList = (ArrayList<Comment>)request.getAttribute("commentList");
    int commentCount = (Integer)request.getAttribute("commentCount");

    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boardList");
    
    int cpage = Integer.parseInt(request.getParameter("cpage"));
    int comment = Integer.parseInt(request.getParameter("comment"));

    int currentPage = pageInfo.getCurrentPage();
    int startPage = pageInfo.getStartPage();
    int endPage = pageInfo.getEndPage();
    int maxPage = pageInfo.getMaxPage();
    int boardLimit = pageInfo.getBoardLimit();
    int pageBarLimit = pageInfo.getPageBarLimit();

    int cCurrentPage = cPageInfo.getCurrentPage();
    int cStartPage = cPageInfo.getStartPage();
    int cEndPage = cPageInfo.getEndPage();
    int cMaxPage = cPageInfo.getMaxPage();
    int cLimit = cPageInfo.getBoardLimit();
    int cPageBarLimit = cPageInfo.getPageBarLimit();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<link rel="stylesheet" href="static/css/communityViewPage.css">
<link rel="stylesheet" href="static/css/communityBoardPage.css">

<!-- jQuery -->
<script 
src="https://code.jquery.com/jquery-3.7.1.min.js"
integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
crossorigin="anonymous"></script>
<script
src="https://code.jquery.com/ui/1.14.0/jquery-ui.min.js"
integrity="sha256-Fb0zP4jE3JHqu+IBB9YktLcSjI1Zc6J2b6gTjB0LpoM="
crossorigin="anonymous"></script>

</head>
<body>
	<%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
		<p>자유 게시판</p>
        <div class="wrapper">
			<div class="board">
                <div class="bulletin-content">
					<div class="bulletin-title">
                        <span class="board-tab"><%=currentBoard.getCommunityTab()%></span>
                        <span><%=currentBoard.getCommunityTitle()%></span>
                        <% if( loginMember != null && loginMember.getMemberNo() == currentBoard.getMemberNo() ) { %>
                            <button onclick="location.href='<%=contextPath%>/delete.bo?userno=<%=loginMember.getMemberNo()%>'"><img src="static/img/trash-icon.png">삭제</button>
                        <% } %>
					</div>

                    <div class="bulletin-info">
                        <div class="author-info">
                            <img src="static/img/test.png">
                            <span><%=currentBoard.getMemberId()%></span>
                        </div>

                        <div class="data-info">
                            <span class="after-vline">작성일: <%=currentBoard.getCommunityDate()%></span>
                            <span class="after-vline">조회수: <%=currentBoard.getCommunityViewcount()%></span>
                            <span class="after-vline">좋아요: <%=currentBoard.getLikeCount()%></span>
                            <span>댓글: <%=commentCount%></span>
                        </div>
                    </div>

                    <div class="bulletin-input">
                        <div class="image-input">
                            <img src="static/img/test.png">
                        </div>
                        <div class="image-input">
                            <img src="static/img/test2.jpg">
                        </div>
                        <p><%=currentBoard.getCommunityContent()%></p>
                    </div>

                    <div class="bulletin-option">
                        <button class="like-button"><img src="static/img/thumbup-icon.png">좋아요</button>
                        <button class="report-button"><img src="static/img/flag-icon.png">신고</button>
                    </div>
                </div>

                <div class="bulletin-comment">
                    <% for(Comment cm : commentList) { %>
                        <div class="comment">
                            <div class="comment-left">
                                <img src="static/img/test.png">
                            </div>
    
                            <div class="comment-right">
                                <div class="user-info">
                                    <span><%=cm.getMemberNo()%></span>
                                    <span>Lv.35</span>
                                </div>
                                <div class="comment-content">
                                    <span><%=cm.getCommentContent()%></span>
                                </div>
                                <div class="comment-option">
                                    <button class="after-vline">답글</button>
                                    <button class="after-vline">신고</button>
                                    <button>삭제</button>
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>

                <div class="comment-page">
                    <!-- 맨 처음으로 가는 버튼 -->
                    <button onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=currentBoard.getCommunityNo()%>&comment=1'">&lt;&lt;</button>
                                                    
                    <!-- 페이징바 단위 만큼 앞으로 이동하는 버튼 -->
                    <% if(cStartPage != 1) { %>
                        <button onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=currentBoard.getCommunityNo()%>&comment=<%=cStartPage - 1%>'">&lt;</button>
                    <% } else { %>                      
                        <button disabled>&lt;</button>
                    <% } %>

                    <!-- 페이지 이동 버튼 -->
                    <%for(int i=cStartPage; i <= cEndPage; i ++) { %>
                        <% if(i == cCurrentPage) { %>
                            <button disabled><%=i %></button>
                        <% } else { %>
                            <button onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=currentBoard.getCommunityNo()%>&comment=<%=i%>'"><%=i %></button>
                        <% } %>
                    <% } %>

                    <!-- 페이징바 단위 만큼 뒤로 이동하는 버튼 -->
                    <% if( (cStartPage + cPageBarLimit < cMaxPage) ) { %>
                        <button onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=currentBoard.getCommunityNo()%>&comment=<%=cStartPage + cLimit%>'">&gt;</button>
                    <% } else { %>
                        <button disabled>&gt;</button>
                    <% } %>

                    <!-- 맨 뒤로 가는 버튼 -->
                    <button onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=currentBoard.getCommunityNo()%>&comment=<%=cMaxPage%>'">&gt;&gt;</button>
                </div>

                <% if(loginMember != null) { %>
                    <form method="post" action="<%=contextPath%>/comment.bo?cpage=<%=cpage%>&no=<%=currentBoard.getCommunityNo()%>&comment=<%=cMaxPage%>">
                    <div class="comment-write">
                        <textarea name="commentContent" placeholder="댓글은 자신의 얼굴을 비추는 거울입니다."></textarea>
                        <button type="submit"><img src="static/img/comment-icon.png">작성</button>
                    </div>
                    </form>
                <% } else { %>
                    <div class="comment-write">
                        <textarea name="commentContent" placeholder="댓글은 자신의 얼굴을 비추는 거울입니다." readonly></textarea>
                        <button onclick="alert('로그인한 유저만 댓글을 작성할 수 있습니다.')"><img src="static/img/comment-icon.png">작성</button>
                    </div>
                <% } %>



<!-- ================================================ 하단 게시글 목록 ==================================================== -->



                <div>
                    <div class="board-tab">
                        <ul>
                            <li><button style="background-color: #FF9139;">전체</button></li>
                            <li><button>인기글</button></li>
                            <li><button>공지</button></li>
                            <li><button>질문</button></li>
                            <li><button>풀이</button></li>
                            <li><button>잡담</button></li>
                        </ul>
                    </div>
                
                    <div class="board-content">
                        <div class="board-info">
                            <table>
                                <tr>
                                    <td class="tab">탭</td>
                                    <td class="title">제목</td>
                                    <td class="author">작성자</td>
                                    <td class="comment-num" style="color:black;">댓글</td>
                                    <td class="date">작성일</td>
                                    <td class="viewcount">조회수</td>
                                </tr>
                            </table>
                        </div>
                
                        <div class="board-list">
                            <table>
                                <% if(boardList.isEmpty()) { %>
                                    <tr>
                                        <td colspan="6">게시글이 없습니다.</td>
                                    </tr>
                                <% } else { %>
                                    <% for(Board b : boardList) { %>
                                        <tr>
                                            <td class="tab"><%=b.getCommunityTab()%></td>
                                            <td class="title" onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=b.getCommunityNo()%>&comment=1'"><%=b.getCommunityTitle()%></td>
                                            <td class="author"><%=b.getMemberId()%></td>
                                            <td class="comment-num"><%=b.getCommentCount()%><img src="static/img/comment-icon.png"></td>
                                            <td class="date"><%=b.getCommunityDate()%></td>
                                            <td class="viewcount"><%=b.getCommunityViewcount()%></td>
                                        </tr>
                                    <% } %>
                                    <% for(int i=boardList.size(); i < boardLimit; i++) { %>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    <% } %>
                                <% } %>
                            </table>
                        </div>
                
                        <div class="board-option">
                            <div class="option1">
                                <select>
                                    <option>최신순</option>
                                    <option>인기순</option> <!-- 좋아요 순 -->
                                    <option>댓글순</option>
                                </select>
                            </div>
                
                            <div class="option2">
                                <!-- 맨 처음으로 가는 버튼 -->
                                <button onclick="location.href='<%=contextPath%>/community?cpage=1&no=<%=currentBoard.getCommunityNo()%>'">&lt;&lt;</button>
                                
                                <!-- 페이징바 단위 만큼 앞으로 이동하는 버튼 -->
                                <% if(startPage != 1) { %>
                                    <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage - 1%>&no=<%=currentBoard.getCommunityNo()%>'">&lt;</button>
                                <% } else { %>
                                    <button disabled>&lt;</button>
                                <% } %>
                
                                <!-- 페이지 이동 버튼 -->
                                <%for(int i=startPage; i <= endPage; i ++) { %>
                                    <% if(i == currentPage) { %>
                                        <button disabled><%=i %></button>
                                    <% } else { %>
                                        <button onclick="location.href='<%=contextPath%>/community?cpage=<%=i%>&no=<%=currentBoard.getCommunityNo()%>'"><%=i %></button>
                                    <% } %>
                                <% } %>
                
                                <!-- 페이징바 단위 만큼 뒤로 이동하는 버튼 -->
                                <% if( (startPage + pageBarLimit < maxPage) ) { %>
                                    <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage + pageBarLimit%>&no=<%=currentBoard.getCommunityNo()%>'">&gt;</button>
                                <% } else { %>
                                    <button disabled>&gt;</button>
                                <% } %>
                
                                <!-- 맨 뒤로 가는 버튼 -->
                                <button onclick="location.href='<%=contextPath%>/community?cpage=<%=maxPage%>&no=<%=currentBoard.getCommunityNo()%>'">&gt;&gt;</button>
                            </div>
                
                            <div class="option1">
                                <% if ( loginMember != null ) { %>
                                    <a href="<%=contextPath%>/write.bo"><img src="static/img/pen-icon-white.png">글쓰기</a>
                                <% } else { %>
                                    <a onclick="alert('로그인한 유저만 글을 작성할 수 있습니다.')"><img src="static/img/pen-icon-white.png">글쓰기</a>
                                <% } %>
                            </div>
                        </div>
                
                        <form class="search-form">
                            <div class="option2">
                                <input type="text">
                            </div>
                
                            <div class="option1">
                                <select>
                                    <option>전체</option>
                                    <option>제목</option> <!-- 좋아요 순 -->
                                    <option>내용</option>
                                    <option>글쓴이</option>
                                </select>
                            </div>
                            
                            <div class="option1">
                                <button><img src="static/img/search-icon.png">검색</button>
                            </div>
                        </form>
                    </div>
                </div>
			</div>
            
            <div class="board-side">
                <%@ include file="communitySidePage.jsp" %>
            </div>
		</div>
    </div>
</body>
</html>