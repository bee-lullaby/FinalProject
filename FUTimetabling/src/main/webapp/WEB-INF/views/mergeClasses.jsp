<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>MERGE-CLASSES</title>

<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">
<link href="../resources/css/flaticon.css" rel="stylesheet">
<link href="../resources/css/pageStyle.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/mergeClasses.js"></script>
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
	<div style="width: 100%;">
		<div id="title" style="width: 80%; margin: 0 auto; color: #71b1d1;"
			class="fade-in">
			<h1>
				<a href="/Timetabling/staff" class="nav-button transform"><span></span></a>
				&nbsp;Merge-Classes
			</h1>
		</div>
		<div id="line"
			style="border-bottom: thin solid #000; margin-bottom: 0.625rem; padding-top: 5px; width: 100%;"></div>

		<div style="width: 80%; margin: 0 auto; margin-top: 20px;"
			class="fade-in">
			<div style="display: flex">
				<div id="select-semester" class="left"
					style="display: inline-block; width: 250px;">
					<ul class="sidebar2 ">
						<li class="title" style="padding: .75rem 2rem .75rem 2.5rem">Semester</li>
						<c:if test="${!empty listSemesters}">
							<c:forEach items="${listSemesters}" var="semester">
								<li><a id="${semester.semesterId}"
									href="?semesterId=${semester.semesterId}">${semester.name}</a></li>
							</c:forEach>
						</c:if>
					</ul>
				</div>
				<div
					style="display: inline-block; margin-left: 25px; width: 100%; background-color: #fff; padding: 20px;">
					<div id="control-bar" style="width: 100%;">
						<h4 style="display: inline-block">Merge-Classes's Data</h4>
						<div style="width: auto; float: right">
							<button id="btn-add-mergeClass" class="button" data-role="hint"
								data-hint-background="#1CB7EC" data-hint-color="fg-white"
								data-hint-position="top" data-hint="Add Merge Class">
								<span class="mif-plus"></span>
							</button>
							<button id="btn-clear-mergeClass" class="button" data-role="hint"
								data-hint-background="#1CB7EC" data-hint-color="fg-white"
								data-hint-position="top" data-hint="Clear Merge Class">
								<span class="icon flaticon-rubbish12"></span>
							</button>
						</div>
					</div>
					<div style="width: 100%; height: 100%;">
						<table id="table-mergeClasses"
							class="table striped hovered border bordered cell-hovered">
							<thead>
								<tr>
									<th>Course</th>
									<th>Class 1</th>
									<th>Class 2</th>
									<th style="width: 100px;">Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty listClassCourseSemesterMerges}">
									<c:forEach items="${listClassCourseSemesterMerges}" var="ccsm">
										<tr data-mergeClassId="${ccsm.classCourseSemesterMergeId}">
											<td>${ccsm.classCourseSemester1.courseSemester.course.code}</td>
											<td>${ccsm.classCourseSemester1.classSemester.classFPT.code}</td>
											<td>${ccsm.classCourseSemester2.classSemester.classFPT.code}</td>
											<td><a href="#" style="color: #000; text-align: center;"
												id="delete-mergeClass"><span
													class="icon flaticon-rubbish12"></span></a></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="bottom-bar" class="fade-in">
			<div id="nav-bottom-bar">
				<a href="http://fpt.edu.vn">FPT University</a><a href="#">Contact</a><a
					href="#">About Us</a>
			</div>
		</div>
	</div>
	<div id="dialog-add-mergeClass" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Add mergeClass</h3>
		<form id="form-add-mergeClass" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-add-mergeClasss" class="table">
				<thead>
					<tr>
						<th>Course</th>
						<td><div class="input-control text" style="width: 100%">
								<select id="select-course">
									<c:if test="${!empty listCourseSemesters}">
										<c:forEach items="${listCourseSemesters}" var="courseSemester">
											<option value="${courseSemester.courseSemesterId}">
												${courseSemester.course.code}</option>
										</c:forEach>
									</c:if>
								</select>
							</div></td>
					</tr>
					<tr>
						<th>Class 1</th>
						<td><div class="input-control select" style="width: 100%">
								<select id="select-class-1" name="classCourseSemesterId1">
									<option value="-1">...</option>
									<c:if test="${!empty listClassCourseSemesters}">
										<c:forEach items="${listClassCourseSemesters}"
											var="classCourseSemester">
											<option value="${classCourseSemester.classCourseSemesterId}"
												data-courseSemesterId="${classCourseSemester.courseSemester.courseSemesterId}">
												${classCourseSemester.classSemester.classFPT.code}</option>
										</c:forEach>
									</c:if>
								</select>
							</div></td>
					</tr>
					<tr>
						<th>Class 2</th>
						<td><div class="input-control select" style="width: 100%">
								<select id="select-class-2" name="classCourseSemesterId2">
									<option value="-1">...</option>
									<c:if test="${!empty listClassCourseSemesters}">
										<c:forEach items="${listClassCourseSemesters}"
											var="classCourseSemester">
											<option value="${classCourseSemester.classCourseSemesterId}"
												data-courseSemesterId="${classCourseSemester.courseSemester.courseSemesterId}">
												${classCourseSemester.classSemester.classFPT.code}</option>
										</c:forEach>
									</c:if>
								</select>
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

	<div id="dialog-delete-mergeClass" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<h2>Are you sure to delete this Merge Class?</h2>
			<div id="btn-group" style="margin-top: 25px;">
				<button class="button" id="btn-delete-accept">ACCEPT</button>
				<button class="button" id="btn-delete-decline">DECLINE</button>
			</div>
		</div>
	</div>
	
	<div id="dialog-clear-mergeClass" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<h2>Are you sure to delete all Merge Class?</h2>
			<div id="btn-group" style="margin-top: 25px;">
				<button class="button" id="btn-clear-accept">ACCEPT</button>
				<button class="button" id="btn-clear-decline">DECLINE</button>
			</div>
		</div>
	</div>
</body>
</html>