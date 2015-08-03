$(document).ready(function() { 

	_init();

	$("#table-departments").on("click", "a[id^='delete-department']",function() {
		$("#dialog-delete-department").attr("data-departmentId", $(this).closest("tr").attr("data-departmentId"));
		_showDialog("dialog-delete-department");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "departments/deleteDepartment?departmentId=" +$("#dialog-delete-department").attr("data-departmentId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-department").removeAttr("data-departmentId");
		_showDialog("dialog-delete-department");
	});
	
	$("#btn-add-department").on("click", function() {
		_clearDialogData($("#dialog-add-department"));
		_showDialog("dialog-add-department");
	});
	
	$("#dialog-add-department #btn-add-save").on("click", function() {
		$("#form-add-department").attr("action", "departments/updateDepartment");
		$("#form-add-department").submit();
	});
	
	$("#dialog-add-department #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-department");
		_clearDialogData($("#dialog-add-department"));
	});
	
	$("#btn-add-from-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	function _init() {
		var table = $('#table-departments').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": false,
			"info": true
	    });
		
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#code").val("");
		dialog.find("#code").attr("readonly", false);
		dialog.find("#name").val("");
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
	
	function _showDialog(id) {
		var dialog = $("#" + id).data('dialog');
		if (!dialog.element.data('opened')) {
			dialog.open();
		} else {
			dialog.close();
		}
	}
	
	
});