<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Departments Page</title>

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/departments.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.js"></script>
</head>
<style>
td a {
	display: block;
	width: 100%;
}

#btn-group button {
	width: 100px;
}

#table-edit-course tr th, #table-add-courses tr th {
	text-align: left;
}

#table-add-edit-courses tr th, #table-add-edit-courses tr td {
	padding: 0.4rem 0;
}

#table-info {
	padding: 5px;
	background-color: #eeeeee;
	font-size: .875rem;
	float: left;
	vertical-align: top;
}

.left>a {
	width: 100%;
	line-height: 3.125rem;
	padding: 0 .625rem;
	font-size: 1rem;
	cursor: pointer;
	color: inherit;
	display: block;
	float: left;
	position: relative;
	vertical-align: middle !important;
	text-decoration: none;
	height: 3.125rem;
}

.left>a:hover {
	background-color: #005696;
	color: #fff;
}

.left>a.active {
	background-color: #005696;
	color: #fff;
}

h3 {
	padding: 0.625rem 1rem;
	padding-left: 0;
	border-bottom: 1px #d9d9d9 solid;
	text-align: left;
	margin: .15625rem 0;
	background-color: #ffffff;
}
</style>
<body>
	<div style="width: 80%; margin: 0 auto;">
		<h1>
			<a href="/Timetabling/staff" class="nav-button transform"><span></span></a>
			&nbsp;Rooms Management
		</h1>
		<div style="display: inline-block; margin-left: 25px">
			<div id="control-bar" style="width: 100%; margin-bottom: 45px;">
				<div style="width: auto; float: right">
					<button id="btn-add-department" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Add Course">
						<span class="mif-plus"></span>
					</button>
					<button id="btn-add-from-file" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Add From File">
						<span class="mif-file-text"></span>
					</button>
				</div>
			</div>
			<div style="width: 100%; height: 100%;">
				<table id="table-departments"
					class="table striped hovered border bordered cell-hovered">
					<thead>
						<tr>
							<th>Code</th>
							<th>Name</th>
							<th>Courses</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty listDepartments}">
							<c:forEach items="${listDepartments}" var="department">
								<tr data-departmentId="${department.departmentId}">
									<td>${department.code}</td>
									<td>${department.name}</td>
									<td><c:if test="${!empty department.courses}">
											<c:forEach items="${department.courses}" var="course">
												${course.name};
											</c:forEach>
										</c:if></td>
									<td><a href="#"
										id="edit-department">Edit</a></td>
									<td><a
										href="<c:url value='/staff/departments/delete/${department.departmentId}' />">Delete</a></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<div id="dialog-add-file" data-role="dialog" data-overlay="true"
		data-overlay-color="op-dark" style="padding: 25px"
		data-close-button="true">
		<h5>Add File</h5>
		<form id="form-add-file" action="departments/addFromFile" method="post"
			enctype="multipart/form-data" style="display: inline-block;">
			<div class="input-control file" data-role="input">
				<input type="file" name="file"
					accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
				<button class="button">
					<span class="mif-folder"></span>
				</button>
			</div>
			<button class="button" id="btn-add-file"
				style="display: inline-block">ADD</button>
		</form>
	</div>
	<div id="dialog-edit-department" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Edit Department</h3>
		<form id="form-edit-department" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-edit-department" class="table">
				<thead>
					<tr style="display: none">
						<td><input type="text" id="departmentId" name="departmentId" /></td>
						<td></td>
					</tr>
					<tr>
						<th>Code</th>
						<td><div class="input-control text" style="width: 300px">
								<input type="text" id="code" name="code" />
							</div></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><div class="input-control text" style="width: 300px">
								<input type="text" id="name" name="name" />
							</div></td>
					</tr>
				</thead>
			</table>
		</form>
		<div id="btn-group" style="float: right;">
			<button class="button" id="btn-edit-save">SAVE</button>
			<button class="button" id="btn-edit-cancel">CANCEL</button>
		</div>
	</div>
	<div id="dialog-add-department" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Add Department</h3>
		<form id="form-add-department" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-add-department" class="table">
				<thead>
					<tr style="display: none">
						<td><input type="text" id="departmentId" name="departmentId" /></td>
						<td></td>
					</tr>
					<tr>
						<th>Code</th>
						<td><div class="input-control text" style="width: 300px">
								<input type="text" id="code" name="code" />
							</div></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><div class="input-control text" style="width: 300px">
								<input type="text" id="name" name="name" />
							</div></td>
					</tr>
				</thead>
			</table>
		</form>
		<div id="btn-group" style="float: right;">
			<button class="button" id="btn-add-save">SAVE</button>
			<button class="button" id="btn-add-cancel">CANCEL</button>
		</div>
	</div>
</body>
</html>