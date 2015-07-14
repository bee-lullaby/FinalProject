package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.RoomDAO;
import vn.edu.fpt.timetabling.model.Room;

@Service
public class RoomServiceImpl implements RoomService {
	
	private RoomDAO	roomDAO;

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	
	@Override
	@Transactional
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		roomDAO.addRoom(room);
	}
	
	@Override
	@Transactional
	public void addRoomFromFile(File rooms) {
		// TODO Auto-generated method stub
		try {
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
				if(row.getCell(3) !=  null && row.getCell(3).getStringCellValue().trim().compareTo("") != 0) {
					r.setClasses(row.getCell(3).getStringCellValue().trim());
				}
				
				roomDAO.addRoom(r);
			}

			workbook.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional
	public void updateRoom(Room room) {
		// TODO Auto-generated method stub
		roomDAO.updateRoom(room);
	}

	@Override
	@Transactional
	public List<Room> listRooms() {
		// TODO Auto-generated method stub
		return roomDAO.listRooms();
	}

	@Override
	@Transactional
	public Room getRoomById(int roomId) {
		// TODO Auto-generated method stub
		return roomDAO.getRoomById(roomId);
	}

	@Override
	@Transactional
	public void deleteRoom(int roomId) {
		// TODO Auto-generated method stub
		roomDAO.deleteRoom(roomId);
	}

}
