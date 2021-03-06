$(document).ready(function() { 

	init();
	
	$("#table-specializeds").on("click", "a[id^='edit-specialized']", function() {
		setDialogEditData($("#dialog-edit-specialized"), $(this).closest("tr"));
		$("#dialog-edit-specialized").attr("data-action", "edit");
		showDialog("dialog-edit-specialized");
	});

	$("#table-specializeds").on("click", "a[id^='delete-specialized']",function() {
		$("#dialog-delete-specialized").attr("data-specializedId", $(this).closest("tr").attr("data-specializedId"));
		showDialog("dialog-delete-specialized");
	});
	
	$("#btn-delete-accept").on("click", function() {
		window.location = "specialized/deleteSpecialized?specializedId=" +$("#dialog-delete-specialized").attr("data-specializedId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-specialized").removeAttr("data-specializedId");
		showDialog("dialog-delete-specialized");
	});
	
	
	$("#btn-add-specialized").on("click", function() {
		clearDialogData($("#dialog-add-specialized"));
		showDialog("dialog-add-specialized");
	});
	
	$("#dialog-add-specialized #btn-add-save").on("click", function() {
		if($("#dialog-add-specialized #code").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Code can not be empty!!!"});
			$("#dialog-add-specialized #code").css("border-color", "red");
		} else {
			$("#dialog-add-specialized #code").css("border-color", "");
			if($("#dialog-add-specialized #name").val() == "") {
				$.Notify({type: 'alert', caption: 'Alert', content: "Your Capacity can not be empty!!!"});
				$("#dialog-add-specialized #name").css("border-color", "red");
			} else {
				$("#form-add-specialized").attr("action", "specialized/updateSpecialized");
				$("#form-add-specialized").submit();
			}
		}
	});
	
	$("#dialog-edit-specialized #btn-edit-cancel").on("click", function() {
		showDialog("dialog-edit-specialized");
		$("#dialog-edit-specialized").removeAttr("data-action");
		
	});
	
	$("#dialog-add-specialized #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-specialized");
		clearDialogData($("#dialog-add-specialized"));
		$("#dialog-add-specialized #code").css("border-color", "");
		$("#dialog-add-specialized #name").css("border-color", "");
	});
	
	$("#btn-add-from-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	function init() {
		var table = $('#table-specializeds').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 30
	    });
		$("#select-semester").find("a[id='" +urlParam("semesterId") +"']").addClass("active");
		
	}
	
	function clearDialogData(dialog) {
		dialog.find("#specializedId").val("-1");
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