$(document).ready(function(){	
	
	var startDate = $("#timetable-container").find("#startDate").val();
	var endDate = $("#timetable-container").find("#startDate").val();
	
	var day = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
	
	_setDateTimetable();
	
	
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
		console.log(columnNo);
		
		var header = $(this).closest("table").find("tr:nth-child(1) th:nth-child("+columnNo+")").text();
		var slot = $(this).parent().get(0).rowIndex;
		$("#dialog-schedule #slot-day").text(header +" - Slot " +slot);
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
		var d = new Date(startDate);
		console.log(d);
		d.setDate(d.getDate() - 1);
		var textDate = d.getDate() +"/" +d.getMonth() +"  -  ";
		
		$("#timetable #header th").each(function() {
			var day = d.getDate();
			var month = d.getMonth() + 1;
			$(this).text($(this).text() + " (" +day +"/" +month +")");
			d.setDate(d.getDate() + 1);
		});
		
		var day = d.getDate();
		var month = d.getMonth() + 1;
		textDate += day +"/" +month;
		
		$('#select-weeks').append($("<option></option>")
							.attr("value", "week-1")
							.text(textDate));
	}
});
