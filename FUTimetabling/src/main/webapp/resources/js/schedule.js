$(document).ready(function(){	
	
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
		console.log($(this).find("select").size());
		console.log(a);
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

});
