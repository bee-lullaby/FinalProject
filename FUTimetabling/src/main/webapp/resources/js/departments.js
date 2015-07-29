$(document).ready(function() { 

	_init();
	
	$("#table-departments").on("click", "a[id^='edit-department']", function() {
		_setDialogEditData($("#dialog-edit-department"), $(this).closest("tr"));
		_showDialog("dialog-edit-department");
	});

	
	$("#btn-add-department").on("click", function() {
		_clearDialogData($("#dialog-add-department"));
		_showDialog("dialog-add-department");
	});
	
	$("#dialog-edit-department #btn-edit-save").on("click", function() {
		$("#form-edit-department").attr("action", "departments/updateDepartment");
		$("#form-edit-department").submit();
	});
	
	$("#dialog-add-department #btn-add-save").on("click", function() {
		$("#form-add-department").attr("action", "departments/updateDepartment");
		$("#form-add-department").submit();
	});
	
	$("#dialog-edit-department #btn-edit-cancel").on("click", function() {
		_showDialog("dialog-edit-department");
		$("#dialog-edit-department").removeAttr("data-action");
		
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
		dialog.find("#departmentId").attr("value", "-1");
		dialog.find("#code").attr("value", "");
		dialog.find("#code").attr("readonly", false);
		dialog.find("#name").attr("value", "");
	}
	
	function _setDialogEditData(dialog, tr) {
		dialog.find("#departmentId").attr("value", tr.attr("data-departmentId"));
		dialog.find("#code").attr("value", tr.find("td:eq(0)").text());
		dialog.find("#code").attr("readonly", true);
		dialog.find("#name").attr("value", tr.find("td:eq(1)").text());
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