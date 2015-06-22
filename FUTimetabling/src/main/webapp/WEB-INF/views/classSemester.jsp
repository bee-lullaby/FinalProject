<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Class Semester Page</title>
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
</head>
<body>
	<h1>Add a Class Semester</h1>
	<c:url var="addAction" value="/classSemester/add"></c:url>

	<form:form action="${addAction}" commandName="classSemester">
		<table>
			<c:choose>
				<c:when test="${!empty classSemester.classFPT}">
					<tr>
						<td><form:label path="classSemesterId">
								<spring:message text="Class Semester ID" />
							</form:label></td>
						<td><form:input path="classSemesterId" readonly="true"
								size="8" disabled="true" /> <form:hidden path="classSemesterId" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><form:hidden path="classSemesterId" value="0" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td><form:label path="classFPT">
						<spring:message text="Class" />
					</form:label></td>
				<td><form:select path="classFPT">
						<c:forEach var="item" items="${classes}">
							<option value="${item.classId}"
								${item.classId == classId ? 'selected="selected"' : ''}>${item.code}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="semester">
						<spring:message text="Semester" />
					</form:label></td>
				<td><form:select path="semester">
						<c:forEach var="item" items="${semesters}">
							<option value="${item.semesterId}"
								${item.semesterId == semesterId ? 'selected="selected"' : ''}>${item.name}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="semesterNumber">
						<spring:message text="Semester Number" />
					</form:label></td>
				<td><form:input path="semesterNumber" /></td>
			</tr>
			<tr>
				<td><form:label path="noOfStudents">
						<spring:message text="Number of Students" />
					</form:label></td>
				<td><form:input path="noOfStudents" readonly="true" size="8"
						disabled="true" /> <form:hidden path="noOfStudents" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${!empty classSemester.classFPT}">
							<input type="submit"
								value="<spring:message text="Edit Class Semester"/>" />
						</c:when>
						<c:otherwise>
							<input type="submit"
								value="<spring:message text="Add Class Semester"/>" />
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
	<h3>Class Semester List</h3>
	<c:if test="${!empty classSemesters}">
		<table class="tg">
			<tr>
				<th width="80">Class Semester ID</th>
				<th width="120">Class Code</th>
				<th width="120">Semester Code</th>
				<th width="120">Semester Number</th>
				<th width="120">Number Of Students</th>
				<th width="120">Manage Course</th>
				<th width="120">Manage Student</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${classSemesters}" var="classSemesterTemp">
				<tr>
					<td>${classSemesterTemp.classSemesterId}</td>
					<td>${classSemesterTemp.classFPT.code}</td>
					<td>${classSemesterTemp.semester.name}</td>
					<td>${classSemesterTemp.semesterNumber}</td>
					<td>${classSemesterTemp.noOfStudents}</td>
					<td></td>
					<td></td>
					<td><a
						href="<c:url value='/classSemester/edit/${classSemesterTemp.classSemesterId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/classSemester/delete/${classSemesterTemp.classSemesterId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>