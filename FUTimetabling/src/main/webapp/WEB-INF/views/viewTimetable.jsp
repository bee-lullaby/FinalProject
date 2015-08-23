
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Timetable</title>
<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/schedule.css" rel="stylesheet">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/select2.min.js"></script>
<script src="../resources/js/viewTimetable.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>

</head>
<style>
td a {
	display: block;
	width: 100%;
}

#btn-group button {
	width: 100px;
}

#table-info {
	padding: 5px;
	background-color: #eeeeee;
	font-size: .875rem;
	float: left;
	vertical-align: top;
}

a {
	line-height: 3.125rem;
	padding: 0 .625rem;
	font-size: 1rem;
	cursor: pointer;
	color: inherit;
	float: left;
	position: relative;
	vertical-align: middle !important;
	text-decoration: none;
	height: 3.125rem;
	width: 100px;
	text-align: center;
}

#action-bar a:link {
	background-color: #005696;
	color: #fff;
}

#action-bar a:visited {
	background-color: #005696;
	color: #fff;
}

#action-bar a:hover {
	background-color: #fff;
	color: #005696;
}

#action-bar a:active {
	background-color: #fff;
	color: #005696;
}

#action-bar a.active {
	background-color: #fff;
	color: #005696;
	font-weight: 600;
}

#select-semester a:hover {
	background-color: #005696;
	color: #fff;
}

a.active {
	background-color: #005696;
	color: #fff;
}

h3 {
	padding: 0.625rem 1rem;
	padding-left: 0;
	border-bottom: 1px #d9d9d9 solid;
	text-align: left;
	margin: .15625rem 0;
	background-color: #F7F7F4;
}

#select-semester a, #select-course a {
	width: 100%;
	text-align: left;
}
</style>
<body style="background-color: #F7F7F4">
	<div style="width: 100%; height: 100%;">
		<div style="margin-top: 25px;">
			<div style="width: 90%; margin: 0 auto;">
				<h1>
					<t:nav />
					&nbsp;Timetable
				</h1>
			</div>
			<c:set var="accountType" value="${accountType}" />
			<c:if test="${accountType == 1}">
				<div id="action-bar"
					style="width: 100%; height: 3.125rem; background-color: #005696; margin: 25px 0;">
					<div style="width: 90%; margin: 0 auto;">
						<c:if test="${!empty listStudents}">
							<div
								style="display: inline-block; line-height: 3.125rem; float: right">
								<font style="color: #fff; font-size: 1rem; font-weight: 600;">STUDENT:
									&nbsp;&nbsp;</font>
								<div class="input-control select"
									style="margin: 0; vertical-align: middle;">
									<select id="select-student">
										<option></option>
										<c:forEach items="${listStudents}" var="student">
											<option value="${student.studentId}">${student.account}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</c:if>
						<c:if test="${!empty listTeacherSemesters}">
							<div
								style="display: inline-block; line-height: 3.125rem; float: right">
								<font style="color: #fff; font-size: 1rem; font-weight: 600;">TEACHER:
									&nbsp;&nbsp;</font>
								<div class="input-control select"
									style="margin: 0; vertical-align: middle;">
									<select id="select-teacher">
										<option></option>
										<c:forEach items="${listTeacherSemesters}"
											var="teacherSemester">
											<option value="${teacherSemester.teacherSemesterId}">${teacherSemester.teacher.account}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</c:if>
						<c:if test="${!empty listClassSemesters}">
							<div
								style="display: inline-block; line-height: 3.125rem; float: right">
								<font style="color: #fff; font-size: 1rem; font-weight: 600;">CLASS:
									&nbsp;&nbsp;</font>
								<div class="input-control select"
									style="margin: 0; vertical-align: middle;">
									<select id="select-class">
										<option></option>
										<c:forEach items="${listClassSemesters}" var="classSemester">
											<option value="${classSemester.classSemesterId}">${classSemester.classFPT.code}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</c:if>
						<div>
							<a id="class-timetable" href="#" style="display: inline-block">CLASS</a>
							<a id="teacher-timetable" href="#" style="display: inline-block">TEACHER</a>
							<a id="student-timetable" href="#" style="display: inline-block">STUDENT</a>
						</div>
					</div>
				</div>
			</c:if>
		</div>
		<div
			style="width: 90%; margin: 0 auto; margin-top: 15px; display: flex;">
			<div id="select-semester"
				style="display: inline-block; float: left; width: 200px">
				<h3 style="text-align: left">SEMESTER</h3>
				<c:if test="${!empty listSemesters}">
					<c:forEach items="${listSemesters}" var="semester">
						<a id="${semester.semesterId}"
							href="#">${semester.name}</a>
					</c:forEach>
				</c:if>
			</div>
			<div style="display: inline-block; width: 100%; margin-left: 25px;">
				<div>
					<h3>Timetable from ${startDate} to ${endDate}</h3>
				</div>
				<table class="table border bordered hovered striped">
					<thead>
						<tr>
							<th>Date</th>
							<th>Slot</th>
							<th>Course</th>
							<th>Class</th>
							<th>Teacher</th>
							<th>Room</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:choose>
								<c:when test="${!empty listTimetables}">
									<c:forEach items="${listTimetables}" var="t">
										<tr>
											<td>${t.date}</td>
											<td>${t.slot}</td>
											<td>${t.classCourseSemester.courseSemester.course.code}</td>
											<td>${t.classCourseSemester.classSemester.classFPT.code}</td>
											<td>${t.teacherSemester.teacher.account}</td>
											<td>${t.room.code}</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<td style="text-align: center" colspan="6">No Timetable To
										Show!</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
			<t:footer />
	</div>
</body>
</html>