$(document).ready(function(){	
	
	var startDate = $("#timetable-container").find("#startDate").val();
	var endDate = $("#timetable-container").find("#endDate").val();
	
	var dayName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	var days = [];
	
	
	
	_setDateTimetable();
	
	_setDateHeader();
	
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
	
	$("td[id^='tt-']").on("click", function() {
		var columnNo = $(this).index() + 1;
		
		var header = $(this).closest("table").find("tr:nth-child(1) th:nth-child("+columnNo+")").text();
		var slot = $(this).parent().get(0).rowIndex;
		$("#dialog-schedule #slot-day").text(header +" - Slot " +slot);
		_setCourseInfoDialog(); 
		_showDialog("dialog-schedule");
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
		_setCourseInfoDialog(); 
	});
	
	function _setCourseInfoDialog() {
		var courseSelected = $("#set-courses option:selected").text();
		$("#course-info-to-set").find("#course-code").text(courseSelected);
	};
});
