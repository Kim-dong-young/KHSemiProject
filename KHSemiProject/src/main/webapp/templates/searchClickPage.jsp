<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.common.PageInfo, java.util.ArrayList, com.kh.search.model.vo.Quiz, com.kh.search.model.vo.Tag, com.kh.member.model.vo.Member" %>
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
    
</head>
<body>
    <%@ include file="common/menu.jsp" %>
    
    <script>
        function init(){
            <c:choose>
                <c:when test="${empty loginMember}">
                </c:when>
                <c:otherwise>
                    $.ajax({
                    url: "mkQuizInit.sl",
                    contentType: "application/json",
                    data: {
                        quizNum:  <%=q.getQuiz_number()%>, 
                        member: ${loginMember.memberNo}
                    },
                    success: function(res){
                        const mk = document.getElementById("Mark");
                        console.log(res)
                        if(res == 1){
                            mk.classList.add('marked');
                        } else {
                            mk.classList.remove('marked');
                        }
                        
                        
                    },
                    error: function(){
                        console.log("태그 조회용 ajax통신 실패")
                    }
                })
                </c:otherwise>
            </c:choose>
        }
    </script>



	<link rel="stylesheet" href="static/css/searchClickPage.css">
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <div id="div-top">
            <div id="image-div">
                <div class="image-container">
                    <%if(q.getThumbnail() == null){ %>
                        <img id="image-playing" src="<%=contextPath%>/static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                    <% } else { %>
                        <img id="image-playing" src="<%=contextPath%>/<%=q.getThumbnail()%>" alt="">
                    <% } %>
                    <div id="image-divs">
                        <div id="image-divs-1"><%=rate%></div>
                        <div id="image-divs-2"><%=viewCount%></div>
                    </div>
                </div>
                <div>
                    <c:choose>
                        <c:when test="${param.page eq '1'}">
                            <button onclick="location.href='<%=contextPath%>/main.me'">뒤로가기</button>
                        </c:when>
                        <c:when test="${param.page eq '2'}">
                            <button onclick="location.href='<%=contextPath%>/main.sl?cpage=1&category=0&search_type=0&orderby=2'">뒤로가기</button>
                        </c:when>
                        <c:when test="${param.page eq '3'}">
                            <button onclick="location.href='<%=contextPath%>/personal.me'">뒤로가기</button>
                        </c:when>
                        <c:otherwise>
                            <button onclick="location.href='<%=contextPath%>/main.sl?cpage=1&category=0&search_type=0&orderby=2'">뒤로가기</button>
                        </c:otherwise>
                    </c:choose>

                    
                    <c:choose>
                        <c:when test="${empty loginMember}">
                        </c:when>
                        <c:otherwise>
                            <button onclick="clickMark(<%=q.getQuiz_number()%>, ${loginMember.memberNo})" id="Mark">북마크</button>
                        </c:otherwise>
                    </c:choose>
                    <button id="share-button" onclick="share('<%=contextPath%>', <%=q.getQuiz_number()%>)">공유</button>
                    <button>신고</button>
                    <button>플레이</button>
                </div>
            </div>
            <script>
                function share(a, b){
                    window.navigator.clipboard.writeText(window.location.origin + a + '/click.sl?quiz_number=' + b)
                        .then(() => {
                            document.getElementById("share-button").innerText = '링크복사 완료!'
                        })
                        .catch(err => {
                            console.error('텍스트 복사에 실패했습니다: ', err);
                        });
                }
                function clickMark(aaa, bbb){
                    $.ajax({
                        url: "mkQuiz.sl",
                        contentType: "application/json",
                        data: {
                            quizNum:  aaa,
                            member: bbb
                        },
                        success: function(res){
                            const mk = document.getElementById("Mark");
                            console.log(res)
                            if(res == 1){
                                mk.classList.add('marked');
                            } else {
                                mk.classList.remove('marked');
                            }
                            
                            
                        },
                        error: function(){
                            console.log("태그 조회용 ajax통신 실패")
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
                            <div class="video-priview" onclick="clickQuiz(<%=qq.getQuiz_number()%>)">
                                <div class="thumbnail-row">
                                    <%if(q.getThumbnail() == null){ %>
                                        <img class="thumbnail" src="<%=contextPath%>/static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                                    <% } else { %>
                                        <img class="thumbnail" src="<%=contextPath%>/<%=qq.getThumbnail()%>" alt="">
                                    <% } %>
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