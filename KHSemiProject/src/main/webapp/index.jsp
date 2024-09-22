<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퀴즈팡</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="static/css/index.css">
</head>
<body>
	<%@ include file="templates/common/loginModal.jsp" %>

    <!-- <a href="templates/mainPage.jsp">메인 페이지로</a> -->
	<div class="wrapper">
        <div class="section1">
            <div class="title">
                <div class="title-logo"><img src="static/img/logo.png"></div>
                <div class="title-desc">
                    <p>나만의 퀴즈쇼, 퀴즈팡</p>
                    <span>
                        퀴즈팡은 여러 유저의 다양한 퀴즈를 풀어볼 수 있는 퀴즈 사이트입니다.<br>
                        나만의 퀴즈를 만들어 여러 유저들과 공유해보세요!
                    </span>
                </div>
            </div>
            
            <div class="content">
                <div class="image"><img src="static/img/section1.png"></div>
                <div class="login">
                    <button data-bs-toggle="modal" data-bs-target="#login-signin-modal"><img src="static/img/playbutton.png">시작하기</button>
                    <button onclick="location.href='templates/mainPage.jsp'"><img src="static/img/google-icon.png">소셜 아이디로 로그인하기</button>
                    <button onclick="location.href='templates/mainPage.jsp'"><img src="static/img/guest-icon.png">게스트 계정으로 플레이하기</button>
                </div>
            </div>
        </div>

        <div class="section2">
            
        </div>

        <div class="section3">

        </div>

        <div class="section4">

        </div>

        <div class="section5">

        </div>

        <div class="footer">
            <span>
                Image by storyset 
                https://www.freepik.com/free-vector/quiz-showconcept-illustration_32784687.htm#fromView=keyword&page=1&position=3&uuid=d7742fd7-7f41-4ea1-8fed-7481c5c29679
            </span>
            <br>
            <span>
                https://www.freepik.com/free-vector/team-happy-cartoon-people-taking-first-place_18734164.htm#fromView=search&page=1&position=28&uuid=e655ecee-cd36-4666-aabe-4105b24194b4">Image by pch.vector on Freepik
            </span>
            <br>
            <span>
                https://www.freepik.com/free-vector/users-with-hashtag-vector_3833322.htm#fromView=search&page=2&position=9&uuid=316a80aa-d953-4ebe-a08c-a79cfb6d1379">Image by rawpixel.com on Freepik
            </span>
        </div>
    </div>
</body>
</html>