$(document).ready(function() { 

	init();
	
	$("#table-courses").on("click", "a[id^='edit-course']", function() {
		clearDialogData($("#dialog-edit-course"));
		setDialogEditData($("#dialog-edit-course"), $(this).closest("tr"));
		$("#dialog-edit-course").attr("data-action", "edit");
		showDialog("dialog-edit-course");
	});
	
	$("#table-courses").on("click", "a[id^='delete-course']",function() {
		$("#dialog-delete-course").attr("data-courseId", $(this).closest("tr").attr("data-courseId"));
		showDialog("dialog-delete-course");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "courses/deleteCourse?semesterId=" +urlParam("semesterId") +"&courseId=" +$("#dialog-delete-course").attr("data-courseId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-course").removeAttr("data-courseId");
		showDialog("dialog-delete-course");
	});
	
	$("#btn-add-course").on("click", function() {
		clearDialogData($("#dialog-add-course"));
		showDialog("dialog-add-course");
	});
	
	$("#dialog-edit-course #btn-edit-save").on("click", function() {
		$("#form-edit-course").attr("action", "courses/updateCourse");
		$("#form-edit-course").submit();
	});
	
	$("#dialog-add-course #btn-add-save").on("click", function() {
		if($("#dialog-add-course #courseCode").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Code can not be empty!!!"});
			$("#dialog-add-course #courseCode").css("border-color", "red");
		} else {
			$("#dialog-add-course #courseCode").css("border-color", "");
			if($("#dialog-add-course #courseName").val() == "") {
				$.Notify({type: 'alert', caption: 'Alert', content: "Your Name can not be empty!!!"});
				$("#dialog-add-course #courseName").css("border-color", "red");
			} else {
				$("#dialog-add-course #courseName").css("border-color", "");
				if($("#dialog-add-course #slots").val() == "") {
					$.Notify({type: 'alert', caption: 'Alert', content: "Your slots can not be empty!!!"});
					$("#dialog-add-course #slots").css("border-color", "red");
				} else {
					$("#form-add-course").attr("action", "courses/updateCourse");
					$("#form-add-course").submit();
				}
			}
		}
	});
	
	$("#dialog-edit-course #btn-edit-cancel").on("click", function() {
		showDialog("dialog-edit-course");
		$("#dialog-edit-course").removeAttr("data-action");
		
	});
	
	$("#dialog-add-course #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-course");
		clearDialogData($("#dialog-add-course"));
		$("#dialog-add-course #courseCode").css("border-color", "");
		$("#dialog-add-course #courseName").css("border-color", "");
		$("#dialog-add-course #slots").css("border-color", "");
	});
	
	$("#btn-add-from-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	function init() {
		var table = $('#table-courses').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 30
	    });
		
		$("#select-semester").find("a[id='" +urlParam("semesterId") +"']").closest("li").addClass("active");
		
		var input = $("<input>").attr("type", "hidden")
        						.attr("name", "semesterId").val(urlParam("semesterId"));
		$('#form-add-file').append($(input));
		
	}
	
	function clearDialogData(dialog) {
		dialog.find("#courseId").val("-1");
		dialog.find("#courseSemesterId").val("-1");
		dialog.find("#courseName").val("");
		dialog.find("#courseName").attr("readonly", false);
		dialog.find("#courseName").attr("disabled", false);
		dialog.find("#courseCode").val("");
		dialog.find("#courseCode").attr("readonly", false);
		dialog.find("#courseCode").attr("disabled", false);
		dialog.find("#slots").val("");
		dialog.find("#select-department-edit").find("option:first").attr("selected", "selected");
		dialog.find("#select-semester-edit option").attr("disabled", true);
		dialog.find("#select-semester-edit").find("option[value='" +urlParam("semesterId") +"']").attr("disabled", false).attr("selected", "selected");
		dialog.find("#select-course-condition-edit option:first").attr("selected", "selected");
	}
	
	function setDialogEditData(dialog, tr) {
		dialog.find("#courseId").val(tr.attr("data-courseId"));
		dialog.find("#courseSemesterId").val(tr.attr("data-courseSemesterId"));
		dialog.find("#courseName").val(tr.find("td:eq(0)").text());
		dialog.find("#courseName").attr("readonly", true);
//		dialog.find("#courseName").attr("disabled", true);
		dialog.find("#courseCode").val(tr.find("td:eq(1)").text());
		dialog.find("#courseCode").attr("readonly", true);
//		dialog.find("#courseCode").attr("disabled", true);
		dialog.find("#slots").val(tr.find("td:eq(3)").text());
		dialog.find("#select-semester-edit").find("option:contains('" +tr.attr("data-semesterName").trim() +"')").attr("selected", "selected");
		dialog.find("#select-department-edit").find("option:contains('" +tr.find("td:eq(2)").text() +"')").attr("selected", "selected");
		
		if(tr.find("td:eq(4)").text() == null || tr.find("td:eq(4)").text() == "") {
			$("#select-course-condition-edit option:first").attr("selected", "selected");
		} else {
			$("#select-course-condition-edit").find("option:contains('" +tr.find("td:eq(4)").text() +"')").attr("selected", "selected");
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
	
	function showDialog(id) {
		var dialog = $("#" + id).data('dialog');
		if (!dialog.element.data('opened')) {
			dialog.open();
		} else {
			dialog.close();
		}
	}
	
	
});