package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.BuildingDAO;
import vn.edu.fpt.timetabling.model.Building;

@Service
@Transactional(rollbackFor = Exception.class)
public class BuildingServiceImpl implements BuildingService {
	private BuildingDAO buildingDAO;

	public void setBuildingDAO(BuildingDAO buildingDAO) {
		this.buildingDAO = buildingDAO;
	}

	@Override
	public void addBuilding(Building building) {
		buildingDAO.addBuilding(building);
	}

	@Override
	public void updateBuilding(Building building) {
		buildingDAO.updateBuilding(building);
	}

	@Override
	public List<Building> listBuildings() {
		return buildingDAO.listBuildings();
	}

	@Override
	public Building getBuildingById(int buildingId) {
		return buildingDAO.getBuildingById(buildingId);
	}

	@Override
	public Building getBuildingByCode(String code) {
		return buildingDAO.getBuildingByCode(code);
	}

	@Override
	public void deleteBuilding(int buildingId) {
		buildingDAO.deleteBuilding(buildingId);
	}
}
