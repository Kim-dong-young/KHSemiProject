<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.common.PageInfo, java.util.ArrayList, com.kh.search.model.vo.Quiz, com.kh.search.model.vo.Tag" %>
<%
    Quiz q = (Quiz)request.getAttribute("Quiz");
    int viewCount = (int)request.getAttribute("viewCount");
    String rate = (String)request.getAttribute("quiz_rate");
    if (rate.equals("")){
        rate = "0";
    }
    ArrayList<Quiz> list = (ArrayList<Quiz>)request.getAttribute("list");
    ArrayList<Tag> tagList = (ArrayList<Tag>)request.getAttribute("TagArr");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="static/css/searchClickPage.css">
</head>
<body>
    <%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <div id="div-top">
            <div id="image-div">
                <div class="image-container">
                    <img src="static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="" id="image-playing">
                    <div id="image-divs">
                        <div id="image-divs-1"><%=rate%></div>
                        <div id="image-divs-2"><%=viewCount%></div>
                    </div>
                </div>
                <div>
                    <button onclick="location.href='<%=contextPath%>/main.sl?cpage=${param.cpage}&category=${param.category}&orderby=${param.orderby}&search_type=${param.search_type}'
                                        + '&search_text=' + '${param.search_text}' + '&tag_list=${param.tag_list}'">뒤로가기</button>
                    <% if(loginMember != null){ %>
                        <button onclick="clickMark()" id="Mark">북마크</button>
                    <% } %>
                    <button>공유</button>
                    <button>신고</button>
                    <button>플레이</button>
                </div>
            </div>
            <script>
                function clickMark(){
                    $.ajax({
                        url: "mkQuiz.sl",
                        contentType: "application/json",
                        data: {
                            quizNum: <%=q.getQuiz_number()%>,
                            userNum: <%=loginMember.getMemberNumber()%>
                        },
                        success: function(res){
                            const mk = document.getElementById("Mark");
                            mk.classList.add('marked');
                            mk.classList.remove('marked');
                        }
                    })
                }
            </script>
            <div>
                <div>
                    <div><%=q.getCategory_name()%></div>
                    <p><%=q.getQuiz_title()%></p>
                </div>
                <div>
                    <div>
                        <%=q.getQuiz_explanation()%>
                    </div>
                    <br>
                    <% for (int i = 0; i < tagList.size(); i++){ %>
                        <button>#<%=tagList.get(i).getQuizTag()%></button>
                    <% } %>
                </div>
                <div>
                    
                </div>
            </div>
        </div>
        <div>
            <p>유사한 퀴즈</p>
            <section class="video-flex" id="video-flex">
                <% if(list.isEmpty()) { %>
                    <p align="center">비슷한 퀴즈가 없어!</p>
                <% } else { %>
                    <% for(Quiz qq : list) { %>
                        <% if(qq.getQuiz_number() != q.getQuiz_number()) { %>
                            <div class="video-priview" onclick="clickQuiz(<%=qq.getQuiz_number()%>)"></div>
                                <div class="thumbnail-row">
                                    <img class="thumbnail" src="static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                                </div>
                                <div class="video-info-grid">
                                    <div class="video-info">
                                        <p class="video-title">
                                            <%=qq.getQuiz_title()%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        <% } %>
                    <% } %>
                <% } %>
            </section>
        </div>
        <script>
            function clickQuiz(qnum){
                location.href='<%=contextPath%>/click.sl?cpage=${param.cpage}&category=${param.category}&orderby=${param.orderby}&search_type=${param.search_type}'
                                        + '&search_text=' + '${param.search_text}' + '&tag_list=${param.tag_list}' + '&quiz_number=' + qnum
            }
            if(document.getElementById("video-flex").innerText == ""){
                document.getElementById("video-flex").innerHTML = "비슷한 퀴즈가 없어!"
            }
        </script>
    </div>
</body>
</html>