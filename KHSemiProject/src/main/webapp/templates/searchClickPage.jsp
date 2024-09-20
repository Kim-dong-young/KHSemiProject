<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../static/css/searchClickPage.css">
</head>
<body>
    <%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <div id="div-top">
            <div id="image-div">
                <div class="image-container">
                    <img src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="" id="image-playing">
                    <div id="image-divs">
                        <div id="image-divs-1">9.56</div>
                        <div id="image-divs-2">58만</div>
                    </div>
                </div>
                <div>
                    <button onclick="location.href='searchMainPage.jsp'">뒤로가기</button>
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