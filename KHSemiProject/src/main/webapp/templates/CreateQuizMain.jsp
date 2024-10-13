<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퀴즈팡 - 퀴즈 생성</title>
    <link rel="stylesheet" href="<%=contextPath%>/static/css/CreateQuizMain.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <img src="<%=contextPath%>/static/img/QuizLogo.png" alt="Quiz Logo" class="logo">
        </div>
        <form class="quiz-form" action="<%=contextPath%>/create.quiz" method="post" enctype="multipart/form-data">
            <label for="quiz-title">퀴즈 제목:</label> 
            <input type="text" id="quiz-title" name="title" placeholder="제목을 입력해주세요." required> 

            <label for="quiz-description">퀴즈 설명:</label>
            <textarea id="quiz-description" name="explanation" placeholder="텍스트로 작성" required></textarea>

            <label for="thumbnail">썸네일:</label>
            <div class="thumbnail-upload">
                <input type="file" id="thumbnail" name="thumbnail" accept="image/*"> 
                <label for="thumbnail">파일 업로드</label>
            </div>
            <div class="screen" id="thumbnail-preview"></div>

            <div class="category-tag-row">
                <div class="form-group">
                    <label for="category">카테고리:</label> 
                    <select id="category" name="category" required>
                        <option value="">선택</option>
                        <option value="1">유머</option>
                        <option value="2">예술/문학</option>
                        <option value="3">세계</option>
                        <option value="4">역사</option>
                        <option value="5">언어</option>
                        <option value="6">과학/자연</option>
                        <option value="7">스포츠</option>
                        <option value="8">기타</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="tag">태그 설정:</label> 
                    <input type="text" id="tag" name="tag" placeholder="태그를 추가해 보세요( 최대 5개, 쉼표(,)로 구분 )">
                </div>
            </div>
            <div class="buttons">
                <a href="<%=contextPath%>/main.me" class="home-btn">
                    <img src="<%=contextPath%>/static/img/homebtn.png" width="130px" height="45px">
                </a>
                <button class="create-btn" type="submit">질문 생성하기</button>
            </div>
        </form>
    </div>

    <script src="<%=contextPath%>/static/js/CreateQuizMain.js"></script>
</body>
</html>
