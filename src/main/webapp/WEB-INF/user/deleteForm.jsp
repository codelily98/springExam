<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Spring: 회원탈퇴</title>
<link rel="stylesheet" href="../css/writeForm.css"/>
</head>
<body>
    <div id="imgWrap">
       <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg"/></a>
    </div>
    <form id="updateForm">
        <div id="nameWrap">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" value="${list[0].name}" readonly="readonly"/>
            <div id="nameDiv"></div>
        </div>
        <div id="idWrap">
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" value="${list[0].id}" readonly="readonly"/>
            <div id="idDiv"></div>
        </div>
        <div id="pwdWrap">
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" value=""/>
            <div id="pwdDiv"></div>
        </div>
        <div id="btnwrap">
            <input type="button" id="deleteBtn" value="삭제"/>
            <input type="button" id="listBtn" value="목록" onclick="location.href='/spring/user/list?pg=${pg}'"/>
            <input type="hidden" id="pg" name="pg" value="${pg}"/>
            <input type="hidden" id="checkPwd" name="checkPwd" value="${list[0].pwd}"/> 
        </div>
    </form>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript"src="../js/delete.js"></script>
</body>
</html>
