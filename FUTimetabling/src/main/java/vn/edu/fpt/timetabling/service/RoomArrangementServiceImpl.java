package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.Semester;

@Service
public class RoomArrangementServiceImpl implements RoomArrangementService {

	@Autowired
	private ClassSemesterService classSemesterService;
	
	@Override
	@Transactional
	public List<ClassSemester> getListClassesCoursesOfSemester(int semesterId) {
		// TODO Auto-generated method stub

		List<ClassSemester> classSemesters = classSemesterService.listClassSemestersBySemester(semesterId, true);
		List<ClassSemester> JSONccs = new ArrayList<ClassSemester>();
		
		for (ClassSemester cs : classSemesters) {
			ClassSemester classSemester = new ClassSemester();
			classSemester.setClassSemesterId(cs.getClassSemesterId());
			
			ClassFPT classFPT = new ClassFPT();
			classFPT.setClassId(cs.getClassFPT().getClassId());
			classFPT.setCode(cs.getClassFPT().getCode());
			classSemester.setClassFPT(classFPT);
			
			Semester semester = new Semester();
			semester.setSemesterId(cs.getSemester().getSemesterId());
			semester.setCode(cs.getSemester().getCode());
			classSemester.setSemester(semester);
			
			Set<ClassCourseSemester> classCourseSemesters = new LinkedHashSet<ClassCourseSemester>(); 
			for(ClassCourseSemester ccs : classCourseSemesters) {
				ClassCourseSemester newCcs = new ClassCourseSemester();
				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourse(ccs.getCourseSemester().getCourse());
				newCcs.setClassCourseSemesterId(ccs.getClassCourseSemesterId());
				newCcs.setCourseSemester(courseSemester);
				classCourseSemesters.add(newCcs);
			}
			
			classSemester.setClassCourseSemesters(classCourseSemesters);
			JSONccs.add(classSemester);
		}
		return JSONccs;
	}

}
