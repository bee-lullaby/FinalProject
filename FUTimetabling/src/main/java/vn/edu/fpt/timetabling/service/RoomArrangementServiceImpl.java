package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.fpt.timetabling.dao.ProgramSemesterDetailDAO;
import vn.edu.fpt.timetabling.model.ClassCourseSemester;
import vn.edu.fpt.timetabling.model.ClassFPT;
import vn.edu.fpt.timetabling.model.ClassSemester;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.DataRoomArrangement;
import vn.edu.fpt.timetabling.model.Semester;

@Service
public class RoomArrangementServiceImpl implements RoomArrangementService {

	private static final Logger logger = LoggerFactory.getLogger(RoomArrangementServiceImpl.class);
	
	@Autowired
	private ClassSemesterService classSemesterService;
	
	@Autowired
	private TimetableService timetableService;
	
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
			for(ClassCourseSemester ccs : cs.getClassCourseSemesters()) {
				ClassCourseSemester newCcs = new ClassCourseSemester();
				CourseSemester courseSemester = new CourseSemester();
				courseSemester.setCourseSemesterId(ccs.getCourseSemester().getCourseSemesterId());
				
				Course course = new Course();
				course.setCourseId(ccs.getCourseSemester().getCourse().getCourseId());
				course.setCode(ccs.getCourseSemester().getCourse().getCode());
				courseSemester.setCourse(course);
				
				newCcs.setClassCourseSemesterId(ccs.getClassCourseSemesterId());
				newCcs.setCourseSemester(courseSemester);
				classCourseSemesters.add(newCcs);
				
			}
			
			classSemester.setClassCourseSemesters(classCourseSemesters);
			JSONccs.add(classSemester);
		}
		return JSONccs;
	}
	
	@Override
	@Transactional
	public List<DataRoomArrangement> getDataRoomArrangement(int semesterId, int classId) {
		//TODO Auto-generated method stub
		List<DataRoomArrangement> dataRoomArrangements = new ArrayList<DataRoomArrangement>();
//		List<ClassSemester> classSemestersConflict;
//		
//		ClassSemester classSemester = classSemesterService.getClassSemesterByClassSemester(semesterId, classId, true);
//		List<Timetable> allTimetable = timetableService.listTimetables();
//		
//		List<ClassCourseSemester> classCourseSemesterOfClass = new ArrayList<ClassCourseSemester>();
//		classCourseSemesterOfClass.addAll(classSemester.getClassCourseSemesters());
//		List<Timetable> timetableOfClass = timetableService.listTimetablesByCCSId(classCourseSemesterOfClass);
		return dataRoomArrangements;
		
		
		
	}
	
}
