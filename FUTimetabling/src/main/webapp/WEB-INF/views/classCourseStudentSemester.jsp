<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Class Course Student Semester Page</title>
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
		window.location
				.replace("http://localhost:8080/Timetabling/classCourseStudentSemesters?semesterId="
						+ semesterId);
	};
	selectClassCourseSemester = function(classCourseSemester) {
		var classCourseSemesterId = classCourseSemester.value;
		window.location
				.replace("http://localhost:8080/Timetabling/classCourseStudentSemesters?classCourseSemesterId="
						+ classCourseSemesterId);
	};
</script>
</head>
<body>
	<h1>Select a Class Course Semester</h1>
	<table>
		<tr>
			<td><label> <spring:message text="Semester" />
			</label></td>
			<td><select onchange="selectSemester(this)">
					<c:forEach var="item" items="${semesters}">
						<option value="${item.semesterId}"
							${item.semesterId == semesterId ? 'selected="selected"' : ''}>${item.name}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><label> <spring:message text="Class Course Semester" />
			</label></td>
			<td><select onchange="selectClassCourseSemester(this)">
					<c:forEach var="item" items="${classCourseSemesters}">
						<option value="${item.classCourseSemesterId}"
							${item.classCourseSemesterId == classCourseSemesterId ? 'selected="selected"' : ''}>${item.classSemester.classFPT.code}
							- ${item.courseSemester.course.name}</option>
					</c:forEach>
			</select></td>
		</tr>
	</table>
	<form:form action="department/addFromFile" method="post"
		enctype="multipart/form-data">
		<input
			accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			name="file" type="file" style="margin-bottom: 20px" />
		<input type="submit" name="addFile" value="AddFile"
			class="button primary" style="margin-right: 5px" />
	</form:form>
	<br>
	<h3>List of suitable students</h3>
	<c:if test="${!empty students}">
		<table class="tg">
			<tr>
				<th width="80">Student ID</th>
				<th width="120">Student Code</th>
				<th width="120">Student Name</th>
				<th width="200">Add to this class course</th>
			</tr>
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.studentId}</td>
					<td>${student.studentCode}</td>
					<td>${student.name}</td>
					<td><a
						href="<c:url value='/classCourseStudentSemester/add/${student.studentId}' />">Add</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>