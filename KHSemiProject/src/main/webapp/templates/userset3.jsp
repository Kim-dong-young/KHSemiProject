<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저설정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/userset.css">
</head>
<body>
	<%@ include file="common/menu.jsp" %>
    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
    <nav class="navbar">
        <ul class="navbar_menu">
            <li><a href="${pageContext.request.contextPath}/templates/userset1.jsp">프로필 설정</a></li>
            <li><a href="${pageContext.request.contextPath}/templates/userset2.jsp">개인정보 설정</a></li>
            <li><a href="${pageContext.request.contextPath}/templates/userset3.jsp">내 정보 설정</a></li>
            <li><a href="${pageContext.request.contextPath}/templates/userset4.jsp">회원 탈퇴 설정</a></li>
        </ul>
    </nav>
    <main>
    <fieldset>
    <section id="notifications" class="content-section">
            <h2>내 정보 설정</h2>
                <form>
                    <div class="notifications-details">

                            <label for="nickname">아이디:</label>
                            <input type="text" id="nickname" name="nickname">
                            <label for="phone">전화번호:</label>
                            <input type="text" id="phone" name="phone">
                            <label for="address">주소:</label>
                            <input type="text" id="address" name="address">
                        </div>
                    <button type="submit">변경</button>
                </form>
            </section>
        </fieldset>
        </main>
    <script src="static/js/userset.js"></script>
</body>
</html>