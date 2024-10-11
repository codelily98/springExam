<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring: 회원정보</title>
<link rel="stylesheet" href="../css/updateForm.css">
</head>
<body>
<div id="imgWrap">
    <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg" /></a>
</div>
<div id="wrap">
	<form id="updateForm">
		<h2>수정</h2>
		<input type="hidden" id="pg" value="${param.pg}">
		<div id="idWrap">
			<label for="id">아이디</label> <input type="text" id="id" name="id" value="${list[0].id }" readonly="readonly" />
			<div id="idDiv"></div>
		</div>
		<div id="nameWrap">
			<label for="name">이름</label> <input type="text" id="name" name="name" value="${list[0].name }" />
			<div id="nameDiv"></div>
		</div>
		<div id="pwdWrap">
			<label for="pwd">비밀번호</label> <input type="text" id="pwd" name="pwd" value="${list[0].pwd }" />
			<div id="pwdDiv"></div>
		</div>
		<div id="btnWrap">
			<div id="btn">
			    <input type="button" id="writeBtn" value="수정하기">
			    <input type="button" id="deleteBtn" value="삭제하기">
			</div>
			<div id="btn2">
			    <input type="button" id="listBtn" value="목록" onclick="location.href='/spring/user/list?pg=${pg}'">
	            <input type="reset" id="resetBtn" value="초기화" onclick="location.reload()">
	            <input type="hidden" id="pg" name="pg" value="${pg}" />
	        </div>
		</div>
	</form>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/updateForm.js"></script>
</body>
</html>