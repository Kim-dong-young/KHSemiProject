<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ 
    page import="com.kh.community.model.vo.Board, 
                com.kh.common.PageInfo, 
                java.util.ArrayList, 
                com.kh.community.model.vo.Board,
                com.kh.community.model.vo.Comment,
                com.kh.community.model.vo.Category,
                com.kh.community.model.vo.Attachment" %>
<% 
    Board currentBoard = (Board)request.getAttribute("board");
    ArrayList<Attachment> attachList = (ArrayList<Attachment>)request.getAttribute("attach"); 

    PageInfo cPageInfo = (PageInfo)request.getAttribute("cPageInfo");
    ArrayList<Comment> commentList = (ArrayList<Comment>)request.getAttribute("commentList");
    int commentCount = (Integer)request.getAttribute("commentCount");

    PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boardList");

    ArrayList<Category> category = (ArrayList<Category>)request.getAttribute("category");
    
    int cpage = Integer.parseInt(request.getParameter("cpage"));
    int comment = Integer.parseInt(request.getParameter("comment"));
    String tno = request.getParameter("tno");

    int currentPage = pageInfo.getCurrentPage();
    int startPage = pageInfo.getStartPage();
    int endPage = pageInfo.getEndPage();
    int maxPage = pageInfo.getMaxPage();
    int boardLimit = pageInfo.getBoardLimit();
    int pageBarLimit = pageInfo.getPageBarLimit();

    int cCurrentPage = cPageInfo.getCurrentPage();
    int cStartPage = cPageInfo.getStartPage();
    int cEndPage = cPageInfo.getEndPage();
    int cMaxPage = cPageInfo.getMaxPage() == 0 ? 1 : cPageInfo.getMaxPage();
    int cLimit = cPageInfo.getBoardLimit();
    int cPageBarLimit = cPageInfo.getPageBarLimit();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<script src="<%=request.getContextPath()%>/static/js/communityViewPage.js"></script>
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
	<input id="errorMsg" type="hidden" value='<%=request.getAttribute("errorMsg") == null ? "" : request.getAttribute("errorMsg") %>'>

	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
		<p onclick="location.href='<%=contextPath%>/community?cpage=1'">자유 게시판</p>
        <div class="wrapper">
			<div class="board">
                <div class="bulletin-content">
					<div class="bulletin-title">
                        <span class="board-tab" onclick="location.href='<%=contextPath%>/community?cpage=1&tno=<%=currentBoard.getCommunityTabNo()%>'"><%=currentBoard.getCommunityTab()%></span>
                        <span><%=currentBoard.getCommunityTitle()%></span>
                        <% if( loginMember != null && loginMember.getMemberNo() == currentBoard.getMemberNo() ) { %>
                            <button onclick="location.href='<%=contextPath%>/delete.bo?bno=<%=currentBoard.getCommunityNo()%>&cpage=<%=cpage%>&comment=<%=cCurrentPage%>'"><img src="static/img/trash-icon.png">삭제</button>
                        <% } %>
					</div>

                    <div class="bulletin-info">
                        <div class="author-info">
                            <img src="<%=contextPath%>/<%=currentBoard.getMemberImage()%>">
                            <span><%=currentBoard.getMemberId()%></span>
                        </div>

                        <div class="data-info">
                            <span class="after-vline">작성일: <%=currentBoard.getCommunityDate()%></span>
                            <span class="after-vline">조회수: <%=currentBoard.getCommunityViewcount()%></span>
                            <span name="likeCount" class="after-vline">좋아요: <%=currentBoard.getLikeCount()%></span>
                            <span>댓글: <%=commentCount%></span>
                        </div>
                    </div>

                    <div class="bulletin-input">
                        <% if( !attachList.isEmpty() ) { %>
                            <% for( Attachment at : attachList ) { %>
                            <div class="image-input">
                                <img src='<%=contextPath%>/<%=at.getFilePath()%><%=at.getChangeName()%>'>
                            </div>
                            <% } %>
                        <% } %>
                        <p><%=currentBoard.getCommunityContent()%></p>
                    </div>

                    <div class="bulletin-option">
                        <% if( loginMember != null ) { %>
                            <button class="like-button" onclick="increaseLike(<%=currentBoard.getCommunityNo()%>)"><img src="static/img/thumbup-icon.png">좋아요</button>
                            <button class="report-button" onclick="changeSubmitBtnBoard(<%=currentBoard.getCommunityNo()%>,<%=currentBoard.getMemberNo()%>)" type="button" data-bs-toggle="modal" data-bs-target="#report-board-modal"><img src="static/img/flag-icon.png">신고</button>
                        <% } else { %>
                            <button class="like-button" onclick='alert("로그인한 유저만 좋아요를 누를 수 있습니다.")'><img src="static/img/thumbup-icon.png">좋아요</button>
                            <button class="report-button" onclick='alert("로그인한 유저만 신고할 수 있습니다.")'><img src="static/img/flag-icon.png">신고</button>
                        <% } %>
                    </div>
                </div>

                <div class="bulletin-comment">
                    <% for(Comment cm : commentList) { %>
                        <div class="comment" style="margin-left:<%=cm.getCommentDepth() * 50%>px">
                            <div class="comment-left">
                                <img src="<%=contextPath%>/<%=cm.getMemberImage()%>">
                            </div>
    
                            <div class="comment-right">
                                <div class="user-info">
                                    <span><%=cm.getMemberName()%></span>
                                </div>
                                <div class="comment-content">
                                    <p><c:out value="<%=cm.getCommentContent()%>"/></p>
                                </div>
                                <div class="comment-option">
                                    <% if( loginMember != null ) { %>
                                        <button class="after-vline" onclick="writeReply(<%=cm.getCommentNo()%>)">답글</button>
                                        <form name="reply-form-<%=cm.getCommentNo()%>" style="display:none;" method="post" onsubmit="return submitComment(this);" action="<%=contextPath%>/reply.co?cpage=<%=cpage%>&no=<%=currentBoard.getCommunityNo()%>&comment=<%=cCurrentPage%>&pno=<%=cm.getCommentNo()%>">
                                            <div class="comment-write">
                                                <textarea name="commentContent" placeholder="댓글은 자신의 얼굴을 비추는 거울입니다." wrap="hard"></textarea>
                                                <button type="submit"><img src="static/img/comment-icon.png">작성</button>
                                            </div>
                                        </form>

                                        <% if( (cm.getMemberNo() == loginMember.getMemberNo()) ) { %>
                                            <button class="after-vline" onclick="changeSubmitBtnComment(<%=cm.getCommentNo()%>,<%=cm.getMemberNo()%>)" data-bs-toggle="modal" data-bs-target="#report-board-modal">신고</button>
                                            <button onclick="location.href='<%=contextPath%>/delete.co?cno=<%=cm.getCommentNo()%>&cpage=<%=cpage%>&no=<%=currentBoard.getCommunityNo()%>&comment=<%=cCurrentPage%>'">삭제</button>
                                        <% } else { %>
                                            <button type="button" onclick="changeSubmitBtnComment(<%=cm.getCommentNo()%>,<%=cm.getMemberNo()%>)" data-bs-toggle="modal" data-bs-target="#report-board-modal">신고</button>
                                        <% } %>
                                    <% } else { %>
                                        <button class="after-vline" onclick="alert('로그인한 유저만 답글을 작성할 수 있습니다.')">답글</button>
                                        <button onclick="alert('로그인한 유저만 신고할 수 있습니다.')">신고</button>
                                    <% } %>
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
                        <li><button id="t" onclick="location.href='<%=contextPath%>/community?cpage=1'">전체</button></li>
                        <li><button id="t0" onclick="location.href='<%=contextPath%>/community?cpage=1&tno=0'">인기글</button></li>
                        <% for( Category c : category ) { %>
                            <li><button id="t<%=c.getTabNumber()%>" onclick="location.href='<%=contextPath%>/community?cpage=1&tno=<%=c.getTabNumber()%>'"><%=c.getTabName()%></button></li>
                        <% } %>
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

                                <% if( tno == null ) { %>
                                    <% for(Board b : boardList) { %>
                                        <tr>
                                            <td class="tab" data-tab-no="<%=b.getCommunityTabNo()%>" onclick="location.href='<%=contextPath%>/community?cpage=1&tno=<%=b.getCommunityTabNo()%>'"><%=b.getCommunityTab()%></td>
                                            <td class="title" onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=b.getCommunityNo()%>&comment=1'"><%=b.getCommunityTitle()%></td>
                                            <td class="author"><%=b.getMemberId()%></td>
                                            <td class="comment-num"><%=b.getCommentCount()%><img src="static/img/comment-icon.png"></td>
                                            <td class="date"><%=b.getCommunityDate()%></td>
                                            <td class="viewcount"><%=b.getCommunityViewcount()%></td>
                                        </tr>
                                    <% } %>
                                <% } else { %>

                                    <% for(Board b : boardList) { %>
                                        <tr>
                                            <td class="tab" data-tab-no="<%=b.getCommunityTabNo()%>" onclick="location.href='<%=contextPath%>/community?cpage=1&tno=<%=b.getCommunityTabNo()%>'"><%=b.getCommunityTab()%></td>
                                            <td class="title" onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=b.getCommunityNo()%>&comment=1&tno=<%=tno%>'"><%=b.getCommunityTitle()%></td>
                                            <td class="author"><%=b.getMemberId()%></td>
                                            <td class="comment-num"><%=b.getCommentCount()%><img src="static/img/comment-icon.png"></td>
                                            <td class="date"><%=b.getCommunityDate()%></td>
                                            <td class="viewcount"><%=b.getCommunityViewcount()%></td>
                                        </tr>
                                    <% } %>
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
                        </div>

                        <div class="option2">
                            <!-- 맨 처음으로 가는 버튼 -->
                            <% if ( tno == null ) { %>
                                <button onclick="location.href='<%=contextPath%>/community?cpage=1'">&lt;&lt;</button>
                            <% } else { %>
                                <button onclick="location.href='<%=contextPath%>/community?cpage=1&tno=<%=tno%>'">&lt;&lt;</button>
                            <% } %>
                            
                            <!-- 페이징바 단위 만큼 앞으로 이동하는 버튼 -->
                            <% if(startPage != 1) { %>
                                <% if ( tno == null ) { %>
                                    <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage - 1%>'">&lt;</button>
                                <% } else { %>
                                    <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage - 1%>&tno=<%=tno%>'">&lt;</button>
                                <% } %>
                            <% } else { %>
                                <button disabled>&lt;</button>
                            <% } %>

                            <!-- 페이지 이동 버튼 -->
                            <%for(int i=startPage; i <= endPage; i ++) { %>
                                <% if(i == currentPage) { %>
                                    <button disabled><%=i %></button>
                                <% } else { %>
                                    <% if ( tno == null ) { %>
                                        <button onclick="location.href='<%=contextPath%>/community?cpage=<%=i%>'"><%=i %></button>
                                    <% } else { %>
                                        <button onclick="location.href='<%=contextPath%>/community?cpage=<%=i%>&tno=<%=tno%>'"><%=i %></button>
                                    <% } %>
                                <% } %>
                            <% } %>

                            <!-- 페이징바 단위 만큼 뒤로 이동하는 버튼 -->
                            <% if( (startPage + pageBarLimit < maxPage) ) { %>
                                <% if ( tno == null ) { %>
                                    <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage + pageBarLimit%>'">&gt;</button>
                                <% } else { %>
                                    <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage + pageBarLimit%>&tno=<%=tno%>'">&gt;</button>
                                <% } %>
                            <% } else { %>
                                <button disabled>&gt;</button>
                            <% } %>

                            <!-- 맨 뒤로 가는 버튼 -->
                            <% if ( tno == null ) { %>
                                <button onclick="location.href='<%=contextPath%>/community?cpage=<%=maxPage%>'">&gt;&gt;</button>
                            <% } else { %>
                                <button onclick="location.href='<%=contextPath%>/community?cpage=<%=maxPage%>&tno=<%=tno%>'">&gt;&gt;</button>
                            <% } %>
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

    <!-- 신고 Modal -->
    <div class="modal fade" id="report-board-modal">
        <div class="modal-dialog">
          <div class="modal-content">
      
            <!-- Modal Header -->
            <div class="modal-header">
              <img src="<%=contextPath%>/static/img/flag-icon.png">
              <h4 class="modal-title">신고하기</h4>
              <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
      
            <!-- Modal body -->
            <form id="reportForm">
                <div class="modal-body">
                    <div class="report-choose-area">
                        <label>
                            <input type="radio" name="reportNumber" value="1" checked>
                            <span class="custom-check"></span>
                            홍보/도배 글입니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="2">
                            <span class="custom-check"></span>
                            음란물을 포함하고 있습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="3">
                            <span class="custom-check"></span>
                            불법적인 내용입니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="4">
                            <span class="custom-check"></span>
                            욕설이 포함되어있습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="5">
                            <span class="custom-check"></span>
                            혐오발언이 포함되어있습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="6">
                            <span class="custom-check"></span>
                            사칭 글입니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="7">
                            <span class="custom-check"></span>
                            괴롭힘 및 따돌림이 포함되었습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="8">
                            <span class="custom-check"></span>
                            기타
                        </label><br>
                    </div>

                    <textarea name="reportReason" wrap="hard" placeholder="자세한 사유를 설명해주세요."></textarea>
                </div>
        
                <!-- Modal footer -->
                <div class="modal-footer">
                <button type="button" id="report-submit-button" onclick="reportBoard(<%=currentBoard.getCommunityNo()%>,<%=currentBoard.getMemberNo()%>);" class="btn btn-danger">제출하기</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </form> 
          </div>
        </div>
      </div>

</body>
</html>