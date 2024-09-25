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
            <li><a href="templates/userset1.jsp">프로필 설정</a></li>
            <li><a href="templates/userset2.jsp">개인정보 설정</a></li>
            <li><a href="templates/userset3.jsp">내 정보 설정</a></li>
            <li><a href="templates/userset4.jsp">회원 탈퇴 설정</a></li>
        </ul>
    </nav>
    <main>
    <fieldset>
    <section id="personal-info" class="content-section">
        <h2>개인정보 설정</h2>
        <form>
            <div class="personal-details">
            <label for="email">이메일:</label>
            <input type="email" id="email" name="email" placeholder="이메일을 입력하세요.">

            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" placeholder="현재 비밀번호">

            <label for="new-password">새 비밀번호:</label>
            <input type="password" id="new-password" name="new-password" placeholder="새 비밀번호">

            <label for="confirm-password">새 비밀번호 확인:</label>
            <input type="password" id="confirm-password" name="confirm-password" placeholder="새 비밀번호 확인">
            </div>
            <button type="submit">변경 완료</button>
        </form>
    </section>
    </fieldset>
    </main>
    <script src="static/js/userset.js"></script>
</body>
</html>