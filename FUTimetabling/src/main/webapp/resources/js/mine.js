$(document).ready(function() {

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

		_showDialog("dialog-info-course-" + course);
	});
	
	$("td[id^='tt-']").on("click", function() {
		console.log("asdasd");
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