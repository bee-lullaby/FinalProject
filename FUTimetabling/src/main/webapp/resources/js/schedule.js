$(document).ready(function(){	
	
	var startDate = $("#timetable-container").find("#startDate").val();
	var endDate = $("#timetable-container").find("#endDate").val();
	
	var dayName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	
	var JSONdataSchedule = $("#JSONdata").text();
	var JSONToSubmit = $("#JSONToSubmit");
	
	var JSONdata = JSON.parse(JSONdataSchedule);
	
	_setDateTimetable();
	
	_setDateHeader();
	
	_setTimetable();
	
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
		
		
		_setCourseInfoDialog(JSONdata[position].dataSchedule); 
		_showDialog("dialog-schedule");
	});
	

	$("#select-weeks").on("change", function(){
		_setDateHeader();
	});
	
	$("#btn-next-week").on("click", function() {
		var nextOption = $("#select-weeks option:selected").next("option");
		if (nextOption.length > 0) {
			$("#select-weeks option:selected").removeAttr("selected");
			nextOption.attr("selected", "selected");
		}
		_setDateHeader();
	});
	
	$("#btn-prev-week").on("click", function() {
		var prevOption = $("#select-weeks option:selected").prev("option");
		if (prevOption.length > 0) {
			$("#select-weeks option:selected").removeAttr("selected");
			prevOption.attr("selected", "selected");
		}
		_setDateHeader();
	});
	
	$("#btn-next-class").on("click", function() {
		var nextOption = $("#select-classes option:selected").next("option");
		if (nextOption.length > 0) {
			$("#select-classes option:selected").removeAttr("selected");
			nextOption.attr("selected", "selected");
		}
		$(this).form.submit();	
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
		_showDialog("dialog-schedule");
		
		var courseSelected = $("#set-courses option:selected").val();
		var color = $("span[id='" +courseSelected +"'] ").attr("class").split(" ")[1];
		var td = $("#dialog-schedule").data("td");
		var position = $("#dialog-schedule").data("position");
		var prevCourse = $("#dialog-schedule").data("prev-course-selected");
		if(td.is("[class^='color-']")) {
			td.removeClass(td.attr("class"));
			JSONdata[position].dataSchedule[prevCourse].classInSlot -= 1;
		}
		
		td.addClass(color);

		var courseSelectedName = $("#set-courses option:selected").text();
		JSONdata[position].setCourseSlot = courseSelected;
		JSONdata[position].dataSchedule[courseSelectedName].classInSlot += 1;
		
		$("#dialog-schedule").removeData("prev-course-selected");
	});
	
	$("#btn-submit").on("click", function(){
		JSONToSubmit.attr("value", JSON.stringify(JSONdata));
		$(this).form.submit();
	});
	
	function _showDialog(id) {
		var dialog = $("#" + id).data('dialog');
		if (!dialog.element.data('opened')) {
			dialog.open();
		} else {
			dialog.close();
		}
	}
	
	
	function _setDateTimetable() {
		$("#JSONprev").attr("value", JSON.stringify(JSONdata));
		console.log($("#JSONprev").val());
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
					.attr("value", "week-" + ++count)
					.text(textDay));
			date.setDate(date.getDate() + 1);
		}
	}
	
	function _setDateHeader() {
		var start = new Date(startDate);
		start.setDate(start.getDate() - 1);
		var week = $("#select-weeks option:selected").index();
		
		start.setDate(start.getDate() + 7*(week));
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
		var courseSelected = $("#set-courses option:selected").text();
		$("#course-info-to-set #course-code").text(courseSelected);
		var data = dataSchedule[courseSelected];
		$("#course-info-to-set #classes").text(data.numOfClasses);
		$("#course-info-to-set #slot").text(data.classInSlot);
		$("#course-info-to-set #teachers").text(data.numOfTeachers);
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
});
