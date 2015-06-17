<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<h1>Add a Specialized</h1>

	<c:url var="addAction" value="/specialized/add"></c:url>

	<form:form action="${addAction}" commandName="specialized">
		<table>
			<c:if test="${!empty specialized.code}">
				<tr>
					<td><form:label path="specializedId">
							<spring:message text="Specialized ID" />
						</form:label></td>
					<td><form:input path="specializedId" readonly="true" size="8"
							disabled="true" /> <form:hidden path="specializedId" /></td>
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
				<td colspan="2"><c:if test="${!empty specialized.name}">
						<input type="submit" value="<spring:message text="Edit Specialized"/>" />
					</c:if> <c:if test="${empty specialized.name}">
						<input type="submit" value="<spring:message text="Add Specialized"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<form:form action="specialized/addFromFile" method="post" enctype="multipart/form-data">
		<input accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" name="file" type="file" style="margin-bottom:20px" />
        <input type="submit" name="addFile" value="AddFile" class="button primary" style="margin-right:5px" />
	</form:form>
	<br>
		<h3>Specialized List</h3> <c:if test="${!empty listSpecializeds}">
			<table class="tg">
				<tr>
					<th width="80">Specialized ID</th>
					<th width="120">Specialized Code</th>
					<th width="120">Specialized Name</th>
					<th width="120">Courses</th>
					<th width="60">Edit</th>
					<th width="60">Delete</th>
				</tr>
				<c:forEach items="${listSpecializeds}" var="specialized">
					<tr>
						<td>${specialized.specializedId}</td>
						<td>${specialized.code}</td>
						<td>${specialized.name}</td>
						<td>${specialized.classesToString()}</td>
						<td><a href="<c:url value='/specialized/edit/${specialized.specializedId}' />">Edit</a></td>
						<td><a href="<c:url value='/specialized/delete/${specialized.specializedId}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
</body>
</html>