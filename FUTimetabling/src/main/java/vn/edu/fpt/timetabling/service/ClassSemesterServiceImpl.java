package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassSemesterDAO;
import vn.edu.fpt.timetabling.model.ClassSemester;

@Service
public class ClassSemesterServiceImpl implements ClassSemesterService {

	private ClassSemesterDAO classSemesterDAO;

	public void setClassSemesterDAO(ClassSemesterDAO classSemesterDAO) {
		this.classSemesterDAO = classSemesterDAO;
	}

	@Override
	@Transactional
	public void addClassSemester(ClassSemester classSemester) {
		classSemesterDAO.addClassSemester(classSemester);
	}

	@Override
	@Transactional
	public void updateClassSemester(ClassSemester classSemester) {
		classSemesterDAO.updateClassSemester(classSemester);
	}

	@Override
	@Transactional
	public List<ClassSemester> listClassSemesters() {
		return classSemesterDAO.listClassSemesters();
	}
	
	@Override
	@Transactional
	public ClassSemester getClassSemesterByClassSemester(int semesterId,
			int classId) {
		// TODO Auto-generated method stub
		return classSemesterDAO.getClassSemesterByClassSemester(semesterId,
				classId);
	}

	@Override
	@Transactional
	public ClassSemester getClassSemesterById(int classSemesterId) {
		return classSemesterDAO.getClassSemesterById(classSemesterId);
	}

	@Override
	@Transactional
	public void deleteClassSemester(int classSemesterId) {
		classSemesterDAO.deleteClassSemester(classSemesterId);
	}

}
