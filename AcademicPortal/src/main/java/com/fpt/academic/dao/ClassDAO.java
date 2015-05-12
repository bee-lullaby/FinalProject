package com.fpt.academic.dao;

import java.io.File;
import java.util.List;

import com.fpt.academic.model.ClassFPT;


public interface ClassDAO {
	public void addClass(ClassFPT classfpt);
	public void addListClass(File classfpts);
	public void updateClass(ClassFPT classfpt);
	public void deleteClass(ClassFPT classfpt);
	public List<ClassFPT> list();
	public List<ClassFPT> listClassSemester(int semester_id);
	public String display();
	public String displaySemester(int semester_id);
}
