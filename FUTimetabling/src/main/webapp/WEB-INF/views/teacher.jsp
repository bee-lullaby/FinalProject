<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Teacher Page</title>
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
	<h1>Add a Teacher</h1>

	<c:url var="addAction" value="/teacher/add"></c:url>

	<form:form action="${addAction}" commandName="teacher">
		<table>
			<c:if test="${!empty teacher.name}">
				<tr>
					<td><form:label path="teacherId">
							<spring:message text="Teacher ID" />
						</form:label></td>
					<td><form:input path="teacherId" readonly="true" size="8"
							disabled="true" /> <form:hidden path="teacherId" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="account">
						<spring:message text="account" />
					</form:label></td>
				<td><form:input path="account" /></td>
			</tr>
			<tr>
				<td><form:label path="email">
						<spring:message text="email" />
					</form:label></td>
				<td><form:input path="email" /></td>
			</tr>

			<tr>
				<td colspan="2"><c:if test="${!empty teacher.account}">
						<input type="submit" value="<spring:message text="Edit Teacher"/>" />
					</c:if> <c:if test="${empty teacher.account}">
						<input type="submit" value="<spring:message text="Add Teacher"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>

	<form:form action="teacherCourse/addFromFile" method="post"
		enctype="multipart/form-data">
		<input
			accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			name="file" type="file" style="margin-bottom: 20px" />
		<input type="submit" name="addFile" value="AddFile"
			class="button primary" style="margin-right: 5px" />
	</form:form>

	<br>
	<h3>Teachers List</h3>
	<c:if test="${!empty listTeachers}">
		<table class="tg">
			<tr>
				<th width="80">Teacher ID</th>
				<th width="120">Account</th>
				<th width="120">Name</th>
				<th width="120">Email</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listTeachers}" var="teacher">
				<tr>
					<td>${teacher.teacherId}</td>
					<td>${teacher.account}</td>
					<td>${teacher.name}</td>
					<td>${teacher.email}</td>
					<td><a
						href="<c:url value='/teacher/edit/${teacher.teacherId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/teacher/delete/${teacher.teacherId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>