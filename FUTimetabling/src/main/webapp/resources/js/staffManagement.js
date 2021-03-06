$(document).ready(function() { 

	init();
	
	$("#table-staffs").on("click", "a[id^='edit-staff']",function() {
		setDialogEditData($("#dialog-edit-staff"), $(this).closest("tr"));
		showDialog("dialog-edit-staff");
	});
	
	$("#table-staffs").on("click", "a[id^='delete-staff']",function() {
		$("#dialog-delete-staff").attr("data-staffId", $(this).closest("tr").attr("data-staffId"));
		showDialog("dialog-delete-staff");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "staffManagement/deleteStaff?staffId=" +$("#dialog-delete-staff").attr("data-staffId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-staff").removeAttr("data-staffId");
		showDialog("dialog-delete-staff");
	});
	
	$("#btn-add-staff").on("click", function() {
		clearDialogData($("#dialog-add-staff"));
		showDialog("dialog-add-staff");
	});
	
	$("#dialog-edit-staff #btn-edit-save").on("click", function() {
		$("#form-edit-staff").attr("action", "staffManagement/updateStaff");
		$("#form-edit-staff").submit();
	});
	
	$("#dialog-add-staff #btn-add-save").on("click", function() {
		$("#form-add-staff").attr("action", "staffManagement/updateStaff");
		
		if($("#dialog-add-staff #account").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Account can not be empty!!!"});
			$("#dialog-add-staff #account").css("border-color", "red");
		} else {
			if($("#dialog-add-staff #name").val() == "") {
				$("#dialog-add-staff #account").css("border-color", "");
				$.Notify({type: 'alert', caption: 'Alert', content: "Your Name can not be empty!!!"});
				$("#dialog-add-staff #name").css("border-color", "red");
			} else {
				if(isFPTEmail($("#dialog-add-staff #email").val())) {
					$("#form-add-staff").submit();
				} else {
					$("#dialog-add-staff #name").css("border-color", "");
					$.Notify({type: 'alert', caption: 'Alert', content: "Your Email is not FPT University's mail!!!"});
					$("#dialog-add-staff #email").css("border-color", "red");
				}
			}
		}
	});
	
	$("#dialog-edit-staff #btn-edit-cancel").on("click", function() {
		showDialog("dialog-edit-staff");
		clearDialogData($("#dialog-edit-staff"));
	});
	
	$("#dialog-add-staff #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-staff");
		$("#dialog-add-staff #email").css("border-color", "");
		$("#dialog-add-staff #name").css("border-color", "");
		$("#dialog-add-staff #account").css("border-color", "");
	});
	
	function init() {
		var table = $('#table-staffs').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 20
	    });
	}
	
	function clearDialogData(dialog) {
		dialog.find("#staffId").val("-1");
		dialog.find("#name").val("");
		dialog.find("#account").val("");
		dialog.find("#email").val( "");
	}
	
	function setDialogEditData(dialog, tr) {
		dialog.find("#staffId").val(tr.attr("data-staffId"));
		dialog.find("#account").val(tr.find("td:eq(0)").text());
		dialog.find("#account").attr("readonly", true);
		dialog.find("#name").val(tr.find("td:eq(1)").text());
		dialog.find("#name").attr("readonly", true);
		dialog.find("#email").val(tr.find("td:eq(2)").text());
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