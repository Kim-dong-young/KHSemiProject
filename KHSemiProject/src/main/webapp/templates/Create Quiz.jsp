<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퀴즈 생성</title>
    <link rel="stylesheet" href="Create Quiz.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="QuizLogo.png" class="logo">
        </div>

        <div class="quiz-layout">
            <div class="question-list">
                <div class="question-item">
                    <span class="question-number">1.</span>
                    <div class="image-placeholder"></div>
                </div>
                <div class="question-item">
                    <span class="question-number">2.</span>
                    <div class="image-placeholder"></div>
                </div>
                <div class="question-item">
                    <span class="question-number">3.</span>
                    <div class="image-placeholder"></div>
                </div>
                <div class="question-item">
                    <span class="question-number">4.</span>
                    <div class="image-placeholder"></div>
                </div>
                <button class="add-question-btn">질문 추가</button>
            </div>

            <div class="quiz-content">
                <div class="question-type">
                    <label>문제 유형</label>
                    <div class="progress">
                        1/4
                    </div>
                    <div class="type-btns">
                        <button class="type-btn">객관식</button>
                        <button class="type-btn">주관식</button>
                        <button class="type-btn">O / X</button>
                    </div>
                </div>

                <div class="question-input">
                    <label for="question">질문 내용:</label>
                    <input type="text" id="question" placeholder="질문을 입력하세요">
                </div>
                
                <div class="media">
                    <input type="file">
                </div>

                <div class="time-limit">
                    <label>제한시간</label>
                    <input type="radio" name="time" value="15"> 15초
                    <input type="radio" name="time" value="30"> 30초
                    <input type="radio" name="time" value="45"> 45초
                </div>

                <div class="hint-answer">
                    <label for="hint">힌트:</label>
                    <input type="text" id="hint" placeholder="없을 경우 '.'을 입력해 주세요">
                    
                    <label for="answer">정답:</label>
                    <input type="text" id="answer" placeholder="정답을 입력해 주세요">
                </div>

                <div class="buttons">
                    <button class="home-btn">홈</button>
                    <button class="create-btn">질문 생성하기</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
