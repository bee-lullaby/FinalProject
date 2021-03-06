$(document).ready(function() { 

	init();
	
	$("#table-buildings").on("click", "a[id^='edit-building']",function() {
		setDialogEditData($("#dialog-edit-building"), $(this).closest("tr"));
		showDialog("dialog-edit-building");
	});
	
	$("#table-buildings").on("click", "a[id^='delete-building']",function() {
		$("#dialog-delete-building").attr("data-buildingId", $(this).closest("tr").attr("data-buildingId"));
		showDialog("dialog-delete-building");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "building/deleteBuilding?buildingId=" +$("#dialog-delete-building").attr("data-buildingId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-building").removeAttr("data-buildingId");
		showDialog("dialog-delete-building");
	});
	
	$("#btn-add-building").on("click", function() {
		clearDialogData($("#dialog-add-building"));
		showDialog("dialog-add-building");
	});
	
	$("#dialog-add-building #btn-add-save").on("click", function() {
		if($("#dialog-add-building #code").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Code can not be empty!!!"});
			$("#dialog-add-building #code").css("border-color", "red");
		} else {
			$("#form-add-building").attr("action", "building/updateBuilding");
			$("#form-add-building").submit();
		}
	});
	
	$("#dialog-add-building #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-building");
		$("#dialog-add-building #code").css("border-color", "");
	});
	
	function init() {
		var table = $('#table-buildings').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 20
	    });
	}
	
	function clearDialogData(dialog) {
		dialog.find("#buildingId").attr("value", "-1");
		dialog.find("#code").val("");
	}
	
	function isFPTEmail(email) {
		var part = email.split("@");
		
		if(part.length != 2) {
			return false;
		}
		
		if(part[1] != "fpt.edu.vn") {
			return false;
		} 
		
		return true;
		
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