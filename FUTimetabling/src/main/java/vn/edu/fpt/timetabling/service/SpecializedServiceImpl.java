package vn.edu.fpt.timetabling.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vn.edu.fpt.timetabling.dao.SpecializedDAO;
import vn.edu.fpt.timetabling.model.Specialized;

public class SpecializedServiceImpl implements SpecializedService {

	private SpecializedDAO specializedDAO;

	public void setSpecializedDAO(SpecializedDAO specializedDAO) {
		this.specializedDAO = specializedDAO;
	}

	@Override
	@Transactional
	public void addSpecialized(Specialized specialized) {
		specializedDAO.addSpecialized(specialized);
	}

	@Override
	@Transactional
	public void updateSpecialized(Specialized specialized) {
		specializedDAO.updateSpecialized(specialized);
	}

	@Override
	@Transactional
	public List<Specialized> listSpecializeds() {
		return specializedDAO.listSpecializeds();
	}

	@Override
	@Transactional
	public Specialized getSpecializedById(int specializedId) {
		return specializedDAO.getSpecializedById(specializedId);
	}

	@Override
	@Transactional
	public Specialized getSpecializedByCode(String code) {
		return specializedDAO.getSpecializedByCode(code);
	}

	@Override
	@Transactional
	public void deleteSpecialized(int specializedId) {
		specializedDAO.deleteSpecialized(specializedId);
	}

}
