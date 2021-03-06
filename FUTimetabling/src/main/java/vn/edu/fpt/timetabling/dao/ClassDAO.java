package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassFPT;

public interface ClassDAO {
	public void addClass(ClassFPT classFPT);

	public void updateClass(ClassFPT classFPT);

	public List<ClassFPT> listClasses();

	public ClassFPT getClassById(int classId);

	public ClassFPT getClassByCode(String code);
	
	public List<ClassFPT> getClassesWithPrefix(String prefix);

	public void deleteClass(int classId);
}
