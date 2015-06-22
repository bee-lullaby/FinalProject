<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Semester</title>
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
	<h3>Semester List</h3>
	<c:if test="${!empty semesters}">
		<table class="tg">
			<tr>
				<th width="80">Semester ID</th>
				<th width="120">Code</th>
				<th width="120">Name</th>
				<th width="120">Semester Year</th>
				<th width="120">Start Date</th>
				<th width="120">End Date</th>
				<th width="60">Select</th>
			</tr>
			<c:forEach items="${semesters}" var="semesterTemp">
				<tr>
					<td>${semesterTemp.semesterId}</td>
					<td>${semesterTemp.code}</td>
					<td>${semesterTemp.name}</td>
					<td>${semesterTemp.semesterYear}</td>
					<td>${semesterTemp.startDate}</td>
					<td>${semesterTemp.endDate}</td>
					<td><a
						href="<c:url value='/classCourseSemesters/?semesterId=${semesterTemp.semesterId}' />">Select</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>