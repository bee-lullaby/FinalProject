$(document).ready(function() { 

	_init();
	
	$("#table-buildings").on("click", "a[id^='edit-building']",function() {
		_setDialogEditData($("#dialog-edit-building"), $(this).closest("tr"));
		_showDialog("dialog-edit-building");
	});
	
	$("#table-buildings").on("click", "a[id^='delete-building']",function() {
		$("#dialog-delete-building").attr("data-buildingId", $(this).closest("tr").attr("data-buildingId"));
		_showDialog("dialog-delete-building");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "building/deleteBuilding?buildingId=" +$("#dialog-delete-building").attr("data-buildingId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-building").removeAttr("data-buildingId");
		_showDialog("dialog-delete-building");
	});
	
	$("#btn-add-building").on("click", function() {
		_clearDialogData($("#dialog-add-building"));
		_showDialog("dialog-add-building");
	});
	
	$("#dialog-add-building #btn-add-save").on("click", function() {
		$("#form-add-building").attr("action", "building/updateBuilding");
		$("#form-add-building").submit();
	});
	
	$("#dialog-add-building #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-building");
	});
	
	function _init() {
		var table = $('#table-buildings').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 20
	    });
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#buildingId").attr("value", "-1");
		dialog.find("#code").val("");
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