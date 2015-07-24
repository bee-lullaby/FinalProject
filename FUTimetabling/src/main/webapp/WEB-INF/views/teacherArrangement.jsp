
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

<style>
.left {
	margin: 5px;
	width: 250px;
	height: auto;
	text-align: center;
}

.left a {
	line-height: 3.125rem;
	padding: 0 .625rem;
	font-size: 1rem;
	cursor: pointer;
	color: inherit;
	display: block;
	float: left;
	position: relative;
	vertical-align: middle !important;
	text-decoration: none;
	height: 3.125rem;
	font-size: 1rem;
}

.left a:hover {
	background-color: #005696;
	color: #fff;
}

.left a.active {
	background-color: #005696;
	color: #fff;
}

#select-semesters a {
	width: 100%;
	text-align: left;
}
h3 {
	padding: 0.625rem 1rem;
	padding-left: 0;
	border-bottom: 1px #d9d9d9 solid;
	text-align: left;
	margin: .15625rem 0;
	background-color: #ffffff;
}
</style>
<body>
	<div style="display: none">
		<div id="departmentsData">${departmentsData}</div>
		<div id="coursesData">${coursesData}</div>
		<div id="courseSemesterData">${courseSemesterData}</div>
		<div id="dtaData">${dtaData}</div>
		<form id="setTeacher" method="post">
			<input type="text" id="dataToSet" name="dataToSet" />
		</form>
	</div>
	<div style="width: 80%; margin: 0 auto;">
		<h2>
			<a href="index.html" class="nav-button transform"><span></span></a>
			&nbsp; Teacher Arrangement
		</h2>
		<div style="display: flex; margin-top: 30px;">
			<div id="select-semesters" class="left" style="display: inline-block">
				<h3>SEMESTERS</h3>
				<c:if test="${!empty listSemesters}">
					<c:forEach items="${listSemesters}" var="semester">
						<a id="${semester.semesterId}"
							href="?semesterId=${semester.semesterId}">${semester.name}</a>
					</c:forEach>
				</c:if>
			</div>
			<div style="display: inline-block" class="left">

				<h3>DEPARTMENTS</h3>
				<div id="select-departments" style="height: 200px;"></div>
				<h3>COURSES</h3>
				<div id="select-courses" style="height: 200px;"></div>
			</div>


			<div class="left"
				style="margin-left: 15px; display: inline-block; width: 100%;">
				<div>
					Number of Classes
					<button id="num-of-classes" class="button"></button>
					Number of Teachers
					<button id="num-of-teachers" class="button"></button>

					<button id="btn-submit" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Submit" style="margin: 0;">
						<span class="mif-download"></span>
					</button>
				</div>
				<div style="width: 100%;">
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