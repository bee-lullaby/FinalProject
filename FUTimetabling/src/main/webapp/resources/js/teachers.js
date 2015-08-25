$(document).ready(function() { 

	_init();
	$("#table-teachers").on("click", "a[id^='edit-teacher']",function() {
		_clearDialogData($("#dialog-edit-teacher"));
		_setDialogEditData($("#dialog-edit-teacher"), $(this).closest("tr"));
		
		_showDialog("dialog-edit-teacher");
	});

	$("#table-teachers").on("click", "a[id^='delete-teacher']",function() {
		$("#dialog-delete-teacher").attr("data-teacherId", $(this).closest("tr").attr("data-teacherId"));
		_showDialog("dialog-delete-teacher");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "teachers/deleteTeacher?semesterId=" +_urlParam("semesterId") +"&teacherId=" +$("#dialog-delete-teacher").attr("data-teacherId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-teacher").removeAttr("data-teacherId");
		_showDialog("dialog-delete-teacher");
	});
	
	$("#btn-add-teacher").on("click", function() {
		_clearDialogData($("#dialog-add-teacher"));
		_showDialog("dialog-add-teacher");
	});
	
	$("#dialog-edit-teacher #btn-edit-save").on("click", function() {
		if(_isFPTEmail($("#dialog-edit-teacher #email").val())) {
			$("#form-edit-teacher").attr("action", "teachers/updateTeacher");
			$("#form-edit-teacher").submit();
		} else {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Email is not FPT University's mail!!!"});
			$("#dialog-edit-teacher #email").css("border-color", "red");
		}
	});
	
	$("#dialog-add-teacher #btn-add-save").on("click", function() {
		if($("#dialog-add-teacher #account").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Account can not be empty!!!"});
			$("#dialog-add-teacher #account").css("border-color", "red");
		} else {
			if($("#dialog-add-teacher #name").val() == "") {
				$.Notify({type: 'alert', caption: 'Alert', content: "Your Name can not be empty!!!"});
				$("#dialog-add-teacher #name").css("border-color", "red");
			} else {
				if(_isFPTEmail($("#dialog-add-teacher #email").val())) {
					$("#form-add-teacher").attr("action", "teachers/updateTeacher");
					$("#form-add-teacher").submit();
				} else {
					$.Notify({type: 'alert', caption: 'Alert', content: "Your Email is not FPT University's mail!!!"});
					$("#dialog-add-teacher #email").css("border-color", "red");
				}
			}
		}
	});
	
	$("#dialog-edit-teacher #btn-edit-cancel").on("click", function() {
		_showDialog("dialog-edit-teacher");
		console.log("cancel");
		_clearDialogData($("#dialog-edit-teacher"));
	});
	
	$("#dialog-add-teacher #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-teacher");
		_clearDialogData($("#dialog-add-teacher"));
	});
	
	$("#btn-add-from-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#table-add-teachers #select-courses").on("change", function() {
		_setTextAriaCoursesSelected($(this));
	});
	
	$("#table-edit-teachers #select-courses").on("change", function() {
		_setTextAriaCoursesSelected($(this));
	});
	
	function _init() {
		var table = $('#table-teachers').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 20
	    });
		
		$("#select-semester").find("a[id='" +_urlParam("semesterId") +"']").closest("li").addClass("active");
		
		var input = $("<input>").attr("type", "hidden")
        						.attr("name", "semesterId").val(_urlParam("semesterId"));
		$('#form-add-file').append($(input));
		
		
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#teacherId").attr("value", "-1");
		dialog.find("#teacherSemesterId").attr("value", "-1");
		dialog.find("#name").attr("value", "");
		dialog.find("#account").attr("value", "");
		dialog.find("#email").attr("value", "");
		dialog.find("#select-semester-edit option").attr("disabled", true);
		dialog.find("#select-semester-edit").find("option[value='" +_urlParam("semesterId") +"']").attr("disabled", false).attr("selected", "selected");
		dialog.find("#select-courses option").each(function() {
			$(this).removeAttr("selected");
		});
		
		_setTextAriaCoursesSelected(dialog.find("#select-courses"));
	}
	
	function _setDialogEditData(dialog, tr) {
		dialog.find("#teacherId").attr("value", tr.attr("data-teacherId"));
		dialog.find("#teacherSemesterId").attr("value", tr.attr("data-teacherSemesterId"));
		dialog.find("#account").attr("value", tr.find("td:eq(0)").text());
		dialog.find("#account").attr("readonly", true);
		dialog.find("#name").attr("value", tr.find("td:eq(1)").text());
		dialog.find("#name").attr("readonly", true);
		dialog.find("#email").attr("value", tr.find("td:eq(2)").text());
		dialog.find("#select-semester-edit").find("option[value='" +_urlParam("semesterId") +"']").attr("selected", "selected");
		dialog.find("#select-department-edit").find("option:contains('" +tr.find("td:eq(2)").text() +"')").attr("selected", "selected");
		
		var listCourses = tr.find("td:eq(4)").text().split(";");
		
		$.each(listCourses, function(i, e) {
			if(e != "") {
				$("#select-courses option:contains('" +e.trim() +"')").prop("selected", true);
			}
		});

		_setTextAriaCoursesSelected($("#table-edit-teachers").find("#select-courses"));
	}
	
	function _setTextAriaCoursesSelected(parents) {
		var text = "";
		parents.find("option:selected").each(function() {
			text += $(this).text() +"; ";
		});
		console.log(text);
		parents.closest("td").find("#courses-selected").text(text);
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

	function _isFPTEmail(email) {
		var part = email.split("@");
		
		if(part.length != 2) {
			return false;
		}
		if(part[1] != "fpt.edu.vn") {
			return false;
		} 
		return true;
	}
	
	function _showDialog(id) {
		var dialog = $("#" + id).data('dialog');
		if (!dialog.element.data('opened')) {
			dialog.open();
		} else {
			dialog.close();
		}
	}
	
	
});