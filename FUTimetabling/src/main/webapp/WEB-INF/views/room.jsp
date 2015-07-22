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
	<form:form action="rooms/addFromFile" method="post"
		enctype="multipart/form-data">
		<input
			accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			name="file" type="file" style="margin-bottom: 20px" />
		<input type="submit" name="addFile" value="AddFile"
			class="button primary" style="margin-right: 5px" />
	</form:form>

	<br>
	<h3>Room List</h3>
	<c:if test="${!empty listRooms}">
		<table class="tg">
			<tr>
				<th width="80">Room ID</th>
				<th width="120">Code</th>
				<th width="120">Capacity</th>
				<th width="120">Courses</th>
				<th width="60">Edit</th>
				<th width="60">Delete</th>
			</tr>
			<c:forEach items="${listRooms}" var="room">
				<tr>
					<td>${room.roomId}</td>
					<td>${room.code}</td>
					<td>${room.capacity}</td>
					<td>${room.courses}</td>
					<td><a
						href="<c:url value='/staff/room/edit/${room.roomId}' />">Edit</a></td>
					<td><a
						href="<c:url value='/staff/room/delete/${room.roomId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>