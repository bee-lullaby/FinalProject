<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>STUDENT</title>

<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">
<link href="../resources/css/flaticon.css" rel="stylesheet">
<link href="../resources/css/pageStyle.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/students.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.js"></script>
<script src="../resources/js/ellipses.js"></script>
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
<script>
	$(document).ready(function() { 
		$('#table-students').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"pageLength": 50,	
			"info": true,
			"pagingType": "full_numbers"
	    });
	});

	function _errorNotify() {
		var text = $("#messageError").text();
		$.Notify({
			type : 'alert',
			caption : 'Alert',
			content : text
		});
	}

	function _successNotify() {
		var text = $("#messageSuccess").text();
		$.Notify({
			type : 'success',
			caption : 'Success',
			content : text
		});
	}
</script>
<body>
	<t:header />
	<div style="width: 100%;">
		<div id="title" style="width: 80%; margin: 0 auto; color: #71b1d1;"
			class="fade-in">
			<h1>
				<t:nav />
				&nbsp;Students
			</h1>
		</div>
		<div id="line"
			style="border-bottom: thin solid #000; margin-bottom: 0.625rem; padding-top: 5px; width: 100%;"></div>
		<div style="width: 80%; margin: 0 auto; margin-top: 20px;"
			class="fade-in">
			<div
				style="display: inline-block; margin-left: 25px; width: 100%; background-color: #fff; padding: 20px;">
				<div id="control-bar" style="width: 100%;">
					<h4 style="display: inline-block">Students's Data</h4>
					<div style="width: auto; float: right">
						<button id="btn-add-student" class="button" data-role="hint"
							data-hint-background="#1CB7EC" data-hint-color="fg-white"
							data-hint-position="top" data-hint="Add Student">
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
					<table id="table-students"
						class="dataTable striped hovered border bordered cell-hovered">
						<thead>
							<tr>
								<th>Student Code</th>
								<th>Name</th>
								<th>Specialized</th>
								<th>Detail Specialized</th>
								<th>Semester</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty listStudents}">
								<c:forEach items="${listStudents}" var="student">
									<tr data-studentId="${student.studentId}"
										data-account="${student.account}"
										data-email="${student.email}">
										<td>${student.studentCode}</td>
										<td>${student.name}</td>
										<td>${student.specialized.name}</td>
										<td>${student.detailSpecialized.name}</td>
										<td>${student.semester}</td>
										<td><a href="#" style="color: #000; text-align: center;"
											id="edit-student"><span class="icon flaticon-pencil43"></span></a></td>
										<td><a href="#" style="color: #000; text-align: center;"
											id="delete-student"><span class="icon flaticon-rubbish12"></span></a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<t:footer />
	</div>



	<div id="dialog-add-file" data-role="dialog" data-overlay="true"
		data-overlay-color="op-dark" style="padding: 25px"
		data-close-button="true">
		<h5>Add File</h5>
		<form id="form-add-file" action="students/addFromFile" method="post"
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
	<div id="dialog-edit-student" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<div style="width: 600px; margin: 0 auto;">
			<h3 id="title">Edit Student</h3>
			<form id="form-edit-student" method="post">
				<table id="table-edit-student" class="table">
					<thead>
						<tr style="display: none">
							<td><input type="text" id="studentId" name="studentId" /></td>
							<td></td>
						</tr>
						<tr>
							<th>Name</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="name" name="name" />
								</div></td>
						</tr>
						<tr>
							<th>Specialized</th>
							<td><div class="input-control select" style="width: 200px">
									<select name="specializedId" id="select-specialized">
										<c:if test="${!empty listSpecializeds}">
											<c:forEach items="${listSpecializeds}" var="specialized">
												<option value="${specialized.specializedId}">${specialized.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div></td>
						</tr>
						<tr>
							<th>Detail Specialized</th>
							<td><div class="input-control select" style="width: 200px">
									<select name="dsId" id="select-ds">
										<c:if test="${!empty listSpecializeds}">
											<c:forEach items="${listSpecializeds}" var="specialized">
												<option value="${specialized.specializedId}">${specialized.name}</option>
											</c:forEach>
										</c:if>
										<c:if test="${!empty listDetailSpecializeds}">
											<c:forEach items="${listDetailSpecializeds}"
												var="detailSpecialized">
												<option value="${detailSpecialized.specializedId}">${detailSpecialized.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div></td>
						</tr>
						<tr>
							<th>Batch</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="batch" name="batch" />
								</div></td>
						</tr>
						<tr>
							<th>Semester</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="number" id="semester" name="semester" />
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
	</div>
	<div id="dialog-add-student" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<div style="width: 400px; margin: 0 auto;">
			<h3 id="title">Add Student</h3>
			<form id="form-add-student" method="post">
				<table id="table-add-student" class="table">
					<thead>
						<tr style="display: none">
							<td><input type="text" id="studentId" name="studentId" /></td>
							<td></td>
						</tr>
						<tr>
							<th>Name</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="name" name="name" />
								</div></td>
						</tr>
						<tr>
							<th>Specialized</th>
							<td><div class="input-control select" style="width: 200px">
									<select name="specializedId" id="select-specialized">
										<c:if test="${!empty listSpecializeds}">
											<c:forEach items="${listSpecializeds}" var="specialized">
												<option value="${specialized.specializedId}">${specialized.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div></td>
						</tr>
						<tr>
							<th>Detail Specialized</th>
							<td><div class="input-control select" style="width: 200px">
									<select name="dsId" id="select-ds">
										<c:if test="${!empty listSpecializeds}">
											<c:forEach items="${listSpecializeds}" var="specialized">
												<option value="${specialized.specializedId}">${specialized.name}</option>
											</c:forEach>
										</c:if>
										<c:if test="${!empty listDetailSpecializeds}">
											<c:forEach items="${listDetailSpecializeds}"
												var="detailSpecialized">
												<option value="${detailSpecialized.specializedId}">${detailSpecialized.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div></td>
						</tr>
						<tr>
							<th>Batch</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="batch" name="batch" />
								</div></td>
						</tr>
						<tr>
							<th>Semester</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="number" id="semester" name="semester" />
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
	</div>

	<div id="dialog-delete-student" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<h2>Are you sure to delete this student?</h2>
			<div id="btn-group" style="margin-top: 25px;">
				<button class="button" id="btn-delete-accept">ACCEPT</button>
				<button class="button" id="btn-delete-decline">DECLINE</button>
			</div>
		</div>
	</div>
</body>
</html>