package com.cruddemo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cruddemo.springmvc.dao.SurveyorDao;
import com.cruddemo.springmvc.model.Surveyor;

@Service("surveyorService")
@Transactional
public class SurveyorServiceImpl implements SurveyorService {

	@Autowired
	private SurveyorDao dao;
	
	public Surveyor findById(int id) {
		return dao.findById(id);
	}

	public void saveSurveyor(Surveyor surveyor) {
		dao.saveSurveyor(surveyor);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateSurveyor(Surveyor surveyor) {
		Surveyor entity = dao.findById(surveyor.getId());
		if(entity!=null){
			entity.setName(surveyor.getName());
			entity.setJoiningDate(surveyor.getJoiningDate());
			entity.setCost(surveyor.getCost());
			entity.setSite(surveyor.getSite());
		}
	}

	public void deleteSurveyorBySite(String site) {
		dao.deleteSurveyorBySite(site);
	}
	
	public List<Surveyor> findAllSurveyors() {
		return dao.findAllSurveyors();
	}

	public Surveyor findSurveyorBySite(String site) {
		return dao.findSurveyorBySite(site);
	}

	public boolean isSurveyorSiteUnique(Integer id, String site) {
		Surveyor surveyor = findSurveyorBySite(site);
		return ( surveyor == null || ((id != null) && (surveyor.getId() == id)));
	}
	
}
