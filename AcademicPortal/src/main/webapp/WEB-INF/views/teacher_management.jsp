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
					<li class="stick bg-blue"><a href="#">Teacher</a></li>
					<li class="stick"><a href="/academic/course_management">Course</a></li>
					<li class="stick"><a href="/academic/class_management">Class</a></li>
					<li class="stick"><a href="/academic/room_management">Room</a></li>
					<li class="stick"><a href="/academic/semester_management">Semester</a>
				</ul>
			</div>
			<div style="float:left; width:70%; margin-left: 40px;">
				<div style="width: 100%; height: 50px"> 
						<p class="place-right" >
							<a href="#" id="addTeacher" onclick="showDialog('addTeacherDialog')">Add Teacher</a> | <a href="#" id="addFile" onclick="showDialog('addFileDialog')"> Add From File</a>
						</p>
				</div>
				
				<div style="width: 100%">
					<table id="dataTables-1" class="table striped hovered dataTable" aria-describedby="dataTables-1_info">
						<thead>
			                <tr>
					         <th class="text-left">No</th>
					         <th class="text-left">Account</th>
					         <th class="text-left">Name</th>
					         <th class="text-left">Email</th>
					        </tr>
		        		</thead>
		        		
		        		<tbody>
		        			${data}
		        		</tbody>
		        		
		        		<tfoot>
		        			<tr>
					         <th class="text-left">No</th>
					         <th class="text-left">Account</th>
					         <th class="text-left">Name</th>
					         <th class="text-left">Email</th>
					        </tr>
		        		</tfoot>
					</table>
				</div>
			</div>
			
			<div id="addTeacherDialog" data-role="dialog"  data-overlay="true" data-close-button="true" data-overlay-color="op-dark" >
				<form:form action="/academic/teacher_management/addTeacher" method="post" modelAttribute="teacher">
	                <br>Name<br>
	                <div class="input-control text" style="margin-bottom:15px" data-role="input">
	                	<form:input path="name" required="required" />
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
	                <br>Account<br>
	                <div class="input-control text" style="margin-bottom:15px" data-role="input">
	                	<form:input path="account" type="text" required="required" />
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
	                <br>Email<br>
	                <div class="input-control text" style="margin-bottom:15px" data-role="input">
	                	<form:input path="email" type="email" required="required" pattern="+.@fpt.edu.vn" placeholder="eg: abc@fpt.edu.vn"/>
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
	                <br><input type="submit" value="Add" class="button primary" style="margin-right:5px"/>
                </form:form>
			</div>				
			<div id="addFileDialog" data-role="dialog"  data-overlay="true" data-close-button="true" data-overlay-color="op-dark">
				<form action="/academic/teacher_management/addfile" method="post" enctype="multipart/form-data" style=" padding: 15px 15px">
	                <br>File Input<br>
	                <input accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="file" type="file" style="margin-bottom:20px" />
	                <br><input type="submit" name="addFile" value="AddFile" class="button primary" style="margin-right:5px" />
            	</form>
			</div>
			<div id="updateDialog" data-role="dialog"  data-overlay="true" data-close-button="true" data-overlay-color="op-dark">
				<form:form action="/academic/teacher_management/updateTeacher" method="post" modelAttribute="teacher">
	                <br>Name<br>
	                <div class="input-control text" style="margin-bottom:15px" data-role="input">
	                	<form:input type="text" path="name" equired="required" id="eName" />
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
	                <br>Account<br>
	                <div class="input-control text" style="margin-bottom:15px" data-role="input">
	                	<form:input path="account" type="text" required="required" id="eAccount" />
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
	                <br>Email<br>
	                <div class="input-control text" style="margin-bottom:15px" data-role="input">
	                	<form:input path="email" type="email" required="required" pattern="+.@fpt.edu.vn" placeholder="eg: abc@fpt.edu.vn" id="eEmail"/>
		            	<button class="button helper-button clear"><span class="mif-cross"></span></button>
		            </div>
	                <br><input type="submit" name="save" value="Save" class="button primary" style="margin-right:5px"/>
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

			$(function(){
			    $('#dataTables-1').dataTable( {} );
			});

			$(document).on('click', '#dataTables-1 tbody tr', function(){
				 var name = $('td:eq(1)', this).html();
				 var account = $('td:eq(2)', this).html();
				 var email = $('td:eq(3)', this).html();
				 showDialog('updateDialog');
	
				 $("#eName").value=name;
				 $("#eAccount").value=account;
				 $("#eEmail").value=email;
			       
			});
			</script>
			
			</div>
		</div>
</body>
</html>