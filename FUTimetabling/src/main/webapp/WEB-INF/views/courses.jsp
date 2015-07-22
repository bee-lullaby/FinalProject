<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Courses Page</title>

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/courses.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.min.js"></script>
</head>
<script>
	
</script>
<body>
	<div style="width: 80%; margin: 0 auto;">
		<h1>
			<a href="index.html" class="nav-button transform"><span></span></a>
			&nbsp;Courses Management
		</h1>
		<div id="control-bar" style="width: 100%; margin-bottom: 65px;">
			<div style="width: auto; float: left">
				<div class="input-control select">
					<select id="select-semester">
						<option value="0">All</option>
						<c:if test="${!empty listSemesters}">
							<c:forEach items="${listSemesters}" var="semester">
								<option value="${semester.semesterId}">${semester.name}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<button id="btn-submit" class="button" data-role="hint"
					data-hint-background="#1CB7EC" data-hint-color="fg-white"
					data-hint-position="top" data-hint="Submit">
					<span class="mif-plus"></span>
				</button>
				<button id="btn-submit" class="button" data-role="hint"
					data-hint-background="#1CB7EC" data-hint-color="fg-white"
					data-hint-position="top" data-hint="Submit">
					<span class="mif-file-text"></span>
				</button>
			</div>
		</div>
		<div style="width: 100%; height: 100%; padding-bottom: 150px;">
			<c:if test="${!empty listCourseSemesters}">
				<table id="table-courses"
					class="table striped hovered border bordered">
					<thead>
						<tr>
							<th>Course</th>
							<th>Code</th>
							<th>Department</th>
							<th>Semester</th>
							<th>Slots</th>
							<th>Course Condition</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listCourseSemesters}" var="courseSemester">
							<tr>
								<td>${courseSemester.course.name}</td>
								<td>${courseSemester.course.code}</td>
								<td>${courseSemester.course.department.code}</td>
								<td>${courseSemester.semester.name}</td>
								<td>${courseSemester.slots}</td>
								<td>${courseSemester.courseCondition.code}</td>
								<td><a
									href="<c:url value='/staff/course/edit/${courseSemester.course.courseId}' />">Edit</a></td>
								<td><a
									href="<c:url value='/staff/course/delete/${courseSemester.course.courseId}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>