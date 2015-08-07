package vn.edu.fpt.timetabling.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.ProgramSemesterDAO;
import vn.edu.fpt.timetabling.model.Course;
import vn.edu.fpt.timetabling.model.CourseSemester;
import vn.edu.fpt.timetabling.model.ProgramSemester;
import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;
import vn.edu.fpt.timetabling.model.Specialized;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProgramSemesterServiceImpl implements ProgramSemesterService {

	private ProgramSemesterDAO programSemesterDAO;

	public void setProgramSemesterDAO(ProgramSemesterDAO programSemesterDAO) {
		this.programSemesterDAO = programSemesterDAO;
	}

	@Autowired
	private SpecializedService specializedService;

	@Autowired
	private SemesterService semesterService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseSemesterService courseSemesterService;	
	
	@Autowired
	private ProgramSemesterDetailService programSemesterDetailService;
	
	@Override
	public void addProgramSemester(ProgramSemester programSemester) {
		programSemesterDAO.addProgramSemester(programSemester);
	}

	@Override
	public void updateProgramSemester(ProgramSemester programSemester) {
		programSemesterDAO.updateProgramSemester(programSemester);
	}

	@Override
	public List<ProgramSemester> listProgramSemesters() {
		return programSemesterDAO.listProgramSemesters();
	}

	@Override
	public List<ProgramSemester> listProgramSemestersBySemester(int semesterId) {
		return programSemesterDAO.listProgramSemestersBySemester(semesterId);
	}

	@Override
	public ProgramSemester getProgramSemesterById(int programSemesterId) {
		return programSemesterDAO.getProgramSemesterById(programSemesterId);
	}

	@Override
	public void deleteProgramSemester(int programSemesterId) {
		programSemesterDAO.deleteProgramSemester(programSemesterId);
	}

	@Override
	public ProgramSemester getProgramSemesterBySpecializedSemester(
			int semesterId, int specializedId, int detailSpecializedId,
			int semesterNumber) {
		return programSemesterDAO.getProgramSemesterBySpecializedSemester(
				semesterId, specializedId, detailSpecializedId, semesterNumber);
	}

	@Override
	public HashMap<String, List<String>> addProgramSemesterFromFile(int semesterId, File programs) throws IOException {
		FileInputStream file = new FileInputStream(programs);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		
		HashMap<String, List<String>> mCheckSpecializedAndCourse = new HashMap<String, List<String>>();
		mCheckSpecializedAndCourse.put("specialized", new ArrayList<String>());
		mCheckSpecializedAndCourse.put("course", new ArrayList<String>());
		
		programSemesterDetailService.deleteProgramSemesterDetailsBySemester(semesterId);
		programSemesterDAO.deleteProgramSemestersBySemester(semesterId);
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();			
			ProgramSemester p = new ProgramSemester();
			p.setSemester(semesterService.getSemesterById(semesterId, false, false, false, false));
			if(row.getCell(0) != null) { 

				String specializedCode = row.getCell(0).getStringCellValue().trim();
				
				Specialized specialized = specializedService.getSpecializedByCode(specializedCode, false, false);
				if(specialized == null) {
					mCheckSpecializedAndCourse.get("specialized").add(specializedCode);
					continue;
				} else { 
					p.setSpecialized(specialized);
				}
					
				if(row.getCell(1) != null) {
					String detailSpecializedCode = row.getCell(1).getStringCellValue().trim();
					Specialized detailSpecialized = specializedService.getSpecializedByCode(detailSpecializedCode, false, false);
					if(detailSpecialized == null) {
						mCheckSpecializedAndCourse.get("specialized").add(detailSpecializedCode);
						continue;
					} else {
						p.setDetailSpecialized(detailSpecialized);
					}
				}
				
				if(row.getCell(2) != null) {
					Double currentSemester = row.getCell(2).getNumericCellValue();
					p.setCurrentSemester(currentSemester.intValue());
				}
				
				if(row.getCell(3) != null) {
					Double batch = row.getCell(3).getNumericCellValue();
					p.setBatch(batch.intValue());
				}
				
				addProgramSemester(p);
				
				int count = 4;
				while(row.getCell(count) != null) {
					String courseCode = row.getCell(count).getStringCellValue().trim();
					if(courseCode.equals("")) break;
					Course course = courseService.getCourseByCode(courseCode);
					System.out.println(p.getDetailSpecialized().getCode() +" " +courseCode);
					if(course == null) { 
						mCheckSpecializedAndCourse.get("course").add(courseCode);
						continue;
					}
					CourseSemester courseSemester = courseSemesterService.getCourseSemesterByCourseCodeSemester(courseCode, semesterId, false, false, false);
					if(courseSemester == null) {
						mCheckSpecializedAndCourse.get("course").add(courseCode);
						continue;
					}
					
					ProgramSemesterDetail psd = new ProgramSemesterDetail();
					psd.setProgramSemester(p);
					psd.setCourseSemester(courseSemester);	
					if(count == 8) {
						psd.setSemesterLong(true);
					}
					programSemesterDetailService.addProgramSemesterDetail(psd);
					count++;
				}
			}
		}
		workbook.close();
		file.close();
		
		return mCheckSpecializedAndCourse;
	}
}
