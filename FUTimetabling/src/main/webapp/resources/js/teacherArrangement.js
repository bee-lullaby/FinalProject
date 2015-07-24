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
		var result=[];
		$("#table-classes tr").each(function() {
			var classCourseSemesterId = $(this).attr("id");
			for(var i = 0; i < courseSemesterJSON.classCourseSemesters.length; i++) {
				if(courseSemesterJSON.classCourseSemesters[i].classCourseSemesterId == classCourseSemesterId) {
					if(courseSemesterJSON.classCourseSemesters[i].timetable != undefined 
							&& courseSemesterJSON.classCourseSemesters[i].timetable != null 
							&& courseSemesterJSON.classCourseSemesters[i].timetable.length > 0) {
						if($(this).find("td:eq(2) select option:selected").val() != "-1") {
							var teacherSemester = _getTeacherSemester($(this).find("td:eq(2) select option:selected").val());
							for(var x = 0; x < courseSemesterJSON.classCourseSemesters[i].timetable.length; x++) {
								courseSemesterJSON.classCourseSemesters[i].timetable[x].teacherSemester = teacherSemester;
								result.push(courseSemesterJSON.classCourseSemesters[i].timetable[x]);
							}
						} else {
							for(var x = 0; x < courseSemesterJSON.classCourseSemesters[i].timetable.length; x++) {
								if(courseSemesterJSON.classCourseSemesters[i].timetable[x].teacherSemester != null) {
									courseSemesterJSON.classCourseSemesters[i].timetable[x].teacherSemester = null;
									result.push(courseSemesterJSON.classCourseSemesters[i].timetable[x]);
								}
							}
						}
					}
				}
			}
		});
		$("#dataToSet").attr("value", JSON.stringify(result));
		$("#setTeacher").attr("action", "teacherArrangement/updateTimetable");
		$("#setTeacher").submit();
	}); 
	
	function _getTeacherSemester(teacherSemesterId) {
		for(var i = 0; i < courseSemesterJSON.teacherCourseSemesters.length; i++) {
			if(courseSemesterJSON.teacherCourseSemesters[i].teacherSemester.teacherSemesterId == teacherSemesterId) {
				return courseSemesterJSON.teacherCourseSemesters[i].teacherSemester;
			}
		}
		return 
	}
	
	function _init() {
		
		$("#select-semesters a[id='" +_urlParam("semesterId") +"']").addClass("active");
		
		_setSelectDepartments();
		
		_setListCourses();
		
		_setTextBtnCourse();
		
		_setDataTableClasses();
		
		_setCellConflict();
		
		_setSelectTeachers();
	}
	
	function _setSelectDepartments() {
		for(var i = 0; i < departmentsJSON.length; i++) {
			$("#select-departments").append(_getOptionSelectDepartments(i));
		}
		$("#select-departments a[id='" +_urlParam("departmentId") +"']").addClass("active");
	}
	
	function _getOptionSelectDepartments(position) {
		return "<a id='" +departmentsJSON[position].departmentId +"'" +
				" href='?semesterId=" + _urlParam("semesterId") +"&departmentId=" +departmentsJSON[position].departmentId +"'		>" +
				departmentsJSON[position].name +"</a>";
	} 
	
	
	function _setTextBtnCourse() {
		$("#btn-course").text(courseSemesterJSON.course.code);
		$("#num-of-classes").text(courseSemesterJSON.classCourseSemesters.length);
		$("#num-of-teachers").text(courseSemesterJSON.teacherCourseSemesters.length);
	}
	
	function _setListCourses() {
		for(var i = 0; i < coursesJSON.length; i++) {
			$("#select-courses").append("<a id='" +coursesJSON[i].courseId +"' " +
					"href='?semesterId=" +_urlParam("semesterId") +"&departmentId="+_urlParam("departmentId") +"" +
					"&courseId=" +coursesJSON[i].courseId +"'>" 
					+coursesJSON[i].code +"</button>");
		} 
		$("#select-courses a[id='" +_urlParam("courseId") +"']").addClass("active");
		
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
		return "<option value='" +courseSemesterJSON.teacherCourseSemesters[position].teacherSemester.teacherSemesterId +"'>" +
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