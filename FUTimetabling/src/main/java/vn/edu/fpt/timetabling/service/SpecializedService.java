package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import vn.edu.fpt.timetabling.model.Specialized;

public interface SpecializedService {
	public void addSpecialized(Specialized specialized);

	public void addSpecializedFromFile(File specializeds) throws IOException;

	public void updateSpecialized(Specialized specialized);

	public List<Specialized> listSpecializeds(boolean jointClasses, boolean jointStudents);

	public List<Specialized> listDetailSpecializeds(boolean jointClasses, boolean jointStudents);

	public Specialized getSpecializedById(int specializedId, boolean jointClasses, boolean jointStudents);

	public Specialized getSpecializedByCode(String code, boolean jointClasses, boolean jointStudents);

	public void deleteSpecialized(int specializedId);
}
