<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.playQuiz.model.vo.Problem"%>
<%
    ArrayList<Problem> pList = (ArrayList<Problem>)request.getAttribute("pList");
        String contextPath2 = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퀴즈팡</title>
    <script src="<%=contextPath2%>/static/js/Quiz Screen(Media O).js"></script>
    <link rel="stylesheet" href="<%=contextPath2%>/static/css/QuizScreen(Media O).css">
</head>
<body>
<div style="display: none;" >
    <%@ include file="common/menu.jsp" %>
</div>
<div id="quiz-container">
    <div id="header">
        <div id="title">
            <img src="<%=contextPath%>/static/img/logo.png" alt="퀴즈 로고">
        </div>
        <div id="right-header">
            
        </div>
    </div>

    <div id="timer-container">
        <img src="<%=contextPath%>/static/img/Timer.png" alt="타이머 아이콘" class="icon">
        <div id="progress-bar">
            <div id="progress"></div>
        </div>
        <div id="timer-value">30</div>          
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
    </button>
</div>
<script>
    function startTimer(duration) {
        const progressBar = document.getElementById('progress');
        const timerValue = document.getElementById('timer-value');
        let remainingTime = duration;

        progressBar.style.width = '100%'; 
        timerValue.textContent = remainingTime; 

        const interval = setInterval(() => {
            if (remainingTime > 0) {
                remainingTime--; 
                const percentage = (remainingTime / duration) * 100;
                progressBar.style.width = percentage + `%`; 

                timerValue.textContent = remainingTime; 
                console.log(`남은 시간: ${remainingTime}, 진행 바 너비: ${progressBar.style.width}`);
            }

            if (remainingTime <= 0) {
                clearInterval(interval); 
                console.log('시간이 다됐습니다!');
            }
        }, 1000);
    }   


    startTimer(15);


</script>
</body>
</html>