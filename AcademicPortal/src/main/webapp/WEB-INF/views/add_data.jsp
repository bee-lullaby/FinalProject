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
    <script src="resources/js/jquery.dataTables.js"></script>
    <script src="resources/js/metro.js"></script>
</head>
<body class="metro" style="margin-bottom:100px">	
	<nav class="navigation-bar" style="height: 100px; background: #FFF">
		<div class="container">
			<nav class="navigation-bar-content">
				<div class="element">
					<img style="margin:0" src="resources/images/logo.jpg">
				</div>
			</nav>

		</div>
	</nav>

	<div class="container" style="margin-top:20px;">
		<div style="width: 100%; margin-top:10px">
			<div class="cell inline-block" style="float:left">
				<ul class="sidebar2">
					<li class="stick"><a href="/academic/teacher_management">Teacher</a></li>
					<li class="stick"><a href="/academic/course_management">Course</a></li>
					<li class="stick"><a href="/academic/class_management">Class</a></li>
					<li class="stick"><a href="/academic/room_management">Room</a></li>
					<li class="stick"><a href="/academic/semester_management">Semester</a></li>
					<li class="stick bg-blue"><a href="#">Add data</a>
				</ul>
			</div>
			<div style="float:left; width:70%; margin-left: 40px;">
			
				<a href="#" id="addCourse" onclick="showDialog('addTCDialog')">Add Teacher Course</a>  
				<br><br>
				<a href="#" id="addFile" onclick="showDialog('addCCDialog')"> Add Class Course</a>
				<br><br>
				<a href="#" id="addFile" onclick="showDialog('addFinalDialog')"> Add Final Table</a>
						
		
			<div id="addTCDialog" data-role="dialog"  data-overlay="true" data-close-button="true" data-overlay-color="op-dark">
				<form action="/academic/add_data/addTeacherCourse" method="post" enctype="multipart/form-data" style=" padding: 15px 15px">
					Semester
                     <select name="semester_id">
                         ${semester}
                     </select><br>
                    <input accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="file" type="file" style="margin-bottom:20px" />
	                <br><input type="submit" name="addFile" value="AddFile" class="button primary" style="margin-right:5px" />
            	</form>
			</div>
			<div id="addCCDialog" data-role="dialog"  data-overlay="true" data-close-button="true" data-overlay-color="op-dark">
				<form action="/academic/add_data/addClassCourse" method="post" enctype="multipart/form-data" style=" padding: 15px 15px">
					Semester
                     <select name="semester_id">
                         ${semester}
                     </select><br>
                    <input accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="file" type="file" style="margin-bottom:20px" />
	                <br><input type="submit" name="addFile" value="AddFile" class="button primary" style="margin-right:5px" />
            	</form>
			</div>
			<div id="addFinalDialog" data-role="dialog"  data-overlay="true" data-close-button="true" data-overlay-color="op-dark">
				<form action="/academic/add_data/addAll" method="post" enctype="multipart/form-data" style=" padding: 15px 15px; margin-top:20px" >
					Semester
                     <select name="semester_id">
                         ${semester}
                     </select><br>
					<input type="submit" name="Add" value="Add Final Data" class="button primary" style="margin:15px 15px;" />
            	</form>
			</div>
			
			<script type="text/javascript">
			function showDialog(id){
		        var dialog = $("#"+id).data('dialog');
		        if (!dialog.element.data('opened')) {
	                dialog.open();
	            } else {
	                dialog.close();
	            }
		    }
			</script>
			
			</div>
		</div>
</body>
</html>