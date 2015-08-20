<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Schedule Infomation</title>

<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/scheduleInfo.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<style>
table tr th, table tr td {
	width: 50%;
	font-size: 20px;
	color: #52677a;
}

table tr th {
	text-align: right;
}

table tr td {
	text-align: left;
}

#table-slots {
	width: 600px;
	margin: 0 auto;
}

#table-slots tr th
#table-slots tr td {
	text-align: center;
}
</style>
<script>

</script>
</head>
<body>
	<div style="width: 80%; margin: 0 auto; padding-bottom: 50px;padding-top: 50px;">
		<div id="head" style="text-align: center; margin-bottom: 30px">
			<h1 style="margin-bottom:30px"	>Schedule Process</h1>
			Semester&nbsp;&nbsp;
			<div class="input-control select">
				<select id="select-semester">
					<c:if test="${!empty listSemesters}">
						<c:forEach items="${listSemesters}" var="semester">
							<option value="${semester.semesterId}">${semester.name}</option>
						</c:forEach>
					</c:if>
				</select>
			</div>
			<button class="button" id="btn-automatic" style="width: 100px;">Automatic</button>
		</div>
	
		<div class="stepper" id="stepper" style="width:100%;"
			data-role="stepper" data-on-step="_stepper_on_step"
			data-on-step-click="_stepper_on_step_click" data-clickable="true"
			data-type="cycle"></div>
			
		<div id="div-create-timetable"
			style="width: 100%; height: 100%; text-align: center; margin-top: 50px; margin-bottom: 50px;">
			<h3>Create Timetable</h3>
			<table class="table" style="width: 100%">
				<tr>
					<th>Total Classes:</th>
					<td>${totalClasses}</td>
				</tr>
				<tr>
					<th>Total Classes was set courses:</th>
					<td id="setCourse">${fn:length(listClassWasSetCourse)}</td>
				</tr>
				<tr>
					<th>Number of Classes was set timetable done:</th>
					<td>${fn:length(listClassWasSetTimetablesDone)}</td>
				</tr>
	<!--		<tr>
					<th>Conflict:</th>
					<td></td>
				</tr>   -->
			</table>
		</div>
		<div id="div-room-arrangement"
			style="width: 100%; height: 100%; text-align: center; margin-top: 50px; margin-bottom: 50px;">
			<h3>Room Arrangement</h3>
			<table class="table" style="width: 100%">
				<tr>
					<th>Total Classes:</th>
					<td>${totalClasses}</td>
				</tr>
				<tr>
					<th>Number of Classes was set timetable done:</th>
					<td id="setTimetable">${fn:length(listClassWasSetTimetablesDone)}</td>
				</tr>
				<tr>
					<th>Number of Classes was set room done:</th>
					<td>${fn:length(listClassWasSetRoomsDone)}</td>
				</tr>
					<!--		<tr>
					<th>Conflict:</th>
					<td></td>
				</tr>   -->
			</table>
		</div>
		
		<div id="div-teacher-arrangement"
			style="width: 100%; height: 100%; text-align: center; margin-top: 50px; margin-bottom: 50px;">
			<h3>Teacher Arrangement</h3>
			<table class="table" style="width: 100%">
				<tr>
					<th>Total Classes:</th>
					<td>${totalClasses}</td>
				</tr>
				<tr>
					<th>Number of Classes was set timetable done:</th>
					<td>${fn:length(listClassWasSetTimetablesDone)}</td>
				</tr>
				<tr>
					<th>Number of Classes was set teacher done:</th>
					<td>${fn:length(listClassWasSetTeachersDone)}</td>
				</tr>
					<!--		<tr>
					<th>Conflict:</th>
					<td></td>
				</tr>   -->
			</table>
		</div>
		<div id="btn-group" style="margin: 0 auto; text-align: center;">
			<button class="button" id="btn-back" style="width: 100px;">Back</button>
 			<button class="button" id="btn-manual" style="width: 100px;">Manual</button>
		</div>
	</div>
</body>
</html>