<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Spring: 이미지 상세보기</title>
<link rel="stylesheet" href="../css/uploadView.css"/>
</head>
<body>
<div id="wrap">
    <div class="container">
        <div id="imgWrap">
		    <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg" /></a>
		</div>
        <div class="image-view">
            <img src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-97/storage/${userUploadDTO.imageFileName}" alt="${userUploadDTO.imageOriginalFileName}" width="250px" height="250px">
        </div>
        <div class="image-info">
            <h3>${userUploadDTO.imageName}</h3>
            <p>${userUploadDTO.imageContent}</p>
            <p>${userUploadDTO.seq}</p>
        </div>
        <div id="btnwrap">
            <input type="button" id="updateBtn" value="이미지 수정" onclick="location.href='/spring/user/uploadUpdateForm?seq=${userUploadDTO.seq}'">
            <input type="button" id="deleteBtn" value="이미지 삭제">
        </div>
        <a onclick="location.href='/spring/user/uploadList'" class="back-button">Go Back</a>
    </div>
</div>
</body>
</html>
