<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.kh.common.PageInfo, java.util.ArrayList, com.kh.search.model.vo.Quiz, com.kh.search.model.vo.Tag" %>

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
                        <div id="image-divs-1">9.56</div>
                        <div id="image-divs-2">58만</div>
                    </div>
                </div>
                <div>
                    <button onclick="location.href='<%=contextPath%>/main.sl?cpage=${param.cpage}&category=${param.category}&orderby=${param.orderby}&search_type=${param.search_type}'
                                        + '&search_text=' + '${param.search_text}' + '&tag_list=${param.tag_list}'">뒤로가기</button>
                    <button>플레이</button>
                </div>
            </div>
            <div>
                <div>
                    <div>기타</div>
                    <p>인물 이름 맞추기</p>
                </div>
                <div>
                    <div>
                        보자마자 답 바로 나오는 인물 퀴즈~~~
                    </div>
                    <br>
                    <button>#인물</button>
                    <button>#KH</button>
                </div>
                <div>
                    <button></button>
                    <button></button>
                    <button></button>
                    <button></button>
                    <button></button>
                </div>
            </div>
        </div>
        <div>
            <div>

            </div>
        </div>
    </div>
</body>
</html>