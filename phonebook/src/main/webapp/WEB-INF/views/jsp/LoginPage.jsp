<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style type="text/css">
.alerttop{
	position: absolute;
    top: 10px;
    left: 50%;
    transform: translate(-50%,0);
	
}
body {
    display: flex;
    align-items: center;
    padding-top: 150px;
    padding-bottom: 100px;
    background-color: #f5f5f5;
}
.main {
    width: 350px;
    padding: 15px;
    margin: auto;
}
</style>
</head>
<body class="text-center">
<!-- 로그인 실패 메세지 -->
<c:if test="${f != null}">
	<div class="alerttop alert alert-danger alert-dismissible fade show mt-3" role="alert">
		<i class="bi bi-exclamation-diamond"></i>
 			${f}
 		<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	</div>
</c:if>

<!-- 회원가입 완료 메세지 -->
<c:if test="${add != null}">
	<div class="alerttop alert alert-success alert-dismissible fade show mt-3" role="alert">
		<i class="bi bi-check-lg"></i>
 			${add}
 		<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	</div>
</c:if>

	<div class="main">
		<form action="/phone/login" method="post" name="form1" id="form1">
		    <h1 class="h3 mb-3 fw-normal">JC</h1>
		
			<div class="form-floating">
		      <input type="text" class="form-control" name="id" placeholder="아이디">
		      <label for="floatingInput">ID</label>
		    </div>
		    <div class="form-floating mb-3">
		      <input type="password" class="form-control" name="pw" placeholder="비밀번호">
		      <label for="floatingPassword">Password</label>
		    </div>
		    <div>
			    <button class="w-100 btn btn-lg btn-dark" type="submit">로그인</button>
		    </div>
		    <div class="col">
		    	<a class="btn dark-link" data-bs-toggle="modal" data-bs-target="#staticBackdrop">회원가입</a>
		    </div>
		    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
		</form>
	</div>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title" id="staticBackdropLabel">회원 가입</h5>
        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      		</div>
      		<form name="form1" action="/phone/insertmember" method="post">
	      		<div class="modal-body">
						<label for="exampleFormControlInput1" class="form-label">ID</label><br>
						<input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요"><br>
						
						<label for="exampleFormControlInput1" class="form-label">PW</label><br>
						<input type="password" class="form-control" name="pw" placeholder="비밀번호를 입력하세요"><br>
						
						<label for="exampleFormControlInput1" class="form-label" >이름</label><br>
						<input type="text" class="form-control" name="membernm" placeholder="이름를 입력하세요"><br>
	      		</div>
	      		<div class="modal-footer">
	        		<button type="submit" class="btn btn-primary">회원가입</button>
	        		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	      		</div>
			</form>
    	</div>
	</div>
</div>
</body>
</html>