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
    <title>Quiz 생성</title>
    <link rel="stylesheet" href="./static/css/CreateQuizMain.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="./static/img/QuizLogo.png" alt="Quiz Logo" class="logo">
        </div>
        <div class="quiz-form">
            <label for="quiz-title">퀴즈 제목:</label>
            <input type="text" id="quiz-title" placeholder="제목을 입력해주세요.">
            
            <label for="quiz-description">퀴즈 설명:</label>
            <textarea id="quiz-description" placeholder="텍스트로 작성"></textarea>
            
            <label for="thumbnail">썸네일:</label>
            <div class="thumbnail-upload">
                <input type="file" id="thumbnail" accept="image/*">
                <label for="thumbnail">파일 업로드</label>
            </div>
            <div class="screen" id="thumbnail-preview"></div>
            
            <div class="category-tag-row">
                <div class="form-group">
                    <label for="category">카테고리:</label>
                    <select id="category">
                        <option value="">선택</option>
                        <option value="humor">유머</option>
                        <option value="art_literature">예술/문학</option>
                        <option value="world">세계</option>
                        <option value="history">역사</option>
                        <option value="language">언어</option>
                        <option value="science_nature">과학/자연</option>
                        <option value="sports">스포츠</option>
                        <option value="etc">기타</option>
                    </select>
                </div>
                
                <div class="form-group"> 
                    <label for="tag">태그 설정:</label>
                    <input type="text" id="tag" placeholder="직접 입력">
                </div>
            </div>
            <div class="buttons">
                <button class="home-btn">
                    <a href="<%=contextPath%>/main.me"></a>
                    <img src="./static/img/homebtn.png" width="130px" height="45px">
                </button>
                <button class="create-btn" onclick="saveQuiz()">질문 생성하기</button> 
            </div>
        </div>
    </div>


    <script src="./static/js/Create QuizMain.js"></script>
</body>
</html>

