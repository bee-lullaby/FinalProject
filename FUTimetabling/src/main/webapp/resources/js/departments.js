$(document).ready(function() { 

	init();

	$("#table-departments").on("click", "a[id^='delete-department']",function() {
		$("#dialog-delete-department").attr("data-departmentId", $(this).closest("tr").attr("data-departmentId"));
		showDialog("dialog-delete-department");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "departments/deleteDepartment?departmentId=" +$("#dialog-delete-department").attr("data-departmentId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-department").removeAttr("data-departmentId");
		showDialog("dialog-delete-department");
	});
	
	$("#btn-add-department").on("click", function() {
		clearDialogData($("#dialog-add-department"));
		showDialog("dialog-add-department");
	});
	
	$("#dialog-add-department #btn-add-save").on("click", function() {
		if($("#dialog-add-department #code").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Code can not be empty!!!"});
			$("#dialog-add-department #code").css("border-color", "red");
		} else {
			$("#dialog-add-department #code").css("border-color", "");
			if($("#dialog-add-department #name").val() == "") {
				$.Notify({type: 'alert', caption: 'Alert', content: "Your Capacity can not be empty!!!"});
				$("#dialog-add-department #name").css("border-color", "red");
			} else {
				$("#form-add-department").attr("action", "departments/updateDepartment");
				$("#form-add-department").submit();
			}
		}
		
	});
	
	$("#dialog-add-department #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-department");
		clearDialogData($("#dialog-add-department"));
		$("#dialog-add-department #code").css("border-color", "");
		$("#dialog-add-department #name").css("border-color", "");
	});
	
	$("#btn-add-from-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	function init() {
		var table = $('#table-departments').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": false,
			"info": true
	    });
		
	}
	
	function clearDialogData(dialog) {
		dialog.find("#code").val("");
		dialog.find("#code").attr("readonly", false);
		dialog.find("#name").val("");
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