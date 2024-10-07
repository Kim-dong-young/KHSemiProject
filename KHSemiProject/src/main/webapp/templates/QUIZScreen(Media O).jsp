<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퀴즈팡</title>
    <link rel="stylesheet" href="<%=contextPath%>/static/css/Quiz Screen(Media O).css">
</head>
<body>

    <div id="quiz-container">
        <div id="header">
            <div id="title">
                <img src="<%=contextPath%>/static/img/QuizLogo.png" alt="">
            </div>
            <div id="right-header">
                <div class="xp-box">
                    <span>등급 : L V.13</span> <br>
                    <div>44,642/50,000 XP</div>
                </div>
                <div class="score">6/10</div>
            </div>
        </div>

        <div id="timer-container">
            <img src="<%=contextPath%>/static/img/Timer.png" alt="타이머 아이콘" class="icon">
            <div id="progress-bar">
                <div>30</div>
            </div>
        </div>

        <div id="quiz-content">
            <div class="Media-area">
                <div id="text1">미디어 영역</div>
            </div>
            <div class="question-and-answer">
                <div class="question">
                    <div id="text2">질문 내용</div>
                </div>
                <div class="answer-container">
                    <input type="text" id="answer-input" placeholder="정답 입력">
                    <button id="submit-btn">제출</button>
                </div>
            </div>
        </div>

        <button id="home-btn">
            <img src="<%=contextPath%>/static/img/homebtn.png" alt="홈 버튼">
            <a href="<%=contextPath%>/main.me"></a>
        </button>
    </div>

</body>
</html>
