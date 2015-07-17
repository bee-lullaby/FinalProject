package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.RoomDAO;
import vn.edu.fpt.timetabling.model.Room;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoomServiceImpl implements RoomService {
	private RoomDAO roomDAO;

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}

	@Override
	public void addRoom(Room room) {
		roomDAO.addRoom(room);
	}

	@Override
	public void addRoomFromFile(File rooms) throws IOException {
		FileInputStream file = new FileInputStream(rooms);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Room r = new Room();
			r.setCode(row.getCell(0).getStringCellValue().trim());
			Double capcity = row.getCell(2).getNumericCellValue();
			r.setCapacity(capcity.intValue());
			if (row.getCell(3) != null && row.getCell(3).getStringCellValue().trim().compareTo("") != 0) {
				r.setCourses(row.getCell(3).getStringCellValue().trim());
			}
			roomDAO.addRoom(r);
		}
		workbook.close();
		file.close();
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
	public void deleteRoom(int roomId) {
		roomDAO.deleteRoom(roomId);
	}
}
