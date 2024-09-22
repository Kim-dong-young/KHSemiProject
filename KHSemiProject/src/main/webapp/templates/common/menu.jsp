<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
=======
<%
    String contextPath = request.getContextPath();
%>
>>>>>>> 02660b56ec7264d40dcad54e3b50c601eb8236ae
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <!-- jQuery -->
    <script 
        src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
    <script
        src="https://code.jquery.com/ui/1.14.0/jquery-ui.min.js"
        integrity="sha256-Fb0zP4jE3JHqu+IBB9YktLcSjI1Zc6J2b6gTjB0LpoM="
        crossorigin="anonymous"></script>
    
    <!-- Google font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<<<<<<< HEAD
	<link rel="stylesheet" href="static/css/default.css">
</head>
<body>
	<%@ include file="loginModal.jsp" %>

	<% if(alertMsg != null) {%>
		<script>
			alert("<%=alertMsg%>");
		</script>
		<% session.removeAttribute("alertMsg"); %>
	<% } %>
    <div id="header">
        <div id="logo-img"><a href=""><img src="static/img/logo.png" alt="퀴즈팡 로고"></a></div>
        <div id="welcome-user">
        	<c:choose>
        		<c:when test="${empty loginMember}">
        			<button type="button" class="btn custom-btn" data-bs-toggle="modal" data-bs-target="#login-signin-modal">로그인</button>
        		</c:when>
        		<c:otherwise>
        			${loginMember.memberNickName}님 환영합니다.
        		</c:otherwise>
        	</c:choose>
=======
    <link rel="stylesheet" href="static/css/default.css">
</head>
<body>
    <div class="header">
        <div id="logo-img"><a href=""><img src="static/img/logo.png" alt=""></a></div>
        <div id="welcome-user">User01님 환영합니다.</div>
    </div>
    <div class="container">
        <div class="side-navi">
            <ul>
                <li><a href="<%=contextPath%>/main.me">홈</a></li>
                <li><a href="personalScreen.jsp">개인화면</a></li>
                <li><a href="searchMainPage.jsp">탐색</a></li>
                <li><a href="userset.jsp">유저설정</a></li>
                <li><a href="<%=contextPath%>/list.bo">커뮤니티</a></li>
                <li><a href="">문제만들기</a></li>
                <li><a href="">도움말</a></li>
            </ul>
>>>>>>> 02660b56ec7264d40dcad54e3b50c601eb8236ae
        </div>
    </div>
    <div class="side-navi">
        <ul>
            <li><a href="mainPage.jsp">홈</a></li>
            <li><a href="personalScreen.jsp">개인화면</a></li>
            <li><a href="searchMainPage.jsp">탐색</a></li>
            <li><a href="userset.jsp">유저설정</a></li>
            <li><a href="communityMainPage.jsp">커뮤니티</a></li>
            <li><a href="">문제만들기</a></li>
            <li><a href="">도움말</a></li>
        </ul>
    </div>
</body>
</html>