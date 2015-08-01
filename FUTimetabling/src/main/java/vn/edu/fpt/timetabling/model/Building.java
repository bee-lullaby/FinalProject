package vn.edu.fpt.timetabling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buildings")
public class Building {

	@Id
	@Column(name = "building_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int buildingId;

	@Column(name = "building_code")
	private String code;

	public Building(int buildingId, String code) {
		super();
		this.buildingId = buildingId;
		this.code = code;
	}

	public Building() {
		super();
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
