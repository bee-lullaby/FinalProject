<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Class Course Page</title>
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
				.replace("http://localhost:8080/Timetabling/classCourseSemesters?semesterId="
						+ semesterId);
	};
</script>
</head>
<body>
	<select onchange="selectSemester(this)">
		<c:forEach var="item" items="${semesters}">
			<option value="${item.semesterId}"
				${item.semesterId == semesterId ? 'selected="selected"' : ''}>${item.name}</option>
		</c:forEach>
	</select>
	<h1>Add a Class Course</h1>
	<c:url var="addAction" value="/classCourseSemester/add"></c:url>

	<form:form action="${addAction}" commandName="classCourseSemester">
		<table>
			<c:choose>
				<c:when test="${!empty classCourseSemester.classSemester}">
					<tr>
						<td><form:label path="classCourseSemesterId">
								<spring:message text="Class Course ID" />
							</form:label></td>
						<td><form:input path="classCourseSemesterId" readonly="true"
								size="8" disabled="true" /> <form:hidden
								path="classCourseSemesterId" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><form:hidden path="classCourseSemesterId"
								value="0" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td><form:label path="classSemester">
						<spring:message text="Class" />
					</form:label></td>
				<td><form:select path="classSemester">
						<c:forEach var="item" items="${classSemesters}">
							<option value="${item.classSemesterId}"
								${item.classSemesterId == classSemesterId ? 'selected="selected"' : ''}>${item.classFPT.code}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="courseSemester">
						<spring:message text="Course" />
					</form:label></td>
				<td><form:select path="courseSemester">
						<c:forEach var="item" items="${courseSemesters}">
							<option value="${item.courseSemesterId}"
								${item.courseSemesterId == courseSemesterId ? 'selected="selected"' : ''}>${item.course.name}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="blockCondition">
						<spring:message text="Block Condition" />
					</form:label></td>
				<td><form:input path="blockCondition" /></td>
			</tr>
			<tr>
				<td><form:label path="semesterLong">
						<spring:message text="Last entire semester" />
					</form:label></td>
				<td><form:checkbox path="semesterLong" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${!empty classCourseSemester.classSemester}">
							<input type="submit"
								value="<spring:message text="Edit Class Course"/>" />
						</c:when>
						<c:otherwise>
							<input type="submit"
								value="<spring:message text="Add Class Course"/>" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<form:form action="department/addFromFile" method="post"
		enctype="multipart/form-data">
		<input
			accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			name="file" type="file" style="margin-bottom: 20px" />
		<input type="submit" name="addFile" value="AddFile"
			class="button primary" style="margin-right: 5px" />
	</form:form>
	<br>
	<h3>Class Course List</h3>
	<c:if test="${!empty classCourseSemesters}">
		<table class="tg">
			<tr>
				<th width="80">Class Course ID</th>
				<th width="120">Class Code</th>
				<th width="120">Course Name</th>
				<th width="120">Block Condition</th>
				<th width="120">Semester Long</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${classCourseSemesters}"
				var="classCourseSemesterTemp">
				<tr>
					<td>${classCourseSemesterTemp.classCourseSemesterId}</td>
					<td>${classCourseSemesterTemp.classSemester.classFPT.code}</td>
					<td>${classCourseSemesterTemp.courseSemester.course.name}</td>
					<td>${classCourseSemesterTemp.blockCondition}</td>
					<td>${classCourseSemesterTemp.semesterLong}</td>
					<td><a
						href="<c:url value='/classCourseSemester/edit/${classCourseSemesterTemp.classCourseSemesterId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/classCourseSemester/delete/${classCourseSemesterTemp.classCourseSemesterId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>