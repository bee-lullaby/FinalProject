$(document).ready(function(){	
	
	var startDate = $("#timetable-container").find("#startDate").val();
	var endDate = $("#timetable-container").find("#endDate").val();
	
	var dayName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	
	var JSONdataSchedule = $("#JSONdata").text();
	var JSONToSubmit = $("#JSONToSubmit");
	
	var JSONdata = JSON.parse(JSONdataSchedule);
	
	
	var mergeClassData = $("#JSONdataMergeClass").text();
	var JSONmergeClassData = null;
	if(mergeClassData != undefined && mergeClassData != null && mergeClassData != "") { 
		JSONmergeClassData = JSON.parse(mergeClassData);
	}
	
	init ();
	
	$("#timetable-body tr td").on("click", function() {
		var columnNo = $(this).index() + 1;
		var slot = $(this).parent().get(0).rowIndex;
		if($(this).find("div[id='color']").is("[class^='color-']")) {
			var courseSet = $(this).find("div[id='color']").attr("class");
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
		

		$("#warning-set-teacher").hide();
		$("#warning-not-enough-slot").hide();
		$("#warning-set-room").hide();
		$("#warning-conflict-merge-class").hide();
		setCourseInfoDialog(position); 
		showDialog("dialog-schedule");
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
		setAction(this, 0);
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
		setCourseInfoDialog($("#dialog-schedule").data("position"));
	});
	
	$("#btn-set-course").on("click", function(){
		var courseSelectedVal = $("#set-courses option:selected").val();
		var courseSelectedText = $("#set-courses option:selected").text();
		
		var td = $("#dialog-schedule").data("td");
		var position = $("#dialog-schedule").data("position");
		var prevCourse = $("#dialog-schedule").data("prev-course-selected");
		if(courseSelectedText.indexOf(prevCourse) > -1) {
			clearScheduleDialog();
			return;
		}
		if(courseSelectedVal != -1 && JSONdata[position].dataSchedule[courseSelectedText].remainSlots == 0) {
				$("#warning-not-enough-slot").show();
		} else if(courseSelectedVal != -1 && JSONdata[position].dataSchedule[courseSelectedText].learnCourseInSlot  
				>= JSONdata[position].dataSchedule[courseSelectedText].numOfTeachers) {
			$("#warning-set-teacher").show();
		} else if(courseSelectedVal != -1 && JSONdata[position].dataSchedule[courseSelectedText].classesInSlot 
				>= JSONdata[position].dataSchedule[courseSelectedText].totalRooms ) {
			$("#warning-set-room").show();
		} else if (!checkMergeClass(position, courseSelectedVal)) {
			$("#warning-conflict-merge-class").show();
		} else {	
//			if(td.find("div[id='color']").is("[class^='color-']")) {
//				td.find("div[id='color']").removeClass(td.find("div[id='color']").attr("class"));
//				td.find("div[id='text']").text("");
//				JSONdata[position].dataSchedule[prevCourse].remainSlots += 1;
//				JSONdata[position].dataSchedule[prevCourse].learnCourseInSlot -= 1;
//			}
//			if(courseSelectedVal != -1) {
//				var color = $("span[id='" +courseSelectedVal +"'] ").attr("class").split(" ")[1];
//				td.find("div[id='color']").addClass(color);
//				td.find("div[id='text']").text($("span[id='" +courseSelectedVal +"'] ").closest("div").text().trim());
//				JSONdata[position].dataSchedule[courseSelectedText].learnCourseInSlot += 1;
//				JSONdata[position].dataSchedule[courseSelectedText].remainSlots -= 1;
//			}
			JSONdata[position].setCourseSlot = courseSelectedVal;
			JSONToSubmit.attr("value", JSON.stringify(JSONdata));
			$("#data").attr("action", "schedule/updateTimetable");
			$("#data").submit();
//			console.log(courseSelectedText);
//			console.log(JSONdata[position].dataSchedule[courseSelectedText]);
		}
	});
	
	function checkMergeClass(position, courseSelectedVal) {
		var check = true;
		$.each(JSONmergeClassData, function(key, value) {
			if(courseSelectedVal == key) {
				for(var i = 0; i < value.length; i++) {
					var date1 = new Date(value[i].date);
					var date2 = new Date(JSONdata[position].date);
					if(date1.getTime() == date2.getTime() && value[i].slot == JSONdata[position].slot) {
						check = false;
					}
				}
			}
		});
		return check;
	}
	
	$("#btn-submit").on("click", function(){
		JSONToSubmit.attr("value", JSON.stringify(JSONdata));
		$("#data").attr("action", "schedule/updateTimetable");
		$("#data").submit();
	});
	
	$("#btn-cancel-set-course").on("click", function() {
		clearScheduleDialog();
	});
	
	$("#btn-clear").on("click", function(){
		showDialog("dialog-warning-clear");
	});
	
	$("#btn-generate").on("click", function() {
		$("#generate #semesterId").attr("value", urlParam("semesterId"));
		$("#generate #classId").attr("value", urlParam("classId"));
		$("#generate #week").attr("value", urlParam("week"));
		$("#generate").attr("action", "schedule/generateFromPreviousWeek");
		$("#generate").submit();
	});
	
	$("#dialog-warning-clear #btn-accept-clear").on("click", function() {
		clearData();
		JSONToSubmit.attr("value", JSON.stringify(JSONdata));
		$("#data").attr("action", "schedule/updateTimetable");
		$("#data").submit();
	});
	
	$("#dialog-warning-clear #btn-decline-clear").on("click", function() {
		showDialog("dialog-warning-clear");
	});
	
	function init () {
		setDateTimetable();
		setDateHeader();
		setTimetable();
		
		var input = $("<input>").attr("type", "hidden")
						.attr("id", "semesterId")
						.attr("name", "semesterId").val(urlParam("semesterId"));
		$("#data").append($(input));
		
	}
	
	function showDialog(id) {
		var dialog = $("#" + id).data('dialog');
		if (!dialog.element.data('opened')) {
			dialog.open();
		} else {
			dialog.close();
		}
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
	
	function setDateTimetable() {
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
		
		var week = urlParam("week");
		$('#select-weeks option:nth-child(' + week +')').attr("selected", "selected");
	}
	
	function setDateHeader() {
		var start = new Date(startDate);
		start.setDate(start.getDate() - 1);
		var week = urlParam("week");
		
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
	
	function setCourseInfoDialog(position) {
		if($("#set-courses option:selected").val() != -1) {
			var courseSelectedText = $("#set-courses option:selected").text();
			var courseSelectedVal = $("#set-courses option:selected").val();
			$("#course-info-to-set #course-code").text(courseSelectedText);
			var data = JSONdata[position].dataSchedule[courseSelectedText];
			$("#course-info-to-set #remainSlots").text(data.remainSlots);
			$("#course-info-to-set #classes").text(data.numOfClasses);
			$("#course-info-to-set #learnCourseInSlot").text(data.learnCourseInSlot);
			$("#course-info-to-set #totalRooms").text(data.totalRooms);
			$("#course-info-to-set #classesInSlot").text(data.classesInSlot);
			$("#course-info-to-set #teachers").text(data.numOfTeachers);
			
			$("#course-info-to-set #remainSlots").css("color", "");
			$("#course-info-to-set #learnCourseInSlot").css("color", "");
			$("#course-info-to-set #teachers").css("color", "");
			$("#course-info-to-set #classesInSlot").css("color", "");
			$("#course-info-to-set #totalRooms").css("color", "");
			
			if($("#course-info-to-set #remainSlots").text() == 0) {
				$("#course-info-to-set #remainSlots").css("color", "red");
			} 
			if(courseSelectedVal != -1 && parseInt($("#course-info-to-set #learnCourseInSlot").text())
				>= parseInt($("#course-info-to-set #teachers").text())) {
				$("#course-info-to-set #learnCourseInSlot").css("color", "red");
				$("#course-info-to-set #teachers").css("color", "red");
			}
			if(courseSelectedVal != -1 && parseInt($("#course-info-to-set #classesInSlot").text())
					>= parseInt($("#course-info-to-set #totalRooms").text())) {
				$("#course-info-to-set #classesInSlot").css("color", "red");
				$("#course-info-to-set #totalRooms").css("color", "red");
			}
			
		} else {
			$("#course-info-to-set #remainSlots").css("color", "");
			$("#course-info-to-set #learnCourseInSlot").css("color", "");
			$("#course-info-to-set #teachers").css("color", "");
			$("#course-info-to-set #classesInSlot").css("color", "");
			$("#course-info-to-set #totalRooms").css("color", "");
			
			$("#course-info-to-set #course-code").text("N/A");
			$("#course-info-to-set #remainSlots").text("N/A");
			$("#course-info-to-set #classes").text("N/A");
			$("#course-info-to-set #learnCourseInSlot").text("N/A");
			$("#course-info-to-set #totalRooms").text("N/A");
			$("#course-info-to-set #classesInSlot").text("N/A");
			$("#course-info-to-set #teachers").text("N/A");
		}
	};
	
	function setTimetable() {
		var obj = JSON.parse(JSONdataSchedule);
		for(var i = 0; i < obj.length; i++) {
			if(JSONdata[i].setCourseSlot !== -1) {
				var color = $("#" +JSONdata[i].setCourseSlot).attr("class").split(' ')[1];
				$('#slot-' +JSONdata[i].slot).append($("<td></td>")
						//.addClass(color)
						.data("position", i).append($("<div id='color' class='" +color +"'></div>"))
						.append($("<div id='text'>" +$("#" +JSONdata[i].setCourseSlot).closest("div").text().trim() +"</div>")));
			} 
			else {
				$('#slot-' +JSONdata[i].slot).append($("<td></td>")
						.data("position", i).append($("<div id='color'></div>"))
						.append($("<div id='text'></div>")));
			}
				
		}
	}
	
	function clearData() {
		for(var i = 0; i < JSONdata.length; i++) {
			if(JSONdata[i].setCourseSlot != -1) {
				var courseSelectedName = $("span[id='"+JSONdata[i].setCourseSlot +"']").parent().closest('div').text().trim();
				JSONdata[i].dataSchedule[courseSelectedName].learnCourseInSlot -= 1;

			}
			JSONdata[i].setCourseSlot = -1;
		}
	}
	
	function clearScheduleDialog() {
		$("#set-courses option:first").attr("selected", "selected");
		$("#warning-set-teacher").hide();
		$("#warning-not-enough-slot").hide();
		$("#warning-set-room").hide();
		$("#warning-conflict-merge-class").hide();
		showDialog("dialog-schedule");
	}
});
