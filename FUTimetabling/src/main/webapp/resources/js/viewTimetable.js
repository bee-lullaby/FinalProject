$(document).ready(function() {
	
	
	$("#select-teacher").on("change", function() {
		window.location = "viewTimetable?semesterId=" + urlParam("semesterId") +"&teacherSemesterId=" + $(this).val();
	});
	
	$("#select-class").on("change", function() {
		window.location = "viewTimetable?semesterId=" + urlParam("semesterId") +"&classSemesterId=" + $(this).val();
	});
	
	$("#select-student").on("change", function() {
		window.location = "viewTimetable?semesterId=" + urlParam("semesterId") +"&studentId=" + $(this).val();
	});
	
	$("#select-semester").on("click", "a", function() {
		var url = "viewTimetable?semesterId=" + $(this).attr("id");
		
		if(urlParam("classSemesterId") != undefined) {
			url += "&classSemesterId=0"; 
		} else if (urlParam("teacherSemesterId") != undefined) {
			url += "&teacherSemesterId=0"; 
		} else if (urlParam("studentId") != undefined) {
			url += "&studentId=0"; 
		}
		
		window.location = url;
	});
	
	init();	
	
	function init() {
		$("#select-semester a[id='" +urlParam("semesterId") +"']").addClass("active");
		
		if(urlParam("classSemesterId") != undefined) {
			$("#class-timetable").addClass("active");
			$("#select-class").val(urlParam("classSemesterId")).select2({
				"placeholder" : ""
			});
		} else if (urlParam("teacherSemesterId") != undefined) {
			$("#teacher-timetable").addClass("active");
			$("#select-teacher").val(urlParam("teacherSemesterId")).select2({
				"placeholder" : ""
			});
		} else if (urlParam("studentId") != undefined) {
			$("#student-timetable").addClass("active");
			$("#select-student").val(urlParam("studentId")).select2({
				"placeholder" : ""
			});
		}
		
		$("#class-timetable").attr("href", "viewTimetable?semesterId=" + urlParam("semesterId") +"&classSemesterId=0");
		$("#teacher-timetable").attr("href", "viewTimetable?semesterId=" + urlParam("semesterId") +"&teacherSemesterId=0");
		$("#student-timetable").attr("href", "viewTimetable?semesterId=" + urlParam("semesterId") +"&studentId=0");
		
	}
	
	function urlParam(param) {
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
