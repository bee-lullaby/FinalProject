<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Schedule</title>
<link href="resources/css/metro.css" rel="stylesheet">
<link href="resources/css/metro-icons.css" rel="stylesheet">
<link href="resources/css/docs.css" rel="stylesheet">

<script src="resources/js/jquery-2.1.3.min.js"></script>
<script src="resources/js/mine.js"></script>
<script src="resources/js/metro.js"></script>
<script src="resources/js/docs.js"></script>
<script src="resources/js/prettify/run_prettify.js"></script>
<script src="resources/js/ga.js"></script>
<style type="text/css">
.timetable th {
	width: 14.2%;
}

.timetable td {
	height: 80px;
	vertical-align: top;
	text-align: left;
}

.course-color {
	width: 1.625rem;
	height: 1.625rem;
	border: 1px #ffffff solid;
	padding: 0;
	position: relative;
	display: inline-block;
	vertical-align: middle;
}

.color-1 {
	background: #60a917 !important;
}

.color-2 {
	background: #57169a !important;
}

.color-3 {
	background: #fa6800 !important;
}

.color-4 {
	background: #00aff0 !important;
}

.color-5 {
	background: #9a165a !important;
}

#table-course th, #table-course td {
	text-align: left;
	padding-left: 0;
}
</style>

