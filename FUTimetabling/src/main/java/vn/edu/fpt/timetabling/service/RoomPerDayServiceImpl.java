package vn.edu.fpt.timetabling.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Teacher;
import vn.edu.fpt.timetabling.model.TeacherSemester;
import vn.edu.fpt.timetabling.model.Timetable;
import vn.edu.fpt.timetabling.utils.Const;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoomPerDayServiceImpl implements RoomPerDayService {

	@Autowired
	private TimetableService timetableService;
	@Autowired
	private RoomService roomService;

	@Override
	public List<Timetable> getListTimetableOfDay(String date) {
		// TODO Auto-generated method stub

		List<Timetable> timetableOfDayData = new ArrayList<Timetable>();

		SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE);
		
		try {
			Date d = sdf.parse(date);
			List<Timetable> listTimetable = timetableService
					.listTimetablesByDate(d);
			for (Timetable t : listTimetable) {
				ClassCourseSemester ccs = t.getClassCourseSemester();

				Timetable timetable = new Timetable();
				timetable.setTimeTableId(t.getTimeTableId());
				timetable.setDate(t.getDate());
				timetable.setSlot(t.getSlot());
				
				ClassCourseSemester classCourseSemester = new ClassCourseSemester();
				classCourseSemester.setClassCourseSemesterId(ccs
						.getClassCourseSemesterId());

				ClassSemester classSemester = new ClassSemester();
				classSemester.setClassSemesterId(ccs.getClassSemester()
						.getClassSemesterId());
				ClassFPT classFPT = new ClassFPT();
				classFPT.setClassId(ccs.getClassSemester().getClassFPT()
						.getClassId());
				classFPT.setCode(ccs.getClassSemester().getClassFPT().getCode());
				classSemester.setClassFPT(classFPT);

				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourseSemesterId(ccs.getCourseSemester()
						.getCourseSemesterId());
				Course course = new Course();
				course.setCourseId(ccs.getCourseSemester().getCourse()
						.getCourseId());
				course.setCode(ccs.getCourseSemester().getCourse().getCode());
				classSemester.setClassFPT(classFPT);
				courseSemester.setCourse(course);

				classCourseSemester.setClassSemester(classSemester);
				classCourseSemester.setCourseSemester(courseSemester);

				timetable.setClassCourseSemester(classCourseSemester);

				if (t.getRoom() != null) {
					Room room = new Room();
					room.setRoomId(t.getRoom().getRoomId());
					room.setCode(t.getRoom().getCode());
					timetable.setRoom(room);
				}

				if (t.getTeacherSemester() != null) {
					TeacherSemester teacher = new TeacherSemester();
					Teacher tea = new Teacher();
					tea.setAccount(t.getTeacherSemester().getTeacher()
							.getAccount());
					teacher.setTeacher(tea);
					timetable.setTeacherSemester(teacher);
				}
				timetableOfDayData.add(timetable);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timetableOfDayData;
	}

	@Override
	public HashMap<String, List<Room>> getListRoom() {
		// TODO Auto-generated method stub

		HashMap<String, List<Room>> listRooms = new HashMap<String, List<Room>>();

		List<Room> buildingA = new ArrayList<Room>();
		List<Room> hb = new ArrayList<Room>();
		listRooms.put("P", buildingA);
		listRooms.put("HB", hb);

		List<Room> rooms = roomService.listRooms(false);

		for (Room room : rooms) {
			Room r = new Room();
			r.setRoomId(room.getRoomId());
			r.setCode(room.getCode());
			r.setCapacity(room.getCapacity());
			r.setCourses(room.getCourses());

			if (r.getCode().charAt(0) == 'H') {
				listRooms.get("HB").add(r);
			} else {
				listRooms.get("P").add(r);
			}
		}

		return listRooms;
	}

	@Override
	public boolean saveRoom(int timetableId, int roomId) {
		// TODO Auto-generated method stub
		Timetable timetable = timetableService.getTimetableById(timetableId);
		timetable.setRoom(roomService.getRoomById(roomId, false));
		timetableService.updateTimetable(timetable);
		return true;
	}

}
