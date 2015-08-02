package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;

public interface ClassCourseSemesterMergeDAO {
	public void addClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public void updateClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges();

	public ClassCourseSemesterMerge getClassCourseSemesterMergeById(int classCourseSemesterMergeId);

	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId);
}
