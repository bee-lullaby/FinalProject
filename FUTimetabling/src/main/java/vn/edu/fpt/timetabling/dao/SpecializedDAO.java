package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.Specialized;

public interface SpecializedDAO {
	public void addSpecialized(Specialized specialized);

	public void updateSpecialized(Specialized specialized);

	public List<Specialized> listSpecializeds(boolean jointClasses, boolean jointStudents);

	public List<Specialized> listDetailSpecializeds(boolean jointClasses, boolean jointStudents);

	public Specialized getSpecializedById(int specializedId, boolean jointClasses, boolean jointStudents);

	public Specialized getSpecializedByCode(String code, boolean jointClasses, boolean jointStudents);

	public void deleteSpecialized(int specializedId);
}
