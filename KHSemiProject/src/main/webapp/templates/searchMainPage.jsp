<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.common.PageInfo, java.util.ArrayList, com.kh.search.model.vo.Quiz, com.kh.search.model.vo.Tag" %>
<%
    PageInfo pi = (PageInfo)request.getAttribute("pi");
    ArrayList<Quiz> list = (ArrayList<Quiz>)request.getAttribute("list");
    
    ArrayList<String> tagList = (ArrayList<String>)request.getAttribute("tagList");

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
<title>퀴즈팡 - 퀴즈 탐색</title>

</head>
<body>
    <%@ include file="common/menu.jsp" %>

    <link rel="stylesheet" href="<%=contextPath%>/static/css/searchMainPage.css">
    <script>
        let tttList = [];
        <% if (tagList != null && !tagList.isEmpty()){ %>
            <% for(int i = 0; i < tagList.size(); i++){ %>
                tttList[<%=i%>] = "<%=tagList.get(i)%>"
            <% } %>
        <% } else {%>
            tttList = "null"
        <% } %>
    </script>

    <div class="content">
        <div class="wrapper">
            <div class="category-div" >
                <div onclick="categorySearch(1)" <c:if test="${param.category == 1}"> style="background-color: bisque;" </c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Happy.png" alt="">
                    <p>유머</p>
                </div>
                <div onclick="categorySearch(2)" <c:if test="${param.category == 2}"> style="background-color: bisque;"</c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Paint Palette.png" alt="">
                    <p>예술/문학</p>
                </div>
                <div onclick="categorySearch(3)" <c:if test="${param.category == 3}"> style="background-color: bisque;"</c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Geography.png" alt="">
                    <p>세계</p>
                </div>
                <div onclick="categorySearch(4)" <c:if test="${param.category == 4}"> style="background-color: bisque;"</c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Colosseum.png" alt="">
                    <p>역사</p>
                </div>
                <div onclick="categorySearch(5)" <c:if test="${param.category == 5}"> style="background-color: bisque;"</c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Language.png" alt="">
                    <p>언어</p>
                </div>
                <div onclick="categorySearch(6)" <c:if test="${param.category == 6}"> style="background-color: bisque;"</c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Test Tube.png" alt="">
                    <p>과학/자연</p>
                </div>
                <div onclick="categorySearch(7)" <c:if test="${param.category == 7}"> style="background-color: bisque;"</c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Game Soccer.png" alt="">
                    <p>스포츠</p>
                </div>
                <div onclick="categorySearch(8)" <c:if test="${param.category == 8}"> style="background-color: bisque;"</c:if>>
                    <img src="<%=contextPath%>/static/img/searchMainPage/Question Mark.png" alt="">
                    <p>기타</p>
                </div>
                <script>
                    function categorySearch(num){
                        const tagList = document.getElementsByClassName("tag-clicked");
                        let String = "";
                        for (let tag of tagList){
                            String += tag.value + "!";
                        }
                        if(num == ${param.category}){
                            location.href='<%=contextPath%>/main.sl?cpage=1&category=0&orderby=${param.orderby}&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value)+ '&tag_list=' + encodeURIComponent(String)
                        } else {
                            location.href='<%=contextPath%>/main.sl?cpage=1&category=' + num + '&orderby=${param.orderby}&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value)+ '&tag_list=' + encodeURIComponent(String)
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
                    <input type="search" id="search-text" value="${param.search_text}" onkeypress="if(window.event.keyCode==13){searchComfirm()}">

                    <input type="image" src="<%=contextPath%>/static/img/icon.png" id="search-submit" onclick="searchComfirm()">
                </div>
            </div>

            <script>
                function searchComfirm(){
                    const tagList = document.getElementsByClassName("tag-clicked");
                    let String = "";
                    for (let tag of tagList){
                        String += tag.value + "!";
                    }
                    location.href='<%=contextPath%>/main.sl?cpage=1&category=${param.category}&search_type=' 
                    + encodeURIComponent(document.getElementById('search-select').value) + '&orderby=${param.orderby}&search_text=' + encodeURIComponent(document.getElementById('search-text').value) + '&tag_list=' + encodeURIComponent(String)
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
                        const tagList = document.getElementsByClassName("tag-clicked");
                        let String = "";
                        for (let tag of tagList){
                            String += tag.value + "!";
                        }
                        location.href='<%=contextPath%>/main.sl?cpage=1&category=${param.category}&orderby=' + num + '&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value) + '&tag_list=' + encodeURIComponent(String)
                    }
                </script>

                <div id="tag-popup-button">
                   <button onclick="tagPopup()">태그 필터</button>
                </div>
                
            </div>
            <div id="tag-popup" style="display: none;">
                <div id="tag-top">
                    <div id="tag-search">
                        <input type="text" onchange="tagSearchAjax()">
                    </div>
                    <div id="tag-search-exit-button" onclick="closeTagPopup()">
                        <input type="image" src="<%=contextPath%>/static/img/searchMainPage/back.png" onclick="pageChange(<%=currentPage%>)">
                    </div>
                </div>
                <br>
                <div id="tag-clear-confirm">
                    <button onclick="tag_clear()">🧹</button>
                    <button onclick="pageChange(<%=currentPage%>)">☑️</button>
                </div>
                <div id="tag-selected-div">
                    
                </div>
                <div id="tag-popup-tags">

                </div>
            </div>
            <script>
                function tagPopup(){
                    document.getElementById("tag-popup").style.display = "flex";
                    tagSearchAjax()
                }
                function closeTagPopup(){
                    document.getElementById("tag-popup").style.display = "none";
                }
                function tagSearchAjax(){
                    $.ajax({
                        url: "tgSearch.sl",
                        contentType: "application/json",
                        data: {
                            searchText: document.querySelector("#tag-search input").value
                        },
                        success: function(res){
                            

                            const tags = document.getElementById("tag-popup-tags")
                            const clickedtags = document.getElementById("tag-selected-div")
                            tags.innerHTML = "";
                            let tagLiist = document.getElementsByClassName("tag-clicked");
                            let check = 0;

                            if(tttList == "null") { 
                                for(let tag of res){
                                    check = 0;
                                    for (let tagli of tagLiist){
                                        if(tag.quizTag == tagli.value){
                                            check = 1;
                                        }
                                    }
                                    if(check == 0){
                                        tags.innerHTML += "<button onclick='lol(this)' value='" + tag.quizTag + "'>" + tag.quizTag + " / " + tag.count + "</button>";
                                    }
                                }
                            } else { 
                                for(let tag of res){
                                    check = 0;
                                    for(let tt of tttList) {
                                        if(tt == tag.quizTag){
                                            check = 1;
                                        }
                                    }
                                    if(check == 1) {
                                        clickedtags.innerHTML += "<button onclick='lol(this)' value='" + tag.quizTag + "' class='tag-clicked'>" + tag.quizTag + " / " + tag.count + "</button>";
                                    } else {
                                        tags.innerHTML += "<button onclick='lol(this)' value='" + tag.quizTag + "'>" + tag.quizTag + " / " + tag.count + "</button>";
                                    }
                                }
                                tttList = "null"
                            }

                            
                            

                        },
                        error: function(){
                            console.log("태그 조회용 ajax통신 실패")
                        }
                    })
                }
                function lol(wow){
                    if(wow.classList.contains('tag-clicked')){
                        wow.classList.remove('tag-clicked');
                        updateTagSelect();
                    } else{
                        wow.classList.add('tag-clicked');
                        updateTagSelect();
                    }
                }
                function updateTagSelect(){
                    const divvv = document.getElementById("tag-selected-div");
                    let tagList = document.querySelectorAll('.tag-clicked');
                    let String = "";
                    for (let tag of tagList){
                        String += "<button onclick='lol(this)' value='" + tag.value + "' class='tag-clicked'>" + tag.innerText + "</button>";
                    }
                    divvv.innerHTML = String;
                    tagSearchAjax();
                }
                function tag_clear(){
                    let tagLiist = document.querySelectorAll('.tag-clicked');
                    for(let sst of tagLiist){
                        if(sst.classList.contains('tag-clicked')){
                            sst.classList.remove('tag-clicked');
                            
                        }
                    }
                    updateTagSelect();
                }
            </script>
            <br>
            <div class="contents">

                <section class="video-flex">
                    <% if(list.isEmpty()) { %>
                        <p align="center">조건에 맞는 퀴즈가 존재하지 않스빈다.</p>
                    <% } else { %>
                        <% for(Quiz q : list) { %>
                            <div class="video-priview" onclick="clickQuiz(<%=q.getQuiz_number()%>)">
                                <div class="thumbnail-row">
                                    <%if(q.getThumbnail() == null){ %>
                                        <img class="thumbnail" src="<%=contextPath%>/static/img/THUMBNAIL/default.png" alt="">
                                    <% } else { %>
                                        <img class="thumbnail" src="<%=contextPath%>/<%=q.getThumbnail()%>" alt="">
                                    <% } %>
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

            <script>
                function clickQuiz(qnum){
                    location.href='<%=contextPath%>/click.sl?' + 'quiz_number=' + qnum + '&page=2'
                }
            </script>

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
                    const tagList = document.getElementsByClassName("tag-clicked");
                    let String = "";
                    for (let tag of tagList){
                        String += tag.value + "!";
                    }
                    location.href='<%=contextPath%>/main.sl?cpage=' + num + '&category=${param.category}&orderby=${param.orderby}&search_type=' + encodeURIComponent(document.getElementById('search-select').value)
                                        + '&search_text=' + encodeURIComponent(document.getElementById('search-text').value) + '&tag_list=' + encodeURIComponent(String)
                }
            </script>





        </div>














    </div>
    
    
        

</body>
</html>