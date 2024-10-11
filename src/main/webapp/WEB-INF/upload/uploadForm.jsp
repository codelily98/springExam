<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring: 파일 업로드</title>
<link rel="stylesheet" href="../css/uploadForm.css">
</head>
<div id="imgWrap">
    <a href="/spring/"><img width="50" height="50" alt="크롬" src="../image/chrome.jpg" /></a>
</div>
<body>
<div id="wrap">
   <form method="post" enctype="multipart/form-data" action="/spring/user/uploads">
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
               <div id="btnWrap">
                   <input type="submit" id="uploadBtn" value="이미지 업로드"/>
                   <input type="reset" value="취소"/>
               </div>
            </td>
         </tr>
      </table>
   </form>
</div>
</body>
</html>


<!-- 다중 업로드 시 name의 이름이 같아야한다. 
멀티 업로드 - 1 [한번에 하나씩 선택] (서버에서 파일을 배열로 받는다.)
<tr>
   <td colspan="2">
      <input type="file" name="multipartFile"/>
   </td>
</tr>
<tr>
   <td colspan="2">
      <input type="file" name="multipartFile"/>
   </td>
</tr>
 -->
<!-- 멀티 업로드 - 2 [한번에 여러개의 파일 선택] (서버에서 List로 받는다.) -->