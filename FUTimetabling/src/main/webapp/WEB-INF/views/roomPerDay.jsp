<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Room Of Day</title>

<link href="../resources/css/roomPerDay.css" rel="stylesheet">

<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/roomPerDay.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div style="display: none">
		<div id="roomsData">${roomsData}</div>
		<div id="timetableData">${timetableData}</div>
	</div>
	<div style="width: 90%; margin: 0 auto;">

		<div style="width: 100%;">
			<h1 id="title" style="display: inline-block">
				<a href="index.html" class="nav-button transform"><span></span></a>
				&nbsp;
			</h1>
			<div style="float: right; margin: 1.2rem 0;">
				<div id="datepicker" class="input-control text"
					data-role="datepicker" data-other-days="true" data-week-start="1"
					data-locale="en"
					style="width: 250px; display: inline-block; margin: 0; margin-right: 5px;">
					<input type="text" name="date" id="dateInput">
					<button class="button">
						<span class="mif-calendar"></span>
					</button>
				</div>
				<button class="button" id="btn-remain-timetable"
					style="display: inline-block; margin: 0;">Not Set</button>
			</div>
		</div>
		<div style="width: 100%">
			<table class="table border bordered cell-hovered" id="table-rooms">
				<thead>
					<tr>
						<th rowspan="2">Rooms</th>
						<th colspan="6">Building A</th>
						<th rowspan="2">Rooms</th>
						<th colspan="6">Building B</th>
					</tr>
					<tr>
						<th>Slot 1</th>
						<th>Slot 2</th>
						<th>Slot 3</th>
						<th>Slot 4</th>
						<th>Slot 5</th>
						<th>Slot 6</th>
						<th>Slot 1</th>
						<th>Slot 2</th>
						<th>Slot 3</th>
						<th>Slot 4</th>
						<th>Slot 5</th>
						<th>Slot 6</th>
					</tr>
				</thead>
				<tbody id="table-body">

				</tbody>
			</table>
		</div>
	</div>

	<!-- dialog -->
	<div id="dialog-remain-timetable" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 600px; margin: 0 auto;">
			<div style="padding-bottom: 30px;" id="dialog-table">
				<table id="table-remain-timetable"
					class="table border bordered hovered">
					<thead>
						<tr>
							<th>Class</th>
							<th>Course</th>
							<th>Slot</th>
						</tr>
					</thead>
					<tbody id="remain-timetable">

					</tbody>
				</table>
			</div>
			<div style="width: 600px; margin: 0 auto;" id="div-empty">
				<h3>All slots of this day were set room successful!</h3>
			</div>
			<div>
				<button id="btn-cancel-remain-timetable" class="button"
					style="width: 100px; float: right;">CLOSE</button>
			</div>
		</div>
	</div>
	<div id="dialog-set-room" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark">
		<form id="form-set-room" method="post">
			<table class="table" id="table-set-room">
				<tr>
					<th>ID</th>
					<td>
						<div class="input-control text" style="width: 100%;">
							<input type="text" id="timetableId" name="timetableId" />
						</div>
					</td>
				</tr>
				<tr>
					<th>Class</th>
					<td>
						<div class="input-control text" style="width: 100%;">
							<input type="text" id="classCode" readonly disabled />
						</div>
					</td>
				</tr>
				<tr>
					<th>Course</th>
					<td><div class="input-control text" style="width: 100%;">
							<input type="text" id="courseCode" readonly disabled />
						</div></td>
				</tr>
				<tr>
					<th>Slot</th>
					<td><div class="input-control text" style="width: 100%;">
							<input type="text" id="slot" readonly disabled />
						</div></td>
				</tr>
				<tr>
					<th>Available<br>Rooms
					</th>
					<td>
						<div class="input-control select" style="width: 100%;">
							<select id="roomId" name="roomId">
							</select>
						</div>
					</td>
				</tr>
			</table>
		</form>
		<div style="width: 100$;">
			<div style="float: right; padding: 0.625rem 0.85rem;">
				<button id="btn-submit-set-room" class="button"
					style="width: 100px; margin-right: 5px">SAVE</button>
				<button id="btn-cancel-set-room" class="button"
					style="width: 100px; float: right;">CANCEL</button>
			</div>
		</div>
	</div>
</body>
</html>