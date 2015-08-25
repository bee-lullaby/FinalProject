$(document).ready(
	function() {

		var roomsData = $("#roomsData").text();
		var roomsJSON = JSON.parse(roomsData);

		var classesCoursesData = $("#classesCoursesData").text();
		var classesCoursesJSON = JSON.parse(classesCoursesData);
		
		var dataRoomArrangements = $("#dataRoomArrangements").text();
		var dataRoomArrangementsJSON = JSON.parse(dataRoomArrangements);
		
		var conflictDateSlots = [];
		
		var specialCourses = [];
		var positionOfSpecialRooms = [];
		_init();
		
		$("#table-info-classes").dataTable( {
			"lengthChange": false,
			"searching": true,
			"paging": true,
			"pageLength": 5
	    });

		$("#btn-num-classes").on("click", function() {
			_showDialog("dialog-info-classes");
		});

		$("#select-classes").on("change", function() {
			var classId = classesCoursesJSON[$(this).find("option:selected").val()].classFPT.classId;
			$("#control-classes #semesterId").attr("value", _urlParam("semesterId"));
			$("#control-classes #classId").attr("value", classId);
			$("#control-classes").submit();
		});

		$("#select-rooms").on("change", function() {
			$("#warning-room-arrangement").html("");
			$("#warning").hide();
			_setRoomForCourses();
		});
		
//		$("select[id^='select-rooms-']").on("change", function () {
////			_compareWithTimetablesRooms($(this).attr("id"));
//
////			if (conflictDateSlots.length > 0) {
////				var text = "<b>+</b> Timetable of Room " +$(this).find("option:selected").text() +" has conflict with Timetable of "; 
////				for(var i = 0; i < conflictDateSlots.length; i++) {
////					text += conflictDateSlots[i] +" ";
////				}
////				$("#warning-room-arrangement").html(text);
////				$("#warning").show();
////				$(this).find("option:first").attr("selected", "selected");
////				$(this).attr("data-error", "error");
////				_setOptionSelectedRoomsForAllCourse();
////			} else if(specialCourses.indexOf(courseCode) > 0) {
//				var courseCode = $(this).closest("tr").find("td:nth-child(1)").text();
//				var check = false;
//				for(var i = 0; i < positionOfSpecialRooms.length; i++) {
//					if(roomsJSON[i].courses.indexOf(courseCode) > 0) {
//						check = true;
//						break;
//					}
//				}
//				
//				if(!check) {
//					$(this).css("border-color", "red");
//					var text = $("#warning-room-arrangement").html() + "<br>" +
//							   "<b>+</b> " +courseCode +" needs a Special Room!<br>" +
//							   "&nbsp;&nbsp;Refer to 'SPECIAL ROOMS' for more information.";
//					$(this).find("option:first").attr("selected", "selected");
//					$("#warning-room-arrangement").html(text);
//					$("#warning").show();
//					$(this).attr("data-error", "error");
//				} else {
//					if($(this).attr("data-error") == "error") {
//						$("#warning-room-arrangement").html("");
//						$("#warning").hide();
//						$(this).attr("data-error", "");
//						if($(this).css("border-color"))
//							$(this).css("border-color", "");
//					}
//				}
////			} else {
////				if($(this).attr("data-error") == "error") {
////					$("#warning-room-arrangement").html("");
////					$("#warning").hide();
////					$(this).attr("data-error", "");
////					if($(this).css("border-color"))
////						$(this).css("border-color", "");
////				}
////			}
////			
//			_checkCustomRooms();
//		});
		
		$("#btn-submit").on("click", function() {
			
			var checkError = false;
			var classCourseSemesters = {};
			$.each(classesCoursesJSON, function(value, object) {
				if(object.classSemesterId == _urlParam("classSemesterId"))
					classCourseSemesters = object.classCourseSemesters;
			});
			for(var i = 0; i < classCourseSemesters.length; i++) {
				var id = "select-rooms-" +classCourseSemesters[i].classCourseSemesterId;
				if($("#" +id).attr("data-error")) {
					checkError = true;
					break;
				}
			}
			if(checkError) {
				 _showDialog("dialog-error");
			} else {
				var check = true;
				for(var i = 0; i < classCourseSemesters.length; i++) {
					var roomSelected = $("#select-rooms-" +classCourseSemesters[i].classCourseSemesterId +" option:selected").val();
					if(classCourseSemesters[i].timetable != null) {
						for(var x = 0; x < classCourseSemesters[i].timetable.length; x++) {
							classCourseSemesters[i].timetable[x].room = null;
							if(roomSelected != -1) {
								var room = new Object();
								room.roomId = roomsJSON[roomSelected].roomId;
								room.code = roomsJSON[roomSelected].code;
								classCourseSemesters[i].timetable[x].room = room;
							}
						}
					}
				}
				$("#dataToSet").attr("value", JSON.stringify(classesCoursesJSON));
				$("#setRooms").attr("action", "roomArrangement/updateTimetable");
				$("#setRooms").submit();
			}
		});
		
		$("#btn-cancel-info-classes").on("click", function() {
			_showDialog("dialog-info-classes");
		});
		
		$("#btn-cancel-error").on("click", function() {
			_showDialog("dialog-error");
		});
		
		function _init() {

			// Set button.
			var count = 0;
			for(var i = 0; i < dataRoomArrangementsJSON.length; i++) {
				if(dataRoomArrangementsJSON[i].setRoomSuccessful == true) {
					count ++;
				}
			}

			$("#btn-num-classes").text(count +"/" +classesCoursesJSON.length);
			$("#btn-num-rooms").text(roomsJSON.length);
			$("#warning").hide();
			
			_setspecialCourses();
			
			_setTextNoteRoomCapacity();
//			
//			_setOptionSelectClasses();
			
			_setOptionSelectRooms();
			
			_setSelectRoomForCourses();
			
			_setDialogInfoClasses();
			 

			$("#select-semesters").find("a[id='" +_urlParam("semesterId") +"']").addClass("active");

			$("#select-classes").find("a[id='" +_urlParam("classSemesterId") +"']").addClass("active");
			$("#className").text($("#select-classes").find("a[id='" +_urlParam("classSemesterId") +"']").text());
			$("#select-classes a").each(function() {
				$(this).attr("href", "?semesterId=" + _urlParam("semesterId") +"&classSemesterId=" +$(this).attr("id"));
			});
		}

//		function _setOptionSelectClasses() {
//			var classId =  _urlParam("classId");
//			for (var i = 0; i < classesCoursesJSON.length; i++) {
//				if(classesCoursesJSON[i].classFPT.classId == classId)
//					$("#select-classes").append($("<option></option>")
//						.attr("value", i)
//						.attr("selected", "selected")
//						.text(classesCoursesJSON[i].classFPT.code));
//				else
//					$("#select-classes").append($("<option></option>")
//							.attr("value", i)
//							.text(classesCoursesJSON[i].classFPT.code));
//			}
//		}

		function _setOptionSelectRooms() {
			var classCourseSemesters = {};
			$.each(classesCoursesJSON, function(value, object) {
				if(object.classSemesterId == _urlParam("classSemesterId"))
					classCourseSemesters = object.classCourseSemesters;
			});
			var listRoomsCantSet = [];
			$.each(classCourseSemesters, function(value, object) {
				var roomsCantSet = _checkRoomsCantSet(object);
				for(var i = 0; i < roomsCantSet.length; i++) {
					if(listRoomsCantSet.indexOf(roomsCantSet[i]) < 0) {
						listRoomsCantSet.push(roomsCantSet[i])
					}
				}
			});
			
			for (var i = 0; i < roomsJSON.length; i++) {
				if(listRoomsCantSet.indexOf(roomsJSON[i].roomId) < 0) {
					$("#select-rooms").append($("<option></option>").attr("value", i)
						.text(roomsJSON[i].code));
				}
			}
		}
		
		function _setSelectRoomForCourses() {
			var classCourseSemesters = {};
			$.each(classesCoursesJSON, function(value, object) {
				if(object.classSemesterId == _urlParam("classSemesterId"))
					classCourseSemesters = object.classCourseSemesters;
			});
			for (var x = 0; x < classCourseSemesters.length; x++) {
				$("#courses-class").append(_getTRCoursesClass(classCourseSemesters[x]))
				var roomsCantSet = _checkRoomsCantSet(classCourseSemesters[x]);
				var courseCode = classCourseSemesters[x].courseSemester.course.code;
				var classCourseSemesterId = classCourseSemesters[x].classCourseSemesterId;
				if(specialCourses.indexOf(courseCode) > -1) {
					for (var i = 0; i < roomsJSON.length; i++) {
						if(roomsJSON[i].courses != null && roomsJSON[i].courses.length > 0 
								&& roomsJSON[i].courses.indexOf(courseCode) > -1) {
							if(roomsCantSet.indexOf(roomsJSON[i].roomId) < 0) {
								$("#select-rooms-" + classCourseSemesterId).append($("<option></option>").attr("value", i)
									.text(roomsJSON[i].code));
							}
						}
					}
				} else {
					for (var i = 0; i < roomsJSON.length; i++) {
						if(roomsCantSet.indexOf(roomsJSON[i].roomId) < 0) {
							$("#select-rooms-" + classCourseSemesterId).append($("<option></option>").attr("value", i)
								.text(roomsJSON[i].code));
						}
					}
				}
				
			}
			
			_setOptionSelectedRoomsForAllCourse();
		}
		
		
		// Use for select-rooms change
		function _setOptionSelectedRoomsForAllCourse() {
			var classCourseSemesters = {};
			$.each(classesCoursesJSON, function(value, object) {
				if(object.classSemesterId == _urlParam("classSemesterId"))
					classCourseSemesters = object.classCourseSemesters;
			});
			for(var i = 0; i < classCourseSemesters.length; i++) {
				if(classCourseSemesters[i].timetable != null
						&& classCourseSemesters[i].timetable.length > 0) {
					var room = classCourseSemesters[i].timetable[0].room;
					var check = false;
					if(room != null) {
						var id  = classCourseSemesters[i].classCourseSemesterId;
						for(var x = 0; x < roomsJSON.length; x++) {
							if(roomsJSON[x].roomId == room.roomId) {
								$("#select-rooms-" +id).find("option[value='"+x+"']").attr("selected", "selected");
								check = true;
							}
						}
						if(!check) $("#select-rooms-" +id +" option:first").attr("selected", "selected");
					}
					
				}
			}
			_checkCustomRooms();
			
		}
		
		
		
	function _setRoomForCourses() {
		var roomSelected = $("#select-rooms option:selected").val();
		
	
		$("select[id^='select-rooms-']").each(function() {
			var courseCode = $(this).closest("tr").find("td:nth-child(1)").text();
			if(specialCourses.indexOf(courseCode) < 0) {
				$(this).find("option[value='"+roomSelected+"']").attr("selected", "selected");
			} else {
				var valueSelected = $(this).find("option:selected").val();
				if(valueSelected == "-1" ) {
					$(this).css("border-color", "red");
					$(this).attr("data-error", "error");
					var text = $("#warning-room-arrangement").html() + "<br>" +
							   "<b>+</b> " +courseCode +" needs a Special Room!<br>" +
							   "&nbsp;&nbsp;Refer to 'SPECIAL ROOMS' for more information.";
					$("#warning-room-arrangement").html(text);
					$("#warning").show();
				}
			}
		});

	}
	
	function _getTRCoursesClass(classCourseSemester) {
		var tr = "<tr>"
				+ "<td " 
				+ "data-courseSemesterId='" +classCourseSemester.courseSemester.courseSemesterId +"'>"
				+ 	classCourseSemester.courseSemester.course.code
				+ "</td>"
				+ "<td><div class='input-control select'><select id='select-rooms-"
				+ classCourseSemester.classCourseSemesterId + "'>"
				+ "<option value='-1'>...</option>"
				+ "</select></div></td>"
				+ "</tr>";
		return tr;
	}	
			
	
	
	function _checkCustomRooms() {
		var classCourseSemesters = {};
		$.each(classesCoursesJSON, function(value, object) {
			if(object.classSemesterId == _urlParam("classSemesterId"))
				classCourseSemesters = object.classCourseSemesters;
		});
		var check = true;
		var value = -1;
		var id = classCourseSemesters[0].classCourseSemesterId;
		for(var i = 1; i < classCourseSemesters.length; i++) {
			var idNext  = classCourseSemesters[i].classCourseSemesterId;
			if($("#select-rooms-" +id +" option:selected").val() != $("#select-rooms-" +idNext +" option:selected").val()) {
				check = false;
				break;
			}
		}
		
		if(check) {
			value = $("#select-rooms-" +id +" option:selected").val();
			$("#select-rooms").find("option[value='"+value+"']").attr("selected", "selected");
		} else {
			$("#select-rooms").find("option[value='custom']").attr("selected", "selected");
		}
	}
//	
//	function _compareWithTimetablesRooms(id) {
//		conflictDateSlots = [];
//		var selectedRoom = $("#" +id +" option:selected").val();
//		var concflictRooms = [];
//		var classCourseSemesters = [];
//		$.each(classesCoursesJSON, function(value, object) {
//			if(object.classSemesterId == _urlParam("classSemesterId")) {
//				$.merge(classCourseSemesters, object.classCourseSemesters);
//			}
//		});
//		if(roomsJSON[selectedRoom] != null && roomsJSON[selectedRoom].timetables != null && roomsJSON[selectedRoom].timetables.length > 0) {
//			for(var x = 0; x < classCourseSemesters.length; x++) {
//				if(classCourseSemesters[x].timetable.length > 0) {
//					for(var y = 0; y < classCourseSemesters[x].timetable.length; y++) {
//						for(var z = 0; z < roomsJSON[selectedRoom].timetables.length; z++) {
//								if(classCourseSemesters[x].timetable[y].date == roomsJSON[selectedRoom].timetables[z].date 
//										&& classCourseSemesters[x].timetable[y].slot 
//										== roomsJSON[selectedRoom].timetables[z].slot) {
//									var roomId = classCourseSemesters[x].timetable[y].room.roomId;
//									if(concflictRooms.indexOf(roomId) < 0) {
//										concflictRooms.push(roomId);
//									}
//									var classCode = roomsJSON[selectedRoom].timetables[z].classCourseSemester.classSemester.classFPT.code;
//									if(conflictDateSlots.indexOf(classCode) < 0) {
//										conflictDateSlots.push(classCode);
//									}
//								}
//							}
//						}
//					}
//			}
//		}
//		
//		return concflictRooms;
//	}
	
	function _checkRoomsCantSet(classCourseSemester) {
		var listRoomsCantSet = [];
		for(var y = 0; y < classCourseSemester.timetable.length; y++) {
			for(var r = 0; r < roomsJSON.length; r++) {
				for(var z = 0; z < roomsJSON[r].timetables.length; z++) {
						var roomId = roomsJSON[r].roomId;
						if(classCourseSemester.timetable[y].date == roomsJSON[r].timetables[z].date 
								&& classCourseSemester.timetable[y].slot == roomsJSON[r].timetables[z].slot) {
							if(classCourseSemester.timetable[y].room != null 
									&& classCourseSemester.timetable[y].room.roomId == roomId) {
								break;
							}
							if(listRoomsCantSet.indexOf(roomId) < 0) {
								listRoomsCantSet.push(roomId);
							}
						}
				}
			}
		}
		return listRoomsCantSet;
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
	
	function _setDialogInfoClasses() {
		for(var i = 0; i < dataRoomArrangementsJSON.length; i++) {
			$("#info-classes").append(_getTRDialogInfoClasses(i));
		}
	}
	
	function _getTRDialogInfoClasses(position) {
		var tr = "<tr>" +
				"<td>" +dataRoomArrangementsJSON[position].classSemester.classFPT.code +"</td>";
		if (dataRoomArrangementsJSON[position].setRoomSuccessful) {
			tr += "<td>Yes</td>"
		} else {
			tr += "<td>No</td>";
		}
		
		tr += "<td>" +dataRoomArrangementsJSON[position].numberOfSlots +"</td>" +
			"<td>" +(dataRoomArrangementsJSON[position].numberOfSlots - dataRoomArrangementsJSON[position].numberOfSlots_WereSetSuccessful) +"</td>" +
			"<td></td>";
		
		return tr;
	}
});