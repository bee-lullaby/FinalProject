$(document).ready(function() {
	$("[id^='manage-'").on("click", function() {
		var pageName = $(this).attr("id").split("-")[1];
		window.location = "staff/" +pageName;
	});
	
	$("#activity-schedule").on("click", function() {
		window.location = "staff/scheduleInfo";
	});
});