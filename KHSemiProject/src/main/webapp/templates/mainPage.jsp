<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <%@ include file="common/menu.jsp" %>
    <link rel="stylesheet" href="../static/css/mainPageContent.css">

    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <div id="content1">
            <div>이런 퀴즈는 어떤가요?~</div>
            <div class="recommend-button">
                <ul>
                    <li><button>최신</button></li>
                    <li><button>조회</button></li>
                    <li><button>평점</button></li>
                    <li><button>장르</button></li>
                </ul>
            </div>
            <div class="quiz-list">
                <ul>
                    <li class="quiz-box">
                        <div class="thumbnail">썸네일</div>
                        <div class="title">제목</div>
                    </li>
                    <li class="quiz-box">
                        <div class="thumbnail">썸네일</div>
                        <div class="title">제목</div>
                    </li>
                    <li class="quiz-box">
                        <div class="thumbnail">썸네일</div>
                        <div class="title">제목</div>
                    </li>
                </ul>
            </div>
        </div>
        <div id="content2">
            <div id="content2-left">
                <div></div>
                <div></div>
                <div></div>
            </div>
            <div id="content2-right">
                <div></div>
                <div></div>
            </div>
        </div>
    </div>
</body>
</html>