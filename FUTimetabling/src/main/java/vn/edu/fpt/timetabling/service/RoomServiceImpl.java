package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.RoomDAO;
import vn.edu.fpt.timetabling.model.Building;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Room;
import vn.edu.fpt.timetabling.model.Timetable;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoomServiceImpl implements RoomService {
	private RoomDAO roomDAO;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseSemesterService courseSemesterService;
	@Autowired
	private ClassCourseSemesterService classCourseSemesterService;
	@Autowired
	private ClassCourseSemesterMergeService classCourseSemesterMergeService;
	
	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	@Override
	public void addRoom(Room room) {
		roomDAO.addRoom(room);
	}

	@Override
	public List<String> addRoomFromFile(File rooms) throws IOException {
		FileInputStream file = new FileInputStream(rooms);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();

		List<String> checkBuildingExisted = new ArrayList<String>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			String buildingCode = row.getCell(4).getStringCellValue().trim();
			Building building = buildingService.getBuildingByCode(buildingCode);
			if (building == null) {
				checkBuildingExisted.add(buildingCode);
			} else {
				String code = row.getCell(0).getStringCellValue().trim();
				Room r = roomDAO.getRoomByCode(code, false);

				if (r != null) {
					Double capacity = row.getCell(2).getNumericCellValue();
					r.setCapacity(capacity.intValue());
					if (row.getCell(3) != null && row.getCell(3).getStringCellValue().trim().compareTo("") != 0) {
						r.setCourses(row.getCell(3).getStringCellValue().trim());
					}
					r.setBuilding(building);
					roomDAO.addRoom(r);
				} else {
					r = new Room();
					r.setCode(code);
					Double capacity = row.getCell(2).getNumericCellValue();
					r.setCapacity(capacity.intValue());
					if (row.getCell(3) != null && row.getCell(3).getStringCellValue().trim().compareTo("") != 0) {
						r.setCourses(row.getCell(3).getStringCellValue().trim());
					}
					r.setBuilding(building);
					roomDAO.addRoom(r);

				}
			}
		}
		workbook.close();
		file.close();
		return checkBuildingExisted;
	}

	@Override
	public void updateRoom(Room room) {
		roomDAO.updateRoom(room);
	}

	@Override
	public List<Room> listRooms(boolean jointTimetable) {
		return roomDAO.listRooms(jointTimetable);
	}

	@Override
	public Room getRoomById(int roomId, boolean jointTimetable) {
		return roomDAO.getRoomById(roomId, jointTimetable);
	}

	@Override
	public Room getRoomByCode(String code, boolean jointTimetable) {
		return roomDAO.getRoomByCode(code, jointTimetable);
	}

	@Override
	public void deleteRoom(int roomId) {
		roomDAO.deleteRoom(roomId);
	}

	@Override
	public Map<Course, Map<String, Room>> getCourseRoomMap() {
		List<Room> rooms = listRooms(false);
		Map<Course, Map<String, Room>> courseRoomMap = new HashMap<>();
		for (Room room : rooms) {
			String coursesString = room.getCourses();
			if (coursesString != null && !coursesString.trim().isEmpty()) {
				StringTokenizer stringTokenizer = new StringTokenizer(coursesString, ", ");
				while (stringTokenizer.hasMoreTokens()) {
					Course course = courseService.getCourseByCode(stringTokenizer.nextToken());
					if (course == null) {
						continue;
					}
					Map<String, Room> suitableRooms;
					if (courseRoomMap.containsKey(course)) {
						suitableRooms = courseRoomMap.get(course);
					} else {
						suitableRooms = new HashMap<>();
						courseRoomMap.put(course, suitableRooms);
					}
					suitableRooms.put(room.getCode(), room);
				}
			}
		}
		return courseRoomMap;
	}
	
	@Override
	public List<Room> listNormalRooms() {
		List<Room> results = new ArrayList<Room>();
		List<Room> rooms = listRooms(false);
		for (Room room : rooms) {
			String coursesString = room.getCourses();
			if (coursesString == null || coursesString.trim().isEmpty()) {
				results.add(room);
			}
		}
		return results;
	}
	
	@Override
	public Map<String, Set<Integer>> getRoomMapClassCourse(int semesterId) {
		Map<String, Set<Integer>> result = new HashMap<String, Set<Integer>>();
		
		Map<Course, Map<String, Room>> courseRoomMap = getCourseRoomMap();
		
		//
		HashMap<String, Set<Integer>> mMergeClass = classCourseSemesterMergeService
				.getMapCourseWithMergeClassInSemester(semesterId);
		
		for(Course course : courseRoomMap.keySet()) {
			CourseSemester courseSemester = courseSemesterService.getCourseSemesterByCourseSemester(course.getCourseId(), semesterId, true, false, false);
			
			List<ClassCourseSemester> list = new ArrayList<ClassCourseSemester>();
			list.addAll(courseSemester.getClassCourseSemesters());
			List<Integer> checkList = new ArrayList<Integer>();
			for (String key : mMergeClass.keySet()) {
				if (key.contains(Integer.toString(courseSemester
						.getCourseSemesterId()))) {
					int count = 0;
					for (int ccsId : mMergeClass.get(key)) {
						if (count == 0) {
							count++;
							continue;
						} else {
							checkList.add(ccsId);
						}
					}
				}
			}
			
			for (int i = 0; i < list.size(); i++) {
				if (checkList.contains(list.get(i).getClassCourseSemesterId())) {
					list.remove(i--);
				}
			}
			
			Set<Integer> setClassCourseSemesterIds = new LinkedHashSet<Integer>();
			for(ClassCourseSemester ccs : list) {
				setClassCourseSemesterIds.add(ccs.getClassCourseSemesterId());
			}
			
			
			for(String roomCode : courseRoomMap.get(course).keySet()) {
				if(!result.containsKey(roomCode)) {
					result.put(roomCode, new LinkedHashSet<Integer>());
				} 
				
				result.get(roomCode).addAll(setClassCourseSemesterIds);
			}
		}
		return result;
	}
	
	
	@Override
	// get Map between SpecialCourse with Timetable of its classes
	public Map<Course, Set<Timetable>> getSpecialCourseMapTimetableOfItsClasses(int semesterId) {
		Map<Course, Set<Timetable>> result = new HashMap<Course, Set<Timetable>>();
		

		// get Map Course with Room
		Map<Course, Map<String, Room>> mCourseRoom = getCourseRoomMap();
		
		// get Map Room with Class Course
		Map<String, Set<Integer>> mRoomClassCourse = getRoomMapClassCourse(semesterId);
		
		for(Course course : mCourseRoom.keySet()) {
			
			if(!result.containsKey(course)) {
				// add new key for result map
				result.put(course, new LinkedHashSet<Timetable>());
			}
			
			for(String roomCode : mCourseRoom.get(course).keySet()) {
				if(mRoomClassCourse.containsKey(roomCode)) {
					for(int classCourseSemesterId : mRoomClassCourse.get(roomCode)) {
						ClassCourseSemester ccs = classCourseSemesterService.getClassCourseSemesterById(classCourseSemesterId, true, false);
						result.get(course).addAll(ccs.getTimetable());
					}
				}
			}
		}
		return result;
	}
}
