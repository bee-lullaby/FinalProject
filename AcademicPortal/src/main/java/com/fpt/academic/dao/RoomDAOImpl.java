package com.fpt.academic.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fpt.academic.model.Room;
import com.fpt.academic.model.Teacher;

public class RoomDAOImpl implements RoomDAO {
	
	private JdbcTemplate jdbc;
	
	public RoomDAOImpl(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public void addRoom(Room room) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO rooms(code, room_type, capacity) VALUES (?, ?, ?)";
		jdbc.update(sql, room.getCode(), room.getRoom_type(), room.getCapacity());
	}

	@Override
	public void addListRoom(File rooms) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO rooms(code, room_type, capacity) VALUES (?, ?, ?)";
		try {
			FileInputStream file = new FileInputStream(rooms); 
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			
			
			rowIterator.next();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				jdbc.update(sql, row.getCell(0).getStringCellValue().trim(), row.getCell(1).getStringCellValue().trim(), 
						row.getCell(2).getNumericCellValue());
			}
			
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateRoom(Room room) {
		// TODO Auto-generated method stub
		String sql = "UPDATE rooms SET room_type=?, capacity=? WHERE code=? ";
		jdbc.update(sql, room.getRoom_type(), room.getCapacity(), room.getCode());
	
	}

	@Override
	public void deleteRoom(Room room) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM rooms WHERE code=?";
		jdbc.update(sql, room.getCode());
	}

	@Override
	public List<Room> list() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM rooms";
		List<Room> list = new ArrayList<Room>();
		List<Map<String, Object>> rows = jdbc.queryForList(sql);
		
		for(Map row: rows) {
			Room room = new Room();
			room.setRoom_id((Integer)row.get("room_id"));
			room.setCode((String)row.get("code"));
			room.setRoom_type((String)row.get("room_type"));
			room.setCapacity((Integer)row.get("capacity"));
			list.add(room);
		}
		return list;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		StringBuilder listToShow = new StringBuilder("");
		
		int count = 1;
		for (Room room: list()) {
			listToShow.append("<tr> "
				+ "<td class=\"text-left\">" +count++ +"</th> "
				+ "<td class=\"text-left\">" +room.getCode() +"</th>"
				+ "<td class=\"text-left\">" +room.getRoom_type() +"</th>"
				+ "<td class=\"text-left\">" +room.getCapacity() +"</th>"
			    + "</tr>");
		}
		
		return listToShow.toString();
	}

}
