<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Course Page</title>
<style type="text/css">
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
	<t:header />
	<h1>Add a Course Semester</h1>
	<c:url var="addAction" value="/courseSemester/add"></c:url>

	<form:form action="${addAction}" commandName="courseSemester">
		<table>
			<c:choose>
				<c:when test="${!empty courseSemester.course}">
					<tr>
						<td><form:label path="courseSemesterId">
								<spring:message text="Course Semester ID" />
							</form:label></td>
						<td><form:input path="courseSemesterId" readonly="true"
								size="8" disabled="true" /> <form:hidden
								path="courseSemesterId" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><form:hidden path="courseSemesterId"
								value="0" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td><form:label path="course">
						<spring:message text="Course" />
					</form:label></td>
				<td><form:select path="course">
						<c:forEach var="item" items="${courses}">
							<option value="${item.courseId}"
								${item.courseId == courseId ? 'selected="selected"' : ''}>${item.name}</option>
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
				<td><form:label path="slots">
						<spring:message text="Slot Number" />
					</form:label></td>
				<td><form:input path="slots" /></td>
			</tr>
			<tr>
				<td><form:label path="courseCondition">
						<spring:message text="Course Condition" />
					</form:label></td>
				<td><form:select path="courseCondition">
						<c:forEach var="item" items="${courseConditions}">
							<option value="${item.courseId}"
								${item.courseId == courseConsitionId ? 'selected="selected"' : ''}>${item.name}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${!empty courseSemester.course}">
							<input type="submit"
								value="<spring:message text="Edit Course Semester"/>" />
						</c:when>
						<c:otherwise>
							<input type="submit"
								value="<spring:message text="Add Course Semester"/>" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<form:form action="courseSemester/addFromFile" method="post"
		enctype="multipart/form-data">
		<input name="semesterId" type="text" />
		<input
			accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			name="file" type="file" style="margin-bottom: 20px" />
		<input type="submit" name="addFile" value="AddFile"
			class="button primary" style="margin-right: 5px" />
	</form:form>
	<br>
	<h3>Course Semester List</h3>
	<c:if test="${!empty listCourseSemesters}">
		<table class="tg">
			<tr>
				<th width="80">Course Semester ID</th>
				<th width="120">Course</th>
				<th width="120">Semester</th>
				<th width="120">Slots</th>
				<th width="120">Course Condition</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listCourseSemesters}" var="courseSemester">
				<tr>
					<td>${courseSemester.courseSemesterId}</td>
					<td>${courseSemester.course.name}</td>
					<td>${courseSemester.semester.name}</td>
					<td>${courseSemester.slots}</td>
					<td>${courseSemester.courseCondition.name}</td>
					<td><a
						href="<c:url value='/courseSemester/edit/${courseSemester.courseSemesterId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/courseSemester/delete/${courseSemester.courseSemesterId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>