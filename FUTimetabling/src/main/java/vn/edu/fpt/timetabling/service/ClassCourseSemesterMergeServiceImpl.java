package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassCourseSemesterMergeDAO;
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
	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId) {
		classCourseSemesterMergeDAO.deleteClassCourseSemesterMerge(classCourseSemesterMergeId);
	}
}
