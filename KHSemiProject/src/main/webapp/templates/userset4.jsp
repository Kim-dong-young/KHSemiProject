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
        <div class="other-details">
        <h2>회원 탈퇴 설정</h2>
        </div>

        <div class="modal-body" align="center">
        <form action="${pageContext.request.contextPath}/delete.me" method="post">
               <b>정말 탈퇴할거야? 정말로? 나 너무 슬퍼<br><br><br>
                형님 이러면 나 울어 정말로<br><br><br>
                마지막 기회야 잘 생각해야돼 형님</b> <br><br><br>
            <input type="hidden" name="memberId" value="${loginMember.memberId }">
            비밀번호 : <input type="password" name="memberPwd" required>
            <button id="edit-delete-btn" type="submit">탈퇴하기</button>
        </form>
        <script>
        document.getElementById("edit-delete-btn").onclick = function(event) {
            const confirmation = confirm("정말 탈퇴하시겠습니까? 탈퇴 시 다시 로그인이 불가합니다.");
            if(!confirmation) {
                event.preventDefault();
            }
        }
        </script>
    </section>
    </fieldset>
    </main>
</body>
</html>