<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Class Course Semester Merge Page</title>
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
				.replace("http://localhost:8080/Timetabling/staff/classCourseSemesterMerges?semesterId="
						+ semesterId);
	};
	selectCourseSemester = function(courseSemester) {
		var courseSemesterId = courseSemester.value;
		window.location
				.replace("http://localhost:8080/Timetabling/staff/classCourseSemesterMerges?courseSemesterId="
						+ courseSemesterId);
	};
</script>
</head>
<body>
	<t:header />
	<select onchange="selectSemester(this)">
		<c:forEach var="item" items="${semesters}">
			<option value="${item.semesterId}"
				${item.semesterId == semesterId ? 'selected="selected"' : ''}>${item.name}</option>
		</c:forEach>
	</select>
	<h1>Add a Class Course</h1>
	<c:url var="addAction" value="/staff/classCourseSemesterMerge/add"></c:url>

	<form:form action="${addAction}" commandName="classCourseSemesterMerge">
		<table>
			<tr>
				<td colspan="2"><form:hidden path="classCourseSemesterMergeId"
						value="0" /></td>
			</tr>
			<tr>
				<td><label> <spring:message text="Course" />
				</label></td>
				<td><select onchange="selectCourseSemester(this)">
						<c:forEach var="item" items="${courseSemesters}">
							<option value="${item.courseSemesterId}"
								${item.courseSemesterId == courseSemesterId ? 'selected="selected"' : ''}>${item.course.code}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><form:label path="classCourseSemester1">
						<spring:message text="Class Course Semester 1" />
					</form:label></td>
				<td><form:select path="classCourseSemester1">
						<c:forEach var="item" items="${classCourseSemesters}">
							<option value="${item.classCourseSemesterId}"
								${item.classCourseSemesterId == classCourseSemesterId1 ? 'selected="selected"' : ''}>${item.classSemester.classFPT.code}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="classCourseSemester2">
						<spring:message text="Class Course Semester 2" />
					</form:label></td>
				<td><form:select path="classCourseSemester2">
						<c:forEach var="item" items="${classCourseSemesters}">
							<option value="${item.classCourseSemesterId}"
								${item.classCourseSemesterId == classCourseSemesterId2 ? 'selected="selected"' : ''}>${item.classSemester.classFPT.code}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message text="Add Class Course Semester Merge"/>" /></td>
			</tr>
		</table>
	</form:form>
	<br>
	<h3>Class Course Semester Merge List</h3>
	<c:if test="${!empty classCourseSemesterMerges}">
		<table class="tg">
			<tr>
				<th width="80">Class Course Semester Merge ID</th>
				<th width="120">Course Code</th>
				<th width="120">Class 1</th>
				<th width="120">Class 2</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${classCourseSemesterMerges}"
				var="classCourseSemesterMergeTemp">
				<tr>
					<td>${classCourseSemesterMergeTemp.classCourseSemesterMergeId}</td>
					<td>${classCourseSemesterMergeTemp.classCourseSemester1.courseSemester.course.code}</td>
					<td>${classCourseSemesterMergeTemp.classCourseSemester1.classSemester.classFPT.code}</td>
					<td>${classCourseSemesterMergeTemp.classCourseSemester2.classSemester.classFPT.code}</td>
					<td><a
						href="<c:url value='/staff/classCourseSemesterMerge/delete/${classCourseSemesterMergeTemp.classCourseSemesterMergeId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>