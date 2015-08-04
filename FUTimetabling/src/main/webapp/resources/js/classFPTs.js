$(document).ready(function() { 
	var table = $('#table-classes').DataTable({
		"lengthChange": false,
		"searching": true,
		"paging": true,
		"info": true,
		"pageLength":30
    });
	
	_init();
	$("#table-classes").on("click", "#a[id^='edit-class']", function() {
		_setDialogEditData($("#dialog-edit-class"), $(this).closest("tr"));
		_showDialog("dialog-edit-class");
	});

	$("#table-classes").on("click", "#a[id='courses-class']", function() {
		var tr = $(this).closest('tr');
        var row = table.row( tr );
 
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child().show();
            tr.addClass('shown');
        }
	});

	$("#btn-add-class").on("click", function() {
		_clearDialogData($("#dialog-add-class"));
		_showDialog("dialog-add-class");
	});
	
	$("#dialog-edit-class #btn-edit-save").on("click", function() {
		$("#form-edit-class").attr("action", "classFPTs/updateClassFPTs");
		$("#form-edit-class").submit();
	});
	
	$("#dialog-add-class #btn-add-save").on("click", function() {
		$("#form-add-class").attr("action", "classFPTs/updateClassFPTs");
		$("#form-add-class").submit();
	});
	
	$("#dialog-edit-class #btn-edit-cancel").on("click", function() {
		_showDialog("dialog-edit-class");
		_clearDialogData($("#dialog-edit-class"));
	});
	
	$("#dialog-add-class #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-class");
		_clearDialogData($("#dialog-add-class"));
	});
	
	$("#btn-add-from-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		_showDialog("dialog-add-file");
	});
	
	$("#table-add-class #select-courses").on("change", function() {
		_setTextAriaCoursesSelected($(this));
	});
	
	$("#table-edit-class #select-courses").on("change", function() {
		_setTextAriaCoursesSelected($(this));
	});
	
	function _init() {
		$("#select-semester").find("a[id='" +_urlParam("semesterId") +"']").addClass("active");
		
		var input = $("<input>").attr("type", "hidden")
        						.attr("name", "semesterId").val(_urlParam("semesterId"));
		$('#form-add-file').append($(input));
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#classId").attr("value", "-1");
		dialog.find("#classSemesterId").attr("value", "-1");
		dialog.find("#batch").attr("value", "");
		dialog.find("#batchChar").attr("value", "");
		dialog.find("#select-specializeds").find("option:first").attr("selected", "selected");
		dialog.find("#select-type").find("option:first").attr("selected", "selected");
		dialog.find("#select-semesters").find("option:first").attr("selected", "selected");
		dialog.find("#select-courses option").each(function() {
			$(this).removeAttr("selected");
		});
		
		_setTextAriaCoursesSelected(dialog.find("#select-courses"));
	}
	
	function _setDialogEditData(dialog, tr) {
		dialog.find("#classId").attr("value", tr.attr("data-classId"));
		dialog.find("#classSemesterId").attr("value", tr.attr("data-classSemesterId"));
		dialog.find("#batch").attr("value", tr.find("td:eq(1)").text());
		dialog.find("#batchChar").attr("value", tr.find("td:eq(2)").text());
		dialog.find("#select-semester-edit").find("option:contains('" +tr.find("td:eq(6)").text().trim() +"')").attr("selected", "selected");
		dialog.find("#select-specializeds").find("option:contains('" +tr.find("td:eq(3)").text().trim() +"')").attr("selected", "selected");
		dialog.find("#select-types").find("option:contains('" +tr.find("td:eq(4)").text().trim() +"')").attr("selected", "selected");
		
		var listCourses = tr.find("td:eq(5)").text().split(";");
		
		$.each(listCourses, function(i, e) {
			if(e != "") {
				console.log(e.trim());
				$("#select-courses option:contains('" +e.trim() +"')").prop("selected", true);
			}
		});

		_setTextAriaCoursesSelected($("#table-edit-class").find("#select-courses"));
	}
	
	function _setTextAriaCoursesSelected(parents) {
		var text = "";
		console.log(parents);
		parents.find("option:selected").each(function() {
			text += $(this).text() +"; ";
		});
		parents.closest("tr").find("#courses-selected").text(text);
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