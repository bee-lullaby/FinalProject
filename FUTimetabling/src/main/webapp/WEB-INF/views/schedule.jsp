<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Schedule</title>

<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/schedule.css" rel="stylesheet">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/schedule.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>

</head>
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
	<div style="display: none">
		<form:form id="generate" method="post">
			<input type="hidden" type="text" id="semesterId" name="semesterId" />
			<input type="hidden" type="text" id="classId" name="classId" />
			<input type="hidden" type="text" id="week" name="week" />
		</form:form>
	</div>
	<div style="display: none">
		<form:form id="data" method="post">
			<div id="JSONdata" style="display: none">${JSONdata}</div>
			<div id="JSONdataMergeClass" style="display: none">${JSONdataMergeClass}</div>
			<input type="hidden" type="text" id="JSONprev" name="JSONprev" />
			<input type="hidden" type="text" id="JSONToSubmit"
				name="JSONToSubmit" />
		</form:form>
	</div>
	<div style="width: 100%; height: 100%">
		<div style="width: 80%; margin: 0 auto;">
			<h1>
				<a href="/FUTimetabling/staff/scheduleInfo"
				class="nav-button transform"><span></span></a>
				&nbsp;Schedule - ${semesterName}
			</h1>

			<div style="width: 100%;">
				<form id="form-schedule" style="height: 100%">
					<div style="display: inline-block;">
						<button id="btn-prev-class" class="button">
							<span class="mif-arrow-left"></span>
						</button>
						<div class="input-control select">
							<input type="hidden" id="semesterId" name="semesterId"
								value="${semesterId}"> <select id="select-classes"
								name="classId">
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

					</div>
					<div style="float: right">
						<button id="btn-prev-week" class="button" data-role="hint"
							data-hint-background="#1CB7EC" data-hint-color="fg-white"
							data-hint-position="top" data-hint="Previous Week">
							<span class="mif-chevron-thin-left"></span>
						</button>
						<div class="input-control select">
							<select id="select-weeks" name="week">
							</select>
						</div>
						<button id="btn-next-week" class="button" data-role="hint"
							data-hint-background="#1CB7EC" data-hint-color="fg-white"
							data-hint-position="top" data-hint="Next Week">
							<span class="mif-chevron-thin-right"></span>
						</button>
					</div>
				</form>
			</div>

			<div style="width: 100%; line-height: 40px; margin: .5rem 0;">
				<div style="display: inline-block">
					<font style="color: #52677a; font-weight: 700; font-size: 1rem">Courses:</font>&nbsp;&nbsp;&nbsp;
					<c:set var="x" value="0" scope="page" />
					<c:forEach items="${listClassCourses}" var="classCourseSemester">
						<c:set var="x" value="${x + 1}" scope="page" />
						<div id="course-${x}"
							style="display: inline-block">
							<span id="${classCourseSemester.classCourseSemesterId}"
								class="course-color color-${x}"></span>
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
					<tbody id="timetable-body">
						<tr id="slot-1"></tr>
						<tr id="slot-2"></tr>
						<tr id="slot-3"></tr>
						<tr id="slot-4"></tr>
						<tr id="slot-5"></tr>
						<tr id="slot-6"></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div id="dialog-schedule" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<table class="bordered" style="margin: 0 auto">
			<tr>
				<td style="padding: 10px; vertical-align: top;">
					<h2 id="slot-day">Course:</h2>
					<div style="margin-top:30px; margin-left: 0;">
						<font style="font-size: 1.5rem">Course:&nbsp;</font>
						<div class="input-control select">
							<select id="set-courses" name="setCourse">
								<option value="-1">...</option>
								<c:forEach items="${listClassCourses}" var="classCourseSemester">
									<option value="${classCourseSemester.classCourseSemesterId}">${classCourseSemester.getCourseSemester().getCourse().code}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div id="warning-not-enough-slot" class="fg-red" style="margin: 5px 0;">Don't have enough slot to set.</div>
					<div id="warning-set-teacher" class="fg-red" style="margin: 5px 0;">Don't have enough Teacher to set.</div>
					<div id="warning-set-room" class="fg-red" style="margin: 5px 0;">Don't have enough Room to set.</div>
					<div id="warning-conflict-merge-class" class="fg-red" style="margin: 5px 0;">Conflict with timetable of Merge-Class.</div>
					<div>
						<button id="btn-set-course" class="button" style="width: 100px">SET</button>
						<button id="btn-cancel-set-course" class="button"
							style="width: 100px;">CANCEL</button>
					</div>
				</td>
				<td style="border-left: thin solid #999999; padding: 10px;">
					<table id="course-info-to-set">
						<tr>
							<td style="text-align: left; padding: 5px;"><font
								style="font-size: 1.5rem" id="course-code"></font></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Remain Slots:</th>
							<td id="remainSlots" style="padding: 15px"></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Teachers:</th>
							<td id="teachers" style="padding: 15px"></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Total Classes
								learn this Course:</th>
							<td id="classes" style="padding: 15px"></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Classes learn
								the course in this slot:
							</th>
							<td id="learnCourseInSlot" style="padding: 15px"></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Total Rooms:</th>
							<td id="totalRooms" style="padding: 15px"></td>
						</tr>
						<tr>
							<th style="text-align: left; padding: 5px;">Classes learn in
								this slot:</th>
							<td id="classesInSlot" style="padding: 15px"></td>
						</tr>
					</table>
				</td>

			</tr>
		</table>
	</div>

	<!-- Dialog Warning Clear -->
	<div data-role="dialog" id="dialog-warning-clear" class="padding20"
		data-close-button="true" data-overlay="true"
		data-overlay-color="op-dark">
		<h1>Clear Data In This Week</h1>
		<p>This action will clear all data in this week of this class. Do
			you really want to clear?</p>
		<div style="text-align: center; width: 100%">
			<button class="button" id="btn-accept-clear">Accept</button>
			<button class="button" id="btn-decline-clear">Decline</button>
		</div>
	</div>


</body>
</html>