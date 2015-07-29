<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Teachers Page</title>

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/teachers.js"></script>
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
	<div style="width: 80%; margin: 0 auto; padding-bottom: 50px">
		<h1>
			<a href="/Timetabling/staff" class="nav-button transform"><span></span></a>
			&nbsp;Teachers Management
		</h1>
		<div style="display: flex">
			<div id="select-semester" class="left" style="display: inline-block">
				<h3>SEMESTER</h3>
				<c:if test="${!empty listSemesters}">
					<c:forEach items="${listSemesters}" var="semester">
						<a id="${semester.semesterId}"
							href="?semesterId=${semester.semesterId}">${semester.name}</a>
					</c:forEach>
				</c:if>
			</div>
			<div style="display: inline-block; margin-left: 25px">
				<div id="control-bar" style="width: 100%; margin-bottom: 45px;">
					<div style="width: auto; float: right">
						<button id="btn-add-teacher" class="button" data-role="hint"
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
					<table id="table-teachers"
						class="table striped hovered border bordered cell-hovered">
						<thead>
							<tr>
								<th>Account</th>
								<th>Name</th>
								<th>E-mail</th>
								<th>Semester</th>
								<th>Courses</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty listTeacherSemesters}">
								<c:forEach items="${listTeacherSemesters}" var="teacherSemester">
									<tr data-teacherSemesterId="${teacherSemester.teacherSemesterId}"
										data-teacherId="${teacherSemester.teacher.teacherId}">
										<td>${teacherSemester.teacher.account}</td>
										<td>${teacherSemester.teacher.name}</td>
										<td>${teacherSemester.teacher.email}</td>
										<td>${teacherSemester.semester.name}</td>
										<td><c:if
												test="${!empty teacherSemester.teacherCourseSemesters}">
												<c:forEach items="${teacherSemester.teacherCourseSemesters}"
													var="teacherCourseSemester" varStatus="loop">
													<c:choose>
														<c:when test="${loop.last}">
															${teacherCourseSemester.courseSemester.course.code}
														</c:when>
														<c:otherwise>
															${teacherCourseSemester.courseSemester.course.code};
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:if></td>
										<td><a href="#"
											id="edit-teacher-${teacherSemester.teacher.teacherId}">Edit</a></td>
										<td><a
											href="<c:url value='/staff/teacher/delete/${teacherSemester.teacher.teacherId}' />">Delete</a></td>
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
		<form id="form-add-file" action="teachers/addFromFile" method="post"
			enctype="multipart/form-data" style="display: inline-block;">
			<div>
				<label class="input-control radio"> <input type="radio"
					name="act" value="0" checked> <span class="check"></span> <span
					class="caption">Add Teachers</span>
				</label>
			</div>
			<div>
				<label class="input-control radio"> <input type="radio"
					name="act" value="1"> <span class="check"></span> <span
					class="caption">Add Courses For Teachers</span>
				</label>
			</div>
			<div class="input-control file" data-role="input">
				<input type="file" name="file"
					accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
				<button class="button">
					<span class="mif-folder"></span>
				</button>
			</div>
			<button class="button" id="btn-add-file"
				style="display: inline-block" style="width:100%;">ADD</button>
		</form>
	</div>


	<div id="dialog-edit-teacher" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Edit Teacher</h3>
		<form id="form-edit-teacher" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-edit-teachers" class="table">
				<thead>
					<tr style="display: none">
						<td><input type="text" id="teacherId" name="teacherId" /></td>
						<td><input type="text" id="teacherSemesterId"
							name="teacherSemesterId" /></td>
					</tr>
					<tr>
						<th>Teacher</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="name" name="name" />
							</div></td>
					</tr>
					<tr>
						<th>Account</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="account" name="account" />
							</div></td>
					</tr>
					<tr>
						<th>E-mail</th>
						<td><div class="input-control number" style="width: 100%">
								<input type="text" id="email" name="email" />
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
						<th>Courses</th>
						<td>
							<div class="input-control text-aria" style="width:100%">
								<textarea rows="2" cols="1" id="courses-selected" disabled></textarea>
							</div>
							<div class="input-control select multiple full-size"
								style="height: 100px">
								<select id="select-courses" name="courses" multiple>
									<c:if test="${!empty listCourseSemesters}">
										<c:forEach items="${listCourseSemesters}" var="courseSemester">
											<option value="${courseSemester.course.courseId}">${courseSemester.course.code}</option>
										</c:forEach>
									</c:if>
								</select>
							</div> <font style="width: 100%; font-size: 0.7rem">(Ctrl +
								right click to select multiple courses)</font>
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
	<div id="dialog-add-teacher" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<h3 id="title">Add Teacher</h3>
		<form id="form-add-teacher" style="width: 400px; margin: 0 auto;"
			method="post">
			<table id="table-add-teachers" class="table">
				<thead>
					<tr style="display: none">
						<td><input type="text" id="teacherId" name="teacherId" /></td>
						<td><input type="text" id="teacherSemesterId"
							name="teacherSemesterId" /></td>
					</tr>
					<tr>
						<th>Teacher</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="name" name="name" />
							</div></td>
					</tr>
					<tr>
						<th>Account</th>
						<td><div class="input-control text" style="width: 100%">
								<input type="text" id="account" name="account" />
							</div></td>
					</tr>
					<tr>
						<th>E-mail</th>
						<td><div class="input-control number" style="width: 100%">
								<input type="text" id="email" name="email" />
							</div></td>
					</tr>
					<tr>
						<th>Semester</th>
						<td>
							<div class="input-control select" style="width: 100%">
								<select id="select-semester-add" name="semesterId">
									<c:if test="${!empty listSemesters}">
										<c:forEach items="${listSemesters}" var="semester">
											<option value="${semester.semesterId}">${semester.name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th>Courses</th>
						<td>
							<div class="input-control text-aria" style="width:100%">
								<textarea rows="2" cols="1" id="courses-selected" disabled></textarea>
							</div>
							<div class="input-control select multiple full-size"
								style="height: 100px">
								<select id="select-courses" name="courses" multiple>
									<c:if test="${!empty listCourseSemesters}">
										<c:forEach items="${listCourseSemesters}" var="courseSemester">
											<option value="${courseSemester.course.courseId}">${courseSemester.course.code}</option>
										</c:forEach>
									</c:if>
								</select>
							</div> <font style="width: 100%; font-size: 0.7rem">(Ctrl +
								right click to select multiple courses)</font>
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
</body>
</html>