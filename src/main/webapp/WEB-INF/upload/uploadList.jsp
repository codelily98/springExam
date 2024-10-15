<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring: 이미지 목록</title>
<link rel="stylesheet" href="../css/updateList.css">
</head>
<body>
    <div class="container">
        <div id="imgWrap">
            <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg" /></a>
        </div>
        <h1>이미지 목록</h1>
        <form id="listForm">
            <div>
                <input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)">
                <label for="selectAll">전체 선택</label>
            </div>
            <div class="gallery">
                <c:forEach var="image" items="${list}">
                <div class="gallery-item">
                    <input type="checkbox" name="selectedImages" value="${image.seq}" id="image-${image.seq}">                   
                    <label for="image-${image.seq}"><img src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-97/storage/${image.imageFileName}" alt="${image.imageOriginalFileName}"></label>
                    <div class="image-info">
                        <h3><a href="/spring/user/uploadView?seq=${image.seq}">${image.imageName}</a></h3>
                        <p>${image.imageContent}</p>
                    </div>
                </div>
                </c:forEach>
            </div>
            <input type="button" id="deleteBtn" value="선택 삭제">
        </form>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/imageDelete.js"></script>
</body>
</html>
