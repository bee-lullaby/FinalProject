package vn.edu.fpt.timetabling.service;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterMergeDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassCourseSemesterMerge;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClassCourseSemesterMergeServiceImpl implements ClassCourseSemesterMergeService {
	private ClassCourseSemesterMergeDAO classCourseSemesterMergeDAO;

	public void setClassCourseSemesterMergeDAO(ClassCourseSemesterMergeDAO classCourseSemesterMergeDAO) {
		this.classCourseSemesterMergeDAO = classCourseSemesterMergeDAO;
	}

	@Override
	public void addClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge) {
		classCourseSemesterMergeDAO.addClassCourseSemesterMerge(classCourseSemesterMerge);
	}

	@Override
	public void updateClassCourseSemesterMerge(ClassCourseSemesterMerge classCourseSemesterMerge) {
		classCourseSemesterMergeDAO.updateClassCourseSemesterMerge(classCourseSemesterMerge);
	}

	@Override
	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges() {
		return classCourseSemesterMergeDAO.listClassCourseSemesterMerges();
	}

	@Override
	public ClassCourseSemesterMerge getClassCourseSemesterMergeById(int classCourseSemesterMergeId) {
		return classCourseSemesterMergeDAO.getClassCourseSemesterMergeById(classCourseSemesterMergeId);
	}
	
	@Override
	public ClassCourseSemesterMerge getClassCourseSemesterMergeBy2CCS(int classCourseSemesterId1, int classCourseSemesterId2) {
		return classCourseSemesterMergeDAO.getClassCourseSemesterMergeBy2CCS(classCourseSemesterId1, classCourseSemesterId2);
	}
	
	@Override
	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId) {
		classCourseSemesterMergeDAO.deleteClassCourseSemesterMerge(classCourseSemesterMergeId);
	}

	@Override
	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges(int semesterId) {
		return classCourseSemesterMergeDAO.listClassCourseSemesterMerges(semesterId);
	}
	
	public HashMap<Integer, Set<Integer>> getMapCourseWithMergeClassInSemester(int semesterId) {
		HashMap<Integer, Set<Integer>> mCourseWithMergeClass = new HashMap<Integer, Set<Integer>>();
		
		List<ClassCourseSemesterMerge> list = listClassCourseSemesterMerges(semesterId);
		for(ClassCourseSemesterMerge ccsm : list) {
			ClassCourseSemester ccs1 = ccsm.getClassCourseSemester1();
			ClassCourseSemester ccs2 = ccsm.getClassCourseSemester2();
			
			int courseSemesterId = ccs1.getCourseSemester().getCourseSemesterId();
			if(!mCourseWithMergeClass.containsKey(courseSemesterId)) {
				mCourseWithMergeClass.put(courseSemesterId, new LinkedHashSet<Integer>());
			}

			mCourseWithMergeClass.get(courseSemesterId).add(ccs1.getClassCourseSemesterId());
			mCourseWithMergeClass.get(courseSemesterId).add(ccs2.getClassCourseSemesterId());
		}
		return mCourseWithMergeClass;
		
	}
}
