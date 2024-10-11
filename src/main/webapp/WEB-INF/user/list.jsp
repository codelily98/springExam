<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring: 리스트</title>
<link rel="stylesheet" href="../css/list.css"/>
</head>
<body>
<div id="wrap">
    <div id="title">
        <div id="imgwrap">
            <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg"/></a>
        </div>
    </div>
    <div id="listwrap">
        <table>
            <tr>
                <th>이름</th>
                <th>아이디</th>
                <th>비밀번호</th>
            </tr>    
            <c:forEach var="userDTO" items="${list}">
                <tr>
                    <td>${userDTO.name}</td>
                    <td id="idwrap"><a href="/spring/user/updateForm?id=${userDTO.id}&pg=${pg}">${userDTO.id}</a></td>
                    <td>${userDTO.pwd}</td>
                </tr>
            </c:forEach>
        </table>
        <div id="awrap">
            ${userPaging.pagingHTML}
        </div>
    </div>
</div>
<script type="text/javascript" src="../js/list.js"></script>
</body> 
</html>