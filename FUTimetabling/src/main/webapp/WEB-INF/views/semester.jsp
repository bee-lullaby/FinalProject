<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Semester Page</title>
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
	<h1>Add a Semester</h1>

	<c:url var="addAction" value="/semester/add"></c:url>

	<form:form action="${addAction}" commandName="semester">
		<table>
			<c:if test="${!empty semester.name}">
				<tr>
					<td><form:label path="semesterId">
							<spring:message text="Semester ID" />
						</form:label></td>
					<td><form:input path="semesterId" readonly="true" size="8"
							disabled="true" /> <form:hidden path="semesterId" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="code">
						<spring:message text="Code" />
					</form:label></td>
				<td><form:input path="code" /></td>
			</tr>
			<tr>
				<td><form:label path="name">
						<spring:message text="Name" />
					</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty semester.name}">
						<input type="submit" value="<spring:message text="Edit Semester"/>" />
					</c:if> <c:if test="${empty semester.name}">
						<input type="submit" value="<spring:message text="Add Semester"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<br>
		<h3>Semester List</h3> <c:if test="${!empty listSemesters}">
			<table class="tg">
				<tr>
					<th width="80">Semester ID</th>
					<th width="120">Semester Code</th>
					<th width="120">Semester Name</th>
					<th width="120">Semester Year</th>
					<th width="120">Semester Start Date</th>
					<th width="120">Semester End Date</th>
					<th width="60">Edit</th>
					<th width="60">Delete</th>
				</tr>
				<c:forEach items="${listSemesters}" var="semester">
					<tr>
						<td>${semester.semesterId}</td>
						<td>${semester.code}</td>
						<td>${semester.name}</td>
						<td>${semester.semesterYear}</td>
						<td>${semester.startDate}</td>
						<td>${semester.endDate}</td>
						<td><a href="<c:url value='/semester/edit/${semester.semesterId}' />">Edit</a></td>
						<td><a href="<c:url value='/semester/delete/${semester.semesterId}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
</body>
</html>