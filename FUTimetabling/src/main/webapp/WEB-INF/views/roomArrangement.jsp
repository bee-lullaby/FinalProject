<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> -->
<!DOCTYPE html>
<html>
<head>
<title>Room Arrangement</title>

<link href="resources/css/metro.css" rel="stylesheet">
<link href="resources/css/metro-icons.css" rel="stylesheet">
<link href="resources/css/docs.css" rel="stylesheet">

<script src="resources/js/jquery-2.1.3.min.js"></script>
<script src="resources/js/roomArrangement.js"></script>
<script src="resources/js/metro.js"></script>
<script src="resources/js/docs.js"></script>
<script src="resources/js/prettify/run_prettify.js"></script>
<script src="resources/js/ga.js"></script>
<script src="resources/js/jquery.dataTables.min.js"></script>
</head>
<body>
	<div style="display: none">
		<div id="roomsData">${rooms}</div>
		<div id="classesCoursesData">${classesCourses}</div>
		<div>${dataArrangement}</div>
	</div>
	<div style="width: 80%; margin: 0 auto;">
		<h1>
			<a href="index.html" class="nav-button transform"><span></span></a>
			&nbsp;Arrange Room - Semester ...
		</h1>
		<div style="width: 100%; padding: 15px; margin-left: 50px">
			<div style="width: 100%"></div>
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
							<option id="-1">...</option>
							<option id="custom">CUSTOM</option>
						</select>
					</div>
					<div class="input-control select" style="">
						<select id="select">
							<option id="mix">MIX</option>
							<option id="morning">MORNING (Slot 1/2/3)</option>
							<option id="afternoon">AFTERNOON (Slot 4/5/6)</option>
						</select>
					</div>
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
						Number of Classes&nbsp;&nbsp;
						<button class="button" id="btn-num-classes"></button>
					</div>
					<div style="display: inline-block">
						Number of Rooms&nbsp;&nbsp;
						<button class="button" id="btn-num-rooms"></button>
					</div>

					<div>
						<h4>WARNING:</h4>
						<div class="listview set-border padding10" data-role="listview">
							<div class="list-group">
								<span class="list-group-toggle"></span>
								<div class="list-group-content" style="margin-top: 0;">
									<pre style="margin-left: 48px"></pre>
								</div>
							</div>
						</div>
						
						
						<h4>Special Rooms:</h4>
						<div class="listview set-border padding10" data-role="listview">
							<div class="list-group">
								<span class="list-group-toggle">The seating capacity of the rooms is more than 30:</span>
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
</body>
</html>