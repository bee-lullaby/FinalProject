$(document).ready(function() { 
	
	var courseSemesterData = $("#courseSemesterData").text();
	var courseSemesterJSON = null;
	
	var dtaData = $("#dtaData").text();
	var dtaJSON = null;
	
	var mMergeClassData = $("#mMergeClassData").text();
	var mMergeClassJSON = null;
	if(mMergeClassData != undefined && mMergeClassData != null && mMergeClassData !=  "") {
		mMergeClassJSON = JSON.parse(mMergeClassData);
	}
	
	if(courseSemesterData != undefined && courseSemesterData != null && courseSemesterData != ""
			&& dtaData != undefined && dtaData != null && dtaData != "") { 
		courseSemesterJSON = JSON.parse(courseSemesterData);
		dtaJSON = JSON.parse(dtaData);
	}

	init();
	
	$("select[id^='select-teacher-']").on("change", function() {
		var tr = $(this).closest("tr");
		$(tr).find("#warning").text("");
		
		var teacherSelected = $(this).find("option:selected").val();
		
		// get list merge class
		var listMergeClasses = [];
		var classCourseSemesterId = $(tr).attr("id");
		if(mMergeClassJSON != undefined && mMergeClassJSON != null) {
			$.each(mMergeClassJSON, function(key, value) {
				if(key.indexOf(urlParam("courseSemesterId")) > -1 
						&& $.inArray(parseInt(classCourseSemesterId) , value) > -1) {
					for(var i = 0; i < value.length; i++) {
						if(value[i] != classCourseSemesterId) {
							listMergeClasses.push(value[i]);
						}
					}
				}
			});
		}
		
		// get list conflict class
		var listConflictClasses = [];
		var textNameClass = $(tr).find("td:eq(0)").text();
		$("#table-classes tr").each(function() {
			var textConflictClasses = $(this).find("td:eq(1)").text();
			if(textConflictClasses.indexOf(textNameClass) > -1) {
				listConflictClasses.push(parseInt($(this).attr("id")));
				
			}
		});
		
		var thisClass = $(tr).find("td:eq(0)").text().trim();
		var check = true;
		$("#table-classes tr").each(function() {
			if($(this).find("td:eq(1)").text().indexOf(thisClass) > -1) {
				if($(this).find("td:eq(3) select option:selected").val() != -1 
						&& $(this).find("td:eq(3) select option:selected").val() == teacherSelected) {
					$(tr).find("#warning").text("Teacher was arranged to class " +$(this).find("td:eq(0)").text()
							+" ! Cant be arranged to  class " +thisClass +"!");
					$(tr).find("select option:first").attr("selected", "selected");
					check = false;
				}
			}
		});
		
		if(check) {
			for(var i = 0; i < listMergeClasses.length; i++) {
				if(listMergeClasses[i] != classCourseSemesterId) {
					$("#table-classes tr[id='" +listMergeClasses[i] +"']").find("td:eq(3) select option[value='" +teacherSelected +"']").attr("selected", "selected");
				}
			}
		}
	});
	
	
	$("#btn-submit").on("click", function() {
		var result=[];
		if(courseSemesterJSON != undefined && courseSemesterJSON != null) {
			$("#table-classes tr").each(function() {
				var classCourseSemesterId = $(this).attr("id");
				for(var i = 0; i < courseSemesterJSON.classCourseSemesters.length; i++) {
					if(courseSemesterJSON.classCourseSemesters[i].classCourseSemesterId == classCourseSemesterId) {
						if(courseSemesterJSON.classCourseSemesters[i].timetable != undefined 
								&& courseSemesterJSON.classCourseSemesters[i].timetable != null 
								&& courseSemesterJSON.classCourseSemesters[i].timetable.length > 0) {
							if($(this).find("td:eq(2) select option:selected").val() != "-1") {
								var teacherSemester = getTeacherSemester($(this).find("td:eq(3) select option:selected").val());
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
		}
	}); 
	
	function getTeacherSemester(teacherSemesterId) {
		if(courseSemesterJSON != undefined && courseSemesterJSON != null) {
			for(var i = 0; i < courseSemesterJSON.teacherCourseSemesters.length; i++) {
				if(courseSemesterJSON.teacherCourseSemesters[i].teacherSemester.teacherSemesterId == teacherSemesterId) {
					return courseSemesterJSON.teacherCourseSemesters[i].teacherSemester;
				}
			}
		}
		return null;
	}
	
	function init() {
		
		setSelectedDepartments();
		
		setListCourses();
		
		setTextBtnCourse();
		
		setDataTableClasses();
		
		setCellConflict();
		
		setCellMerge();
		
		setSelectTeachers();
		
		$("#select-semesters a[id='" +urlParam("semesterId") +"']").addClass("active");
		$("#select-courses a[id='" +urlParam("courseSemesterId") +"']").addClass("active");
	}
	
	
	
	function setSelectedDepartments() {
		$("#select-departments a").each(function() {
			$(this).attr("href", "?semesterId=" + urlParam("semesterId") +"&departmentId=" +$(this).attr("id"));
		});
		
		$("#select-departments a[id='" +urlParam("departmentId") +"']").addClass("active");
	}
	
	
	
	function setTextBtnCourse() {
		if(courseSemesterJSON != null) {
			$("#num-of-classes").text(courseSemesterJSON.classCourseSemesters.length);
			$("#num-of-teachers").text(courseSemesterJSON.teacherCourseSemesters.length);
		} else {
			$("#num-of-classes").text("0/0");
			$("#num-of-teachers").text("0");
		}
	}
	
	function setListCourses() {
		$.each($("#select-courses a"), function() {
			$(this).attr("href", "?semesterId=" +urlParam("semesterId") +"&departmentId=" +urlParam("departmentId")
					+"&courseSemesterId=" +$(this).attr("id"));
		});
		$("#select-courses a[id='" +urlParam("courseSemesterId") +"']").addClass("active");
		$("#title-course").text($("#select-courses a[id='" +urlParam("courseSemesterId") +"']").text());
	}
	
	function setDataTableClasses() {
		console.log("!23");
		if(dtaJSON != undefined && dtaJSON != null && dtaJSON.length != 0) {
			for(var i = 0; i < dtaJSON.length; i++) {
				$("#table-classes tbody").append(getTRTableClasses(i));
			}
		} else {
			$("#table-classes tbody").append("<tr><td colspan='4' style='text-align:center'>No Class To Show!</td></tr>");
		}
	}
	
	function getTRTableClasses(position) {
		text = "<tr id='" +dtaJSON[position].classCourseSemester.classCourseSemesterId +"' data-position='" +position +"'>" +
				"<td>" 
				+dtaJSON[position].classCourseSemester.classSemester.classFPT.code +"</td>" +
				"<td id='conflict'></td><td id='merge-class'></td>" +
				"<td style='width:300px;'><div class='input-control select' style='width:100%;'>" +
				"<select id='select-teacher-" +dtaJSON[position].classCourseSemester.classCourseSemesterId +"'>" +
				"<option value='-1'>...</option>" +
				"</select></div>" +
				"<div id='warning' class='fg-red'>" +	
				"</div></td></tr>";
		return text;
	}
	
	function setCellConflict() {
		$("#table-classes tbody tr").each(function () {
			var listMergeClasses = [];
			var classCourseSemesterId = $(this).attr("id");
			if(mMergeClassJSON != undefined && mMergeClassJSON != null) {
				$.each(mMergeClassJSON, function(key, value) {
					if(key.indexOf(urlParam("courseSemesterId")) > -1 
							&& $.inArray(parseInt(classCourseSemesterId) , value) > -1) {
						for(var i = 0; i < value.length; i++) {
							if(value[i] != classCourseSemesterId) {
								listMergeClasses.push(value[i]);
							}
						}
					}
				});
			} 
			

			if(dtaJSON != undefined && dtaJSON != null) {
				for(var i = 0; i < dtaJSON.length; i++) {
					if(dtaJSON[i].classCourseSemester.classCourseSemesterId == classCourseSemesterId) {
						var text = "";
						if(dtaJSON[i].conflictClasses != null) {
							for(var x = 0; x < dtaJSON[i].conflictClasses.length; x++) {
								if($.inArray(parseInt(dtaJSON[i].conflictClasses[x].classCourseSemesterId), listMergeClasses) < 0) {
									text += dtaJSON[i].conflictClasses[x].classSemester.classFPT.code +"; ";	
								}
							}
						}
						$(this).find("td:eq(1)").text(text);					
					}
				}
			}
		});
	}
	
	function setCellMerge() {
		$("#table-classes tbody tr").each(function () {
			var listMergeClasses = [];
			var classCourseSemesterId = $(this).attr("id");
			if(mMergeClassJSON != undefined && mMergeClassJSON != null) {
				$.each(mMergeClassJSON, function(key, value) {
					if(key.indexOf(urlParam("courseSemesterId")) > -1 
							&& $.inArray(parseInt(classCourseSemesterId) , value) > -1) {
						for(var i = 0; i < value.length; i++) {
							if(value[i] != classCourseSemesterId) {
								listMergeClasses.push($("#table-classes").find("tr[id='" +value[i] +"']").find("td:eq(0)").text());
							}
						}
					}
				});
			} 
			var text = "";
			for(var i = 0; i < listMergeClasses.length; i++) {
				text += listMergeClasses[i] +"; "
			}
			$(this).find("td:eq(2)").text(text);	
		});
	}
	
	function setCellMergeClass() {
		$("#table-classes tbody tr").each(function () {
			var classCourseSemesterId = $(this).attr("id");
			if(dtaJSON != undefined && dtaJSON != null) {
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
			}
		});
	}
	
	function setSelectTeachers() {
		var count = 0;
		$("#table-classes tbody tr").each(function () {
			$(this).find("td:eq(3) select").append(getListTeacherForSelect(count++));
		})

		$.each($("#table-classes tbody tr"), function(id, tr) {
			var position = $(tr).attr("data-position");
			if(dtaJSON != undefined && dtaJSON != null && dtaJSON.length != 0 &&
					courseSemesterJSON != null && courseSemesterJSON.classCourseSemesters[position].timetable.length > 0) {
				if(courseSemesterJSON.classCourseSemesters[position].timetable[0].teacherSemester != undefined 
					&& courseSemesterJSON.classCourseSemesters[position].timetable[0].teacherSemester != null) {
					$(tr).find("td:eq(3) select option[value='" +courseSemesterJSON.classCourseSemesters[position].timetable[0].teacherSemester.teacherSemesterId +"']").attr("selected", "selected");

				}
			}
			
		});
	}

	function getListTeacherForSelect(position) {
		var text = "";

		if(dtaJSON != undefined && dtaJSON != null && dtaJSON.length != 0) {
			for(var i = 0; i < dtaJSON[position].teacherAvailable.length; i++) {
				text += getOptionTeacher(position, i);
			}
		}
		return text;
	}
	
	function getOptionTeacher(x, y) {
		return "<option value='" +dtaJSON[x].teacherAvailable[y].teacherSemesterId +"'>" +
					dtaJSON[x].teacherAvailable[y].teacher.account +"</option>";
	}
	function urlParam(param) {
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