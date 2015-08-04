$(document).ready(function() { 

	_init();

	$("#table-semesters").on("click", "a[id^='delete-semester']",function() {
		$("#dialog-delete-semester").attr("data-semesterId", $(this).closest("tr").attr("data-semesterId"));
		_showDialog("dialog-delete-semester");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "semesters/deleteSemester?semesterId=" +$("#dialog-delete-semester").attr("data-semesterId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-semester").removeAttr("data-semesterId");
		_showDialog("dialog-delete-semester");
	});
	
	$("#btn-add-semester").on("click", function() {
		_clearDialogData($("#dialog-add-semester"));
		_showDialog("dialog-add-semester");
	});
	
	$("#dialog-add-semester #btn-add-save").on("click", function() {
		if($("#dialog-add-semester #code").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Code can not be empty!!!"});
			$("#dialog-add-semester #code").css("border-color", "red");
		} else {
			$("#dialog-add-semester #code").css("border-color", "");
			if($("#dialog-add-semester #name").val() == "") {
				$.Notify({type: 'alert', caption: 'Alert', content: "Your Name can not be empty!!!"});
				$("#dialog-add-semester #name").css("border-color", "red");
			} else {
				$("#dialog-add-semester #name").css("border-color", "");
				if($("#dialog-add-semester #startDate").val() == "") {
					$.Notify({type: 'alert', caption: 'Alert', content: "Your Start Date can not be empty!!!"});
					$("#dialog-add-semester #startDate").css("border-color", "red");
				} else {
					$("#dialog-add-semester #startDate").css("border-color", "");
					if($("#dialog-add-semester #endDate").val() == "") {
						$.Notify({type: 'alert', caption: 'Alert', content: "Your End Date can not be empty!!!"});
						$("#dialog-add-semester #endDate").css("border-color", "red");
					} else {
						if(new Date($("#dialog-add-semester #endDate").val()) <= new Date($("#dialog-add-semester #startDate").val())) {
							$.Notify({type: 'alert', caption: 'Alert', content: "Your End Date must be greater than Start Date!!!"});
							$("#dialog-add-semester #endDate").css("border-color", "red");
							$("#dialog-add-semester #startDate").css("border-color", "red");
						} else {
							$("#form-add-semester").attr("action", "semesters/updateSemester");
							$("#form-add-semester").submit();
						}
					}
				}
			}
		}
	});
	
	$("#dialog-add-semester #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-semester");
		_clearDialogData($("#dialog-add-semester"));
		$("#dialog-add-semester #code").css("border-color", "");
		$("#dialog-add-semester #name").css("border-color", "");
		$("#dialog-add-semester #startDate").css("border-color", "");
		$("#dialog-add-semester #endDate").css("border-color", "");
	});
	
	$("#btn-add-from-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	function _init() {
		var table = $('#table-semesters').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": false,
			"info": true
	    });
		
		$("#datePicker-start").datepicker({
			position: "top"
		});
		
		$("#datePicker-end").datepicker({
			position: "top"
		});
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#code").val("");
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