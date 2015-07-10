package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ProgramSemester;
import vn.edu.fpt.timetabling.model.Specialized;

@Repository
public class ProgramSemesterDAOImpl implements ProgramSemesterDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProgramSemesterDAO.class);
	@Autowired
	private SpecializedDAO specializedDAO;
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
		logger.info("ProgramSemester was saved successfully, ProgramSemester details=" + programSemester);
	}

	@Override
	public void updateProgramSemester(ProgramSemester programSemester) {
		getCurrentSession().update(programSemester);
		logger.info("ProgramSemester was updated successfully, ProgramSemester details=" + programSemester);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramSemester> listProgramSemesters() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemester"
				+ " PS LEFT OUTER JOIN FETCH PS.programSemesterDetails" + " ORDER BY PS.programSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProgramSemester> programSemesters = (List<ProgramSemester>) query.list();
		for (ProgramSemester programSemester : programSemesters) {
			logger.info("ProgramSemester list:" + programSemester);
		}
		return programSemesters;
	}

	@Override
	public ProgramSemester getProgramSemesterById(int programSemesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemester"
				+ " PS LEFT OUTER JOIN FETCH PS.programSemesterDetails"
				+ " WHERE PS.programSemesterId = :programSemesterId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("programSemesterId", programSemesterId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			ProgramSemester programSemester = (ProgramSemester) temp;
			logger.info("ProgramSemester was loaded successfully, semester details=" + programSemester);
			return programSemester;
		} else {
			return null;
		}
	}

	@Override
	public void deleteProgramSemester(int programSemesterId) {
		ProgramSemester programSemester = getProgramSemesterById(programSemesterId);
		if (programSemester != null) {
			getCurrentSession().delete(programSemester);
			logger.info("ProgramSemester was deleted successfully, ProgramSemester details=" + programSemester);
		}
	}

	@Override
	public ProgramSemester getProgramSemesterBySpecializedSemester(int semesterId, int specializedId,
			int detailSpecializedId, int semesterNumber) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemester PS"
				+ " WHERE PS.semester.semesterId = :semesterId" + " AND PS.specialized.specializedId = :specializedId"
				+ " AND PS.currentSemester = :semesterNumber";
		Specialized detailSpecialized = specializedDAO.getSpecializedById(detailSpecializedId);
		if (detailSpecialized != null) {
			hql += " AND PS.detailSpecialized= :detailSpecialized";
		}
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setParameter("specializedId", specializedId);
		query.setParameter("semesterNumber", semesterNumber);
		if (detailSpecialized != null) {
			query.setParameter("detailSpecialized", detailSpecialized);
		}
		Object temp = query.uniqueResult();
		if (temp != null) {
			return (ProgramSemester) temp;
		}
		return null;
	}

}
