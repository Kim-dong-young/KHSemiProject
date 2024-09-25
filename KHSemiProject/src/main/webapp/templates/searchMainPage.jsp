<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.common.PageInfo, java.util.ArrayList, com.kh.search.model.vo.Quiz" %>
<%
    PageInfo pi = (PageInfo)request.getAttribute("pi");
    ArrayList<Quiz> list = (ArrayList<Quiz>)request.getAttribute("list");

    int currentPage = pi.getCurrentPage();
    int startPage = pi.getStartPage();
    int endPage = pi.getEndPage();
    int maxPage = pi.getMaxPage();

%>


<!DOCTYPE html>
<html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/searchMainPage.css">

</head>
<body>
    <%@ include file="common/menu.jsp" %>
    <div class="content">
        <div class="wrapper">
            <div class="category-div" >
                <div onclick="categorySearch(1)" <c:if test="${param.category == 1}"> style="background-color: bisque;" </c:if>>
                    <img src="static/img/searchMainPage/Happy.png" alt="">
                    <p>유머</p>
                </div>
                <div onclick="categorySearch(2)" <c:if test="${param.category == 2}"> style="background-color: bisque;"</c:if>>
                    <img src="static/img/searchMainPage/Paint Palette.png" alt="">
                    <p>예술/문학</p>
                </div>
                <div onclick="categorySearch(3)" <c:if test="${param.category == 3}"> style="background-color: bisque;"</c:if>>
                    <img src="static/img/searchMainPage/Geography.png" alt="">
                    <p>세계</p>
                </div>
                <div onclick="categorySearch(4)" <c:if test="${param.category == 4}"> style="background-color: bisque;"</c:if>>
                    <img src="static/img/searchMainPage/Colosseum.png" alt="">
                    <p>역사</p>
                </div>
                <div onclick="categorySearch(5)" <c:if test="${param.category == 5}"> style="background-color: bisque;"</c:if>>
                    <img src="static/img/searchMainPage/Language.png" alt="">
                    <p>언어</p>
                </div>
                <div onclick="categorySearch(6)" <c:if test="${param.category == 6}"> style="background-color: bisque;"</c:if>>
                    <img src="static/img/searchMainPage/Test Tube.png" alt="">
                    <p>과학/자연</p>
                </div>
                <div onclick="categorySearch(7)" <c:if test="${param.category == 7}"> style="background-color: bisque;"</c:if>>
                    <img src="static/img/searchMainPage/Game Soccer.png" alt="">
                    <p>스포츠</p>
                </div>
                <div onclick="categorySearch(8)" <c:if test="${param.category == 8}"> style="background-color: bisque;"</c:if>>
                    <img src="static/img/searchMainPage/Question Mark.png" alt="">
                    <p>기타</p>
                </div>
                <script>
                    function categorySearch(num){
                        if(num == ${param.category}){
                            location.href='<%=contextPath%>/main.sl?cpage=1&category=0&orderby=${param.orderby}&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value)
                        } else {
                            location.href='<%=contextPath%>/main.sl?cpage=1&category=' + num + '&orderby=${param.orderby}&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value)
                        }
                        
                    }
                </script>
            </div>
            <br>
            <div class="search-div">
                <div id="search-div-area">
                    <select name="search-menu" id="search-select">
                        <option value="1" <c:if test="${param.search_type == 1}">selected</c:if>>제목</option>
                        <option value="2" <c:if test="${param.search_type == 2}">selected</c:if>>제작자</option>
                    </select>
                    <input type="text" id="search-text" value="${param.search_text}" onkeypress="if(window.event.keyCode==13){searchComfirm()}">

                    <input type="image" src="static/img/icon.png" id="search-submit" onclick="searchComfirm()">
                </div>
            </div>

            <script>
                function searchComfirm(){
                    location.href='<%=contextPath%>/main.sl?cpage=1&category=${param.category}&search_type=' 
                    + encodeURIComponent(document.getElementById('search-select').value) + '&orderby=${param.orderby}&search_text=' + encodeURIComponent(document.getElementById('search-text').value)
                }
            </script>

            <br>
            <div class="buttons">
                <div id="array-buttons">
                    <% if(Integer.parseInt(request.getParameter("orderby")) == 1) { %>
                        <button disabled>조회수</button>
                    <% } else {%>
                        <button onclick="arraySearch(1)">조회수</button>
                    <% } %>
                    <% if(Integer.parseInt(request.getParameter("orderby")) == 2) { %>
                        <button disabled>최신순</button>
                    <% } else {%>
                        <button onclick="arraySearch(2)">최신순</button>
                    <% } %>
                    <% if(Integer.parseInt(request.getParameter("orderby")) == 3) { %>
                        <button disabled>평점순</button>
                    <% } else {%>
                        <button onclick="arraySearch(3)">평점순</button>
                    <% } %>
                </div>

                <script>
                    function arraySearch(num){
                        location.href='<%=contextPath%>/main.sl?cpage=1&category=${param.category}&orderby=' + num + '&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value)
                    }
                </script>

                <div id="tag-popup-button">
                   <button onclick="tagPopup()">태그 필터</button>
                </div>
                
            </div>
            <div id="tag-popup" style="display: none;">
                <div id="tag-top">
                    <div id="tag-search">
                        <input type="text">
                    </div>
                    <div id="tag-search-exit-button" onclick="closeTagPopup()">
                        <input type="image" src="static/img/searchMainPage/back.png">
                    </div>
                </div>
                <br>
                <div id="tag-popup-tags">
                    <button>
                        asdf
                    </button>
                    <button>
                        asdf
                    </button>
                    <button>
                        asdf
                    </button>
                    <button>
                        asdf
                    </button>
                    <button>
                        asdf
                    </button>
                    <button>
                        asdf
                    </button>
                    <button>
                        asdf
                    </button>
                </div>
            </div>
            <script>
                function tagPopup(){
                    document.getElementById("tag-popup").style.display = "flex";
                }
                function closeTagPopup(){
                    document.getElementById("tag-popup").style.display = "none";
                }
            </script>
            <br>
            <div class="contents">

                <section class="video-flex">
                    <% if(list.isEmpty()) { %>
                        <p align="center">조건에 맞는 퀴즈가 존재하지 않스빈다.</p>
                    <% } else { %>
                        <% for(Quiz q : list) { %>
                            <div class="video-priview" onclick="location.href='searchClickPage.jsp'">
                                <div class="thumbnail-row">
                                    <img class="thumbnail" src="static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                                </div>
                                <div class="video-info-grid">
                                    <div class="video-info">
                                        <p class="video-title">
                                            <%=q.getQuiz_title()%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        <% } %>
                    <% } %>
                </section>
            </div>

            <div class="option2">
                <%if(currentPage > 1) { %>
                    <button onclick="pageChange(<%=currentPage - 1%>)">&lt;</button>
                <% } %>
                <% for(int p = startPage; p <= endPage; p++) { %>
                    <% if(p == currentPage) { %>
                        <button disabled><%=p%></button>
                    <% } else {%>
                        <button onclick="pageChange(<%=p%>)"><%=p%></button>
                    <% } %>
                <% } %>
                <%if(currentPage < maxPage) { %>
                    <button onclick="pageChange(<%=currentPage + 1%>)">&gt;</button>
                <% } %>
            </div>

            <script>
                function pageChange(num){
                    location.href='<%=contextPath%>/main.sl?cpage=' + num + '&category=${param.category}&orderby=${param.orderby}&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value)
                }
            </script>





        </div>














    </div>
    
        

</body>
</html>