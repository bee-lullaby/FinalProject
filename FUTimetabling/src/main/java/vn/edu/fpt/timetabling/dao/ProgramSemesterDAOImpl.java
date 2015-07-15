package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ProgramSemester;

@Repository
public class ProgramSemesterDAOImpl implements ProgramSemesterDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addProgramSemester(ProgramSemester programSemester) {
		getCurrentSession().persist(programSemester);
	}

	@Override
	public void updateProgramSemester(ProgramSemester programSemester) {
		getCurrentSession().update(programSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramSemester> listProgramSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemester PS"
				+ " LEFT OUTER JOIN FETCH PS.programSemesterDetails" + " ORDER BY PS.programSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProgramSemester> programSemesters = (List<ProgramSemester>) query.list();
		return programSemesters;
	}

	@Override
	public ProgramSemester getProgramSemesterById(int programSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemester PS"
				+ " LEFT OUTER JOIN FETCH PS.programSemesterDetails"
				+ " WHERE PS.programSemesterId = :programSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("programSemesterId", programSemesterId);
		return (ProgramSemester) query.uniqueResult();
	}

	@Override
	public void deleteProgramSemester(int programSemesterId) {
		ProgramSemester programSemester = getProgramSemesterById(programSemesterId);
		getCurrentSession().delete(programSemester);
	}

	@Override
	public ProgramSemester getProgramSemesterBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemester PS"
				+ " WHERE PS.semester.semesterId = :semesterId" + " AND PS.specialized.specializedId = :specializedId"
				+ " AND PS.currentSemester = :semesterNumber";
		if (detailSpecializedId != 0) {
			hql += " AND PS.detailSpecialized.specializedId = :detailSpecialized";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setParameter("specializedId", specializedId);
		query.setParameter("semesterNumber", semesterNumber);
		if (detailSpecializedId != 0) {
			query.setParameter("detailSpecialized", detailSpecializedId);
		}
		return (ProgramSemester) query.uniqueResult();
	}
}
