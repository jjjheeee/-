<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 페이지</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
.alerttop{
	position: absolute;
    top: 10px;
    left: 50%;
    transform: translate(-50%,0);
	
}
body {
    display: flex;
    align-items: center;
    background-color: #f5f5f5;
}
</style>
</head>
<body>
<!-- 연락처 추가 완료 메세지 -->
<c:if test="${addphone != null}">
	<div class="alerttop alert alert-success alert-dismissible fade show mt-6" role="alert">
		<i class="bi bi-check-lg"></i>
			${addphone}
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	</div>
</c:if>

<!-- 연락처 수정 완료 메세지 -->
<c:if test="${update != null}">
	<div class="alerttop alert alert-success alert-dismissible fade show mt-6" role="alert">
		<i class="bi bi-check-lg"></i>
			${update}
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	</div>
</c:if>

<!-- 연락처 삭제 완료 메세지 -->
<c:if test="${delete != null}">
	<div class="alerttop alert alert-success alert-dismissible fade show mt-6" role="alert">
		<i class="bi bi-check-lg"></i>
			${delete}
			<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
	</div>
</c:if>

	<div class="container w-75 mt-5 mx-auto">
		<div class="header d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
			<a href="/phone/select" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto  text-dark text-decoration-none">
			<span class="fs-4">${name}님 환영합니다</span>
			</a>
			<ul class="nav nav-pills">
	       		<li class="nav-item">
	       			<button type="button" class="btn btn-lg btn-outline-dark" data-bs-toggle="modal" data-bs-target="#insertmodal">
  						회원 추가하기
					</button></li>
	       		<li class="nav-item"><a href="/phone/logout" class="btn dark-link">로그아웃</a></li>
	     	</ul>
	     	

		</div>
		<div class="d-flex justify-content-between align-items-center">
			<div>
				<h6>저장된 연락처목록</h6>
			</div>
			<form action="/phone/selectname" method="get" class="input-group w-25 ">
				<input type="text" class="form-control mb-3" name="name" placeholder="이름을 검색하세요">
				<button type="submit" class="btn btn-outline-dark mb-3"
				data-bs-toggle="collapse" data-bs-target="#selectname" aria-expanded="false" aria-controls="selectname">
					<i class="bi bi-search"></i>
				</button>
	    	</form>
	    	<!-- <div class="collapse" id="selectname">
				<div class="card card-body">
					
				</div>
			</div> -->
	    </div>
	    <table class="table table-hover">
	    	<thead>
				<tr>
					<th>no.</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>구분</th>
					<th>수정</th>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${list.name}</td>
						<td>${list.telnum}</td>
						<td>${list.addr}</td>
						<td>${list.gubun}</td>
						<td>
						
						<!-- updateModal -->
<div class="modal fade" id="modal${status.count}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title" id="staticBackdropLabel">수정 할 정보를 입력하세요</h5>
        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      		</div>
      		<div class="modal-body">
      			<form name="form1" action="/phone/update/${list.telnum}" method="post">
					<label for="exampleFormControlInput1" class="form-label">이름</label><br>
					<input type="text" class="form-control" name="name" placeholder="이름을 입력하세요"><br>
					
					<label for="exampleFormControlInput1" class="form-label">전화번호</label><br>
					<input type="text" class="form-control" name="telnum" placeholder="ex)010-1234-5678"><br>
					
					<label for="exampleFormControlInput1" class="form-label">주소</label><br>
					<input type="text" class="form-control" name="addr" placeholder="주소를 입력하세요"><br>
					
					<label for="exampleFormControlInput1" class="form-label">구분</label><br>
					<select class="form-select" name="gubun" aria-label="Default select example">
						<option selected>선택하세요</option>
						<option value="가족">가족</option>
						<option value="친구">친구</option>
						<option value="회사">회사</option>
						<option value="기타">기타</option>
					</select>
		      		<div class="modal-footer">
		        		<button type="submit" class="btn btn-primary">수정하기</button>
		        		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      		</div>
				</form>
      		</div>
    	</div>
	</div>
</div>
						
						
							<button type="button" class="text-decoration-none" data-bs-toggle="modal" data-bs-target="#modal${status.count}">
								<i class="bi bi-eraser"></i>
							</button>
						</td>
						<td>
							<form action="/phone/delete/${list.telnum}" method="get">
								<button type="submit" class="text-decoration-none">
									<i class="bi bi-trash3"></i>
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
	    </table>
	</div>
	
<!-- insertModal -->
<div class="modal fade" id="insertmodal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title" id="staticBackdropLabel">회원 정보</h5>
        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      		</div>
      		<div class="modal-body">
      			<form name="form1" action="/phone/insertphone" method="post">
					<label for="exampleFormControlInput1" class="form-label">이름</label><br>
					<input type="text" class="form-control" name="name" placeholder="이름을 입력하세요"><br>
					
					<label for="exampleFormControlInput1" class="form-label">전화번호</label><br>
					<input type="text" class="form-control" name="telnum" placeholder="ex)010-1234-5678"><br>
					
					<label for="exampleFormControlInput1" class="form-label">주소</label><br>
					<input type="text" class="form-control" name="addr" placeholder="주소를 입력하세요"><br>
					
					<label for="exampleFormControlInput1" class="form-label">구분</label><br>
					<select class="form-select" name="gubun" aria-label="Default select example">
						<option selected>선택하세요</option>
						<option value="가족">가족</option>
						<option value="친구">친구</option>
						<option value="회사">회사</option>
						<option value="기타">기타</option>
					</select>
		      		<div class="modal-footer">
		        		<button type="submit" class="btn btn-primary">추가하기</button>
		        		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		      		</div>
				</form>
      		</div>
    	</div>
	</div>
</div>

</body>
</html>