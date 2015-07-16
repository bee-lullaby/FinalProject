<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Student Page</title>
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
	<h1>Add a Student</h1>
	<c:url var="addAction" value="/staff/student/add"></c:url>

	<form:form action="${addAction}" commandName="student"
		acceptCharset="UTF-8">
		<table>
			<c:choose>
				<c:when test="${!empty student.account}">
					<tr>
						<td><form:label path="studentId">
								<spring:message text="Student IDx" />
							</form:label></td>
						<td><form:input path="studentId" readonly="true" size="8"
								disabled="true" /> <form:hidden path="studentId" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><form:hidden path="studentId" value="0" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td><form:label path="account">
						<spring:message text="Account" />
					</form:label></td>
				<td><form:input path="account" readonly="true" size="8"
						disabled="true" /></td>
			</tr>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="email">
						<spring:message text="Email" />
					</form:label></td>
				<td><form:input path="email" readonly="true" size="8"
						disabled="true" /></td>
			</tr>
			<tr>
				<td><form:label path="studentCode">
						<spring:message text="Student Code" />
					</form:label></td>
				<td><form:input path="studentCode" readonly="true" size="8"
						disabled="true" /></td>
			</tr>
			<tr>
				<td><form:label path="specialized">
						<spring:message text="Specialized" />
					</form:label></td>
				<td><form:select path="specialized">
						<c:forEach var="item" items="${specializeds}">
							<option value="${item.specializedId}"
								${item.specializedId == specializedId ? 'selected="selected"' : ''}>${item.name}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="batch">
						<spring:message text="Batch" />
					</form:label></td>
				<td><form:input path="batch" /></td>
			</tr>
			<tr>
				<td><form:label path="semester">
						<spring:message text="Semester" />
					</form:label></td>
				<td><form:input path="semester" /></td>
			</tr>
			<tr>
				<td><form:label path="classSemester.classFPT.code">
						<spring:message text="Class" />
					</form:label></td>
				<td><form:input path="classSemester.classFPT.code"
						readonly="true" size="8" disabled="true" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${!empty student.account}">
							<input type="submit"
								value="<spring:message text="Edit Student"/>" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="<spring:message text="Add Student"/>" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<form:form action="student/addFromFile" method="post"
		enctype="multipart/form-data">
		<input name="semesterId" type="text" />
		<input
			accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			name="file" type="file" style="margin-bottom: 20px" />
		<input type="submit" name="addFile" value="AddFile"
			class="button primary" style="margin-right: 5px" />
	</form:form>
	<br>
	<h3>Student List</h3>
	<c:if test="${!empty students}">
		<table class="tg">
			<tr>
				<th width="80">Student ID</th>
				<th width="120">Account</th>
				<th width="120">Name</th>
				<th width="120">Email</th>
				<th width="120">Student Code</th>
				<th width="120">Specialized</th>
				<th width="60">Batch</th>
				<th width="60">Semester</th>
				<th width="60">Class</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${students}" var="student">
				<tr>
					<td>${student.studentId}</td>
					<td>${student.account}</td>
					<td>${student.name}</td>
					<td>${student.email}</td>
					<td>${student.studentCode}</td>
					<td>${student.specialized.name}</td>
					<td>${student.batch}</td>
					<td>${student.semester}</td>
					<td>${student.classSemester.classFPT.code}</td>
					<td><a
						href="<c:url value='/staff/student/edit/${student.studentId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/staff/student/delete/${student.studentId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>