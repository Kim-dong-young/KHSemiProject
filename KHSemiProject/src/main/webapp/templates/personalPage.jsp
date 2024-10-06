<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
			<div class="search">
				<div class="sub-option">
					<select name="opt">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="titleNContent">제목+내용</option>
					</select>
				</div>
	            <input class="search-bar" type="search" placeholder="검색 키워드를 입력하세요...">
				<button class="search-button"><img src="static/img/search-icon.png"></button>
	        </div>
			<div class="search-sub-opt">
				<div class="recommend-button">
					<ul>
						<li><button class="custom-btn" name="latest" value="latest" disabled>최신</button></li>
						<li><button class="custom-btn" name="inquiry" value="inquiry">가나다</button></li>
						<!-- <li><button class="custom-btn" name="grade" value="grade"></button></li> -->
					</ul>
				</div>
				<div class="sub-opt-rating">
					<select name="showOpt" onchange="changeVal()">
						<option value="create" selected>내가 만든 문제</option>
						<option value="bookmark">북마크한 문제</option>
						<option value="solved">내가 풀었던 문제</option>
					</select>
				</div>
			</div>
		</div>
    	<div class="leftBox">
	        <div class="interestList">
				<!-- <div class="quizbox">
					<div class="thumnail">썸네일입니다.</div>
					<div class="title">제목입니다.</div>
				</div> -->
	        </div>
			<div class="rightBox"></div>
        </div>
    </body>
</html>
