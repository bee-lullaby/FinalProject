package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;

@Repository
public class ProgramSemesterDetailDAOImpl implements ProgramSemesterDetailDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addProgramSemesterDetail(ProgramSemesterDetail programSemesterDetail) {
		getCurrentSession().persist(programSemesterDetail);
	}

	@Override
	public void updateProgramSemesterDetail(ProgramSemesterDetail programSemesterDetail) {
		getCurrentSession().update(programSemesterDetail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramSemesterDetail> listProgramSemesterDetails() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemesterDetail PSD"
				+ " ORDER BY PSD.programSemesterDetailId";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProgramSemesterDetail> programSemesterDetails = (List<ProgramSemesterDetail>) query.list();
		return programSemesterDetails;
	}

	@Override
	public ProgramSemesterDetail getProgramSemesterDetailById(int programSemesterDetailId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemesterDetail PSD"
				+ " WHERE PSD.programSemesterDetailId = :programSemesterDetailId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("programSemesterDetailId", programSemesterDetailId);
		return (ProgramSemesterDetail) query.uniqueResult();
	}

	@Override
	public void deleteProgramSemesterDetail(int programSemesterDetailId) {
		ProgramSemesterDetail programSemesterDetail = getProgramSemesterDetailById(programSemesterDetailId);
		getCurrentSession().delete(programSemesterDetail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramSemesterDetail> listProgramSemesterDetailsBySemester(int semesterId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemesterDetail PSD";
		hql += " WHERE PSD.programSemester.semester.semesterId = :semesterId";
		hql += " ORDER BY PSD.programSemesterDetailId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semesterId", semesterId);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProgramSemesterDetail> programSemesterDetails = (List<ProgramSemesterDetail>) query.list();
		return programSemesterDetails;
	}
}
