package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Building;

public interface BuildingDAO {
	public void addBuilding(Building building);

	public void updateBuilding(Building building);

	public List<Building> listBuildings();

	public Building getBuildingById(int buildingId);

	public Building getBuildingByCode(String code);

	public void deleteBuilding(int buildingId);
}
