$(document).ready(function() { 
	_init();
	
	$("#table-mergeClasses").on("click", "a[id^='delete-mergeClass']",function() {
		$("#dialog-delete-mergeClass").attr("data-mergeClassId", $(this).closest("tr").attr("data-mergeClassId"));
		_showDialog("dialog-delete-mergeClass");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "mergeClasses/deleteMergeClass?mergeClassId=" +$("#dialog-delete-mergeClass").attr("data-mergeClassId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-mergeClass").removeAttr("data-mergeClassId");
		_showDialog("dialog-delete-mergeClass");
	});
	
	$("#btn-add-mergeClass").on("click", function() {
		_clearDialogData($("#dialog-add-mergeClass"));
		_setDataSelect($("#dialog-add-mergeClass").find("#select-course"));
		_showDialog("dialog-add-mergeClass");
	});
	
	$("#dialog-add-mergeClass #btn-add-save").on("click", function() {
		if($("#dialog-add-mergeClass #select-class-1 option:selected").val() 
				== $("#dialog-add-mergeClass #select-class-2 option:selected").val()) {
			$.Notify({type: 'alert', caption: 'Alert', content: "2 classes can not be same!!!"});
			$("#dialog-add-mergeClass #select-class-1").css("border-color", "red");
			$("#dialog-add-mergeClass #select-class-2").css("border-color", "red");
		} else {
			$("#form-add-mergeClass").attr("action", "mergeClasses/addMergeClass");
			$("#form-add-mergeClass").submit();
		}
	});
	
	$("#dialog-add-mergeClass #btn-add-cancel").on("click", function() {
		_showDialog("dialog-add-mergeClass");
		_clearDialogData($("#dialog-add-mergeClass"));
		$("#dialog-add-mergeClass #select-class-1").css("border-color", "");
		$("#dialog-add-mergeClass #select-class-2").css("border-color", "");
	});
	
	$("#dialog-add-mergeClass #select-course").on("change", function() {
		$("#dialog-add-mergeClass").find("#select-class-1 option:first").attr("selected", "selected");
		$("#dialog-add-mergeClass").find("#select-class-2 option:first").attr("selected", "selected");
		$("#dialog-add-mergeClass").find("select[id^='select-class'] option").hide();
		_setDataSelect($("#dialog-add-mergeClass").find("#select-course"));
	});
	
	function _init() {
		var table = $('#table-mergeClasses').DataTable({
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"info": true,
			"pageLength": 30
	    });
		
		$("#select-semester").find("a[id='" +_urlParam("semesterId") +"']").closest("li").addClass("active");
		
	}
	
	function _clearDialogData(dialog) {
		dialog.find("#select-course option:first").attr("selected", "selected");
		dialog.find("#select-class-1 option:first").attr("selected", "selected");
		dialog.find("#select-class-2 option:first").attr("selected", "selected");
		dialog.find("select[id^='select-class'] option").hide();
	}
	
	function _setDataSelect(select) {
		$("select[id^='select-class-'] option:first").hide();
		var courseSemesterId = $(select).find("option:selected").val();
		$("select[id^='select-class-'] option").filter(function() {
	        return $(this).attr("data-courseSemesterId") === courseSemesterId
	    }).show();
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