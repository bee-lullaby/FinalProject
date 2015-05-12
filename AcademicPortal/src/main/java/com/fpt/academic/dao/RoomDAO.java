package com.fpt.academic.dao;

import java.io.File;
import java.util.List;

import com.fpt.academic.model.Room;

public interface RoomDAO {
	public void addRoom(Room room);
	void addListRoom(File rooms);
	public void updateRoom(Room room);
	public void deleteRoom(Room room);
	public List<Room> list();
	public String display();
}
