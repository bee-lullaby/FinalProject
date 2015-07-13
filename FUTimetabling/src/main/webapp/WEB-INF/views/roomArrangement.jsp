<!-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div style="display:none">
		<div>${rooms}</div>
		<div>${classesCourses}</div>
		<div>${dataArrangement}</div>
	</div>
	<div style="width: 80%; margin: 0 auto;">
		<h1>
			<a href="index.html" class="nav-button transform"><span></span></a>
			&nbsp;Arrange Room - Semester ...
		</h1>
		<div style="width: 100%; padding: 15px; margin-left:50px">
			<div style="width: 100%">  
				
			</div>
			<div style="padding-top: 15px; width: 100%;">
				<div style="display:inline-block">
					<button id="btn-prev-class" class="button" >
						<span class="mif-arrow-left"></span>
					</button>
					<div style="display:inline-block" class="input-control select">
						<select  id="select-classes" name="classId">
							<option>ES20701</option>
							<option>ES20702</option>
							<option>ES20703</option>
							<option>ES20704</option>
						</select>
					</div>
					<div id="arrange-4-class" class="input-control select" style="">
						<select>
							<option>...</option>
							<option>Room 1</option>
							<option>Room 2</option>
							<option>Room 3</option>
							<option>Room 4</option>
							<option>Room 5</option>
						</select>
					</div>
					<button id="btn-prev-class" class="button">
						<span class="mif-arrow-right"></span>
					</button>
				</div>
			</div>

			<div id="arrange-room" style="padding-top:15px; width:100%; display: flex;">
				<div style="display:inline-block;">
					<table id="example_table" class="table striped hovered border bordered" style="width:auto;">
						<thead>
							<tr>
								<th>Course</th>
								<th>Room</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Course 1</td>
								<td>
									<div class="input-control select" style="">
										<select>
											<option>...</option>
											<option>Custom</option>
											<option>Room 1</option>
											<option>Room 2</option>
											<option>Room 3</option>
											<option>Room 4</option>
											<option>Room 5</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>Course 1</td>
								<td>
									<div class="input-control select" style="">
										<select>
											<option>...</option>
											<option>Room 1</option>
											<option>Room 2</option>
											<option>Room 3</option>
											<option>Room 4</option>
											<option>Room 5</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>Course 1</td>
								<td>
									<div class="input-control select" style="">
										<select>
											<option>...</option>
											<option>Room 1</option>
											<option>Room 2</option>
											<option>Room 3</option>
											<option>Room 4</option>
											<option>Room 5</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>Course 1</td>
								<td>
									<div class="input-control select" style="">
										<select>
											<option>...</option>
											<option>Room 1</option>
											<option>Room 2</option>
											<option>Room 3</option>
											<option>Room 4</option>
											<option>Room 5</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>Course 1</td>
								<td>
									<div class="input-control select" style="">
										<select>
											<option>...</option>
											<option>Room 1</option>
											<option>Room 2</option>
											<option>Room 3</option>
											<option>Room 4</option>
											<option>Room 5</option>
										</select>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="example" data-text="note" style=" width: auto; display:inline-block; margin: 0.65rem 1rem; flex: 1; float: right; word-wrap: break-word;">
					<div style="display:inline-block; margin-right: 25px">
						Number of Classes&nbsp;&nbsp; 
						<button class="button" id="btn-num-classes"></button>
					</div>
					<div style="display:inline-block">
						Number of Rooms&nbsp;&nbsp;
						<button class="button" id="btn-num-rooms"></button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>