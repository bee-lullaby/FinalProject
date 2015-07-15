$(document).ready(function(){	
	
	var startDate = $("#timetable-container").find("#startDate").val();
	var endDate = $("#timetable-container").find("#endDate").val();
	
	var dayName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	
	var JSONdataSchedule = $("#JSONdata").text();
	var JSONToSubmit = $("#JSONToSubmit");
	
	var JSONdata = JSON.parse(JSONdataSchedule);
	
	_init ();
	
	$("div[id^='course-']").on("click", function() {
		var course;
		if (this.id.indexOf('1') > -1) {
			course = 1;
		} else if (this.id.indexOf('2') > -1) {
			course = 2;
		} else if (this.id.indexOf('3') > -1) {
			course = 3;
		} else if (this.id.indexOf('4') > -1) {
			course = 4;
		} else if (this.id.indexOf('5') > -1) {
			course = 5;
		}

		$("#dialog-info-course-" + course).find("#numberOfTeacher").text($("#dialog-info-course-" + course).find("option").size());

		_showDialog("dialog-info-course-" + course);
	});
	
	$("#timetable-body tr td").on("click", function() {
		var columnNo = $(this).index() + 1;
		var slot = $(this).parent().get(0).rowIndex;
		if($(this).is("[class^='color-']")) {
			var courseSet = $(this).attr("class");
			var courseSelected = $("span[class*='" +courseSet +"']").parent().closest('div').text().trim();
			$("#set-courses option:selected").removeAttr("selected");
			$("#set-courses option:contains('" +courseSelected +"')").attr("selected", "selected");
		}
		
		var position = $(this).data("position");
		var JSONobj = JSONdata[position];
		var d = new Date(JSONobj.date);
		
		$("#dialog-schedule #slot-day").text(dayName[d.getDay()] +" (" +d.getDate() +"/" +(d.getMonth() + 1) +") - Slot " +JSONobj.slot);
		$("#dialog-schedule").data("td", $(this));
		$("#dialog-schedule").data("position", position);
		$("#dialog-schedule").data("prev-course-selected", $("#set-courses option:selected").text());
		

		$("#warning-set-course").hide();
		_setCourseInfoDialog(JSONdata[position].dataSchedule); 
		_showDialog("dialog-schedule");
	});
	

	$("#select-weeks").on("change", function(){
		this.form.submit();	
	});
	
	$("#select-classes").on("change", function(){
		this.form.submit();	
	});
	
	$("#btn-next-week").on("click", function() {
		var nextOption = $("#select-weeks option:selected").next("option");
		if (nextOption.length > 0) {
			$("#select-weeks option:selected").removeAttr("selected");
			nextOption.attr("selected", "selected");
		}
		this.form.submit();
	});
	
	$("#btn-prev-week").on("click", function() {
		var prevOption = $("#select-weeks option:selected").prev("option");
		if (prevOption.length > 0) {
			$("#select-weeks option:selected").removeAttr("selected");
			prevOption.attr("selected", "selected");
		}
		this.form.submit();
	});
	
	$("#btn-next-class").on("click", function() {
		var nextOption = $("#select-classes option:selected").next("option");
		if (nextOption.length > 0) {
			$("#select-classes option:selected").removeAttr("selected");
			nextOption.attr("selected", "selected");
		}
		_setAction(this, 0);
		this.form.submit();	
	});
	
	$("#btn-prev-class").on("click", function() {
		var prevOption = $("#select-classes option:selected").prev("option");
		if (prevOption.length > 0) {
			$("#select-classes option:selected").removeAttr("selected");
			prevOption.attr("selected", "selected");
		}
		$(this).form.submit();	
	});
	
	$("#set-courses").on("change", function() {
		_setCourseInfoDialog(JSONdata[$("#dialog-schedule").data("position")].dataSchedule);
	});
	
	$("#btn-set-course").on("click", function(){
		
		var courseSelectedVal = $("#set-courses option:selected").val();
		var courseSelectedText = $("#set-courses option:selected").text();
		
		var td = $("#dialog-schedule").data("td");
		var position = $("#dialog-schedule").data("position");
		var prevCourse = $("#dialog-schedule").data("prev-course-selected");
		
		if(courseSelectedVal != -1 && JSONdata[position].dataSchedule[courseSelectedText].classInSlot  
				== JSONdata[position].dataSchedule[courseSelectedText].numOfTeachers) {
			$("#warning-set-course").show();
		} else {	
			if(td.is("[class^='color-']")) {
				td.removeClass(td.attr("class"));
				JSONdata[position].dataSchedule[prevCourse].classInSlot -= 1;
			}
			if(courseSelectedVal != -1) {
				console.log(courseSelectedText);
				var color = $("span[id='" +courseSelectedVal +"'] ").attr("class").split(" ")[1];
				td.addClass(color);
				JSONdata[position].dataSchedule[courseSelectedText].classInSlot += 1;
			}
			JSONdata[position].setCourseSlot = courseSelectedVal;
			$("#dialog-schedule").removeData("prev-course-selected");
			_clearScheduleDialog();
		}
	});
	
	$("#btn-submit").on("click", function(){
		JSONToSubmit.attr("value", JSON.stringify(JSONdata));
		$("#data").attr("action", "schedule/updateTimetable");
		$("#data").submit();
	});
	
	$("#btn-cancel-set-course").on("click", function() {
		_clearScheduleDialog();
	});
	
	$("#btn-clear").on("click", function(){
		_showDialog("dialog-warning-clear");
	});
	
	$("#btn-generate").on("click", function() {
		$("#generate #semesterId").attr("value", _urlParam("semesterId"));
		$("#generate #classId").attr("value", _urlParam("classId"));
		$("#generate #week").attr("value", _urlParam("week"));
		$("#generate").attr("action", "schedule/generateFromPreviousWeek");
		$("#generate").submit();
	});
	
	$("#dialog-warning-clear #btn-accept-clear").on("click", function() {
		_clearData();
		JSONToSubmit.attr("value", JSON.stringify(JSONdata));
		$("#data").attr("action", "schedule/updateTimetable");
		$("#data").submit();
	});
	
	$("#dialog-warning-clear #btn-decline-clear").on("click", function() {
		_showDialog("dialog-warning-clear");
	});
	
	function _init () {
		_setDateTimetable();
		_setDateHeader();
		_setTimetable();
	}
//	
//	function _setAction(child, generate) {
//		var semesterId = $("#form-class-week #semesterId").val();
//		var classId = $("#form-class-week #select-classes option:selected").val();
//		var week = $("#form-class-week #select-weeks option:selected").val();
//		var action = "schedule?semesterId=" +semesterId +"&classId=" +classId +"&week=" +week;
//		if(generate === 1)
//		var form = child.closest("form");
//		form.attr("action", action);
//	}
//	
	function _showDialog(id) {
		var dialog = $("#" + id).data('dialog');
		if (!dialog.element.data('opened')) {
			dialog.open();
		} else {
			dialog.close();
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
	
	function _setDateTimetable() {
		$("#JSONprev").attr("value", JSON.stringify(JSONdata));
		var start = new Date(startDate);
		var end = new Date(endDate);
		start.setDate(start.getDate() - 1);
		
		var date = new Date(startDate);
		date.setDate(date.getDate() - 1);
		var count = 0;
		while (date.getTime() < end.getTime()) {
			var day = date.getDate();
			var month = date.getMonth() + 1;
			var textDay =  day +"/" +month +"  -  ";
			
			date.setDate(date.getDate() + 6);
			day = date.getDate();
			month = date.getMonth() + 1;
			textDay += day +"/" +month;
			
			$('#select-weeks').append($("<option></option>")
					.attr("value", ++count)
					.text(textDay));
			date.setDate(date.getDate() + 1);
		}
		
		var week = _urlParam("week");
		$('#select-weeks option:nth-child(' + week +')').attr("selected", "selected");
	}
	
	function _setDateHeader() {
		var start = new Date(startDate);
		start.setDate(start.getDate() - 1);
		var week = _urlParam("week");
		
		start.setDate(start.getDate() + 7*(week - 1));
		var $counter= 0;
		$("#timetable #header th").each(function() {
			var day = start.getDate();
			var month = start.getMonth() + 1;
			$(this).text(dayName[$counter] + " (" +day +"/" +month +")");
			start.setDate(start.getDate() + 1);
			$counter += 1;
		});
		
	}
	
	function _setCourseInfoDialog(dataSchedule) {
		if($("#set-courses option:selected").val() != -1) {
			var courseSelected = $("#set-courses option:selected").text();
			$("#course-info-to-set #course-code").text(courseSelected);
			var data = dataSchedule[courseSelected];
			$("#course-info-to-set #classes").text(data.numOfClasses);
			$("#course-info-to-set #slot").text(data.classInSlot);
			$("#course-info-to-set #teachers").text(data.numOfTeachers);
		} else {
			$("#course-info-to-set #course-code").text("N/A");
			$("#course-info-to-set #remains_slot").text("N/A");
			$("#course-info-to-set #classes").text("N/A");
			$("#course-info-to-set #classes-code").text("N/A");
			$("#course-info-to-set #slot").text("N/A");
			$("#course-info-to-set #teachers").text("N/A");
		}
	};
	
	function _setTimetable() {
		var obj = JSON.parse(JSONdataSchedule);
		for(var i = 0; i < obj.length; i++) {
			if(JSONdata[i].setCourseSlot !== -1) {
				var color = $("#" +JSONdata[i].setCourseSlot).attr("class").split(' ')[1];
				$('#slot-' +JSONdata[i].slot).append($("<td></td>")
						.addClass(color)
						.data("position", i));
			} 
			else {
				$('#slot-' +JSONdata[i].slot).append($("<td></td>")
						.data("position", i));
			}
				
		}
	}
	
	function _clearData() {
		for(var i = 0; i < JSONdata.length; i++) {
			if(JSONdata[i].setCourseSlot != -1) {
				var courseSelectedName = $("span[id='"+JSONdata[i].setCourseSlot +"']").parent().closest('div').text().trim();
				JSONdata[i].dataSchedule[courseSelectedName].classInSlot -= 1;

			}
			JSONdata[i].setCourseSlot = -1;
		}
	}
	
	function _clearScheduleDialog() {
		$("#set-courses option:first").attr("selected", "selected");
		$("#warning-set-course").hide();
		_showDialog("dialog-schedule");
	}
});
