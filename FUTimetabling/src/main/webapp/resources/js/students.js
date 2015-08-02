$(document).ready(function() { 

	_init();
	
	$("#table-students").on("click", "a[id^='edit-student']", function() {
		_setDialogEditData($("#dialog-edit-student"), $(this).closest("tr"));
		$("#dialog-edit-student").attr("data-action", "edit");
		_showDialog("dialog-edit-student");
	});
	
	$("#btn-add-student").on("click", function() {
		_clearDialogData($("#dialog-add-student"));
		_showDialog("dialog-add-student");
	});
	
	$("#dialog-edit-student #btn-edit-save").on("click", function() {
		$("#form-edit-student").attr("action", "students/editStudent");
		$("#form-edit-student").submit();
	});
	
	$("#dialog-add-student #btn-add-save").on("click", function() {
		$("#form-add-student").attr("action", "students/addStudent");
		$("#form-add-student").submit();
	});
	
	$("#dialog-edit-student #btn-edit-cancel").on("click", function() {
		_showDialog("dialog-edit-student");
		$("#dialog-edit-student").removeAttr("data-action");
		
	});
	
	$("#dialog-add-student #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-student");
		_clearDialogData($("#dialog-add-student"));
	});
	
	$("#btn-add-from-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	function _init() {
		var table = $('#table-students').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"pageLength": 50,	
			"info": true
	    });
		
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#studentId").attr("value", "-1");
		dialog.find("#name").attr("value", "");
		dialog.find("#email").attr("value", "");
		dialog.find("#batch").attr("value", "");
		dialog.find("#semester").attr("value", "");
		dialog.find("#select-class").find("option:first").attr("selected", "selected");
		dialog.find("#select-specialized").find("option:first").attr("selected", "selected");
		dialog.find("#select-ds").find("option:first").attr("selected", "selected");
	}
	
	function _setDialogEditData(dialog, tr) {
		dialog.find("#studentId").attr("value", tr.attr("data-studentId"));
		dialog.find("#code").attr("value", tr.find("td:eq(0)").text());
		dialog.find("#name").attr("value", tr.find("td:eq(1)").text());
		dialog.find("#email").attr("value", tr.attr("data-email"));
		dialog.find("#batch").attr("value", tr.find("td:eq(4)").text());
		dialog.find("#semester").attr("value", tr.find("td:eq(5)").text());
		dialog.find("#select-class").find("option:contains('" +tr.find("td:eq(6)").text().trim() +"')").attr("selected", "selected");
		dialog.find("#select-specialized").find("option:contains('" +tr.find("td:eq(2)").text().trim() +"')").attr("selected", "selected");
		dialog.find("#select-ds").find("option:contains('" +tr.find("td:eq(3)").text().trim() +"')").attr("selected", "selected");	
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