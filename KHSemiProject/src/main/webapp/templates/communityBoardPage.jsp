<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ 
    page import="com.kh.common.PageInfo, 
                java.util.ArrayList, 
                com.kh.community.model.vo.Board, 
                com.kh.community.model.vo.Category,
                com.kh.community.model.vo.Attachment" 
%>
<%
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	ArrayList<Board> boardList = (ArrayList<Board>)request.getAttribute("boardList");
    ArrayList<Category> category = (ArrayList<Category>)request.getAttribute("category");
    ArrayList<Attachment> attachList = (ArrayList<Attachment>)request.getAttribute("attach"); 

    int currentPage = pageInfo.getCurrentPage();
    int startPage = pageInfo.getStartPage();
    int endPage = pageInfo.getEndPage();
    int maxPage = pageInfo.getMaxPage();
    int boardLimit = pageInfo.getBoardLimit();
    int pageBarLimit = pageInfo.getPageBarLimit();

    String tno = request.getParameter("tno");
    request.setAttribute("optional",tno);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<script src="<%=request.getContextPath()%>/static/js/communityBoardPage.js"></script>
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
                    <th class="tab">탭</th>
                    <th class="title">제목</th>
                    <th class="author">작성자</th>
                    <th class="comment-num" style="color:black;">댓글</th>
                    <th class="date">작성일</th>
                    <th class="viewcount">조회수</th>
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
                                <td class="tab" data-tab-no="<%=b.getCommunityTabNo()%>" onclick="location.href='<%=contextPath%>/community?cpage=1&tno=<%=b.getCommunityTabNo()%>'">
                                    <%=b.getCommunityTab()%>
                                </td>
                                <td class="title" onclick="location.href='<%=contextPath%>/board?cpage=<%=currentPage%>&no=<%=b.getCommunityNo()%>&comment=1'"><%=b.getCommunityTitle()%></td>
                                <td class="author"><%=b.getMemberId()%></td>
                                <td class="comment-num">[<%=b.getCommentCount()%>]<img src="static/img/comment-icon.png"></td>
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
                                <td class="comment-num">[<%=b.getCommentCount()%>]<img src="static/img/comment-icon.png"></td>
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
                <% if (request.getParameter("searchOption") == null ) { %> <!-- 검색하지 않고, 그냥 페이징 바 버튼 눌러서 이동할 때 -->
                    <% if ( tno == null ) { %>
                        <button onclick="location.href='<%=contextPath%>/community?cpage=1'">&lt;&lt;</button>
                    <% } else { %>
                        <button onclick="location.href='<%=contextPath%>/community?cpage=1&tno=<%=tno%>'">&lt;&lt;</button>
                    <% } %>
                <% } else { %> <!-- 검색하고, 페이징 바를 통해 이동할때 -->
                    <% if ( tno == null ) { %> <!-- tno가 null이면 전체 탭 -->
                        <button onclick="location.href='<%=contextPath%>/search.bo?cpage=1&searchText=${searchText}&searchOption=${searchOption}'">&lt;&lt;</button>
                    <% } else { %>
                        <button onclick="location.href='<%=contextPath%>/search.bo?cpage=1&tno=<%=tno%>&searchText=${searchText}&searchOption=${searchOption}'">&lt;&lt;</button>
                    <% } %>
                <% } %>
                
                <!-- 페이징바 단위 만큼 앞으로 이동하는 버튼 -->
                <% if(startPage != 1) { %>
                    <% if (request.getParameter("searchOption") == null ) { %>
                        <% if ( tno == null ) { %>
                            <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage - 1%>'">&lt;</button>
                        <% } else { %>
                            <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage - 1%>&tno=<%=tno%>'">&lt;</button>
                        <% } %>
                    <% } else { %>
                        <% if ( tno == null ) { %>
                            <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=startPage - 1%>&searchText=${searchText}&searchOption=${searchOption}'">&lt;</button>
                        <% } else { %>
                            <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=startPage - 1%>&tno=<%=tno%>&searchText=${searchText}&searchOption=${searchOption}'">&lt;</button>
                        <% } %>
                    <% } %>
                <% } else { %>
                    <button disabled>&lt;</button>
                <% } %>

                <!-- 페이지 이동 버튼 -->
                <%for(int i=startPage; i <= endPage; i ++) { %>
                    <% if(i == currentPage) { %>
                        <button disabled><%=i %></button>
                    <% } else { %>
                        <% if (request.getParameter("searchOption") == null ) { %>
                            <% if ( tno == null ) { %>
                                <button onclick="location.href='<%=contextPath%>/community?cpage=<%=i%>'"><%=i %></button>
                            <% } else { %>
                                <button onclick="location.href='<%=contextPath%>/community?cpage=<%=i%>&tno=<%=tno%>'"><%=i %></button>
                            <% } %>
                        <% } else { %>
                            <% if ( tno == null ) { %>
                                <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=i%>&searchText=${searchText}&searchOption=${searchOption}'"><%=i %></button>
                            <% } else { %>
                                <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=i%>&tno=<%=tno%>&searchText=${searchText}&searchOption=${searchOption}'"><%=i %></button>
                            <% } %>
                        <% } %>
                    <% } %>
                <% } %>

                <!-- 페이징바 단위 만큼 뒤로 이동하는 버튼 -->
                <% if( (startPage + pageBarLimit < maxPage) ) { %>
                    <% if (request.getParameter("searchOption") == null ) { %>
                        <% if ( tno == null ) { %>
                            <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage + pageBarLimit%>'">&gt;</button>
                        <% } else { %>
                            <button onclick="location.href='<%=contextPath%>/community?cpage=<%=startPage + pageBarLimit%>&tno=<%=tno%>'">&gt;</button>
                        <% } %>
                    <% } else { %>
                        <% if ( tno == null ) { %>
                            <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=startPage + pageBarLimit%>&searchText=${searchText}&searchOption=${searchOption}'">&gt;</button>
                        <% } else { %>
                            <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=startPage + pageBarLimit%>&tno=<%=tno%>&searchText=${searchText}&searchOption=${searchOption}'">&gt;</button>
                        <% } %>
                    <% } %>
                <% } else { %>
                    <button disabled>&gt;</button>
                <% } %>

                <!-- 맨 뒤로 가는 버튼 -->
                <% if (request.getParameter("searchOption") == null ) { %>
                    <% if ( tno == null ) { %>
                        <button onclick="location.href='<%=contextPath%>/community?cpage=<%=maxPage%>'">&gt;&gt;</button>
                    <% } else { %>
                        <button onclick="location.href='<%=contextPath%>/community?cpage=<%=maxPage%>&tno=<%=tno%>'">&gt;&gt;</button>
                    <% } %>
                <% } else { %>
                    <% if ( tno == null ) { %>
                        <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=maxPage%>&searchText=${searchText}&searchOption=${searchOption}'">&gt;&gt;</button>
                    <% } else { %>
                        <button onclick="location.href='<%=contextPath%>/search.bo?cpage=<%=maxPage%>&tno=<%=tno%>&searchText=${searchText}&searchOption=${searchOption}'">&gt;&gt;</button>
                    <% } %>
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

        <form class="search-form" action="<%=contextPath%>/search.bo">
            <input type="hidden" name="cpage" value="1">
            <% if( tno != null ) { %>
                <input type="hidden" name="tno" value="<%=tno%>">
            <% } %>
            <div class="option2">
                <input name="searchText" type="text">
            </div>

            <div class="option1">
                <select name="searchOption">
                    <option value="all">전체</option>
                    <option value="title">제목</option> <!-- 좋아요 순 -->
                    <option value="content">내용</option>
                    <option value="writer">글쓴이</option>
                </select>
            </div>
            
            <div class="option1">
                <button type="submit"><img src="static/img/search-icon.png">검색</button>
            </div>
        </form>
    </div>

</div>
</body>
</html>