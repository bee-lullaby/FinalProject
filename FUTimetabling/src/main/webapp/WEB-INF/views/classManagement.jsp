<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>

<title>Class Page</title>
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
<script src="http://code.jquery.com/jquery-2.1.4.js"></script>
<script>
	function temp() {
		var radios = document.getElementsByName('type'), i = 0, j = 0, specializeds = document
				.getElementsByName('specialized'), courses = document
				.getElementsByName('course');
		for (i = 0; i < radios.length; i += 1) {
			if (radios[i].checked) {
				if (radios[i].value === 'Specialized') {
					$('.specialized').removeClass('hide');
					$('.course').addClass('hide');
				} else {
					$('.specialized').addClass('hide');
					$('.course').removeClass('hide');
				}
				return;
			}
		}
	}
</script>
</head>
<body>
	<h1>Add a Class</h1>

	<c:url var="addAction" value="/class/add"></c:url>

	<form:form action="${addAction}" commandName="classFPT">
		<table>
			<c:choose>
				<c:when test="${!empty classFPT.code}">
					<tr>
						<td><form:label path="classId">
								<spring:message text="Class ID" />
							</form:label></td>
						<td><form:input path="classId" readonly="true" size="8"
								disabled="true" /> <form:hidden path="classId" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="2"><form:hidden path="classId" value="0" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td><form:label path="code">
						<spring:message text="Code" />
					</form:label></td>
				<td><form:input path="code" readonly="true" size="8"
						disabled="true" /> <form:hidden path="code" /></td>
			</tr>
			<tr>
				<td><form:label path="type">
						<spring:message text="Type" />
					</form:label></td>
				<td><form:radiobutton path="type" onchange="temp()"
						value="Specialized" />Specialized <form:radiobutton path="type"
						onchange="temp()" value="Course" />Course</td>
			</tr>
			<tr class="specialized">
				<td><form:label path="specialized">
						<spring:message text="Specialized" />
					</form:label></td>
				<td><form:select path="specialized" items="${specializeds}" /></td>
			</tr>
			<tr class="specialized">
				<td><form:label path="batch">
						<spring:message text="Batch" />
					</form:label></td>
				<td><form:input path="batch" /></td>
			</tr>
			<tr class="course hide">
				<td><form:label path="course">
						<spring:message text="Course" />
					</form:label></td>
				<td><form:select path="course" items="${courses}" /></td>
			</tr>
			<tr>
				<td colspan="2"><c:choose>
						<c:when test="${!empty classFPT.code}">
							<input type="submit" value="<spring:message text="Edit Class"/>" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="<spring:message text="Add Class"/>" />
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
	<h3>Class List</h3>
	<c:if test="${!empty classes}">
		<table class="tg">
			<tr>
				<th width="80">Class ID</th>
				<th width="120">Class Code</th>
				<th width="120">Class Type</th>
				<th width="120">Class Specialized</th>
				<th width="120">Class Batch</th>
				<th width="120">Class Course</th>
				<th width="120">Class Number</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${classes}" var="classFPT">
				<tr>
					<td>${classFPT.classId}</td>
					<td>${classFPT.code}</td>
					<td>${classFPT.type}</td>
					<td>${classFPT.specialized.name}</td>
					<td>${classFPT.batch}</td>
					<td>${classFPT.course}</td>
					<td>${classFPT.number}</td>
					<td><a
						href="<c:url value='/class/edit/${classFPT.classId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/class/delete/${classFPT.classId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>