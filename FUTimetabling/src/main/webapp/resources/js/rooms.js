$(document).ready(function() { 

	init();
	
	
	$("#btn-room-per-day").on("click", function() {
		window.location = "roomPerDay";
	});
	
	$("#table-rooms").on("click", "a[id^='edit-room']", function() {
		setDialogEditData($("#dialog-edit-room"), $(this).closest("tr"));
		$("#dialog-edit-room").attr("data-action", "edit");
		showDialog("dialog-edit-room");
	});

	$("#table-rooms").on("click", "a[id^='delete-room']",function() {
		$("#dialog-delete-room").attr("data-roomId", $(this).closest("tr").attr("data-roomId"));
		showDialog("dialog-delete-room");
	});
	
	$("#btn-delete-accept").on("click", function() {
		window.location = "rooms/deleteRoom?roomId=" +$("#dialog-delete-room").attr("data-roomId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-room").removeAttr("data-roomId");
		showDialog("dialog-delete-room");
	});
	
	
	$("#btn-add-room").on("click", function() {
		clearDialogData($("#dialog-add-room"));
		showDialog("dialog-add-room");
	});
	
	$("#dialog-edit-room #btn-edit-save").on("click", function() {
		$("#form-edit-room").attr("action", "rooms/updateRoom");
		$("#form-edit-room").submit();
	});
	
	$("#dialog-add-room #btn-add-save").on("click", function() {
		if($("#dialog-add-room #code").val() == "") {
			$.Notify({type: 'alert', caption: 'Alert', content: "Your Code can not be empty!!!"});
			$("#dialog-add-room #code").css("border-color", "red");
		} else {
			$("#dialog-add-room #code").css("border-color", "");
			if($("#dialog-add-room #capacity").val() == "") {
				$.Notify({type: 'alert', caption: 'Alert', content: "Your Capacity can not be empty!!!"});
				$("#dialog-add-room #capacity").css("border-color", "red");
			} else {
				$("#form-add-room").attr("action", "rooms/updateRoom");
				$("#form-add-room").submit();
			}
		}
	});
	
	$("#dialog-edit-room #btn-edit-cancel").on("click", function() {
		showDialog("dialog-edit-room");
		$("#dialog-edit-room").removeAttr("data-action");
		
	});
	
	$("#dialog-add-room #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-room");
		clearDialogData($("#dialog-add-room"));
		$("#dialog-add-room #code").css("border-color", "");
		$("#dialog-add-room #capacity").css("border-color", "");
	});
	
	$("#btn-add-from-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	function init() {
		var table = $('#table-rooms').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 30
	    });
		$("#select-semester").find("a[id='" +urlParam("semesterId") +"']").addClass("active");
		
		var input = $("<input>").attr("type", "hidden")
        						.attr("name", "semesterId").val(urlParam("semesterId"));
		$('#form-add-file').append($(input));
		
	}
	
	function clearDialogData(dialog) {
		dialog.find("#roomId").val("-1");
		dialog.find("#code").val("");
		dialog.find("#code").attr("readonly", false);
		dialog.find("#capacity").val("");
		dialog.find("#select-building option:first").attr("selected", "selected");
		dialog.find("#select-courses option").each(function() {
			$(this).removeAttr("selected");
		});
		
		setTextAriaCoursesSelected(dialog.find("#select-courses"));
	}
	
	function setDialogEditData(dialog, tr) {
		dialog.find("#roomId").val(tr.attr("data-roomId"));
		dialog.find("#code").val(tr.find("td:eq(0)").text());
		dialog.find("#code").attr("readonly", true);
		dialog.find("#capacity").val(tr.find("td:eq(1)").text());
		dialog.find("#select-building option").filter(function() {
		            return $(this).text() === tr.find("td:eq(2)").text().trim()
		        }).attr("selected", "selected");
		
		
		var listCourses = tr.find("td:eq(3)").text().split(", ");
		
		$.each(listCourses, function(i, e) {
			if(e != "") {
				$('#select-courses option').filter(function() {
		             return $(this).text() == e.trim()
		        }).prop("selected", true);
			}
		});

		setTextAriaCoursesSelected($("#table-edit-room").find("#select-courses"));
	}
	
	function setTextAriaCoursesSelected(parents) {
		var text = "";
		parents.find("option:selected").each(function() {
			text += $(this).text() +"; ";
		});
		console.log(text);
		parents.closest("td").find("#courses-selected").text(text);
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