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
<title>퀴즈팡-퀴즈 생성</title>
<link rel="stylesheet" href="<%=contextPath%>/static/css/CreateQuiz.css">

<script defer src="<%=contextPath%>/static/js/CreateQuiz.js"></script>

</head>
<body>
    <div class="container">
        <div class="header">
            <img src="<%=contextPath%>/static/img/QuizLogo.png" class="logo">
        </div>

    
            <div class="quiz-layout">
                <div class="question-list" id="question-list">
                    <div class="question-item" data-page="1">
                        <span class="question-number">1.</span>
                        <div class="image-placeholder"></div>
                    </div>
                    <div>
                        <button class="add-question-btn">질문추가</button>
                        <button class="delete-question-btn">삭제하기</button>
                    </div>
                </div>
                <form action="<%=contextPath%>/problems.co" method="post" enctype="multipart/form-data">
                    <div id="quiz-content-wrapper" class="quiz-content">
                        <div class="quiz-slide">
                            <div class="question-type">
                                
                                <div class="progress">${totalPages}/${totalPages}</div>
                                
                            </div>
                            <input type="hidden" name="quiz_number" value="${quiz_num}">
                            <div class="question-input">
                                <label for="question-1">질문 내용:</label> 
                                <input type="text" id="question-1" name="pcontent-1" placeholder="질문을 입력하세요">
                            </div>

                            <div>
                                <input type="file" name="file-1" id="file-1" accept="image/*">
                            </div>
                            <div class="media">
                                <img id="preview-1" src="" alt="미리보기 이미지">
                            </div>

                            <div class="time-limit">
                                <label>제한시간</label> 
                                <input type="radio" name="ptime-1" value="15" checked="checked">15초 
                                <input type="radio" name="ptime-1" value="30">30초 
                                <input type="radio" name="ptime-1" value="45">45초
                            </div>

                            <div class="hint-answer">
                                <label for="hint-1">힌트:</label> 
                                <input type="text" id="hint-1" name="phint-1" placeholder="없을 경우 '.'을 입력해 주세요"> 
                                <label for="answer-1">정답:</label>
                                <input type="text" id="answer-1" name="panswer-1" placeholder="정답을 입력해 주세요">
                            </div>

                            <div class="buttons">
                                <a class="home-btn" href="<%=contextPath%>/main.me">
                                    <img src="<%=contextPath%>/static/img/homebtn.png"  width="135px" height="45px">
                                </a>
                                <button class="create-btn" type="submit">질문 생성하기</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
    </div>
</body>
</html>
