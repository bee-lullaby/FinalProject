$(document).ready(function() { 

	_init();
	
	$("#table-programs").on("click", "a[id^='edit-program']", function() {
		_setDialogEditData($("#dialog-edit-program"), $(this).closest("tr"));
		$("#dialog-edit-program").attr("data-action", "edit");
		_showDialog("dialog-edit-program");
	});

	$("#btn-add-from-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	function _init() {
		var table = $('#table-programs').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 30
	    });
		
		$("#select-semester").find("a[id='" +_urlParam("semesterId") +"']").closest("li").addClass("active");
		
		var input = $("<input>").attr("type", "hidden")
        						.attr("name", "semesterId").val(_urlParam("semesterId"));
		$('#form-add-file').append($(input));
		
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#programSemesterId").val("-1");
	}
	
	function _setDialogEditData(dialog, tr) {
		dialog.find("#programSemesterId").attr("value", tr.attr("data-programSemesterId"));
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