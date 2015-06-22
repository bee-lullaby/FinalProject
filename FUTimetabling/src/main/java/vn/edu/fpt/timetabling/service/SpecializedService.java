package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.util.List;

import vn.edu.fpt.timetabling.model.Specialized;

public interface SpecializedService {
	public void addSpecialized(Specialized specialized);

	public void addSpecializedFromFile(File specializeds);

	public void updateSpecialized(Specialized specialized);

	public List<Specialized> listSpecializeds();

	public Specialized getSpecializedById(int specializedId);

	public Specialized getSpecializedByCode(String code);

	public void deleteSpecialized(int specializedId);
}
