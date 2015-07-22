<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Teacher Arrangement</title>

<link href="../resources/css/teacherArrangement.css" rel="stylesheet">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/teacherArrangement.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div style="display: none">
		<div id="departmentsData">${departmentsData}</div>
		<div id="coursesData">${coursesData}</div>
		<div id="courseSemesterData">${courseSemesterData}</div>
		<div id="dtaData">${dtaData}</div>
		<form id="setTeacher" action="teacherArrangement/updateTimetable" method="post">
			<input type="text" id="dataToSet" name="dataToSet" /> 
		</form>
	</div>
	<div style="width: 80%; margin: 0 auto;">
		<h2>
			<a href="index.html" class="nav-button transform"><span></span></a>
			&nbsp; Teacher Arrangement
		</h2>
		<div style="margin-top: 25px; margin-left: 50px; display: flex;">
			<div style="display: inline-block; width: 60%; margin-right: 15px;">
				<table id="table-classes" class="table border bordered hovered">
					<thead>
						<tr>
							<th>Class</th>
							<th>Class has conflict of timetable</th>
							<th>Teacher</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
			<div id="course-information"
				style="display: inline-block; width: 35%; margin: 0.625rem 0;">
				<div id="btn-name-course">
					<div class="input-control select" style="margin:0;">
						<select id="select-departments" name="departmentId" >

						</select>
					</div>
					<button id="btn-course" class="button" style="font-size: 1rem; margin:0;">Course
						1</button>
					<button id="btn-submit" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Submit" style="margin:0;">
						<span class="mif-download"></span>
					</button>
				</div>
				<div id="list-btn-courses">
					<h5>+List Of Courses:</h5>
				</div>
				<div>
					<table id="table-information" class="table hovered border">
						<thead>
							<tr>
								<th style="text-align: left;">Number of Classes</th>
								<th><button id="num-of-classes" class="button"></button></th>
							</tr>
							<tr>
								<th style="text-align: left;">Number of Teachers</th>
								<th><button id="num-of-teachers" class="button"></button></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="dialog-select-course" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<table id="table-courses" class="table border bordered cell-hovered">
			<thead>
				<tr>
					<th>Course</th>
					<th>Number of Classes</th>
					<th>Number of Teachers</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</body>
</html>