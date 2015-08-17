<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>COURSE</title>

<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">
<link href="../resources/css/flaticon.css" rel="stylesheet">
<link href="../resources/css/pageStyle.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/courses.js"></script>
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
	<div style="width: 100%;">
		<div id="title" style="width: 80%; margin: 0 auto; color: #71b1d1;"
			class="fade-in">
			<h1>
				<a href="/Timetabling/staff" class="nav-button transform"><span></span></a>
				&nbsp;Courses
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
						<h4 style="display: inline-block">Courses's Data</h4>
						<div style="width: auto; float: right">
							<button id="btn-add-course" class="button" data-role="hint"
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
						<table id="table-courses"
							class="table striped hovered border bordered cell-hovered">
							<thead>
								<tr>
									<th>Course</th>
									<th>Code</th>
									<th>Department</th>
									<th>Slots</th>
									<th>Course Condition</th>
									<th>Edit</th>
									<th>Delete</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty listCourseSemesters}">
									<c:forEach items="${listCourseSemesters}" var="courseSemester">
										<tr data-courseSemesterId="${courseSemester.courseSemesterId}"
											data-courseId="${courseSemester.course.courseId}"
											data-semesterName="${courseSemester.semester.name}">
											<td>${courseSemester.course.name}</td>
											<td>${courseSemester.course.code}</td>
											<td>${courseSemester.course.department.code}</td>
											<td>${courseSemester.slots}</td>
											<td>${courseSemester.courseCondition.code}</td>
											<td><a href="#" style="color: #000; text-align: center;"
												id="edit-course-${courseSemester.course.courseId}"><span
													class="icon flaticon-pencil43"></span></a></td>
											<td><a href="#" style="color: #000; text-align: center;"
												id="delete-course-${courseSemester.course.courseId}"><span
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
	<div id="dialog-add-file" data-role="dialog" data-overlay="true"
		data-overlay-color="op-dark" style="padding: 25px"
		data-close-button="true">
		<h5>Add File</h5>
		<form id="form-add-file" action="courses/addFromFile" method="post"
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
	<div id="dialog-edit-course" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Edit Course</h3>
		<form id="form-edit-course" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-edit-course" class="table">
				<thead>
					<tr style="display: none">
						<td><input type="text" id="courseId" name="courseId" /></td>
						<td><input type="text" id="courseSemesterId"
							name="courseSemesterId" /></td>
					</tr>
					<tr>
						<th>Code</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="courseCode" name="code" />
							</div></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="courseName" name="name" />
							</div></td>
					</tr>
					<tr>
						<th>Slots</th>
						<td><div class="input-control number" style="width: 100%">
								<input type="number" id="slots" name="slots" />
							</div></td>
					</tr>
					<tr>
						<th>Semester</th>
						<td><div class="input-control select" style="width: 100%">
								<select id="select-semester-edit" name="semesterId">
									<c:if test="${!empty listSemesters}">
										<c:forEach items="${listSemesters}" var="semester">
											<option value="${semester.semesterId}">${semester.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div></td>
					</tr>
					<tr>
						<th>Department</th>
						<td>
							<div class="input-control select" style="width: 100%">
								<select id="select-department-edit" name="departmentId">
									<c:if test="${!empty listDepartments}">
										<c:forEach items="${listDepartments}" var="department">
											<option value="${department.departmentId}">${department.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>Course Condition</th>
						<td>
							<div class="input-control select" style="width: 100%">
								<select id="select-course-condition-edit"
									name="courseConditionId">
									<option value="0">...</option>
									<c:if test="${!empty listCourses}">
										<c:forEach items="${listCourses}" var="course">
											<option value="${course.courseId}">${course.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</td>
					</tr>
				</thead>
			</table>
		</form>
		<div id="btn-group" style="float: right;">
			<button class="button" id="btn-edit-save">SAVE</button>
			<button class="button" id="btn-edit-cancel">CANCEL</button>
		</div>
	</div>
	<div id="dialog-add-course" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Add Course</h3>
		<form id="form-add-course" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-add-courses" class="table">
				<thead>
					<tr style="display: none">
						<td><input type="text" id="courseId" name="courseId" /></td>
						<td><input type="text" id="courseSemesterId"
							name="courseSemesterId" /></td>
					</tr>
					<tr>
						<th>Code</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="courseCode" name="code" readonly />
							</div></td>
					</tr>
					<tr>
						<th>Name</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="courseName" name="name" readonly />
							</div></td>
					</tr>
					<tr>
						<th>Slots</th>
						<td><div class="input-control number" style="width: 100%">
								<input type="number" id="slots" name="slots" />
							</div></td>
					</tr>
					<tr>
						<th>Semester</th>
						<td><div class="input-control select" style="width: 100%">
								<select id="select-semester-edit" name="semesterId">
									<c:if test="${!empty listSemesters}">
										<c:forEach items="${listSemesters}" var="semester">
											<option value="${semester.semesterId}">${semester.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div></td>
					</tr>
					<tr>
						<th>Department</th>
						<td>
							<div class="input-control select" style="width: 100%">
								<select id="select-department-add" name="departmentId">
									<c:if test="${!empty listDepartments}">
										<c:forEach items="${listDepartments}" var="department">
											<option value="${department.departmentId}">${department.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>Course Condition</th>
						<td>
							<div class="input-control select" style="width: 100%">
								<select id="select-course-condition-add"
									name="courseConditionId">
									<option value="0">...</option>
									<c:if test="${!empty listCourses}">
										<c:forEach items="${listCourses}" var="course">
											<option value="${course.courseId}">${course.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</td>
					</tr>
				</thead>
			</table>
		</form>
		<div id="btn-group" style="float: right;">
			<button class="button" id="btn-add-save">SAVE</button>
			<button class="button" id="btn-add-cancel">CANCEL</button>
		</div>
	</div>


	<div id="dialog-delete-course" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<h2>Are you sure to delete this course?</h2>
			<div id="btn-group" style="margin-top: 25px;">
				<button class="button" id="btn-delete-accept">ACCEPT</button>
				<button class="button" id="btn-delete-decline">DECLINE</button>
			</div>
		</div>
	</div>
</body>
</html>