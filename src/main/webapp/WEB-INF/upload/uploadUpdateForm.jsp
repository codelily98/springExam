<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring: 이미지 수정</title>
<link rel="stylesheet" href="../css/uploadFormAjax.css">
</head>
<body>
<div id="imgWrap">
    <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg" /></a>
</div>
<div id="wrap" data-imageFileName="${userUploadDTO.imageFileName}">
   <form id="updateForm">
      <table>
         <tr>
            <th>상품명</th>
            <td>
               <input type="text" name="imageName" size="35" value="${userUploadDTO.imageName}">
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <textarea name="imageContent" rows="10" cols="50">${userUploadDTO.imageContent}</textarea>
            </td>
         </tr>
         <tr>
            <td colspan="2">
                <input id="file" type="file" name="multipartFile"/>
            </td>
         </tr>
         <tr>
            <td colspan="2">
                <div id="imgwrap">
                    <span id="showImgList">
                        <img src="https://kr.object.ncloudstorage.com/bitcamp-9th-bucket-97/storage/${userUploadDTO.imageFileName}" alt="${userUploadDTO.imageOriginalFileName}" width="250px" height="250px"/>
                    </span>
                </div>
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <div id="btnWrap">
                   <input type="button" id="updateBtn" value="이미지 수정"/>
                   <input type="reset" value="취소"/>
                   <input type="hidden" value="${userUploadDTO.seq}" id="seq" name="seq"/>
               </div>
            </td>
         </tr>
      </table>
   </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/uploadUpdate.js"></script>
</body>
</html>