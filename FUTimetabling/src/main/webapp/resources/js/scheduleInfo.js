	function _stepper_on_step(index, step) {
		if (index == 1) {
			$("#btn-prev").attr("disabled", true);
			$("#btn-next").attr("disabled", false);
			$("#div-create-timetable").show();
			$("#div-room-arrangement").hide();
			$("#div-teacher-arrangement").hide();
		} else if (index == 2) {
			$("#btn-prev").attr("disabled", false);
			$("#btn-next").attr("disabled", false);
			$("#div-create-timetable").hide();
			$("#div-room-arrangement").show();
			$("#div-teacher-arrangement").hide();
		} else if (index == 3) {
			$("#btn-prev").attr("disabled", false);
			$("#btn-next").attr("disabled", true);
			$("#div-create-timetable").hide();
			$("#div-room-arrangement").hide();
			$("#div-teacher-arrangement").show();
		}
	}

	function _stepper_on_step_click(index, step) {
		if (index == 1) {
			$("#btn-prev").attr("disabled", true);
			$("#btn-next").attr("disabled", false);
			$("#div-create-timetable").show();
			$("#div-room-arrangement").hide();
			$("#div-teacher-arrangement").hide();
		} else if (index == 2) {
			$("#btn-prev").attr("disabled", false);
			$("#btn-next").attr("disabled", false);
			$("#div-create-timetable").hide();
			$("#div-room-arrangement").show();
			$("#div-teacher-arrangement").hide();
		} else if (index == 3) {
			$("#btn-prev").attr("disabled", false);
			$("#btn-next").attr("disabled", true);
			$("#div-create-timetable").hide();
			$("#div-room-arrangement").hide();
			$("#div-teacher-arrangement").show();
		}

		$("#stepper").stepper('stepTo', index + 1);
	}
	
	$(document).ready(function() {
		
		_init();
		
		$("#btn-back").on("click", function() {
			window.location = "/Timetabling/staff";
		});
		
		$("#btn-manual").on("click", function() {
			var semester = $("#select-semester option:selected").val()
			
			if(!$("#div-create-timetable").is(":hidden")) {
				window.location = "/Timetabling/staff/schedule?semesterId=" +semester;
			} else if(!$("#div-room-arrangement").is(":hidden")) {
				window.location = "/Timetabling/staff/roomArrangement?semesterId=" +semester;
			} else if(!$("#div-teacher-arrangement").is(":hidden")) {
				window.location = "/Timetabling/staff/teacherArrangement?semesterId=" +semester;
			}
		});
		
		function _init() {
			$("#select-semester").find("option[value='" +_urlParam("semesterId") +"']").attr("selected", "selected");
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