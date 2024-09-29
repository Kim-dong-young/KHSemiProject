<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<script src="<%=request.getContextPath()%>/static/js/communitySidePage.js"></script>
<link rel="stylesheet" href="static/css/communitySidePage.css">

</head>
<body onload="initSide()">
    <div class="hot-bulletin">
        <img src="static/img/fire-icon.png">
        <span>실시간 인기글</span>
        <div class="hot-bulletin-list">
            <table>

            </table>
        </div>
    </div>

    <div class="cm-banner">
        <button><img src="static/img/x-icon-white.png"></button>
        <img src="static/img/logo.png">
    </div>
</body>
</html>