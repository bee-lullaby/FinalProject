$(document)
.ready(
	function() {

		var roomsData = $("#roomsData").text();
		var roomsJSON = JSON.parse(roomsData);

		var classesCoursesData = $("#classesCoursesData").text();
		var classesCoursesJSON = JSON.parse(classesCoursesData);
		
		var conflictDateSlots = [];
		
		var specialRooms = [];
		
		_init();

		$("#btn-num-classes").on("click", function() {
			_showDialog("dialog-info-classes");
		});

		$("#btn-num-rooms").on("click", function() {

		});

		$("#select-classes").on("change", function() {
			$("#courses-class").empty();
			sessionStorage.currentClass = $("#select-classes option:selected").val();
			_setSelectRoomForCourses();
		});

		$("#select-rooms").on("change", function() {
			_setRoomForCourses();
		});
		
		$("select[id^='select-rooms-']").on("change", function () {
			
		});
		
		$("#btn-submit").on("click", function() {
			var classSelected = $("#select-classes option:selected").val(); 
			
			var check = true;
			var classCourseSemesters = classesCoursesJSON[classSelected].classCourseSemesters;
			for(var i = 0; i < classCourseSemesters.length; i++) {
				var roomSelected = $("#select-rooms-" +classCourseSemesters[i].classCourseSemesterId +" option:selected").val();
				if(classCourseSemesters[i].timetable != null) {
					for(var x = 0; x < classCourseSemesters[i].timetable.length; x++) {
						if(roomSelected == -1) 
							classCourseSemesters[i].timetable[x].room = null;
						else 
							classCourseSemesters[i].timetable[x].room = roomsJSON[roomSelected];
					}
				}
			}
			$("#dataToSet").attr("value", JSON.stringify(classesCoursesJSON));
			$("#setRooms").attr("action", "roomArrangement/updateTimetable");
			
			$("#setRooms").submit();
		});
		
		function _init() {

			$("#btn-num-classes").text(classesCoursesJSON.length);
			$("#btn-num-rooms").text(roomsJSON.length);
			$("#warning").hide();
			$("#prevData").attr("value", JSON.stringify(classesCoursesJSON));
			
			if(typeof(Storage) !== "undefined") {
		        if (sessionStorage.currentClass) {
		             $("#select-classes").find("option[value='"+sessionStorage.currentClass+"']").attr("selected", "selected");
		        } else {
		            sessionStorage.currentClass = 0;
		        }
		    } else {
		    
		    }
			

			_setSpecialRooms();
			
			_setTextNoteRoomCapacity();
			
			_setOptionSelectClasses();
			
			_setOptionSelectRooms();
			
			_setSelectRoomForCourses();
			
			
		}

		function _setOptionSelectClasses() {
			for (var i = 0; i < classesCoursesJSON.length; i++) {
				$("#select-classes").append($("<option></option>")
					.attr("value", i)
					.text(classesCoursesJSON[i].classFPT.code));
			}
			
			$("#select-classes").find("option[value='"+sessionStorage.currentClass +"']").attr("selected","selected");
		}

		function _setOptionSelectRooms() {
			for (var i = 0; i < roomsJSON.length; i++) {
				$("#select-rooms").append($("<option></option>").attr("value", i)
					.text(roomsJSON[i].code));
			}
		}
		
		function _setSelectRoomForCourses() {
			var selectedClass = $("#select-classes option:selected").val();
			classCourseSemesters = classesCoursesJSON[selectedClass].classCourseSemesters;
			for (var x = 0; x < classCourseSemesters.length; x++) {
				$("#courses-class").append(_getTRCoursesClass(classCourseSemesters[x]))
				
				var courseCode = classCourseSemesters[x].courseSemester.course.code;
				var classCourseSemesterId = classCourseSemesters[x].classCourseSemesterId;
				if(specialRooms.indexOf(courseCode) > -1) {
					for (var i = 0; i < roomsJSON.length; i++) {
						if(roomsJSON[i].courses != null && roomsJSON[i].courses.length > 0 
								&& roomsJSON[i].courses.indexOf(courseCode) > -1) {
							$("#select-rooms-" + classCourseSemesterId).append($("<option></option>").attr("value", i)
								.text(roomsJSON[i].code));
						}
					}
				} else {
					for (var i = 0; i < roomsJSON.length; i++) {
							$("#select-rooms-" + classCourseSemesterId).append($("<option></option>").attr("value", i)
								.text(roomsJSON[i].code));
					}
				}
				
			}
			
			_setOptionSelectedRoomsForAllCourse();
		}
		
		
		// Use for select-rooms change
		function _setOptionSelectedRoomsForAllCourse() {
			var classSelected = $("#select-classes option:selected").val();
			for(var i = 0; i < classesCoursesJSON[classSelected].classCourseSemesters.length; i++) {
				if(classesCoursesJSON[classSelected].classCourseSemesters[i].timetable != null
						&& classesCoursesJSON[classSelected].classCourseSemesters[i].timetable.length > 0) {
					var room = classesCoursesJSON[classSelected].classCourseSemesters[i].timetable[0].room;
					var check = false;
					if(room != null) {
						for(var x = 0; x < roomsJSON.length; x++) {
							if(roomsJSON[x].roomId == room.roomId) {
								var id  = classesCoursesJSON[classSelected].classCourseSemesters[i].classCourseSemesterId;
								$("#select-rooms-" +id).find("option[value='"+x+"']").attr("selected", "selected");
								check = true;
								break;
							}
						}
					}
					if(!check) $("#select-rooms-" +id +" option:first").attr("selected", "selected");
				}
			}
			
			var check = true;
			var value = -1;
			var id = classesCoursesJSON[classSelected].classCourseSemesters[0].classCourseSemesterId;
			for(var i = 1; i < classesCoursesJSON[classSelected].classCourseSemesters.length; i++) {
				var idNext  = classesCoursesJSON[classSelected].classCourseSemesters[i].classCourseSemesterId;
				if($("#select-rooms-" +id +" option:selected").val() != $("#select-rooms-" +idNext +" option:selected").val()) {
					check = false;
					break;
				}
			}
			
			if(check) {
				value = $("#select-rooms-" +classesCoursesJSON[classSelected].classCourseSemesters[i - 1].classCourseSemesterId +" option:selected").val()
				$("#select-rooms").find("option[value='"+value+"']").attr("selected", "selected");
			} else {
				$("#select-rooms").find("option[value='custom']").attr("selected", "selected");
			}
			
		}
		
		function _setTextNoteRoomCapacity() {
			for (var i = 0; i < roomsJSON.length; i++) {
				if (roomsJSON[i].capacity > 30) {
					var text = $("#more-than-30").html();
					text += "<b>" + roomsJSON[i].code
					+ "</b> - Capacity: "
					+ roomsJSON[i].capacity;
					if (roomsJSON[i].capacity == 70)
						text += " (Only for classes have more than 32 students)";
					text += "\n";
					$("#more-than-30").html(text);
				}
				if (roomsJSON[i].courses != null
					&& roomsJSON[i].courses != " ") {
					var text = $("#courses-only").html();
				text += "<b>" + roomsJSON[i].code
				+ "</b>: " + roomsJSON[i].courses;
				text += "\n";
				$("#courses-only").html(text);
			}
		}
	}
	
	function _getTRCoursesClass(classCourseSemester) {
		var tr = "<tr>"
				+ "<td>"
				+ 	classCourseSemester.courseSemester.course.code
				+ "</td>"
				+ "<td><div class='input-control select'><select id='select-rooms-"
				+ classCourseSemester.classCourseSemesterId
				+ "'>"
				+ "<option value='-1'>...</option>"
				+ "</select></div></td>"
				+ "</tr>";
		return tr;
	}	
		
	function _setSpecialRooms() {
		for(var i = 0; i < roomsJSON.length; i++) {
			if(roomsJSON[i].courses != null) {
				var courses = roomsJSON[i].courses.split(", ");
				for(var x = 0; x < courses.length; x++) {
					if(specialRooms.indexOf(courses[x]) < 0) {
						specialRooms.push(courses[x]);
					}
				}
			}
		}
	}		
});