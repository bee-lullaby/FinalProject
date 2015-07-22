$(document).ready(function() { 
	
	var departmentsData = $("#departmentsData").text();
	var departmentsJSON = JSON.parse(departmentsData);
	
	var coursesData = $("#coursesData").text();
	var coursesJSON = JSON.parse(coursesData);
	
	var courseSemesterData = $("#courseSemesterData").text();
	var courseSemesterJSON = JSON.parse(courseSemesterData);
	
	var dtaData = $("#dtaData").text();
	var dtaJSON = JSON.parse(dtaData);
	

	_init();
	
	$("#btn-course").on("click", function() {
		$("#list-btn-courses").slideToggle();
	});
	
	$("#select-departments").on("change", function() {
		window.location="teacherArrangement?semesterId=" +_urlParam("semesterId") +"&departmentId=" +$(this).find("option:selected").val();
	});
	
	$("#list-btn-courses button").on("click", function() {
		window.location="teacherArrangement?semesterId=" +_urlParam("semesterId") +"&departmentId=" +_urlParam("departmentId") +"&courseId=" +$(this).val();
	});
	
	$("select[id^='select-teacher-']").on("change", function() {
		var tr = $(this).closest("tr");
		if(tr.find("#warning").text() != null && tr.find("#warning").text() != "") {
			$("#warning").text("");
		}
		
		var thisClass = tr.find("td:eq(0)").text();
		var teacherSelected = $(this).find("option:selected").val();
		
		$("#table-classes tr").each(function() {
			if($(this).find("td:eq(1)").text().indexOf(thisClass) > -1) {
				if($(this).find("td:eq(2) select option:selected").val() == teacherSelected) {
					tr.find("#warning").text("Teacher was arranged to class " +$(this).find("td:eq(0)").text()
							+" ! Cant be arranged to  class " +thisClass +"!");
					tr.find("select option:first").attr("selected", "selected");
				}
			}
		});
	});
	
	
	$("#btn-submit").on("click", function() {
		$("#table-classes tr").each(function() {
			var classCourseSemester = _getClassCourseSemester($(this).attr("id"));
			var teacherSemester = _getTeacherSemester($(this).find("td:eq(2) select option:selected").val());
			if(classCourseSemester.timetable != null && classCourseSemester.timetable.length > 0) {
				if(teacherSemester != "-1") {
						classCourseSemester.timetable[i].teacherSemester = teacherSemester;
					
				} else {

					for(var i = 0; i < classCourseSemester.timetable.length; i++) {
						if(classCourseSemester.timetable[i].teacherSemester != null) {
							classCourseSemester.timetable[i].teacherSemester = null;
						}
					}
				}
			}
		});
		
		
	}); 
	
	function _getTeacherSemester(teacherSemesterId) {
		for(var i = 0; i < courseSemesterJSON.teacherCourseSemesters.length; i++) {
			if(courseSemesterJSON.teacherCourseSemesters[i].teacherCourseSemesterId == teacherSemesterId) {
				return courseSemesterJSON.teacherCourseSemesters[i];
			}
		}
	}
	
	function _getClassCourseSemester(classCourseSemesterId) {
		for(var i = 0; i < courseSemesterJSON.classCourseSemesters.length; i++) {
			if(courseSemesterJSON.classCourseSemesters[i].classCourseSemesterId == classCourseSemesterId) {
				return courseSemesterJSON.classCourseSemesters[i];
			}
		}
	}
	
	function _init() {
		_setSelectDepartments();
		
		_setListBtnCourses();
		
		_setTextBtnCourse();
		
		_setDataTableClasses();
		
		_setCellConflict();
		
		_setSelectTeachers();
	}
	
	
	function _setSelectDepartments() {
		for(var i = 0; i < departmentsJSON.length; i++) {
			$("#select-departments").append(_getOptionSelectDepartments(i));
		}
		
		$("#select-departments option[value='" +_urlParam("departmentId") +"']").attr("selected", "selected");
	}
	
	function _getOptionSelectDepartments(position) {
		return "<option value='" +departmentsJSON[position].departmentId +"'>" +
				departmentsJSON[position].name +"</option>";
	} 
	
	
	function _setTextBtnCourse() {
		$("#btn-course").text(courseSemesterJSON.course.code);
		$("#num-of-classes").text(courseSemesterJSON.classCourseSemesters.length);
		$("#num-of-teachers").text(courseSemesterJSON.teacherCourseSemesters.length);
	}
	
	function _setListBtnCourses() {
		for(var i = 0; i < coursesJSON.length; i++) {
			$("#list-btn-courses").append("<button class='button' value='" +coursesJSON[i].courseId +"'>" +coursesJSON[i].code +"</button>");
		} 
	}
	
	function _setDataTableClasses() {
		var classCourseSemesters = courseSemesterJSON.classCourseSemesters;
		for(var i = 0; i < classCourseSemesters.length; i++) {
			$("#table-classes tbody").append(_getTRTableClasses(classCourseSemesters,  i));
		}
		if(classCourseSemesters == null || classCourseSemesters.length == 0) {
			$("#table-classes tbody").append("<tr><td colspan='3' style='text-align:center'>No Class To Show!</td></tr>");
		}
	}
	
	function _getTRTableClasses(classCourseSemesters, position) {
		text = "<tr id='" +classCourseSemesters[position].classCourseSemesterId +"'>" +
				"<td>" 
				+classCourseSemesters[position].classSemester.classFPT.code +"</td>" +
				"<td></td>" +
				"<td><div class='input-control select'>" +
				"<select id='select-teacher-'" +classCourseSemesters[position].classCourseSemesterId +">" +
				"<option value='-1'>...</option>" +
				"</select></div>" +
				"<div id='warning' class='fg-red'>" +	
				"</div></td></tr>";
		return text;
	}
	
	function _setCellConflict() {
		$("#table-classes tbody tr").each(function () {
			var classCourseSemesterId = $(this).find("td:eq(0)").attr("id");
			for(var i = 0; i < dtaJSON.length; i++) {
				if(dtaJSON[i].classCourseSemester.classCourseSemesterId == classCourseSemesterId) {
					var text = "";
					if(dtaJSON[i].conflictClasses != null) {
						for(var x = 0; x < dtaJSON[i].conflictClasses.length; x++) {
							text += dtaJSON[i].conflictClasses[x].classSemester.classFPT.code +"; ";	
						}
					}
					$(this).find("td:eq(1)").text(text);					
				}
			}
		});
	}
	
	function _setSelectTeachers() {
		$("#table-classes tbody tr").each(function () {
			$(this).find("td:eq(2) select").append(_getListTeacherForSelect());
		})
	}

	function _getListTeacherForSelect() {
		var text = "";
		var teacherCourseSemesters = courseSemesterJSON.teacherCourseSemesters;
		for(var i = 0; i < teacherCourseSemesters.length; i++) {
			text += _getOptionTeacher(i);
		}
		return text;
	}
	
	function _getOptionTeacher(position) {
		return "<option id='" +courseSemesterJSON.teacherCourseSemesters[position].teacherSemester.teacherSemesterId +"'>" +
				courseSemesterJSON.teacherCourseSemesters[position].teacherSemester.teacher.account +"</option>";
	}
	function _urlParam(param) {
		var url = $(location).attr('search').substring(1);
		var parameters = url.split('&');
		for (var i = 0; i < parameters.length; i++) {
			var parameter = parameters[i].split('=');
			if (parameter[0] == param) {
				return parameter[1];
			}
		}
	}
});