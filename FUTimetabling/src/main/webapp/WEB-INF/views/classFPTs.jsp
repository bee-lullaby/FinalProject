<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>ClassFPT Page</title>

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/classFPTs.js"></script>
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

<body style="margin-bottom: 50px;">
	<div style="width: 80%; margin: 0 auto;">
		<h1>
			<a href="/Timetabling/staff" class="nav-button transform"><span></span></a>
			&nbsp;Classes Management
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
						<button id="btn-add-class" class="button" data-role="hint"
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
					<div id="table-info" style="float: left; margin-top: 20px;"></div>
					<table id="table-classes"
						class="table striped hovered border bordered cell-hovered">
						<thead>
							<tr>
								<th>Code</th>
								<th>Batch</th>
								<th>Batch Char</th>
								<th>Specialized</th>
								<th>Type</th>
								<th>Semester</th>
								<th>Courses</th>
								<th>Edit</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${!empty listClassSemesters}">
								<c:forEach items="${listClassSemesters}" var="classSemester">
									<tr data-classSemesterId="${classSemester.classSemesterId}"
										data-classId="${classSemester.classFPT.classId}">
										<td>${classSemester.classFPT.code}</td>
										<td>${classSemester.classFPT.batch}</td>
										<td>${classSemester.classFPT.batchChar}</td>
										<td>${classSemester.classFPT.specialized.name}</td>
										<td>${classSemester.classFPT.type}</td>
										<td>${classSemester.semester.name}</td>
										<td><a href="#">Courses</a>
											<div style="display: none" id="data-courses">
											<c:if
												test="${!empty classSemester.classCourseSemesters}">
												<c:forEach items="${classSemester.classCourseSemesters}"
													var="classCourseSemester" varStatus="loop">
													<div id="course-${classCourseSemester.classCourseSemesterId}">
														
													</div>
												</c:forEach>
											</c:if>
											</div></td>
										<td><a href="#"
											id="edit-class-${classSemester.classFPT.classId}">Edit</a></td>
										<td><a
											href="<c:url value='/staff/classFPTs/delete/${classSemester.classFPT.classId}' />">Delete</a></td>
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
		<form id="form-add-file" action="classFPTs/addFromFile" method="post"
			enctype="multipart/form-data" style="display: inline-block;">
			<div>
				<label class="input-control radio"> <input type="radio"
					name="act" value="0" checked> <span class="check"></span> <span
					class="caption">Add Classes</span>
				</label>
			</div>
			<div>
				<label class="input-control radio"> <input type="radio"
					name="act" value="1"> <span class="check"></span> <span
					class="caption">Add Courses For Classes</span>
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


	<div id="dialog-edit-class" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 600px; margin: 0 auto;">
			<h3 id="title">Edit Class</h3>
			<form id="form-edit-class" method="post">
				<table id="table-edit-class" class="table">
					<thead>
						<tr style="display: none">
							<td><input type="text" id="classId" name="classId" /></td>
							<td><input type="text" id="classSemesterId"
								name="classSemesterId" /></td>
						</tr>
						<tr>
							<th>Semester</th>
							<td><div class="input-control select" style="width: 200px">
									<select id="select-semester-edit" name="semesterId">
										<c:if test="${!empty listSemesters}">
											<c:forEach items="${listSemesters}" var="semester">
												<option value="${semester.semesterId}">${semester.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<th>Batch</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="batch" name="batch" />
								</div></td>
							<th>Batch Char</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="batchChar" name="batchChar" />
								</div></td>
						</tr>
						<tr>
							<th>Type</th>
							<td>
								<div class="input-control select" style="width: 200px">
									<select id="select-types" name="type">
										<option>Specialized</option>
										<option>Course</option>
									</select>
								</div>
							</td>
							<th>Specialized</th>
							<td>
								<div class="input-control select" style="width: 200px">
									<select id="select-specializeds" name="specializedId">
										<option value="-1">...</option>
										<c:if test="${!empty listSpecializeds}">
											<c:forEach items="${listSpecializeds}" var="specialized">
												<option value="${specialized.specializedId}">${specialized.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th>Courses</th>
							<td>
								<div class="input-control select multiple"
									style="height: 100px; width: 200px;">
									<select id="select-courses" name="courses" multiple>
										<c:if test="${!empty listCourseSemesters}">
											<c:forEach items="${listCourseSemesters}"
												var="courseSemester">
												<option value="${courseSemester.course.courseId}">${courseSemester.course.code}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
								<div>
									<font style="width: 100%; font-size: 0.7rem">(Ctrl +
										right click to select multiple courses)</font>
								</div>
							</td>
							<td colspan="2">
								<div class="input-control text-aria"
									style="width: 100%; height: 100%; vertical-align: top;">
									<textarea id="courses-selected" disabled></textarea>
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
	</div>

	<!-- Dialog Add -->
	<div id="dialog-add-class" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 600px; margin: 0 auto;">

			<h3 id="title">Add Class</h3>
			<form id="form-add-class" method="post">
				<table id="table-add-class" class="table">
					<thead>
						<tr style="display: none">
							<td><input type="text" id="classId" name="classId" /></td>
							<td><input type="text" id="classSemesterId"
								name="classSemesterId" /></td>
						</tr>
						<tr>
							<th>Semester</th>
							<td><div class="input-control select" style="width: 200px">
									<select id="select-semester-add" name="semesterId">
										<c:if test="${!empty listSemesters}">
											<c:forEach items="${listSemesters}" var="semester">
												<option value="${semester.semesterId}">${semester.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<th>Batch</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="batch" name="batch" />
								</div></td>
							<th>Batch Char</th>
							<td><div class="input-control text" style="width: 200px">
									<input type="text" id="batchChar" name="batchChar" />
								</div></td>
						</tr>
						<tr>
							<th>Type</th>
							<td>
								<div class="input-control select" style="width: 200px">
									<select id="select-types" name="type">
										<option>Specialized</option>
										<option>Course</option>
									</select>
								</div>
							</td>
							<th>Specialized</th>
							<td>
								<div class="input-control select" style="width: 200px">
									<select id="select-specializeds" name="specializedId">
										<option value="-1">...</option>
										<c:if test="${!empty listSpecializeds}">
											<c:forEach items="${listSpecializeds}" var="specialized">
												<option value="${specialized.specializedId}">${specialized.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th>Courses</th>
							<td>
								<div class="input-control select multiple"
									style="height: 100px; width: 200px;">
									<select id="select-courses" name="courses" multiple>
										<c:if test="${!empty listCourseSemesters}">
											<c:forEach items="${listCourseSemesters}"
												var="courseSemester">
												<option value="${courseSemester.course.courseId}">${courseSemester.course.code}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
								<div>
									<font style="width: 100%; font-size: 0.7rem">(Ctrl +
										right click to select multiple courses)</font>
								</div>
							</td>
							<td colspan="2">
								<div class="input-control text-aria"
									style="width: 100%; height: 100%; vertical-align: top;">
									<textarea id="courses-selected" disabled></textarea>
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
	</div>
</body>
</html>