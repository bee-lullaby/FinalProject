$(document).ready(function() { 
	var table = $('#table-classes').DataTable({
		"lengthChange": false,
		"searching": true,
		"paging": true,
		"info": true,
		"pageLength":30
    });
	
	init();
	
	$("#table-classes").on("click", "a[id^='delete-class']",function() {
		$("#dialog-delete-class").attr("data-classId", $(this).closest("tr").attr("data-classId"));
		showDialog("dialog-delete-class");
	});

	$("#btn-delete-accept").on("click", function() {
		window.location = "classFPTs/deleteClassFPT?classId=" +$("#dialog-delete-class").attr("data-classId");
	});
	
	$("#btn-delete-decline").on("click", function() {
		$("#dialog-delete-course").removeAttr("data-courseId");
		showDialog("dialog-delete-course");
	});
	
	$("#btn-add-class").on("click", function() {
		clearDialogData($("#dialog-add-class"));
		showDialog("dialog-add-class");
	});
	
	$("#dialog-add-class #btn-add-save").on("click", function() {
		$("#form-add-class").attr("action", "classFPTs/updateClassFPTs");
		$("#form-add-class").submit();
	});
	
	$("#dialog-add-class #btn-add-cancel").on("click", function() {
		showDialog("dialog-add-class");
		clearDialogData($("#dialog-add-class"));
	});
	
	$("#btn-add-from-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#btn-cancel-add-file").on("click", function() {
		showDialog("dialog-add-file");
	});
	
	$("#table-add-class #select-courses").on("change", function() {
		setTextAriaCoursesSelected($(this));
	});
	
	$("#table-classes").on("click", "#courses-class", function() {
		var td = $(this).closest("td");
		setDataDialogClassCourse(td);
		$("#dialog-class-course").attr("data-classId", $(td).closest("tr").attr("data-classId")); 
		showDialog("dialog-class-course");
	});
	
	$("#dialog-class-course").on("click", "#btn-cancel", function() {
		clearDataDialogClassCourse();
		showDialog("dialog-class-course");
		$("#dialog-class-course").removeAttr("data-classId");
	});
	
	function init() {
		$("#select-semester").find("a[id='" +urlParam("semesterId") +"']").closest("li").addClass("active");
		
		var input = $("<input>").attr("type", "hidden")
        						.attr("name", "semesterId").val(urlParam("semesterId"));
		$('#form-add-file').append($(input));
		
		

		var $options = $("#dialog-add-class").find("#select-courses option").clone();
		$("#table-class-course #select-course").append($options);
	}
	
	function clearDialogData(dialog) {
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
		
		setTextAriaCoursesSelected(dialog.find("#select-courses"));
	}
	
	function setTextAriaCoursesSelected(parents) {
		var text = "";
		console.log(parents);
		parents.find("option:selected").each(function() {
			text += $(this).text() +"; ";
		});
		parents.closest("tr").find("#courses-selected").text(text);
	}
	
	function setDataDialogClassCourse(td) {
		var tr = $(td).closest("tr");
		$("#class-name").text($(tr).find("td:eq(0)").text());
		
		var dataCourses = $(td).find("#data-courses");
		var classCourses = $(dataCourses).find("div[id^='class-course']");
		var tr = $("#dialog-class-course tr[id^='course']");
		for(var i = 0; i < classCourses.length; i++) {
			console.log($(classCourses[i]).find("#name").text().trim());
			$(tr[i]).find("#select-course option").filter(function() {
	            return $(this).text() === $(classCourses[i]).find("#name").text().trim()
	        }).attr("selected", "selected");
			
			$(tr[i]).find("#blockCondition option").filter(function() {
	            return $(this).val() === $(classCourses[i]).find("#blockCondition").text().trim()
	        }).attr("selected", "selected");;
	        	
	        $(tr[i]).find("#semesterLong option").filter(function() {
				return $(this).val() === $(classCourses[i]).find("#semesterLong").text().trim();
	        }).attr("selected", "selected");
	        
		}
	}
	
	function clearDataDialogClassCourse() {
		$.each($("#dialog-class-course #select-course"), function() {
			$(this).find("option:first").attr("selected", "selected");
		});
		$.each($("#dialog-class-course #blockCondition"), function() {
			$(this).find("option:first").attr("selected", "selected");
		});
		$.each($("#dialog-class-course #semesterLong"), function() {
			$(this).find("option:first").attr("selected", "selected");
		});
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