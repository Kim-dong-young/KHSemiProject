<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String alertMsg = (String)session.getAttribute("alertMsg");

	Member loginMember = (Member)session.getAttribute("loginMember");

    String pageName = (String)request.getAttribute("pageName");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <!-- JavaScript -->
    <script src="<%=request.getContextPath()%>/static/js/menu.js"></script>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/default.css">
</head>
<% if(pageName != null ) { %>
<body onload="init('${pageName}', '${optional}'); signinit();">
<% } else { %>
<body>
<% } %>
	<%@ include file="loginModal.jsp" %>
	<% if(alertMsg != null) {%>
		<script>
			alert("<%=alertMsg%>");
		</script>
		<% session.removeAttribute("alertMsg"); %>
	<% } %>
    <div id="header">
        <div id="logo-img"><a href="<%=request.getContextPath()%>/main.me"><img src="<%=request.getContextPath()%>/static/img/logo.png" alt="퀴즈팡 로고"></a></div>
        <div id="welcome-user">
        	<c:choose>
        		<c:when test="${empty loginMember}">
        			<button type="button" class="btn custom-btn login-btn" data-bs-toggle="modal" data-bs-target="#login-modal">로그인</button>
        		</c:when>
        		<c:otherwise>
        			<a href="<%=contextPath%>/logout.me">로그아웃</a>
        		</c:otherwise>
        	</c:choose>
        </div>
    </div>
    <div class="side-navi">
        <ul>
            <li><a href="<%=contextPath%>/main.me">홈</a></li>
            <li><a href="<%=contextPath%>/personal.me">개인화면</a></li>
            <li><a href="<%=contextPath%>/main.sl?cpage=1&category=0&search_type=0&orderby=2">탐색</a></li>
            <li><a href="<%=contextPath%>/userset.me">유저설정</a></li>
            <li><a href="<%=contextPath%>/community?cpage=1">커뮤니티</a></li>
            <li><a href="<%=contextPath%>/insert.quiz">문제만들기</a></li>
            <li id="help" ><a onclick="document.getElementById('help').textContent = '🥕🥕🥕'">도움!</a></li>
        </ul>
    </div>
</body>
</html>