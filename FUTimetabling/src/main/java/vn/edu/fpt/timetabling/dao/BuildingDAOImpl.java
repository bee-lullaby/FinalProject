package vn.edu.fpt.timetabling.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import vn.edu.fpt.timetabling.model.Building;

@Repository
public class BuildingDAOImpl implements BuildingDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addBuilding(Building building) {
		getCurrentSession().persist(building);
	}

	@Override
	public void updateBuilding(Building building) {
		getCurrentSession().update(building);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Building> listBuildings() {
		String hql = "FROM vn.edu.fpt.timetabling.model.Building";
		Query query = getCurrentSession().createQuery(hql);
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Building> buildings = (List<Building>) query.list();
		return buildings;
	}

	@Override
	public Building getBuildingById(int buildingId) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Building B" + " WHERE B.buildingId = : buildingId";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("buildingId", buildingId);
		return (Building) query.uniqueResult();
	}

	@Override
	public Building getBuildingByCode(String code) {
		String hql = "FROM vn.edu.fpt.timetabling.model.Building B" + " WHERE B.buildingCode = : buildingCode";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("buildingCode", code);
		return (Building) query.uniqueResult();
	}

	@Override
	public void deleteBuilding(int buildingId) {
		Building building = getBuildingById(buildingId);
		getCurrentSession().delete(building);
	}
}
