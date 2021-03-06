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

<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">
<link href="../resources/css/pageStyle.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/roomArrangement.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<script src="../resources/js/jquery.dataTables.min.js"></script>

</head>
<style>
.left {
	margin: 5px;
	height: auto;
	text-align: center;
}

.left a {
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
	font-size: 1rem;
}

.left a:hover {
	background-color: #005696;
	color: #fff;
}

.left a.active {
	background-color: #005696;
	color: #fff;
}

#select-semesters a {
	width: 100%;
	text-align: left;
}

h3 {
	padding: 0.625rem 1rem;
	padding-left: 0;
	border-bottom: 1px #d9d9d9 solid;
	text-align: left;
	margin: .15625rem 0;
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
	<div style="display: none">
		<form:form id="setRooms" method="post">
			<div id="roomsData">${rooms}</div>
			<div id="classesCoursesData">${classesCourses}</div>
			<div id="dataRoomArrangements">${dataRoomArrangements}</div>
			<div id="dataMergeClass">${dataMergeClass}</div>
			<input type="text" id="dataToSet" name="dataToSet" />
		</form:form>
	</div>
	<div style="width: 80%; margin: 0 auto;">
		<h1>
			<a href="/FUTimetabling/staff/scheduleInfo"
				class="nav-button transform"><span></span></a> &nbsp;Arrange Room
		</h1>
		<div style="display: flex; margin-top: 30px;">
			<div id="select-semesters" class="left" style="display: inline-block">
				<h3>SEMESTERS</h3>
				<c:if test="${!empty listSemesters}">
					<c:forEach items="${listSemesters}" var="semester">
						<a id="${semester.semesterId}"
							href="?semesterId=${semester.semesterId}">${semester.name}</a>
					</c:forEach>
				</c:if>
			</div>
			<div style="display: inline-block; width: 100%;" class="left">
				<h3>CLASSES</h3>
				<div id="select-classes" style="height: 200px;">
					<c:if test="${!empty listClassSemesters}">
						<c:forEach items="${listClassSemesters}" var="classSemester">
							<a id="${classSemester.classSemesterId}">${classSemester.classFPT.code}</a>
						</c:forEach>
					</c:if>
				</div>

			</div>
		</div>
		<h3 style="width: 100%;">Arrangement - <font id="className"></font></h3>
		<div style="width: 100%;">
			<div style="width: 100%; margin-top: 10px;">
				<div style="display: inline-block; float: left">
					Select Room: &nbsp;&nbsp;
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
				</div>
			</div>

			<div id="arrange-room"
				style="margin-top: 10px; width: 100%; display: flex;">
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
					style="text-align: left; width: auto; display: inline-block; margin: 0.65rem 1rem; flex: 1; float: right; word-wrap: break-word;">
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
		<t:footer />
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
		style="text-align: center">
		<h3>You still have Error in your room arrangement!</h3>
		<button id="btn-cancel-error" class="button"
			style="width: 100px; margin: 0 auto;">CLOSE</button>

	</div>
</body>
</html>