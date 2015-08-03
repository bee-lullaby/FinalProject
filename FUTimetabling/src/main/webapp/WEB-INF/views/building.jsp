<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Building Page</title>
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
	<h1>Add a Building</h1>

	<c:url var="addAction" value="/staff/building/add"></c:url>

	<form:form action="${addAction}" commandName="building">
		<table>
			<c:if test="${!empty building.code}">
				<tr>
					<td><form:label path="buildingId">
							<spring:message text="Building ID" />
						</form:label></td>
					<td><form:input path="buildingId" readonly="true" size="8"
							disabled="true" /> <form:hidden path="buildingId" /></td>
				</tr>
			</c:if>
			<tr>
				<td><form:label path="code">
						<spring:message text="Building Code" />
					</form:label></td>
				<td><form:input path="code" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:if test="${!empty building.code}">
						<input type="submit"
							value="<spring:message text="Edit Building"/>" />
					</c:if> <c:if test="${empty building.code}">
						<input type="submit" value="<spring:message text="Add Building"/>" />
					</c:if></td>
			</tr>
		</table>
	</form:form>
	<br>
	<h3>Building List</h3>
	<c:if test="${!empty buildings}">
		<table class="tg">
			<tr>
				<th width="80">Building ID</th>
				<th width="120">Building Code</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${buildings}" var="building">
				<tr>
					<td>${building.buildingId}</td>
					<td>${building.code}</td>
					<td><a
						href="<c:url value='/staff/building/edit/${building.buildingId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/staff/building/delete/${building.buildingId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>