$(document).ready(function() {
	
	
	$("#select-student").select2({
		"placeholder" : ""
	});
	
	$("#select-teacher").on("change", function() {
		window.location = "viewTimetable?semesterId=" + _urlParam("semesterId") +"&teacherSemesterId=" + $(this).val();
	});
	
	_init();	
	
	function _init() {
		$("#select-semester a[id='" +_urlParam("semesterId") +"']").addClass("active");
		
		if(_urlParam("classSemesterId") != undefined) {
			$("#class-timetable").addClass("active");
			$("#select-class").val(_urlParam("classSemesterId")).select2({
				"placeholder" : ""
			});
		} else if (_urlParam("teacherSemesterId") != undefined) {
			$("#teacher-timetable").addClass("active");
			$("#select-teacher").val(_urlParam("teacherSemesterId")).select2({
				"placeholder" : ""
			});
		} else if (_urlParam("studentId") != undefined) {
			$("#student-timetable").addClass("active");
			$("#select-student").val(_urlParam("studentId")).select2({
				"placeholder" : ""
			});
		}
		
		$("#class-timetable").attr("href", "viewTimetable?semesterId=" + _urlParam("semesterId") +"&classSemesterId=0");
		$("#teacher-timetable").attr("href", "viewTimetable?semesterId=" + _urlParam("semesterId") +"&teacherSemesterId=0");
		$("#student-timetable").attr("href", "viewTimetable?semesterId=" + _urlParam("semesterId") +"&studentId=0");
		
	}
	
	function _urlParam(param) {
	    var url = $(location).attr('search').substring(1);
	    var parameters = url.split('&');
	    for (var i = 0; i < parameters.length; i++) 
	    {
	        var parameter = parameters[i].split('=');
	        if (parameter[0] == param) 
	        {
	            return parameter[1];
	        }
	    }
	}
});
