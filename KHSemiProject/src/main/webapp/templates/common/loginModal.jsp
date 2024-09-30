<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .modal-body .form-label{
            display: flex;
            justify-content: flex-start;
        }

        .btn.custom-lsin-btn{
            font-family: "Noto Sans KR", sans-serif;
            font-weight: bold;
            background: #FF9139;
            color: black;
        }
    </style>
</head>
<body>
	<!-- contextPath 로그인 modal -->
	<div class="modal" id="login-modal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">		     
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body" align="center">
					<form id="loginForm" action="<%=contextPath %>/login.me" method="POST">
						<input type="hidden" name="origin" value="<%=request.getRequestURI()%>">
						<div class="mb-3 mt-3">
							<label for="id" class="form-label">아이디:</label>
							<input type="text" class="form-control" id="id" placeholder="Enter ID" name="memberId">
						</div>
						<div class="mb-3">
							<label for="pwd" class="form-label">비밀번호:</label>
							<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="memberPwd">
						</div>
						<button type="submit" class="btn custom-lsin-btn">로그인</button>
						<button type="button" class="btn custom-lsin-btn" data-bs-toggle="modal" data-bs-target="#signin-modal">회원가입</button>
					</form>
				</div>
			</div>
		</div>	
	</div>
		
	<!-- 회원가입 -->
	<div class="modal" id="signin-modal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">		     
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body" align="center">
					<form action="<%=contextPath %>/signin.me" method="POST" id="enroll-form">
						<input type="hidden" name="origin" value="<%=request.getRequestURI()%>">
						<table>
							<tr>
								<td>*아이디</td>
								<td><input type="text" name="memberId" maxlength="20" required></td>
							</tr>
							<tr>
								<td>*비밀번호</td>
								<td><input type="password" name="memberPwd" maxlength="20" required></td>
							</tr>
							<tr>
								<td>*비밀번호 확인</td>
								<td><input type="password" name="memberPwdCheck" maxlength="20" required></td>
							</tr>
							<tr>
								<td>*닉네임</td>
								<td><input type="text" name="nickname" maxlength="20" required></td>
							</tr>
						</table>
						<button type="submit" class="btn custom-lsin-btn" onclick="return checkPwd()">회원가입</button>
						<button type="reset" class="btn custom-lsin-btn">초기화</button>
					</form>
				</div>
			</div>
		</div>
	</div>	
	
	<script>
        function checkPwd(){
            const pwd = document.querySelector("#enroll-form input[name=memberPwd]").value
            const pwdCheck = document.querySelector("#enroll-form input[name=memberPwdCheck]").value

            if(pwd !== pwdCheck) {
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }
        }
    </script>
</body>
</html>