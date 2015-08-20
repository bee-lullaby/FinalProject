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

button {
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
	$("#btn-timetabling").on("click", function() {
		_showDialog("dialog-timetabling");
		$("#start-time").text("Start time: " +$("#clock").text());
	});
	$("#btn-back").on("click", function() {
		window.location = "scheduleInfo"
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
</script>
</head>
<body>
	<div style="width: 100%; padding-bottom: 50px; text-align: center">
		<div id="head"
			style="width: 80%; text-align: center; margin: 0 auto; margin-top: 50px;">
			<h1>Automatic Timetabling</h1>
			<h6 id="clock" class="fg-steel"></h6>
		</div>


		<h5 style="margin-top: 30px;">Data will be used to schedule</h5>
		<div id="line"
			style="border-bottom: 2px solid #999999; width: 50%; margin: 0 auto;"></div>
		<div style="width: 50%; text-align: center; margin: 0 auto;">

			<table id="dataAuto" class="table">
				<thead>
					<tr>
						<th>Semester:</th>
						<td>${semesterName}</td>
					</tr>
					<tr>
						<th>Total classes:</th>
						<td>${totalClasses}</td>
					</tr>
					<tr>
						<th>Total class-course:</th>
						<td>${totalClassCourses}</td>
					</tr>
					<tr>
						<th>Total teachers:</th>
						<td>${totalTeachers}</td>
					</tr>
					<tr>
						<th>Total rooms:</th>
						<td>${totalRooms}</td>
					</tr>
				</thead>
			</table>
			<button class="button">Back</button>
			<button class="button" id="btn-timetabling">Timetabling</button>
		</div>
	</div>
	
	<div id="dialog-timetabling" data-role="dialog" class="padding20"
		data-overlay="true" data-overlay-color="op-dark"
		data-windows-style="true">
		<div style="width: 500px; margin: 0 auto; text-align: center;">
			<h7 style="float: left; margin-bottom: 30px;" id="start-time"></h7>
			<div data-role="preloader" data-type="metro" data-style="dark"></div>
			<h6 id="text-processing">Timetabling...</h6>
		</div>
	</div>
	
</body>
</html>