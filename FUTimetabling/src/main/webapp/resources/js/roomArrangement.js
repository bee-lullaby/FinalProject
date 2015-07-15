$(document).ready(function(){	
	
	var roomsData = $("#roomsData").text();
	var roomsJSON = JSON.parse(roomsData);
	
	var classesCoursesData = $("#classesCoursesData").text();
	var classesCoursesJSON = JSON.parse(classesCoursesData);
	
	_init();
	
	$("#btn-num-classes").on("click", function(){
		_showDialog("dialog-info-classes");
	});

	$("#btn-num-rooms").on("click", function() {
		
	});
	
	$("#select-classes").on("change", function() {
		$("#courses-class").empty();
		_setCoursesClass();
	});
	
	function _init() {
		
		$("#btn-num-classes").text(classesCoursesJSON.length);
		$("#btn-num-rooms").text(roomsJSON.length);
		
		_setOptionSelectClasses();
		
		_setOptionSelectRooms("select-rooms");
		
		_setCoursesClass();
		
		_setTextNote()
	}
	
	function _setOptionSelectClasses() {
		for(var i = 0; i < classesCoursesJSON.length; i++) {
			$('#select-classes').append($("<option></option>")
					.attr("value", i)
					.text(classesCoursesJSON[i].classFPT.code));
		}
	}
	
	function _setOptionSelectRooms(id) {
		for(var i = 0; i < classesCoursesJSON.length; i++) {
			$('#' +id).append($("<option></option>")
					.attr("value", i)
					.text(roomsJSON[i].code));
		}
	}
	
	function _setTextNote() {
		for(var i = 0; i < roomsJSON.length; i++) {
			if(roomsJSON[i].capacity > 30) {
				var text = $("#more-than-30").html();				
				text += "<b>" +roomsJSON[i].code +"</b> - Capacity: " +roomsJSON[i].capacity;
				if(roomsJSON[i].capacity == 70) 
					text += " (Only for classes have more than 32 students)";
				text += "\n";
				$("#more-than-30").html(text);
			}
			if(roomsJSON[i].classes != null && roomsJSON.classes != " ") {
				var text = $("#courses-only").html();				
				text += "<b>" +roomsJSON[i].code +"</b>: " +roomsJSON[i].classes;
				text += "\n";
				$("#courses-only").html(text);
			}
		}
	}
	
	function _setCoursesClass() {
		var selectedClass = $("#select-classes option:selected").index();
		var coursesClass;
		console.log(selectedClass);
		if(classesCoursesJSON[selectedClass].classCourseSemesters) {
			coursesClass = classesCoursesJSON[selectedClass].classCourseSemesters;
			for(var x = 0; x < coursesClass.length; x++) {
				$("#courses-class").append(_getTRCoursesClass(coursesClass[x]))
				_setOptionSelectRooms(coursesClass[x].classCourseSemesterId);
				
			}
		}
		
	}
	
	function _getTRCoursesClass(coursesClass) {
		var tr = "<tr>" +
				"<td>"+coursesClass.courseSemester.course.code+"</td>" +
				"<td><div class='input-control select'><select id='"+coursesClass.classCourseSemesterId +"'></select></div></td>" +
				"</tr>";
		return tr;
	}
});