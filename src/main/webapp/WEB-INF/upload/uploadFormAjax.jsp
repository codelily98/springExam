<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring: 파일 Ajax 업로드</title>
<link rel="stylesheet" href="../css/uploadFormAjax.css">
</head>
<div id="imgWrap">
    <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg" /></a>
</div>
<body>
<div id="wrap">
   <form id="uploadForm">
      <table>
         <tr>
            <th>상품명</th>
            <td>
               <input type="text" name="imageName" size="35">
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <textarea name="imageContent" rows="10" cols="50"></textarea>
            </td>
         </tr>
         <tr>
            <td colspan="2">
                <input id="file" type="file" name="multipartFile[]" multiple="multiple"/>
            </td>
         </tr>
         <tr>
            <td colspan="2">
                <div id="imgwrap">
                    <span id="showImgList">이미지 미리보기</span>
                </div>
            </td>
         </tr>
         <tr>
            <td colspan="2">
               <div id="btnWrap">
                   <input type="button" id="uploadBtn" value="이미지 업로드"/>
                   <input type="reset" value="취소"/>
               </div>
            </td>
         </tr>
      </table>
   </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/upload.js"></script>
</body>
</html>