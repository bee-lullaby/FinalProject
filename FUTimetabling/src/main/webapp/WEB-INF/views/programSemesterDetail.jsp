<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Program Semester Detail Page</title>
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
				.replace("http://localhost:8080/Timetabling/staff/programSemesterDetails?semesterId="
						+ semesterId);
	};
</script>
</head>
<body>
	<t:header />
	<select onchange="selectSemester(this)">
		<c:forEach var="item" items="${semesters}">
			<option value="${item.semesterId}"
				${item.semesterId == semesterId ? 'selected="selected"' : ''}>
				${item.name}</option>
		</c:forEach>
	</select>
	<h1>Add a Program Semester Detail</h1>
	<c:url var="addAction" value="/staff/programSemesterDetail/add"></c:url>

	<form:form action="${addAction}" commandName="programSemesterDetail">
		<table>
			<c:choose>
				<c:when test="${!empty programSemesterDetail.programSemester}">
					<tr>
						<td><form:label path="programSemesterDetailId">
								<spring:message text="Program Semester Detail ID" />
							</form:label></td>
						<td><form:input path="programSemesterDetailId"
								readonly="true" size="8" disabled="true" /> <form:hidden
								path="programSemesterDetailId" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><form:hidden path="programSemesterDetailId"
								value="0" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td><form:label path="programSemester">
						<spring:message text="Program Semester" />
					</form:label></td>
				<td><form:select path="programSemester">
						<c:forEach var="item" items="${programSemesters}">
							<option value="${item.programSemesterId}"
								${item.programSemesterId == programSemesterId ? 'selected="selected"' : ''}>
								${item.specialized.name} ${item.detailSpecialized != null ? item.detailSpecialized.name : ''}
								- Semester ${item.currentSemester}</option>
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
								${item.courseSemesterId == courseSemesterId ? 'selected="selected"' : ''}>${item.course.code}</option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${!empty programSemesterDetail.programSemester}">
							<input type="submit"
								value="<spring:message text="Edit Program Semester Detail"/>" />
						</c:when>
						<c:otherwise>
							<input type="submit"
								value="<spring:message text="Add Program Semester Detail"/>" />
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
	<h3>Program Semester Detail List</h3>
	<c:if test="${!empty programSemesterDetails}">
		<table class="tg">
			<tr>
				<th width="80">Program Semester Detail ID</th>
				<th width="200">Program Semester</th>
				<th width="120">Course</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${programSemesterDetails}"
				var="programSemesterDetailTemp">
				<tr>
					<td>${programSemesterDetailTemp.programSemesterDetailId}</td>
					<td>${programSemesterDetailTemp.programSemester.specialized.name}
						${programSemesterDetailTemp.programSemester.detailSpecialized != null ? programSemesterDetailTemp.programSemester.detailSpecialized.name : ''}
						- Semester
						${programSemesterDetailTemp.programSemester.currentSemester}</td>
					<td>${programSemesterDetailTemp.courseSemester.course.code}</td>
					<td><a
						href="<c:url value='/staff/programSemesterDetail/edit/${programSemesterDetailTemp.programSemesterDetailId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/staff/programSemesterDetail/delete/${programSemesterDetailTemp.programSemesterDetailId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>