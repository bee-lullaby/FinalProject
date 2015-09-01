$(document).ready(function() { 

	init();
	
	$("#table-students").on("click", "a[id^='edit-student']", function() {
		setDialogEditData($("#dialog-edit-student"), $(this).closest("tr"));
		$("#dialog-edit-student").attr("data-action", "edit");
		showDialog("dialog-edit-student");
	});
	
	$("#table-students").on("click", "a[id^='delete-student']",function() {
		$("#dialog-delete-student").attr("data-studentId", $(this).closest("tr").attr("data-studentId"));
		showDialog("dialog-delete-student");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "students/deleteStudent?studentId=" +$("#dialog-delete-student").attr("data-studentId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-student").removeAttr("data-studentId");
		showDialog("dialog-delete-student");
	});
	
	$("#btn-add-student").on("click", function() {
		clearDialogData($("#dialog-add-student"));
		showDialog("dialog-add-student");
	});
	
	$("#dialog-edit-student #btn-edit-save").on("click", function() {
		$("#form-edit-student").attr("action", "students/updateStudent");
		$("#form-edit-student").submit();
	});
	
	$("#dialog-add-student #btn-add-save").on("click", function() {
		$("#form-add-student").attr("action", "students/updateStudent");
		$("#form-add-student").submit();
	});
	
	$("#dialog-edit-student #btn-edit-cancel").on("click", function() {
		showDialog("dialog-edit-student");
		$("#dialog-edit-student").removeAttr("data-action");
		
	});
	
	$("#dialog-add-student #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-student");
		clearDialogData($("#dialog-add-student"));
	});
	
	$("#btn-add-from-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	function init() { 
//		var table = $('#table-students').DataTable({
//				"lengthChange": false,
//				"searching": true,
//				"paging": true,
//				"pageLength": 50,	
//				"info": true
//		    });
	}
	
	function clearDialogData(dialog) {
		dialog.find("#studentId").attr("value", "-1");
		dialog.find("#name").attr("value", "");
		dialog.find("#batch").attr("value", "");
		dialog.find("#semester").attr("value", "");
		dialog.find("#select-specialized").find("option:first").attr("selected", "selected");
		dialog.find("#select-ds").find("option:first").attr("selected", "selected");
	}
	
	function setDialogEditData(dialog, tr) {
		dialog.find("#studentId").attr("value", tr.attr("data-studentId"));
		dialog.find("#code").attr("value", tr.find("td:eq(0)").text());
		dialog.find("#name").attr("value", tr.find("td:eq(1)").text());
		dialog.find("#batch").attr("value", tr.find("td:eq(4)").text());
		dialog.find("#semester").attr("value", tr.find("td:eq(5)").text());
		dialog.find("#select-specialized").find("option:contains('" +tr.find("td:eq(2)").text().trim() +"')").attr("selected", "selected");
		dialog.find("#select-ds").find("option:contains('" +tr.find("td:eq(3)").text().trim() +"')").attr("selected", "selected");	
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