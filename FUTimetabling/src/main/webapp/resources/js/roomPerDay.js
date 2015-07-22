$(document).ready(function() {

	var roomsData = $("#roomsData").text();
	var roomsJSON = JSON.parse(roomsData);

	var timetableData = $("#timetableData").text();
	var timetableJSON = JSON.parse(timetableData);

	var buildingA = roomsJSON.P;
	var buildingB = roomsJSON.HB;
	
	_init();

	$("#table-remain-timetable").dataTable({
		"lengthChange": false,
		"searching": true,
		"paging": true,
		"pageLength": 5
	});
	
	$("#btn-remain-timetable").on("click", function() {
		_showDialog("dialog-remain-timetable");
	});
	
	$("#btn-cancel-remain-timetable").on("click", function() {
		_showDialog("dialog-remain-timetable");	
	});
	
	$("#table-remain-timetable tbody tr").on("click", function() {
		_showDialog("dialog-remain-timetable");	
		_setDataTableRemainRoom($(this).attr("data-position"), $(this).find("td:eq(2)").text());
		_showDialog("dialog-set-room");
	});
	
	$("#btn-cancel-set-room").on("click", function() {
		_showDialog("dialog-set-room");
	});
	
	$("#btn-cancel-submit-room").on("click", function() {
		_showDialog("dialog-set-room");
	});
	
	$("#btn-submit-set-room").on("click", function() {
		$("#form-set-room").attr("action", "roomPerDay/setRoom");
		$("#form-set-room").submit();
	});
	
	function _init() {
		$("#title").append("Rooms of ...");
		$("#datepicker").attr("data-preset", _urlParam("date"));
		
		_setTable();
		_setDataForCell();
		_setDialogRemainTimetable();
	}

	function _setTable() {
		var count = 0;
		if (buildingA.length >= buildingB.length) {
			count = buildingA.length;
		} else {
			count = buildingB.length;
		}

		for (var i = 0; i < count; i++) {
			$("#table-body").append(_getTRformat(i));
		}
	}
	
	function _getTRformat(position) {
		text = "<tr>";
		if (buildingA[position]) {
			text += "<th>" + buildingA[position].code + "</th>";
		} else {
			text += "<th></th>";
		}
		text += "<td></td><td></td><td></td><td></td><td></td><td></td>";
		if (buildingB[position]) {
			text += "<th>" + buildingB[position].code + "</th>";
		} else {
			text += "<th></th>";
		}
		text += "<td></td><td></td><td></td><td></td><td></td><td></td>";
		text += "</tr>"
		return text;
	}

	
	function _setDataForCell() { 
		if(timetableJSON != null) {
			for(var i = 0; i < timetableJSON.length; i++) {
				if(timetableJSON[i].room != null) {
					var roomCode = timetableJSON[i].room.code;
					var tr = $("#table-body").find("th:contains('"+roomCode+"')").closest("tr");
					var slot = timetableJSON[i].slot - 1;
					if(roomCode.charAt(0) == 'H') {
						var position = slot + 6;
						$(tr).find("td:eq("+positions+")").text(_getTextForCell(i));
					} else {
						$(tr).find("td:eq("+slot+")").html(_getTextForCell(i));
					}
				}
			}
		}
	}
	
	function _getTextForCell(position) {
		var text = timetableJSON[position].classCourseSemester.classSemester.classFPT.code +"<br>" +
					timetableJSON[position].classCourseSemester.courseSemester.course.code +"<br>";
		if(timetableJSON[position].teacherSemester != null) {
			text += timetableJSON[position].teacherSemester.teacher.account;
		}
		return text;
	}
	
	function _setDialogRemainTimetable() {
		var checkEmpty = true;
		if(timetableJSON != null) {
			for(var i = 0; i < timetableJSON.length; i++) {
				if(timetableJSON[i].room == null) {
					$("#remain-timetable").append(_getTRremainTimetable(i));
					checkEmpty = false;
				}
			}
		}
		
		if(checkEmpty) {
			$("#dialog-table").hide();
			$("#div-empty").show();
		} else {
			$("#dialog-table").show();
			$("#div-empty").hide();
		}
	}
	
	function _getTRremainTimetable(position) {
		var classCourseSemester = timetableJSON[position].classCourseSemester;
		var text = "<tr data-position='"+position+"'>" +
				"<td>" +classCourseSemester.classSemester.classFPT.code +"</td>" +
				"<td>" +classCourseSemester.courseSemester.course.code +"</td>" +
				"<td>" +timetableJSON[position].slot +"</td>" +
				"</tr>";
		return text;		
	}
	
	function _setDataTableRemainRoom(position, slot) {
		var timetable = timetableJSON[position];
		$("#table-set-room #timetableId").attr("value", timetable.timeTableId);
		$("#table-set-room #classCode").attr("value", timetable.classCourseSemester.classSemester.classFPT.code);
		$("#table-set-room #courseCode").attr("value", timetable.classCourseSemester.courseSemester.course.code);
		$("#table-set-room #slot").attr("value", timetable.slot);
		
		$("#table-rooms tr").each(function () {
			var td = $(this).find("td:eq("+ (slot-1) +")");
			if($(td).text() != null) {
				var th = $(this).find("th:eq(0)");
				if(th.text() != null) {
					$("#table-set-room #roomId").append(_getOptionForAvailableRoom($(this).index(), 0));	
				}
			}
		});
		
		$("#table-rooms td:eq("+ (slot+5) +")").each(function () {
			var td = $(this).find("td:eq("+ (slot+5) +")");
			if($(td).text() != null) {
				var th = $(this).find("th:eq(1)");
				if(th.text() != null) {
					$("#table-set-room #roomId").append(_getOptionForAvailableRoom($(this).index(), 1));	
				}
			}
		});	
	}
	
	function _getOptionForAvailableRoom(position, building) {
		if(building == 0) {
			return "<option value='"+buildingA[position].roomId +"'>" +buildingA[position].code  +"</option>";
		} else if (building == 1) { 
			return "<option value='"+buildingB[position].roomId +"'>" +buildingB[position].code  +"</option>";
		}
	}
	
	function _urlParam(param) {
		var url = $(location).attr('search').substring(1);
		var parameters = url.split('&');
		for (var i = 0; i < parameters.length; i++) {
			var parameter = parameters[i].split('=');
			if (parameter[0] == param) {
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