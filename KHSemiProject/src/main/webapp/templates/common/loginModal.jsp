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
	<!-- 로그인 modal -->
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
					<form action="<%=contextPath %>/login.me" method="POST">
						<div class="mb-3 mt-3">
							<label for="email" class="form-label">아이디:</label>
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
					<form action="<%=contextPath %>/signin.me" method="POST">
						<div class="mb-3 mt-3">
							<label for="email" class="form-label">아이디:</label>
							<input type="text" class="form-control" id="id" placeholder="Enter ID" name="memberId">
						</div>
						<div class="mb-3">
							<label for="pwd" class="form-label">비밀번호:</label>
							<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="memberPwd">
						</div>
						<button type="submit" class="btn custom-lsin-btn">회원가입</button>
						<button type="reset" class="btn custom-lsin-btn">초기화</button>
					</form>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>