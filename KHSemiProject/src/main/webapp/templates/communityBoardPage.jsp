<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ 
    page import="com.kh.common.PageInfo, java.util.ArrayList, com.kh.community.model.vo.Board" 
%>
<%
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boardList");

    int currentPage = pageInfo.getCurrentPage();
    int startPage = pageInfo.getStartPage();
    int endPage = pageInfo.getEndPage();
    int maxPage = pageInfo.getMaxPage();
    int boardLimit = pageInfo.getBoardLimit();
    int pageBarLimit = pageInfo.getPageBarLimit();

    String tno = request.getParameter("tno");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

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

    <div class="board-tab">
        <ul>
            <li><button onclick="location.href='<%=contextPath%>/community?cpage=1'" style="background-color: #FF9139;">전체</button></li>
            <li><button onclick="location.href='<%=contextPath%>/community?cpage=1&tno=0'">인기글</button></li>
            <li><button onclick="location.href='<%=contextPath%>/community?cpage=1&tno=1'">공지</button></li>
            <li><button onclick="location.href='<%=contextPath%>/community?cpage=1&tno=2'">질문</button></li>
            <li><button onclick="location.href='<%=contextPath%>/community?cpage=1&tno=3'">풀이</button></li>
            <li><button onclick="location.href='<%=contextPath%>/community?cpage=1&tno=4'">잡담</button></li>
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
</body>
</html>