package vn.edu.fpt.timetabling.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;

public interface ClassCourseSemesterMergeService {
	public void addClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public void updateClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge);

	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges();

	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges(int semesterId);

	public ClassCourseSemesterMerge getClassCourseSemesterMergeById(int classCourseSemesterMergeId);

	public ClassCourseSemesterMerge getClassCourseSemesterMergeBy2CCS(int classCourseSemesterId1,
			int classCourseSemesterId2);

	public HashMap<String, Set<Integer>> getMapCourseWithMergeClassInSemester(int semesterId);

	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId);

	public int deleteClassCourseSemesterMerges(int semesterId);
	
	public Map<ClassCourseSemester, ClassCourseSemester> getGuestHostMap(int semesterId);
	
	public Map<ClassCourseSemester, List<ClassCourseSemester>> getHostGuestMap(int semesterId);
}
