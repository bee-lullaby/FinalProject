<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Program Semester Page</title>
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
	<h1>Add a Program Semester</h1>
	<c:url var="addAction" value="/programSemester/add"></c:url>

	<form:form action="${addAction}" commandName="programSemester">
		<table>
			<c:choose>
				<c:when test="${!empty programSemester.semester}">
					<tr>
						<td><form:label path="programSemesterId">
								<spring:message text="Program Semester ID" />
							</form:label></td>
						<td><form:input path="programSemesterId" readonly="true"
								size="8" disabled="true" /> <form:hidden
								path="programSemesterId" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><form:hidden path="programSemesterId"
								value="0" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
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
				<td><form:label path="currentSemester">
						<spring:message text="Current Semester" />
					</form:label></td>
				<td><form:input path="currentSemester" /></td>
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
				<td><form:label path="detailSpecialized">
						<spring:message text="Detail Specialized" />
					</form:label></td>
				<td><form:select path="detailSpecialized">
						<c:forEach var="item" items="${detailSpecializeds}">
							<option value="${item.specializedId}"
								${item.specializedId == detailSpecializedId ? 'selected="selected"' : ''}>${item.name}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${!empty programSemester.semester}">
							<input type="submit"
								value="<spring:message text="Edit Program Semester"/>" />
						</c:when>
						<c:otherwise>
							<input type="submit"
								value="<spring:message text="Add Program Semester"/>" />
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
	<h3>Program Semester List</h3>
	<c:if test="${!empty programSemesters}">
		<table class="tg">
			<tr>
				<th width="80">Program Semester ID</th>
				<th width="120">Semester</th>
				<th width="120">Current Semester</th>
				<th width="120">Specialized</th>
				<th width="120">Detail Specialized</th>
				<th width="120">Courses</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${programSemesters}" var="programSemesterTemp">
				<tr>
					<td>${programSemesterTemp.programSemesterId}</td>
					<td>${programSemesterTemp.semester.name}</td>
					<td>${programSemesterTemp.currentSemester}</td>
					<td>${programSemesterTemp.specialized.name}</td>
					<td>${programSemesterTemp.detailSpecialized.name}</td>
					<td>${programSemesterTemp.programSemesterDetails}</td>
					<td><a
						href="<c:url value='/programSemester/edit/${programSemesterTemp.programSemesterId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/programSemester/delete/${programSemesterTemp.programSemesterId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>