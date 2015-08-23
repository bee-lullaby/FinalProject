<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>Schedule Infomation</title>

<link rel="shortcut icon" href="../resources/images/logo_fpt.png">
<link href="../resources/css/metro.css" rel="stylesheet">
<link href="../resources/css/metro-icons.css" rel="stylesheet">
<link href="../resources/css/docs.css" rel="stylesheet">

<script src="../resources/js/jquery-2.1.3.min.js"></script>
<script src="../resources/js/metro.js"></script>
<script src="../resources/js/docs.js"></script>
<script src="../resources/js/prettify/run_prettify.js"></script>
<script src="../resources/js/ga.js"></script>
<style>
#dataAuto thead {
	border-bottom: 2px solid #999999;
}

#dataAuto thead tr th, #dataAuto thead tr th {
	width: 50%;
}

#dataAuto thead tr th {
	text-align: right;
}

#dataAuto thead tr td {
	text-align: left;
}

#main button {
	width: 150px;
}
</style>
<script>
$(document).ready(function() {
	var monthNames = [
	                  "January", "February", "March",
	                  "April", "May", "June", "July",
	                  "August", "September", "October",
	                  "November", "December"
	              ];
	
	startTime();
	function startTime() {
	    var today= new Date();
	    var month = today.getMonth();
	    var date = today.getDate();
	    var year = today.getFullYear();
	    var h = today.getHours();
	    var m = today.getMinutes();
	    var s = today.getSeconds();
	    m = checkTime(m);
	    s = checkTime(s);
	    $("#clock").text(monthNames[month] +" " +date +", " +year +" " +h +":" +m +":" +s);
	    var t = setTimeout(function(){startTime()},500);
	}

	function checkTime(i) {
	    if (i<10) {i = "0" + i};  // add zero in front of numbers < 10
	    return i;
	}
	$("#btn-finish").on("click", function() {
		window.location = "/FUTimetabling/staff"
	});
	$("#btn-download").on("click", function() {
		_showDialog("dialog-download");
	});
	$("#btn-download-class").on("click", function() {
		window.location = "downloadTimetableClass?semesterId=" +${semesterId};
		_showDialog("dialog-download");
	});
	$("#btn-download-teacher").on("click", function() {
		window.location = "downloadTimetableTeacher?semesterId=" +${semesterId};
		_showDialog("dialog-download");
	});
	function _showDialog(id) {
		var dialog = $("#" + id).data('dialog');
		if (!dialog.element.data('opened')) {
			dialog.open();
		} else {
			dialog.close();
		}
	}
}); 
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
</head>
<body>
	<t:header />
	<div style="width: 100%; padding-bottom: 50px; text-align: center">
		<div id="head"
			style="width: 80%; text-align: center; margin: 0 auto; margin-top: 50px;">
			<h1>Automatic Timetabling Result</h1>
			<h6 id="clock" class="fg-steel"></h6>
		</div>


		<h5 style="margin-top: 30px;">Data result for ClassCourses of ${semesterName}</h5>
		<div id="line"
			style="border-bottom: 2px solid #999999; width: 50%; margin: 0 auto;"></div>
		<div id="main" style="width: 50%; text-align: center; margin: 0 auto;">
			<table id="dataAuto" class="table">
				<thead>
					<tr>
						<th>Set Timetable:</th>
						<td>${fn:length(listClassWasSetTimetablesDone)}</td>
					</tr>
					<tr>
						<th>Set Room:</th>
						<td>${fn:length(listClassWasSetRoomsDone)}</td>
					</tr>
					<tr>
						<th>Set Teacher:</th>
						<td>${fn:length(listClassWasSetTeachersDone)}</td>
					</tr>
				</thead>
			</table>
			<button class="button" id="btn-finish">Finish</button>
			<button class="button" id="btn-download">Download</button>
		</div>
	</div>
	
	<div id="dialog-download" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true" data-close-button="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<div id="btn-group" style="margin-top: 25px;">
			<h2>Download</h2>
				<button class="button" id="btn-download-class">Timetable for Classes</button>
				<button class="button" id="btn-download-teacher">Timetable for Teachers</button>
			</div>
		</div>
	</div>
</body>
</html>