<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/personalPage.css">
<script src="static/js/personalPage.js"></script>
</head>
    <body>
	<%@ include file="common/menu.jsp" %>
    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
		<div class="topBox">
			<div class="sub-option">
				<select name="opt">
					<option value="1">내가 만든 문제</option>
					<option value="2">북마크한 문제</option>
					<option value="3">내가 풀었던 문제</option>
				</select>
			</div>
			<div class="search">
	            <input class="search-bar" type="search" placeholder="검색 키워드를 입력하세요...">
				<button class="search-button"><img src="static/img/search-icon.png"></button>
	        </div>
		</div>
    	<div class="leftBox">
	        <div class="interestList">
				<!-- <div class="quizbox">
					<div class="thumnail">썸네일입니다.</div>
					<div class="title">제목입니다.</div>
				</div> -->
	        </div>
			<script>
				window.onload = function() {
    const mno = ${loginMember.memberNo};  // 서버에서 넘겨받은 memberNo

    $.ajax({
        url: "myquiz.sl",    // 서버로 보낼 URL
        type: "post",        // POST 요청
        data: {
            memberNo: mno    // 서버로 보낼 데이터
        },
        success: function(res) {
            console.log(res);  // 서버 응답 확인

            // 퀴즈 데이터를 담을 HTML을 동적으로 생성
            const interestList = document.querySelector('.interestList');  // 첫 번째 .interestList 요소 선택
            
			for(let myQuiz of res) {
				interestList.innerHTML += "<div class='quizbox'>" +
											   "<div class='thumnail'>썸네일입니다.</div>" + 
											   "<div class='title'>" + myQuiz.quiz_title + "</div>" + 
										   "<div>"
			}

            // 생성한 HTML을 innerHTML로 삽입
            if (interestList) {
                interestList.innerHTML = htmlContent;
            } else {
                console.error('Element with class "interestList" not found.');
            }
        },
        error: function() {
            alert("조회 실패");
        }
    });
};

			</script>
			<div class="rightBox"></div>
        </div>
    </body>
</html>
