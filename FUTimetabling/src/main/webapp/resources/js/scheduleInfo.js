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
		if (index == 0) {
			$("#btn-prev").attr("disabled", true);
			$("#btn-next").attr("disabled", false);
			$("#div-create-timetable").show();
			$("#div-room-arrangement").hide();
			$("#div-teacher-arrangement").hide();
			if($("#setCourse").text() == "N/A" || $("#setCourse").text() == 0) {
				$("#btn-manual").attr("disabled", true);
				$("#btn-automatic").attr("disabled", true);
				$.Notify({type: 'alert', caption: 'Alert', content: "Can't use schedule method because you dont have enough data!!!"});
			} else {
				$("#btn-manual").attr("disabled", false);
				$("#btn-automatic").attr("disabled", false);
			}
		} else if (index == 1) {
			$("#btn-prev").attr("disabled", false);
			$("#btn-next").attr("disabled", false);
			$("#div-create-timetable").hide();
			$("#div-room-arrangement").show();
			$("#div-teacher-arrangement").hide();
			if($("#setTimetable").text() == "N/A" || $("#setTimetable").text() == 0) {
				$("#btn-manual").attr("disabled", true);
				$.Notify({type: 'alert', caption: 'Alert', content: "Can't use room arrangement method because you dont have enough data!!!"});
			} else {
				$("#btn-manual").attr("disabled", false);
			}
		} else if (index == 2) {
			$("#btn-prev").attr("disabled", false);
			$("#btn-next").attr("disabled", true);
			$("#div-create-timetable").hide();
			$("#div-room-arrangement").hide();
			$("#div-teacher-arrangement").show();
			if($("#setTimetable").text() == "N/A" || $("#setTimetable").text() == 0) {
				$("#btn-manual").attr("disabled", true);
				$.Notify({type: 'alert', caption: 'Alert', content: "Can't use teacher arrangement method because you dont have enough data!!!"});
			} else {
				$("#btn-manual").attr("disabled", false);
			}
		}

		$("#stepper").stepper('stepTo', index + 1);
	}
	
	$(document).ready(function() {
		
		_init();
		
		$("#btn-back").on("click", function() {
			window.location = "/FUTimetabling/staff";
		});
		
		$("#btn-manual").on("click", function() {
			var semester = $("#select-semester option:selected").val()
			
			if(!$("#div-create-timetable").is(":hidden")) {
				window.location = "/FUTimetabling/staff/schedule?semesterId=" +semester;
			} else if(!$("#div-room-arrangement").is(":hidden")) {
				window.location = "/FUTimetabling/staff/roomArrangement?semesterId=" +semester;
			} else if(!$("#div-teacher-arrangement").is(":hidden")) {
				window.location = "/FUTimetabling/staff/teacherArrangement?semesterId=" +semester;
			}
		});
		
		$("#select-semester").on("change", function() {
			window.location = "scheduleInfo?semesterId=" +$(this).find("option:selected").val();
		});
		
		$("#btn-automatic").on("click", function() {
			window.location = "/FUTimetabling/staff/automaticTimetabling?semesterId=" +_urlParam("semesterId");
		})
		
		function _init() {
			$("#select-semester").find("option[value='" +_urlParam("semesterId") +"']").attr("selected", "selected");
			if($("#setCourse").text() == "N/A" || $("#setCourse").text() == 0) {
				$("#btn-manual").attr("disabled", true);
				$("#btn-automatic").attr("disabled", true);
				$.Notify({type: 'alert', caption: 'Alert', content: "Can't use schedule method because you dont have enough data!!!"});
			} else {
				$("#btn-manual").attr("disabled", false);
				$("#btn-automatic").attr("disabled", false);
			}
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