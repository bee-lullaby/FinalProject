package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Specialized;

public interface SpecializedDAO {
	public void addSpecialized(Specialized specialized);

	public void updateSpecialized(Specialized specialized);

	public List<Specialized> listSpecializeds();
	
	public List<Specialized> listDetailSpecializeds();

	public Specialized getSpecializedById(int specializedId);

	public Specialized getSpecializedByCode(String code);

	public void deleteSpecialized(int specializedId);
}
