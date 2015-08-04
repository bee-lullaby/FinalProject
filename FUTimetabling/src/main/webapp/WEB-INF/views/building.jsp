<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>buildings Page</title>

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/building.js"></script>
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
	<div style="width: 80%; margin: 0 auto; padding-bottom: 50px">
		<h1>
			<a href="/Timetabling/staff" class="nav-button transform"><span></span></a>
			&nbsp;Building Management
		</h1>
		<div style="display: flex">
			<div id="select-semester" class="left"
				style="display: inline-block; width: 250px;">
				<h3>General Management</h3>
				<a href="staffManagement">Staff</a> <a class="active" href="#">Building</a>
				<a href="rooms">Room</a> <a href="semesters">Semester</a> 
				<a href="secialized">Specialized</a> <a href="departments">Department</a>
			</div>
			<div style="display: inline-block; margin-left: 25px; width: 100%;">
				<div id="control-bar" style="width: 100%; margin-bottom: 45px;">
					<div style="width: auto; float: right">
						<button id="btn-add-building" class="button" data-role="hint"
							data-hint-background="#1CB7EC" data-hint-color="fg-white"
							data-hint-position="top" data-hint="Add building">
							<span class="mif-plus"></span>
						</button>
					</div>
				</div>
				<div style="width: 100%; height: 100%;">
					<table id="table-buildings"
						class="table striped hovered border bordered cell-hovered">
						<thead>
							<tr>
								<th>Code</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty listBuildings}">
								<c:forEach items="${listBuildings}" var="building">
									<tr data-buildingId="${building.buildingId}">
										<td>${building.code}</td>
										<td><a href="#"
											id="delete-building-${building.buildingId}">Delete</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="dialog-add-building" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Add building</h3>
		<form id="form-add-building" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-add-buildings" class="table">
				<thead>
					<tr style="display: none">
						<td><input type="text" id="buildingId" name="buildingId" /></td>
						<td></td>
					</tr>
					<tr>
						<th>Code</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="code" name="code" />
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

	<div id="dialog-delete-building" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<h2>Are you sure to delete this building?</h2>
			<div id="btn-group" style="margin-top: 25px;">
				<button class="button" id="btn-delete-accept">ACCEPT</button>
				<button class="button" id="btn-delete-decline">DECLINE</button>
			</div>
		</div>
	</div>


</body>
</html>