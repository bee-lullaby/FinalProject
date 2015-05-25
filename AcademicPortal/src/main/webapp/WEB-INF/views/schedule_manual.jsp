<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>FPT University</title>

<link href="resources/css/metro.css" rel="stylesheet">
<link href="resources/css/metro-icons.css" rel="stylesheet">
<link href="resources/css/docs.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

<script src="resources/js/jquery-2.1.3.min.js"></script>
<script src="resources/js/metro.js"></script>
<script src="resources/js/docs.js"></script>
<script src="resources/js/prettify/run_prettify.js"></script>
<script src="resources/js/ga.js"></script>
<script src="resources/js/schedule-manual.js"></script>
<script async
	src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
</head>
<body>

	<div class="container">
		<div style="width: 100%">
			<div class="" style="display: inline-block">
				<h1>Schedule Courses</h1>
			</div>
			<div class="class-name"
				style="display: inline-block; float: right; width: auto;">
				<div class="mif-chevron-thin-left icon button-previous"
					style="display: inline-block; margin: .625rem 0; font-size: 1.5rem"
					onclick="window.location='/academic/schedule_manual?semester_id=1&action=previous'"></div>
				<div
					style="padding-left: 5px; padding-right: 5px; display: inline-block; vertical-align: middle;">
					<h3>${classname}</h3>
				</div>
				<div class="mif-chevron-thin-right icon button-next"
					style="display: inline-block; margin: .625rem 0; font-size: 1.5rem"
					onclick="window.location='/academic/schedule_manual?semester_id=1&action=next'"></div>
			</div>
		</div>
		<div style="width: 100%; height: 500px; padding-top: 10px">
			<div class="timetable-course"
				style="width: 80%; height: 100%; display: inline-block">
				<table class="table border bordered striped"
					style="width: 100%; height: 100%; margin-top: 0">
					<thead>
						<tr>
							<th>Mon (date)</th>
							<th>Tue (date)</th>
							<th>Wed (date)</th>
							<th>Thu (date)</th>
							<th>Fri (date)</th>
							<th>Sat (date)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td id="tt-1-1">1</td>
							<td id="tt-2-1">1</td>
							<td id="tt-3-1">1</td>
							<td id="tt-4-1">1</td>
							<td id="tt-5-1">1</td>
							<td id="tt-6-1">1</td>
						</tr>
						<tr>
							<td id="tt-1-2">2</td>
							<td id="tt-2-2">2</td>
							<td id="tt-3-2">2</td>
							<td id="tt-4-2">2</td>
							<td id="tt-5-2">2</td>
							<td id="tt-6-2">2</td>
						</tr>
						<tr>
							<td id="tt-1-3">3</td>
							<td id="tt-2-3">3</td>
							<td id="tt-3-3">3</td>
							<td id="tt-4-3">3</td>
							<td id="tt-5-3">3</td>
							<td id="tt-6-3">3</td>
						</tr>
						<tr>
							<td id="tt-1-4">4</td>
							<td id="tt-2-4">4</td>
							<td id="tt-3-4">4</td>
							<td id="tt-4-4">4</td>
							<td id="tt-5-4">4</td>
							<td id="tt-6-4">4</td>
						</tr>
						<tr>
							<td id="tt-1-5">5</td>
							<td id="tt-2-5">5</td>
							<td id="tt-3-5">5</td>
							<td id="tt-4-5">5</td>
							<td id="tt-5-5">5</td>
							<td id="tt-6-5">5</td>
						</tr>
						<tr>
							<td id="tt-1-6">6</td>
							<td id="tt-2-6">6</td>
							<td id="tt-3-6">6</td>
							<td id="tt-4-6">6</td>
							<td id="tt-5-6">6</td>
							<td id="tt-6-6">6</td>
						</tr>
					</tbody>
				</table>
				<div class="timetable-buttons-group"
					style="float: left; display: inline-block">
					<div id="button-copy" class="timetable-buttons"
						style="display: inline-block">
						<span class="mif-table icon"></span> <font
							style="padding-left: 5px; padding-right: 5px">Copy</font>
					</div>
					<div id="button-undo" class="timetable-buttons"
						style="display: inline-block">
						<span class="mif-undo icon"></span> <font
							style="padding-left: 5px; padding-right: 5px">Undo</font>
					</div>
					<div id="button-redo" class="timetable-buttons"
						style="display: inline-block">
						<span class="mif-redo icon"></span> <font
							style="padding-left: 5px; padding-right: 5px">Redo</font>
					</div>
					<div id="button-del" class="timetable-buttons"
						style="display: inline-block">
						<span class="mif-bin icon"></span> <font
							style="padding-left: 5px; padding-right: 5px">Clear</font>
					</div>
				</div>
				<div class="week">
					<span class="mif-chevron-thin-left icon button-previous"></span> <font
						style="padding-left: 5px; padding-right: 5px">17/5 - 23/5</font> <span
						class="mif-chevron-thin-right icon button-next"></span>
				</div>
			</div>
			<div class="button-course"
				style="width: 19%; margin-top: 0; text-align: center">
				${courses}
			</div>
		</div>
	</div>
</body>
</html>