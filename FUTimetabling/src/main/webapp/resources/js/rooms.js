$(document).ready(function() { 

	_init();
	
	$("a[id^='edit-room']").on("click", function() {
		_setDialogEditData($("#dialog-edit-room"), $(this).closest("tr"));
		$("#dialog-edit-room").attr("data-action", "edit");
		_showDialog("dialog-edit-room");
	});

	
	$("#btn-add-room").on("click", function() {
		_clearDialogData($("#dialog-add-room"));
		_showDialog("dialog-add-room");
	});
	
	$("#dialog-edit-room #btn-edit-save").on("click", function() {
		$("#form-edit-room").attr("action", "rooms/updateRoom");
		$("#form-edit-room").submit();
	});
	
	$("#dialog-add-room #btn-add-save").on("click", function() {
		$("#form-add-room").attr("action", "rooms/updateRoom");
		$("#form-add-room").submit();
	});
	
	$("#dialog-edit-room #btn-edit-cancel").on("click", function() {
		_showDialog("dialog-edit-room");
		$("#dialog-edit-room").removeAttr("data-action");
		
	});
	
	$("#dialog-add-room #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-room");
		_clearDialogData($("#dialog-add-room"));
	});
	
	$("#btn-add-from-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	function _init() {
		var table = $('#table-rooms').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": false,
			"info": true
	    });
		$("#select-semester").find("a[id='" +_urlParam("semesterId") +"']").addClass("active");
		
		var input = $("<input>").attr("type", "hidden")
        						.attr("name", "semesterId").val(_urlParam("semesterId"));
		$('#form-add-file').append($(input));
		
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#roomId").attr("value", "-1");
		dialog.find("#code").attr("value", "");
		dialog.find("#code").attr("readonly", false);
		dialog.find("#capacity").attr("value", "");
		dialog.find("#select-courses option").each(function() {
			$(this).removeAttr("selected");
		});
		
		_setTextAriaCoursesSelected(dialog.find("#select-courses"));
	}
	
	function _setDialogEditData(dialog, tr) {
		dialog.find("#roomId").attr("value", tr.attr("data-roomId"));
		dialog.find("#code").attr("value", tr.find("td:eq(0)").text());
		dialog.find("#code").attr("readonly", true);
		dialog.find("#capacity").attr("value", tr.find("td:eq(1)").text());
		
		var listCourses = tr.find("td:eq(2)").text().split(", ");
		
		$.each(listCourses, function(i, e) {
			if(e != "") {
				$("#select-courses option:contains('" +e.trim() +"')").prop("selected", true);
			}
		});

		_setTextAriaCoursesSelected($("#table-edit-room").find("#select-courses"));
	}
	
	function _setTextAriaCoursesSelected(parents) {
		var text = "";
		parents.find("option:selected").each(function() {
			text += $(this).text() +"; ";
		});
		console.log(text);
		parents.closest("td").find("#courses-selected").text(text);
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