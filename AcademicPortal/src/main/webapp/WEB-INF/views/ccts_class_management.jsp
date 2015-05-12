<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<title>FPT University</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<link rel="stylesheet" href="resources/css/mystyle.css">
	<link rel="stylesheet" href="resources/css/metro.css">
	<link rel="stylesheet" href="resources/css/metro-icons.css">
	<link rel="stylesheet" href="resources/css/jquery.dataTables.css">
	<link rel="stylesheet" href="resources/css/jquery.dataTables_themeroller.css">
	
    <script src="resources/js/jquery-2.1.3.min.js"></script>
    <script src="resources/js/select2.min.js"></script>
    <script src="resources/js/metro.js"></script>
</head>
<body class="metro" style="margin-bottom:100px">	
	<nav class="navigation-bar" style="height: 100px; background: #FFF">
		<div class="container" style="max-width: 1024px">
			<nav class="navigation-bar-content">
				<div class="element">
					<img style="margin:0" src="resources/images/logo.jpg">
				</div>
			</nav>

		</div>
	</nav>

	<div class="container" style="margin-top:20px; max-width:1024px">
		
		<div style="width: 100%; margin-top:10px">
			<div class="cell inline-block" style="float:left">
				<ul class="sidebar2">
					<li class="stick"><a href="/academic/teacher_management">Teacher</a></li>
					<li class="stick"><a href="/academic/course_management">Course</a></li>
					<li class="stick"><a href="/academic/class_management">Class</a></li>
					<li class="stick"><a href="/academic/room_management">Room</a></li>
					<li class="stick"><a href="/academic/semester_management">Semester</a>
					<li class="stick bg-blue"><a href="#">Course_Teachers</a>
				</ul>
			</div>
			<div style="float:left; width:70%; margin-left: 40px;">
                  <h2>${title}</h2>
                  <form>
                  	 <font class="myfont">Course</font>
                     <select name="course1">
                         ${course1}
                     </select>
                     <font class="myfont">Teacher</font>
                     <select name="teacher1">
                         ${teacher1}
                     </select><br>
                     <font class="myfont">Course</font>
                     <select name="course2">
                         ${course2}
                     </select>
                     <font class="myfont">Teacher</font>
                     <select name="teacher3">
                         ${teacher2}
                     </select><br>
                     <font class="myfont">Course</font>
                     <select name="course2">
                         ${course3}
                     </select>
                     <font class="myfont">Teacher</font>
                     <select name="teacher3">
                         ${teacher3}
                     </select><br>
                  </form>
			</div>
		</div>
</body>
</html>