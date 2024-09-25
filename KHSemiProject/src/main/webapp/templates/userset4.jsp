<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <section id="other" class="content-section">
        <h2>회원 탈퇴 설정</h2>
        <form>
            <div class="other-details">
            <label for="theme">테마 선택:</label>
            <select id="theme" name="theme">
                <option value="light" selected>라이트 모드</option>
                <option value="dark">다크 모드</option>
            </select>
            <button type="submit">저장</button>
        </form>
    </section>
    </fieldset>
    </main>
    <script src="static/js/userset.js"></script>
</body>
</html>