</head>
<body>
	<div style="width: 100%; height: 100%">
		<div style="width: 80%; margin: 0 auto;">
			<h1>
				<a href="index.html" class="nav-button transform"><span></span></a>
				&nbsp;Schedule - ${semesterName}
			</h1>

			<div style="width: 100%;">
				<div style="display: inline-block;">
					<button id="btn-prev-class" class="button">
						<span class="mif-arrow-left"></span>
					</button>
					<div class="input-control select">
						<form style="height:100%">
							<input type="hidden" name="semesterId" value="${semesterId}">
							<select id="select-classes" name="classId" onchange="this.form.submit()">
								<c:forEach items="${listClasses}" var="classSemester">
									<option value="${classSemester.getClassFPT().classId}">${classSemester.getClassFPT().code}</option>
								</c:forEach>
							</select>
						</form>
					</div>
					<button id="btn-next-class" class="button">
						<span class="mif-arrow-right"></span>
					</button>
				</div>
				<div style="float: right">
					<button id="btn-prev-week" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Previous Week">
						<span class="mif-chevron-thin-left"></span>
					</button>
					<div class="input-control text">
						<input type="text">
					</div>
					<button id="btn-next-week" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Next Week">
						<span class="mif-chevron-thin-right"></span>
					</button>
				</div>
			</div>

			<div style="width: 100%; line-height: 40px; margin: .5rem 0;">
				<div style="display: inline-block">
					<font style="color: #52677a; font-weight: 700; font-size: 1rem">Courses:</font>&nbsp;&nbsp;&nbsp;
					<c:set var="count" value="0" scope="page" />
					<c:forEach items="${listCourses}" var="courseSemester">
						<c:set var="count" value="${count + 1}" scope="page" />
						<div id="course-${count}"
							style="cursor: pointer; display: inline-block">
							<span class="course-color color-${count}"></span>&nbsp;${courseSemester.getCourse().code}&nbsp;&nbsp;
						</div>
					</c:forEach>
				</div>
				<div style="float: right;">
					<button id="btn-generate" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Generate From Previous Week">
						<span class="mif-table"></span>
					</button>
					|
					<button id="btn-undo" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Undo">
						<span class="mif-undo"></span>
					</button>
					<button id="btn-redo" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Redo">
						<span class="mif-redo"></span>
					</button>
					|
					<button id="btn-clear" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Clear">
						<span class="mif-cross"></span>
					</button>
				</div>
			</div>
			<div style="width: 100%; margin-top: 15px">
				<table class="table cell-hovered border bordered timetable"
					style="width: 100%">
					<thead>
						<tr>
							<th>Sun</th>
							<th>Mon</th>
							<th>Tue</th>
							<th>Wed</th>
							<th>Thu</th>
							<th>Fri</th>
							<th>Sat</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="tt-1-1"></td>
							<td id="tt-2-1"></td>
							<td id="tt-3-1"></td>
							<td id="tt-4-1"></td>
							<td id="tt-5-1"></td>
							<td id="tt-6-1"></td>
							<td id="tt-7-1"></td>
						</tr>
						<tr>
							<td id="tt-1-2"></td>
							<td id="tt-2-2"></td>
							<td id="tt-3-2"></td>
							<td id="tt-4-2"></td>
							<td id="tt-5-2"></td>
							<td id="tt-6-2"></td>
							<td id="tt-7-2"></td>
						</tr>
						<tr>
							<td id="tt-1-3"></td>
							<td id="tt-2-3"></td>
							<td id="tt-3-3"></td>
							<td id="tt-4-3"></td>
							<td id="tt-5-3"></td>
							<td id="tt-6-3"></td>
							<td id="tt-7-3"></td>
						</tr>
						<tr>
							<td id="tt-1-4"></td>
							<td id="tt-2-4"></td>
							<td id="tt-3-4"></td>
							<td id="tt-4-4"></td>
							<td id="tt-5-4"></td>
							<td id="tt-6-4"></td>
							<td id="tt-7-4"></td>
						</tr>
						<tr>
							<td id="tt-1-5"></td>
							<td id="tt-2-5"></td>
							<td id="tt-3-5"></td>
							<td id="tt-4-5"></td>
							<td id="tt-5-5"></td>
							<td id="tt-6-5"></td>
							<td id="tt-7-5"></td>
						</tr>
						<tr>
							<td id="tt-1-6"></td>
							<td id="tt-2-6"></td>
							<td id="tt-3-6"></td>
							<td id="tt-4-6"></td>
							<td id="tt-5-6"></td>
							<td id="tt-6-6"></td>
							<td id="tt-7-6"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Dialogs -->
	<div id="dialog-info-course-1" data-role="dialog" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">
		<h1>Course 1</h1>
		<p>
		<table id="table-course" class="table">
			<tr>
				<th>Teacher (4):</th>
				<td colspan="3">
					<div class="input-control select">
						<select>
							<option value="1">Teacher 1</option>
							<option value="2">Teacher 2</option>
							<option value="3">Teacher 3</option>
						</select>
					</div>
				</td>
			</tr>
			<tr style="display: none">
				<td colspan="4"></td>
			</tr>
			<tr>
				<th>Remain slots:</th>
				<td>30</td>
				<th style="padding-left: 30px">Classes:</th>
				<td>6</td>
			</tr>
			<tr>
				<th>Morning:</th>
				<td>4</td>
				<th style="padding-left: 30px">Afternoon:</th>
				<td>2</td>
			</tr>
		</table>
	</div>
	<div id="dialog-info-course-2" data-role="dialog" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">
		<h1></h1>
		<p></p>
	</div>
	<div id="dialog-info-course-3" data-role="dialog" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">
		<h1></h1>
		<p></p>
	</div>
	<div id="dialog-info-course-4" data-role="dialog" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">
		<h1></h1>
		<p></p>
	</div>
	<div id="dialog-info-course-5" data-role="dialog" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">
		<h1></h1>
		<p></p>
	</div>

	<div id="dialog-schedule" data-role="dialog" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">
		<h4>Monday (dd/mm) - Slot 1</h4>
		<table>
			<tr>
				<th>Course:</th>
				<td>
					<div class="input-control select">
						<select>
							<option>Course 1</option>
							<option disabled>Course 2</option>
							<option>Course 3</option>
							<option>Course 4</option>
							<option>Course 5</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<th>Can:</th>
				<td>c1, c2, c3</td>
			</tr>
			<tr>
				<th>Can't:</th>
				<td>c4, c5</td>
			</tr>
		</table>

	</div>
</body>
</html>