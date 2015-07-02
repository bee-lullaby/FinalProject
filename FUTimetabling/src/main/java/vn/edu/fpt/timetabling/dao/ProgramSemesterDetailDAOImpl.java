package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.ProgramSemesterDetail;

@Repository
public class ProgramSemesterDetailDAOImpl implements ProgramSemesterDetailDAO {
	private static final Logger logger = LoggerFactory
			.getLogger(ProgramSemesterDetailDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail) {
		getCurrentSession().persist(programSemesterDetail);
		logger.info("programSemesterDetail was saved successfully, programSemesterDetail details="
				+ programSemesterDetail);
	}

	@Override
	public void updateProgramSemesterDetail(
			ProgramSemesterDetail programSemesterDetail) {
		getCurrentSession().update(programSemesterDetail);
		logger.info("programSemesterDetail was updated successfully, programSemesterDetail details="
				+ programSemesterDetail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgramSemesterDetail> listProgramSemesterDetails() {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemesterDetail PSD"
				+ " ORDER BY PSD.programSemesterDetailId";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<ProgramSemesterDetail> programSemesterDetails = (List<ProgramSemesterDetail>) query
				.list();
		for (ProgramSemesterDetail programSemesterDetail : programSemesterDetails) {
			logger.info("programSemesterDetail list:" + programSemesterDetail);
		}
		return programSemesterDetails;
	}

	@Override
	public ProgramSemesterDetail getProgramSemesterDetailById(
			int programSemesterDetailId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.ProgramSemesterDetail PSD"
				+ " WHERE PSD.programSemesterDetailId = :programSemesterDetailId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("programSemesterDetailId", programSemesterDetailId);
		Object temp = query.uniqueResult();
		if (temp != null) {
			ProgramSemesterDetail programSemesterDetail = (ProgramSemesterDetail) temp;
			logger.info("ProgramSemesterDetail was loaded successfully, programSemesterDetail details="
					+ programSemesterDetail);
			return programSemesterDetail;
		} else {
			return null;
		}
	}

	@Override
	public void deleteProgramSemesterDetail(int programSemesterDetailId) {
		ProgramSemesterDetail programSemesterDetail = getProgramSemesterDetailById(programSemesterDetailId);
		if (programSemesterDetail != null) {
			getCurrentSession().delete(programSemesterDetail);
			logger.info("ProgramSemesterDetail was deleted successfully, ProgramSemesterDetail details="
					+ programSemesterDetail);
		}
	}

}
