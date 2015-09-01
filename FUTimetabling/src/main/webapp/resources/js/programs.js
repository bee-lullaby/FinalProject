$(document).ready(function() { 

	init();
	
	$("#table-programs").on("click", "a[id^='edit-program']", function() {
		setDialogEditData($("#dialog-edit-program"), $(this).closest("tr"));
		$("#dialog-edit-program").attr("data-action", "edit");
		showDialog("dialog-edit-program");
	});

	$("#btn-add-from-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-clear").on("click", function() {
		window.location = "programs/clearPrograms?semesterId=" +urlParam("semesterId");
	});
	
	function init() {
		var table = $('#table-programs').DataTable({
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
		dialog.find("#programSemesterId").val("-1");
	}
	
	function setDialogEditData(dialog, tr) {
		dialog.find("#programSemesterId").attr("value", tr.attr("data-programSemesterId"));
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