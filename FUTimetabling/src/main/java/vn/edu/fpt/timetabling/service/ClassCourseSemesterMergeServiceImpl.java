package vn.edu.fpt.timetabling.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
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
	public ClassCourseSemesterMerge getClassCourseSemesterMergeBy2CCS(int classCourseSemesterId1,
			int classCourseSemesterId2) {
		return classCourseSemesterMergeDAO.getClassCourseSemesterMergeBy2CCS(classCourseSemesterId1,
				classCourseSemesterId2);
	}

	@Override
	public void deleteClassCourseSemesterMerge(int classCourseSemesterMergeId) {
		classCourseSemesterMergeDAO.deleteClassCourseSemesterMerge(classCourseSemesterMergeId);
	}

	@Override
	public List<ClassCourseSemesterMerge> listClassCourseSemesterMerges(int semesterId) {
		return classCourseSemesterMergeDAO.listClassCourseSemesterMerges(semesterId);
	}

	public HashMap<String, Set<Integer>> getMapCourseWithMergeClassInSemester(int semesterId) {
		HashMap<String, Set<Integer>> result = new HashMap<String, Set<Integer>>();
		HashMap<String, Set<Integer>> mCourseWithMergeClass = new HashMap<String, Set<Integer>>();

		List<ClassCourseSemesterMerge> list = listClassCourseSemesterMerges(semesterId);

		for (ClassCourseSemesterMerge ccsm : list) {
			ClassCourseSemester ccs1 = ccsm.getClassCourseSemester1();
			ClassCourseSemester ccs2 = ccsm.getClassCourseSemester2();

			int courseSemesterId = ccs1.getCourseSemester().getCourseSemesterId();
			String getKey = "";
			int count = 0;
			for (String key : mCourseWithMergeClass.keySet()) {
				if (key.contains(Integer.toString(courseSemesterId))) {
					count++;
					getKey = "";
					if (mCourseWithMergeClass.get(key).contains(ccs1.getClassCourseSemesterId())) {
						mCourseWithMergeClass.get(key).add(ccs2.getClassCourseSemesterId());
						getKey = key;
						break;
					} else if (mCourseWithMergeClass.get(key).contains(ccs2.getClassCourseSemesterId())) {
						mCourseWithMergeClass.get(key).add(ccs1.getClassCourseSemesterId());
						getKey = key;
						break;
					}
				}
			}

			if (getKey.compareTo("") == 0) {
				mCourseWithMergeClass.put(courseSemesterId + "-" + (count + 1), new LinkedHashSet<Integer>());
				mCourseWithMergeClass.get(courseSemesterId + "-" + (count + 1)).add(ccs1.getClassCourseSemesterId());
				mCourseWithMergeClass.get(courseSemesterId + "-" + (count + 1)).add(ccs2.getClassCourseSemesterId());
			}
		}

		Set<String> removeKey = new LinkedHashSet<String>();

		for (String key : mCourseWithMergeClass.keySet()) {
			if (!removeKey.contains(key)) {
				result.put(key, mCourseWithMergeClass.get(key));
				Set<Integer> values = mCourseWithMergeClass.get(key);

				for (String keyMCourse : mCourseWithMergeClass.keySet()) {
					for (int id : values) {
						if (mCourseWithMergeClass.get(keyMCourse).contains(id) && !removeKey.contains(keyMCourse)) {
							result.get(key).addAll(mCourseWithMergeClass.get(keyMCourse));
							removeKey.add(keyMCourse);
							break;
						}
					}
				}
			}
		}

		return result;

	}

	@Override
	public int deleteClassCourseSemesterMerges(int semesterId) {
		return classCourseSemesterMergeDAO.deleteClassCourseSemesterMerges(semesterId);
	}

	@Override
	public Map<ClassCourseSemester, ClassCourseSemester> getGuestHostMap(int semesterId) {
		List<ClassCourseSemesterMerge> classCourseSemesterMerges = listClassCourseSemesterMerges(semesterId);
		Map<ClassCourseSemester, ClassCourseSemester> guestHostMap = new HashMap<>();
		for (ClassCourseSemesterMerge classCourseSemesterMerge : classCourseSemesterMerges) {
			ClassCourseSemester hostClassCourseSemester = classCourseSemesterMerge.getClassCourseSemester1();
			ClassCourseSemester guestClassCourseSemester = classCourseSemesterMerge.getClassCourseSemester2();
			guestHostMap.put(guestClassCourseSemester, hostClassCourseSemester);
		}
		return guestHostMap;
	}

	@Override
	public Map<ClassCourseSemester, List<ClassCourseSemester>> getHostGuestMap(int semesterId) {
		List<ClassCourseSemesterMerge> classCourseSemesterMerges = listClassCourseSemesterMerges(semesterId);
		Map<ClassCourseSemester, List<ClassCourseSemester>> hostGuestMap = new HashMap<>();
		for (ClassCourseSemesterMerge classCourseSemesterMerge : classCourseSemesterMerges) {
			ClassCourseSemester hostClassCourseSemester = classCourseSemesterMerge.getClassCourseSemester1();
			ClassCourseSemester guestClassCourseSemester = classCourseSemesterMerge.getClassCourseSemester2();
			if (!hostGuestMap.containsKey(hostClassCourseSemester)) {
				hostGuestMap.put(hostClassCourseSemester, new ArrayList<ClassCourseSemester>());
			}
			hostGuestMap.get(hostClassCourseSemester).add(guestClassCourseSemester);
		}
		return hostGuestMap;
	}
}
