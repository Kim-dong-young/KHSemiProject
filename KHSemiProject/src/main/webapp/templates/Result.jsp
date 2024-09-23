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
    <title>Quiz Result Page</title>
    <link rel="stylesheet" href="../static/css/Result.css">
</head>
<body>
    <div class="container">
        <div class="box">
            <p>당신은 상위 <span class="highlight">?</span>% 입니다.</p>
            <p>당신의 정답률: <span class="highlight">80%</span> <span class="highlight">16/20</span></p>
            <p>평균 정답률: <span class="highlight">?</span>%</p>
        </div>
        
        <div class="level-box">
            <p>LV.66</p>
            <div class="progress-bar">
                <div class="progress-fill"></div>
            </div>
        </div>

        <div class="rating-box">
            <p>별점</p>
            <div class="stars">
                <span class="star" data-value="1">★</span>
                <span class="star" data-value="2">★</span>
                <span class="star" data-value="3">★</span>
                <span class="star" data-value="4">★</span>
                <span class="star" data-value="5">★</span>
            </div>
            <p id="rating-value">별점을 통해 문제를 평가해주세요.</p>
        </div>

        <div class="buttons">
            <button class="button">질문하기</button>
            <button class="button">댓글보기</button>
        </div>

        <div class="bottom-buttons">
            <button class="report-btn">신고하기</button>
            <button class="button">
                <a href="<%=contextPath%>/main.me"></a>
                홈으로
            </button>
            <button class="button">다시하기</button>
        </div>
    </div>

    <script src="../static/js/Result.js"></script>
</body>
</html>
