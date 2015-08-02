<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Room Arrangement</title>

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/roomArrangement.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div style="display: none">
		<form:form id="setRooms" method="post">
			<div id="roomsData">${rooms}</div>
			<div id="classesCoursesData">${classesCourses}</div>
			<div id="dataRoomArrangements">${dataRoomArrangements}</div>
			<input type="text" id="dataToSet" name="dataToSet" />
		</form:form>
	</div>
	<div style="width: 80%; margin: 0 auto;">
		<h1>
			<a href="/Timetabling/staff/scheduleInfo" class="nav-button transform"><span></span></a>
			&nbsp;Arrange Room - Semester ...
		</h1>
		<div style="width: 100%; padding: 15px; margin-left: 50px">
			<div style="width: 100%"></div>
			<form:form id="control-classes" method="get">
				<input type="hidden" id="semesterId" name="semesterId" />
				<input type="hidden" id="classId" name="classId" />
			</form:form>
			<div style="padding-top: 15px; width: 100%;">
				<div style="display: inline-block">
					<button id="btn-prev-class" class="button">
						<span class="mif-arrow-left"></span>
					</button>
					<div style="display: inline-block" class="input-control select">
						<select id="select-classes" name="classId">
						</select>
					</div>
					<div class="input-control select">
						<select id="select-rooms">
							<option value="-1">...</option>
							<option value="custom">CUSTOM</option>
						</select>
					</div>
					<button id="btn-submit" class="button" data-role="hint"
						data-hint-background="#1CB7EC" data-hint-color="fg-white"
						data-hint-position="top" data-hint="Submit">
						<span class="mif-download"></span>
					</button>
					<button id="btn-prev-class" class="button">
						<span class="mif-arrow-right"></span>
					</button>
				</div>
			</div>

			<div id="arrange-room"
				style="padding-top: 15px; width: 100%; display: flex;">
				<div style="display: inline-block;">
					<table id="example_table"
						class="table striped hovered border bordered" style="width: auto;">
						<thead>
							<tr>
								<th>Course</th>
								<th>Room</th>
							</tr>
						</thead>
						<tbody id="courses-class">

						</tbody>
					</table>
				</div>

				<div class="example" data-text="note"
					style="width: auto; display: inline-block; margin: 0.65rem 1rem; flex: 1; float: right; word-wrap: break-word;">
					<div style="display: inline-block; margin-right: 25px">
						<b>Number of Classes:</b>&nbsp;&nbsp;
						<button class="button" id="btn-num-classes"></button>
					</div>
					<div style="display: inline-block">
						<b>Number of Rooms:</b>&nbsp;&nbsp;
						<button class="button" id="btn-num-rooms"></button>
					</div>

					<div>
						<div id="warning">
							<h5>WARNING:</h5>
							<div class="listview set-border padding10" data-role="listview">
								<div class="list-group">
									<div class="list-group-content" style="margin-top: 0;">
										<pre id="warning-room-arrangement" class="fg-red"
											style="margin-left: 25px; font-size: 1rem"></pre>
									</div>
								</div>
							</div>
						</div>

						<h5>SPECIAL ROOMS:</h5>
						<div class="listview set-border padding10" data-role="listview">
							<div class="list-group">
								<span class="list-group-toggle">The seating capacity of
									the rooms is more than 30:</span>
								<div class="list-group-content" style="margin-top: 0;">
									<pre id="more-than-30" style="margin-left: 48px"></pre>
								</div>
							</div>
							<div class="list-group">
								<span class="list-group-toggle"> For Courses: </span>
								<div class="list-group-content" style="margin-top: 0;">
									<pre id="courses-only" style="margin-left: 48px"></pre>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- dialog -->
	<div id="dialog-info-classes" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 60%; margin: 0 auto;">
			<div style="padding-bottom: 30px;">
				<table id="table-info-classes"
					class="dataTable striped border bordered">
					<thead>
						<tr>
							<th>Class</th>
							<th>Set Successful</th>
							<th>Total Slots</th>
							<th>Not Set Slots</th>
							<th>Note</th>
						</tr>
					</thead>
					<tbody id="info-classes">

					</tbody>
				</table>
			</div>
			<div>
				<button id="btn-cancel-info-classes" class="button"
					style="width: 100px; float: right;">CLOSE</button>
			</div>
		</div>
	</div>

	<div id="dialog-error" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true" data-type="warning"
		style="text-align:center">
		<h3>You still have Error in your room arrangement!</h3>
		<button id="btn-cancel-error" class="button"
			style="width: 100px; margin: 0 auto;">CLOSE</button>

	</div>
</body>
</html>