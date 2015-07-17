$(document).ready(
	function() {

		var roomsData = $("#roomsData").text();
		var roomsJSON = JSON.parse(roomsData);

		var classesCoursesData = $("#classesCoursesData").text();
		var classesCoursesJSON = JSON.parse(classesCoursesData);
		
		var conflictDateSlots = [];
		
		var specialCourses = [];
		var positionOfSpecialRooms = [];
		_init();

		$("#btn-num-classes").on("click", function() {
			_showDialog("dialog-info-classes");
		});

		$("#btn-num-rooms").on("click", function() {

		});

		$("#select-classes").on("change", function() {
			var classId = classesCoursesJSON[$(this).find("option:selected").val()].classFPT.classId;
			$("#control-classes #semesterId").attr("value", _urlParam("semesterId"));
			$("#control-classes #classId").attr("value", classId);
			$("#control-classes").submit();
		});

		$("#select-rooms").on("change", function() {
			_setRoomForCourses();
		});
		
		$("select[id^='select-rooms-']").on("change", function () {
			_compareWithTimetablesRooms($(this).attr("id"));
			if (conflictDateSlots.length > 0) {
				var text = "<b>+</b> Timetable of Room " +$(this).find("option:selected").text() +" has "+conflictDateSlots.length +" slots conflict with Timetable of <br>&nbsp;&nbsp;Course " 
							+$(this).closest("tr td:first").text();
				$("#warning-room-arrangement").html(text);
				$("#warning").show();
			} else if(specialCourses.indexOf(courseCode) > 0) {
				var courseCode = $(this).closest("tr").find("td:nth-child(1)").text();
				var check = false;
				for(var i = 0; i < positionOfSpecialRooms.length; i++) {
					if(roomsJSON[positionOfSpecialRooms].courses.indexOf(courseCode) > 0) {
						check = true;
						break;
					}
				}
				
				if(!check) {
					$(this).css("border-color", "red");
					var text = $("#warning-room-arrangement").html() + "<br>" +
							   "<b>+</b> " +courseCode +" needs a Special Room!<br>" +
							   "&nbsp;&nbsp;Refer to 'SPECIAL ROOMS' for more information.";
					$(this).find("option:first").attr("selected", "selected");
					$("#warning-room-arrangement").html(text);
					$("#warning").show();
				} else {
					$("#warning-room-arrangement").html("");
					$("#warning").hide();
				}
			} else {
				$("#warning-room-arrangement").html("");
				$("#warning").hide();
			}
			
			_checkCustomRooms();
		});
		
		$("#btn-submit").on("click", function() {
			var classSelected = $("#select-classes option:selected").val(); 
			
			var check = true;
			for(var i = 0; i < classesCoursesJSON[classSelected].classCourseSemesters.length; i++) {
				var roomSelected = $("#select-rooms-" +classesCoursesJSON[classSelected].classCourseSemesters[i].classCourseSemesterId +" option:selected").val();
				if(classesCoursesJSON[classSelected].classCourseSemesters[i].timetable != null) {
					for(var x = 0; x < classesCoursesJSON[classSelected].classCourseSemesters[i].timetable.length; x++) {
						classesCoursesJSON[classSelected].classCourseSemesters[i].timetable[x].room = null;
						if(roomSelected != -1) {
							var room = roomsJSON[roomSelected];
							room.timetables = null;
							classesCoursesJSON[classSelected].classCourseSemesters[i].timetable[x].room = room;
						}	
					}
				}
			}
//			console.log(JSON.stringify(classesCoursesJSON));
			$("#dataToSet").attr("value", JSON.stringify(classesCoursesJSON));
			$("#setRooms").attr("action", "roomArrangement/updateTimetable");
			
			$("#setRooms").submit();
		});
		
		function _init() {

			$("#btn-num-classes").text(classesCoursesJSON.length);
			$("#btn-num-rooms").text(roomsJSON.length);
			$("#warning").hide();
			
			_setspecialCourses();
			
			_setTextNoteRoomCapacity();
			
			_setOptionSelectClasses();
			
			_setOptionSelectRooms();
			
			_setSelectRoomForCourses();
			
			
		}

		function _setOptionSelectClasses() {
			var classId =  _urlParam("classId");
			for (var i = 0; i < classesCoursesJSON.length; i++) {
				if(classesCoursesJSON[i].classFPT.classId == classId)
					$("#select-classes").append($("<option></option>")
						.attr("value", i)
						.attr("selected", "selected")
						.text(classesCoursesJSON[i].classFPT.code));
				else
					$("#select-classes").append($("<option></option>")
							.attr("value", i)
							.text(classesCoursesJSON[i].classFPT.code));
			}
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
				if(specialCourses.indexOf(courseCode) > -1) {
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
			
			_checkCustomRooms();
			
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
		
	function _setRoomForCourses() {
		var roomSelected = $("#select-rooms option:selected").val();
		_compareWithTimetablesRooms("select-rooms");
		if (conflictDateSlots.length > 0) {
			var text = "<b>+</b> Timetable of Room has "+conflictDateSlots.length +" slots conflict with Timetable of <br>&nbsp;&nbsp;Class " 
						+$("#select-classes option:selected").text();
			$("#warning-room-arrangement").html(text);
			$("#warning").show();
		} else {
			$("select[id^='select-rooms-']").each(function() {
				var courseCode = $(this).closest("tr").find("td:nth-child(1)").text();
				if(specialCourses.indexOf(courseCode) < 0) {
					$(this).find("option[value='"+roomSelected+"']").attr("selected", "selected");
				} else {
					$(this).css("border-color", "red");
					var text = $("#warning-room-arrangement").html() + "<br>" +
							   "<b>+</b> " +courseCode +" needs a Special Room!<br>" +
							   "&nbsp;&nbsp;Refer to 'SPECIAL ROOMS' for more information.";
					$("#warning-room-arrangement").html(text);
					$("#warning").show();
				}
			});
			$("#warning-room-arrangement").html("");
			$("#warning").hide();
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
			
	function _setspecialCourses() {
		for(var i = 0; i < roomsJSON.length; i++) {
			if(roomsJSON[i].courses != null) {
				positionOfSpecialRooms.push(i);
				var courses = roomsJSON[i].courses.split(", ");
				for(var x = 0; x < courses.length; x++) {
					if(specialCourses.indexOf(courses[x]) < 0) {
						specialCourses.push(courses[x]);
					}
				}
			}
		}
	}
	
	function _checkCustomRooms() {
		var classSelected = $("#select-classes option:selected").val();
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
	
	function _compareWithTimetablesRooms(id) {
		conflictDateSlots = [];
		var selectedClass = $("#select-classes option:selected").val();
		var selectedRoom = $("#" +id +" option:selected").val();
		var concflictRooms = [];
		if(roomsJSON[selectedRoom] != null && roomsJSON[selectedRoom].timetables != null && roomsJSON[selectedRoom].timetables.length > 0) {
			for(var x = 0; x < classesCoursesJSON[selectedClass].classCourseSemesters.length; x++) {
				if(classesCoursesJSON[selectedClass].classCourseSemesters[x].timetable.length > 0) {
					for(var y = 0; y < classesCoursesJSON[selectedClass].classCourseSemesters[x].timetable.length; y++) {
						for(var z = 0; z < roomsJSON[selectedRoom].timetables.length; z++) {
								if(classesCoursesJSON[selectedClass].classCourseSemesters[x].timetable[y].date == roomsJSON[selectedRoom].timetables[z].date 
										&& classesCoursesJSON[selectedClass].classCourseSemesters[x].timetable[y].slot 
										== roomsJSON[selectedRoom].timetables[z].slot) {
									concflictRooms.push(classesCoursesJSON[selectedClass].classCourseSemesters[x]);
									
									var dateSlot = new Object();
									dateSlot.date = roomsJSON[selectedRoom].timetables[z].date;
									dateSlot.slot = roomsJSON[selectedRoom].timetables[z].slot;
									dateSlot.course = classesCoursesJSON[selectedClass].classCourseSemesters[x].courseSemester.course.code;
									
									conflictDateSlots.push(dateSlot);
								}
							}
						}
					}
			}
		}
		
		return concflictRooms;
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