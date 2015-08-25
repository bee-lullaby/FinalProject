package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.RoomDAO;
import vn.edu.fpt.timetabling.model.Building;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.Room;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoomServiceImpl implements RoomService {
	private RoomDAO roomDAO;
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private CourseService courseService;

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
}
