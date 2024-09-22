<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<link rel="stylesheet" href="static/css/communityWritePage.css">

</head>
<body>
	<%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <p>글 쓰기</p>
        <div class="wrapper">
            <div class="board">
                <div class="board-write">
                    <div class="tab-select">
                        <span>탭 선택</span>
                        <button>질문</button>
                        <button>풀이</button>
                        <button>잡담</button>
                    </div>

                    <div class="title-input">
                        <input type="text" placeholder="글 제목을 입력해주세요.">
                    </div>

                    <div class="content-input">
                        <div class="input-option">
                            <img src="static/img/bold-icon.png">
                            <img src="static/img/italic-icon.png">
                            <img src="static/img/underlined-icon.png">
                            <img src="static/img/strikethrough-icon.png">
                            <img src="static/img/fontsize-icon.png" style="margin-right:15px;">
                            <img src="static/img/color-icon.png">
                            <img src="static/img/fill-icon.png">
                        </div>
                        <textarea></textarea>
                    </div>
                </div>

                <div class="board-write-option">
                    <a href="communityMainPage.jsp"><img src="static/img/pen-icon-white.png">글쓰기</a>
                </div>

            </div>

            <div class="board-side">
                <%@ include file="communitySidePage.jsp" %>
            </div>
        </div>

    </div>
</body>
</html>