<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../static/css/userset.css">
</head>
<body>
	<%@ include file="common/menu.jsp" %>
    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
    <nav class="navbar">
        <ul class="navbar_menu">
            <li><a href="../templates/userset1.jsp">프로필 설정</a></li>
            <li><a href="../templates/userset2.jsp">개인정보 설정</a></li>
            <li><a href="../templates/userset3.jsp">알림 설정</a></li>
            <li><a href="../templates/userset4.jsp">기타 설정</a></li>
        </ul>
    </nav>
    <section id="personal-info" class="content-section">
        <h2>개인정보 설정</h2>
        <form>
            <label for="email">이메일 주소:</label>
            <input type="email" id="email" name="email" placeholder="이메일을 입력하세요.">

            <label for="password">비밀번호:</label>
            <input type="password" id="password" name="password" placeholder="현재 비밀번호">

            <label for="new-password">새 비밀번호:</label>
            <input type="password" id="new-password" name="new-password" placeholder="새 비밀번호">

            <label for="confirm-password">새 비밀번호 확인:</label>
            <input type="password" id="confirm-password" name="confirm-password" placeholder="새 비밀번호 확인">
            
            <button type="submit">변경 완료</button>
        </form>
    </section>
    <script src="../static/js/userset.js"></script>
</body>
</html>