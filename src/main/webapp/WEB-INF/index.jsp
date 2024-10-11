<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring: 메인화면</title>
<link rel="stylesheet" href="./css/index.css"/>
</head>
<body>
<div id="wrap">
    <div id="title">
        <h2>메인화면</h2>        
    </div>
    <div id="menu">
	    <div class="awrap">
	       <a href="http://localhost:8080/spring/user/writeForm">입력</a>
        </div>
	    <div class="awrap">
	       <a href="/spring/user/list">출력</a>
        </div><!-- pg=1 일때는 생략 가능 -->
	    <div class="awrap">
           <a href="/spring/user/uploadForm">파일 업로드</a>
        </div> 
        <div class="awrap">
           <a href="/spring/user/uploadFormAjax">파일 업로드 Ajax</a>
        </div>
        <div class="awrap">
           <a href="/spring/user/uploadList">파일 출력</a>
        </div> 
    </div>
</div>
</body>
</html>

<!-- Spring Framwork + Maven + MySQL + MyBatis(@Mapper 사용) + NCP(NaverCloud) -->
<!-- 
    Project : SpringProject
	src/main/java
	    com.controller.SpringProject
	        MainController.java
	
	user.controller
	    UserController.java
	    UserUploadController.java
	
	user.service
	    UserService.java (Interface)
	user.service.impl
	    UserServiceImpl.java
	
	user.dao
	    UserDAO.java (Interface)
	user.dao.impl
	    UserService.java (MyBatis)
	user.bean
	    UserDTO.java
	    UserPaging.java
	
	src/main/resources
	    mapper
	        userMapper.xml
	    spring
	        applicationContext.xml 
	        db.properties
	        mybatis-config.xml
	        
	src/main/webapp
	    WEB-INF
	        css
	            write.css
	        js
	            write.js
	        image
	            *.jpg
 -->
