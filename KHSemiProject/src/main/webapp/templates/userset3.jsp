<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/userset.css">
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
    <main>
    <fieldset>
    <section id="notifications" class="content-section">
        <h2>알림 설정</h2>
        <form>
            <div class="notifications-details">
            <label>
                <input type="checkbox" name="email-notifications" checked>
                이메일 알림
            </label>
            <label>
                <input type="checkbox" name="push-notifications">
                푸시 알림
            </label>
            </div>
            <button type="submit">저장</button>
        </form>
    </section>
    </fieldset>
    </main>
    <script src="static/js/userset.js"></script>
</body>
</html>