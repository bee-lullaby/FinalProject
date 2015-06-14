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
		th{
			width: 14.2%;
		}
		td {
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
	</style>

</head>
<body>
	<div style="width: 100%; height: 100%">
		<div style="width: 80%; margin: 0 auto;">
			<h1>
				<a href="index.html" class="nav-button transform"><span></span></a>
				&nbsp;Schedule - Semester ...
			</h1>

			<div style="width: 100%;">
				<div style="display:inline-block;">
					<button id="btn-prev-class" class="button">
						<span class="mif-arrow-left"></span>
					</button>
					<div class="input-control select" >
						<select id="select-classes">
							<option>Class 1</option>
							<option>Class 2</option>
							<option>Class 3</option>
							<option>Class 4</option>
							<option>Class 5</option>
							<option>Class 6</option>
						</select>
					</div>		
					<button id="btn-next-class" class="button" >
						<span class="mif-arrow-right"></span>
					</button>	
				</div>
				<div style="float: right">
					<button id="btn-prev-week" class="button" data-role="hint" data-hint-background="#1CB7EC"  data-hint-color="fg-white" data-hint-position="top" data-hint="Previous Week">
						<span class="mif-chevron-thin-left"></span>
					</button>	
					<div class="input-control text">
						<input type="text">
					</div>		
					<button id="btn-next-week" class="button" data-role="hint" data-hint-background="#1CB7EC"  data-hint-color="fg-white" data-hint-position="top" data-hint="Next Week">
						<span class="mif-chevron-thin-right"></span>
					</button>
				</div>
			</div>

			<div style="width: 100%; line-height: 40px; margin: .5rem 0;">
				<div style="display: inline-block">
					<font style="color: #52677a;font-weight: 700; font-size: 1rem">Courses:</font>&nbsp;&nbsp;&nbsp;
					<div id="course-1" style="cursor: pointer; display: inline-block">
						<span class="course-color bg-green"></span>&nbsp;Course 1&nbsp;&nbsp;
					</div>
					<div id="course-2" style="cursor: pointer; display: inline-block">
						<span class="course-color bg-darkViolet"></span>&nbsp;Course 2&nbsp;&nbsp;
					</div>
					<div id="course-3" style="cursor: pointer; display: inline-block">
						<span class="course-color bg-orange"></span>&nbsp;Course 3&nbsp;&nbsp;
					</div>
					<div id="course-4" style="cursor: pointer; display: inline-block">
						<span class="course-color bg-blue"></span>&nbsp;Course 4&nbsp;&nbsp;
					</div>
					<div id="course-5" style="cursor: pointer; display: inline-block">
						<span class="course-color bg-darkPink"></span>&nbsp;Course 5&nbsp;&nbsp;
					</div>
				</div>
				<div style="float: right;">
					<button id="btn-generate" class="button" data-role="hint" data-hint-background="#1CB7EC"  data-hint-color="fg-white" data-hint-position="top" data-hint="Generate From Previous Week">
						<span class="mif-table"></span>
					</button>
					|
					<button id="btn-undo" class="button" data-role="hint" data-hint-background="#1CB7EC"  data-hint-color="fg-white" data-hint-position="top" data-hint="Undo">
						<span class="mif-undo"></span>
					</button>	
					<button id="btn-redo" class="button" data-role="hint" data-hint-background="#1CB7EC"  data-hint-color="fg-white" data-hint-position="top" data-hint="Redo">
						<span class="mif-redo"></span>
					</button>
					|
					<button id="btn-clear" class="button" data-role="hint" data-hint-background="#1CB7EC"  data-hint-color="fg-white" data-hint-position="top" data-hint="Clear">
						<span class="mif-cross"></span>
					</button>	
				</div>
			</div>
			<div style="width: 100%; margin-top: 15px">
				<table class="table cell-hovered border bordered"style="width: 100%">
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
	<div id="dialog-info-course-1" data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		${course1}
	</div>
	<div id="dialog-info-course-2" data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		${course2}
	</div>
	<div id="dialog-info-course-3" data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		${course3}
	</div>
	<div id="dialog-info-course-4" data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		${course4}
	</div>
	<div id="dialog-info-course-5" data-role="dialog" class="padding20" data-close-button="true" data-overlay="true" data-overlay-color="op-dark">
		${course5}
	</div>
</body>
</html>