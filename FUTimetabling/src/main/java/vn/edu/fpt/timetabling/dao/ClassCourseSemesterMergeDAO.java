package vn.edu.fpt.timetabling.dao;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;

public interface ClassCourseSemesterMergeDAO {
	public void addClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public void updateClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges();

	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges(int semesterId);

	public ClassCourseSemesterMerge getClassCourseSemesterMergeById(int classCourseSemesterMergeId);

	public ClassCourseSemesterMerge getClassCourseSemesterMergeBy2CCS(int classCourseSemesterId1,
			int classCourseSemesterId2);

	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId);

	public int deleteClassCourseSemesterMerges(int semesterId);
}
