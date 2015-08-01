package vn.edu.fpt.timetabling.service;

import java.util.List;

import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;

public interface ClassCourseSemesterMergeService {
	public void addClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public void updateClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges();

	public ClassCourseSemesterMerge getClassCourseSemesterMergeById(int classCourseSemesterMergeId);

	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId);
}
