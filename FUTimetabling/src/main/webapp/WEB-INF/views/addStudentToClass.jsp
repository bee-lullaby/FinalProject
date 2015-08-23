<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<%@ page import="vn.edu.fpt.timetabling.utils.Const"%>
<!DOCTYPE html>
<html>
<head>

<title>Add Students To Class</title>


<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">
<link href="../resources/css/flaticon.css" rel="stylesheet">
<link href="../resources/css/pageStyle.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/classFPTs.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.js"></script>

<style type="text/css">
.hide {
	visibility: hidden;
}

.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #ccc;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #fff;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #ccc;
	color: #333;
	background-color: #f0f0f0;
}

.tg .tg-4eph {
	background-color: #f9f9f9
}
</style>
<script>
	selectSemester = function(semester) {
		var semesterId = semester.value;
		window.location = "addStudentToClass?semesterId=" + semesterId;
	};
	selectClassSemester = function(classSemester) {
		var classSemesterId = classSemester.value;
		window.location = "addStudentToClass?classSemesterId="
				+ classSemesterId;
	};
	selectClassCourseSemester = function(classCourseSemester) {
		var classCourseSemesterId = classCourseSemester.value;
		window.location = "addStudentToClass?classCourseSemesterId="
				+ classCourseSemesterId;
	};

	$(document)
			.ready(
					function() {
						$("#btn-set-student-semester")
								.on(
										"click",
										function() {
											window.location = "addStudentToClass/autoStudentClasses?semesterId="
													+ $(
															"#select-semester option:selected")
															.val();
										});

						$("#btn-clear-student-semester")
								.on(
										"click",
										function() {
											window.location = "addStudentToClass/clearStudentClasses?semesterId="
													+ $(
															"#select-semester option:selected")
															.val();
										});

						$("#btn-set-student-class")
								.on(
										"click",
										function() {
											window.location = "addStudentToClass/autoStudentClass?classSemesterId="
													+ $(
															"#select-class option:selected")
															.val();
										});

						$("#btn-clear-student-class")
								.on(
										"click",
										function() {
											window.location = "addStudentToClass/clearStudentClass?classSemesterId="
													+ $(
															"#select-class option:selected")
															.val();
										});
					});
</script>
</head>
<body>
	<t:header />
	<div style="width: 100%;">
		<div id="title" style="width: 80%; margin: 0 auto; color: #71b1d1;"
			class="fade-in">
			<h1>
				<a href="/FUTimetabling/staff" class="nav-button transform"><span></span></a>
				&nbsp;Add Students To Class
			</h1>
		</div>
		<div id="line"
			style="border-bottom: thin solid #000; margin-bottom: 0.625rem; padding-top: 5px; width: 100%;"></div>

		<div style="width: 80%; margin: 0 auto;">
			<div style="width: 100%;">
				<div style="width: 100px; text-align: left; display: inline-block">SEMESTER:</div>
				<div class="input-control select">
					<select id="select-semester" onchange="selectSemester(this)">
						<c:forEach var="item" items="${semesters}">
							<option value="${item.semesterId}"
								${item.semesterId == semesterId ? 'selected="selected"' : ''}>${item.name}</option>
						</c:forEach>
					</select>
				</div>
				<button id="btn-set-student-semester" class="button"
					data-role="hint" data-hint-background="#1CB7EC"
					data-hint-color="fg-white" data-hint-position="top"
					data-hint="Auto Set Classes For All Students">Set Students
					For Semester</button>
				<button id="btn-clear-student-semester" class="button"
					data-role="hint" data-hint-background="#1CB7EC"
					data-hint-color="fg-white" data-hint-position="top"
					data-hint="Auto Clear Students From All Classes">Clear
					Students Of Semester</button>
			</div>
			<div style="width: 100%;">
				<div style="width: 100px; text-align: left; display: inline-block">CLASS:</div>
				<div class="input-control select">
					<select id="select-class" onchange="selectClassSemester(this)">
						<c:forEach var="item" items="${classSemesters}">
							<option value="${item.classSemesterId}"
								${item.classSemesterId == classSemesterId ? 'selected="selected"' : ''}>${item.classFPT.code}</option>
						</c:forEach>
					</select>
				</div>
				<button id="btn-set-student-class" class="button" data-role="hint"
					data-hint-background="#1CB7EC" data-hint-color="fg-white"
					data-hint-position="top" data-hint="Auto Set Students For A Class">Set
					Students For Class</button>
				<button id="btn-clear-student-class" class="button" data-role="hint"
					data-hint-background="#1CB7EC" data-hint-color="fg-white"
					data-hint-position="top"
					data-hint="Auto Clear Students From A Class">Clear
					Students Of Class</button>
			</div>
			<div style="width: 100%;">
				<div style="width: 100px; text-align: left; display: inline-block">Course:</div>
				<div class="input-control select">
					<select onchange="selectClassCourseSemester(this)">
						<c:forEach var="item" items="${classCourseSemesters}">
							<option value="${item.classCourseSemesterId}"
								${item.classCourseSemesterId == classCourseSemesterId ? 'selected="selected"' : ''}>${item.courseSemester.course.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div style="width: 100%; display: flex;">
				<div style="width: 49%; margin-right: 15px;">
					<h3>List of suitable students</h3>

					<div>
						Total: <b>${fn:length(freeStudents)}</b>
					</div>
					<table class="table border bordered striped hovered"
						style="width: 100%;">
						<thead>
							<tr>
								<th width="80">ID</th>
								<th width="120">Student Code</th>
								<th>Student Name</th>
								<th width="100">Add</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty freeStudents}">
								<c:forEach items="${freeStudents}" var="student">
									<tr>
										<td>${student.studentId}</td>
										<td>${student.studentCode}</td>
										<td>${student.name}</td>
										<td><a
											href="<c:url value='/staff/addStudentToClass/add/${student.studentId}' />">Add</a></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty freeStudents}">
								<td colspan="4" style="text-align: center">No data for
									this!</td>
							</c:if>
						</tbody>
					</table>
				</div>
				<div style="width: 49%;">
					<h3>List of students in this class course semester</h3>

					<div>
						Total: <b>${fn:length(busyStudents)}</b>
					</div>
					<table class="table border bordered striped hovered"
						style="width: 100%;">
						<thead>
							<tr>
								<th width="80">ID</th>
								<th width="120">Student Code</th>
								<th>Student Name</th>
								<th width="100">Remove</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty busyStudents}">
								<c:forEach items="${busyStudents}" var="student">
									<tr>
										<td>${student.studentId}</td>
										<td>${student.studentCode}</td>
										<td>${student.name}</td>
										<td><a
											href="<c:url value='/staff/addStudentToClass/remove/${student.studentId}' />">Remove</a></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty busyStudents}">
								<td colspan="4" style="text-align: center">No data for
									this!</td>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<t:footer />
	</div>
</body>
</html>