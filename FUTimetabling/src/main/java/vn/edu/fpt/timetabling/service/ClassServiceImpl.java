package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ClassDAO;
import vn.edu.fpt.timetabling.model.ClassFPT;

@Service
public class ClassServiceImpl implements ClassService {

	private ClassDAO classDAO;

	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}

	@Override
	@Transactional
	public void addClass(ClassFPT classFPT) {
		classDAO.addClass(classFPT);
	}

	@Override
	@Transactional
	public void updateClass(ClassFPT classFPT) {
		classDAO.updateClass(classFPT);
	}

	@Override
	@Transactional
	public List<ClassFPT> listClasses() {
		return classDAO.listClasses();
	}

	@Override
	@Transactional
	public ClassFPT getClassById(int classId) {
		return classDAO.getClassById(classId);
	}

	@Override
	@Transactional
	public ClassFPT getClassByCode(String code) {
		return classDAO.getClassByCode(code);
	}

	@Override
	@Transactional
	public void deleteClass(int classId) {
		classDAO.deleteClass(classId);
	}

	@Override
	@Transactional
	public List<ClassFPT> getClassesWithPrefix(String prefix) {
		return classDAO.getClassesWithPrefix(prefix);
	}

	@Override
	@Transactional
	public Integer getNextClassNumber(String prefix) {
		List<ClassFPT> classesWithPrefix = getClassesWithPrefix(prefix);
		if (classesWithPrefix == null || classesWithPrefix.isEmpty()) {
			return 1;
		} else {
			return classesWithPrefix.get(0).getNumber() + 1;
		}
	}

}
