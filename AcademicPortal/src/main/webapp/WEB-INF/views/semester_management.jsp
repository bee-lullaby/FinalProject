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
<body style="margin-bottom:100px">	
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
						<li class="stick "><a href=/academic/teacher_management>Teacher</a></li>
						<li class="stick"><a href="/academic/course_management">Course</a></li>
						<li class="stick"><a href="/academic/class_management">Class</a></li>
						<li class="stick"><a href="/academic/room_management">Room</a></li>
						<li class="stick bg-blue"><a href="#">Semester</a>
					
				</ul>
			</div>
			<div style="float:left; width:70%; margin-left: 40px;">
				<div style="width: 100%; height: 50px"> 
						<p class="place-right" >
							<a href="#" id="addTeacher" onclick="showDialog('addSemesterDialog')">Add Semester</a> 
						</p>
				</div>
				<div style="width: 100%">
					<table id="dataTables-1" class="table striped hovered dataTable" aria-describedby="dataTables-1_info">
						<thead>
			                <tr>
					         <th class="text-left">No</th>
					         <th class="text-left">Code</th>
					         <th class="text-left">Name</th>
					         <th class="text-left">Year</th>
					         <th class="text-left">Start Date</th>
					         <th class="text-left">End Date</th>
					        </tr>
		        		</thead>
		        		
		        		<tbody>
		        			${data}
		        		</tbody>
		        		
		        		<tfoot>
		        			<tr>
					         <th class="text-left">No</th>
					         <th class="text-left">Code</th>
					         <th class="text-left">Name</th>
					         <th class="text-left">Year</th>
					         <th class="text-left">Start Date</th>
					         <th class="text-left">End Date</th>
					        </tr>
		        		</tfoot>
					</table>
				</div>
			</div>
			
			<div  id="addSemesterDialog" data-role="dialog" data-overlay="true" data-close-button="true" data-overlay-color="op-dark">
				<form:form action="/academic/semester_management/addSemester" method="post" modelAttribute="semester" style=" padding: 15px 15px" >
		           	<br>Code<br>
		            <div class="input-control text" style="margin-bottom:15px" data-role="input" >
			            <form:input path="code" required="required" />
			            <button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
		            <br>Name<br>
		            <div class="input-control text" style="margin-bottom:15px" data-role="input">
		            	<form:input path="name" type="text" required="required" />
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
		            <br>Year<br>
		            <div class="input-control text" style="margin-bottom:15px" data-role="input">
		            	<form:input path="semester_year" type="text" required="required" />
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
		            <br>Start Date<br>
		            <div class="input-control text" style="margin-bottom:15px" data-role="datepicker" id="start_date" style="margin-bottom:15px">
		            	<form:input path="start_date" type="text" />
		            	<button class="button"><span class="mif-calendar"></span></button>
	            	</div>
		            <br>End Date<br>
		            <div class="input-control text" id="end_date" data-role="datepicker" style="margin-bottom:15px">
		            	<form:input path="end_date" type="text" />
		            	<button class="button"><span class="mif-calendar"></span></button>
	            	</div>
		            <br><input type="submit" value="Add" class="button primary" style="margin-right:5px"/>
	            </form:form>
			</div>
			<div  id="deleteSemester" data-role="dialog" data-overlay="true" data-close-button="true" data-overlay-color="op-dark">
				<form:form action="/academic/semester_management/updateSemester" method="post" modelAttribute="semester" style=" padding: 50px 50px">
	              	<input type="submit" name="delete" value="Delete" class="button" style="margin-right:5px"/>
               </form:form>
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
				
			    $("#start_date").datepicker({
			    	format: "d/m/yyyy",
			    	position: "top",
			    	effect: "none"
			    });
			    $("#end_date").datepicker({
			    	format: "d/m/yyyy",
			    	position: "top",
			    	effect: "none"
			    });
			    
	
				$(function(){
				    $('#dataTables-1').dataTable( {} );
				});
	
				$(document).on('click', '#dataTables-1 tbody tr', function(){
					showDialog('deleteSemester');
				});
			</script>
			
			</div>
		</div>
</body>
</html>