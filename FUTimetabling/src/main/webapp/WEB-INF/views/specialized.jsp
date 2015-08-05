<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Specialized Page</title>

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/specialized.js"></script>
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
	<div style="width: 80%; margin: 0 auto; padding: 30px;">
		<h1>
			<a href="/Timetabling/staff" class="nav-button transform"><span></span></a>
			&nbsp;Specialized Management
		</h1>

		<div style="display: flex">
			<div id="select-semester" class="left"
				style="display: inline-block; width: 250px;">
				<h3>General Management</h3>
				<a href="staffManagement">Staff</a> <a href="building">Building</a>
				<a href="rooms">Room</a> <a href="semesters">Semester</a> <a
					class="active" href="#">Specialized</a> <a href="departments">Department</a>
			</div>
			<div style="display: inline-block; margin-left: 25px; width: 100%;">
				<div id="control-bar" style="width: 100%; margin-bottom: 45px;">
					<div style="width: auto; float: right">
						<button id="btn-add-specialized" class="button" data-role="hint"
							data-hint-background="#1CB7EC" data-hint-color="fg-white"
							data-hint-position="top" data-hint="Add Specialized">
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
					<table id="table-specializeds"
						class="table striped hovered border bordered cell-hovered">
						<thead>
							<tr>
								<th>Code</th>
								<th>Name</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty listSpecializeds}">
								<c:forEach items="${listSpecializeds}" var="specialized">
									<tr data-specializedId="${specialized.specializedId}">
										<td>${specialized.code}</td>
										<td>${specialized.name}</td>
										<td><a href="#"
											id="delete-specialized-${specialized.specializedId}">Delete</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="dialog-add-file" data-role="dialog" data-overlay="true"
		data-overlay-color="op-dark" style="padding: 25px"
		data-close-button="true">
		<h5>Add File</h5>
		<form id="form-add-file" action="specialized/addFromFile"
			method="post" enctype="multipart/form-data"
			style="display: inline-block;">
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

	<div id="dialog-add-specialized" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Add specialized</h3>
		<form id="form-add-specialized" style="width: auto; margin: 0 auto;"
			method="post">
			<table id="table-add-specialized" class="table">
				<thead>
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
					<tr>
						<th>Is Detail Specialized</th>
						<td style="text-align:left"><label class="input-control radio"> <input
								type="radio" name="isDetail" value="1"> <span
								class="check"></span> <span class="caption">Yes</span></label> <label
							class="input-control radio"> <input type="radio"
								name="isDetail" value="1" checked> <span class="check"></span>
								<span class="caption">No</span>

						</label></td>
					</tr>
				</thead>
			</table>
		</form>
		<div id="btn-group" style="float: right;">
			<button class="button" id="btn-add-save">SAVE</button>
			<button class="button" id="btn-add-cancel">CANCEL</button>
		</div>
	</div>

	<div id="dialog-delete-specialized" data-role="dialog"
		class="padding20" data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<h2>Are you sure to delete this specialized?</h2>
			<div id="btn-group" style="margin-top: 25px;">
				<button class="button" id="btn-delete-accept">ACCEPT</button>
				<button class="button" id="btn-delete-decline">DECLINE</button>
			</div>
		</div>
	</div>

</body>
</html>