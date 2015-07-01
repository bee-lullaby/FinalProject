<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Schedule</title>

<link href="resources/css/schedule.css" rel="stylesheet">
<link href="resources/css/metro.css" rel="stylesheet">
<link href="resources/css/metro-icons.css" rel="stylesheet">
<link href="resources/css/docs.css" rel="stylesheet">

<script src="resources/js/jquery-2.1.3.min.js"></script>
<script src="resources/js/schedule.js"></script>
<script src="resources/js/metro.js"></script>
<script src="resources/js/docs.js"></script>
<script src="resources/js/prettify/run_prettify.js"></script>
<script src="resources/js/ga.js"></script>

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
					<form style="height: 100%">
						<button id="btn-prev-class" class="button">
							<span class="mif-arrow-left"></span>
						</button>
						<div class="input-control select">
							<input type="hidden" name="semesterId" value="${semesterId}">
							<select id="select-classes" name="classId"
								onchange="this.form.submit()">

								<c:forEach items="${listClasses}" var="classSemester">
									<c:choose>
										<c:when
											test="${classSemester.getClassFPT().classId == param.classId}">
											<option value="${classSemester.getClassFPT().classId}"
												selected>${classSemester.getClassFPT().code}</option>
										</c:when>
										<c:otherwise>
											<option value="${classSemester.getClassFPT().classId}">${classSemester.getClassFPT().code}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>

						</div>
						<button id="btn-next-class" class="button">
							<span class="mif-arrow-right"></span>
						</button>
					</form>
				</div>
				<div style="float: right">
					<button id="btn-prev-week" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Previous Week">
						<span class="mif-chevron-thin-left"></span>
					</button>
					<div class="input-control select">
						<select id="select-weeks" onchange="_setDateHeader()">

						</select>
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
					<c:set var="x" value="0" scope="page" />
					<c:forEach items="${listClassCourses}" var="classCourseSemester">
						<c:set var="x" value="${x + 1}" scope="page" />
						<div id="course-${x}"
							style="cursor: pointer; display: inline-block">
							<span class="course-color color-${x}"></span>
							&nbsp;${classCourseSemester.getCourseSemester().getCourse().code}&nbsp;&nbsp;
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
			<div id="timetable-container" style="width: 100%; margin-top: 15px">
				<input type="hidden" id="startDate" value="${startDate}" /> <input
					type="hidden" id="endDate" value="${endDate}" />
				<table id="timetable"
					class="table cell-hovered border bordered timetable"
					style="width: 100%">
					<thead>
						<tr id="header">
							<th id="sun">Sun</th>
							<th id="mon">Mon</th>
							<th id="tue">Tue</th>
							<th id="wed">Wed</th>
							<th id="thu">Thu</th>
							<th id="fri">Fri</th>
							<th id="sat">Sat</th>
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

	<c:set var="y" value="0" scope="page" />
	<c:forEach items="${listCourses}" var="courseSemester">

		<c:set var="y" value="${y + 1}" scope="page" />
		<div id="dialog-info-course-${y}" data-role="dialog" class="padding20"
			data-close-button="true" data-overlay="true"
			data-overlay-color="op-dark">

			<h1>${courseSemester.getCourse().code}</h1>
			<table id="table-course" class="table">
				<tr>
					<th>Teacher (<font id="numberOfTeacher"></font>):
					</th>
					<td colspan="3">
						<div class="input-control select">
							<select>
								<c:forEach items="${listTeacherCourseSemester}" var="teacher">
									<c:choose>
										<c:when
											test="${courseSemester.courseSemesterId == teacher.getCourseSemester().courseSemesterId}">
											<option
												value="${teacher.getTeacherSemester().getTeacher().teacherId}">
												${teacher.getTeacherSemester().getTeacher().account}</option>
										</c:when>
									</c:choose>

								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr style="display: none">
					<td colspan="4"></td>
				</tr>
				<tr>
					<th>Remain slots:</th>
					<td>${courseSemester.getSlots()}</td>
					<th style="padding-left: 30px">Classes:</th>
					<td>${fn:length(courseSemester.classCourseSemesters)}</td>
				</tr>

			</table>
		</div>
	</c:forEach>


	<div id="dialog-schedule" data-role="dialog" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">

		<table class="bordered">
			<tr>
				<td style="padding: 10px; vertical-align: top;">
					<h3 id="slot-day">Course:</h3> Course:
					<div class="input-control select">
						<select id="set-courses" name="setCourse">
							<c:forEach items="${listCourses}" var="courseSemester">
								<option value="${courseSemester.getCourse().code}">${courseSemester.getCourse().code}</option>
							</c:forEach>
						</select>
					</div> <br>
					<button id="btn-clear" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Clear">SAVE</button>
				</td>
				<td style="border-left: thin solid #999999; padding: 10px;">
					<table id="course-info-to-set">
						
						<tr>
							<td style="text-align: left; padding: 5px;"><h4
									id="course-code"></h4></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Remain Slots:</th>
							<td id="remains_slot">30</td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Classes learn the course: </th>
							<td id="cltc"></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">In this slot: </th>
							<td id="in-this-slot"></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Afternoon:</th>
							<td id="teachers"></td>
						</tr>
					</table>
			</td>

			</tr>
		</table>
	</div>
</body>
</html>