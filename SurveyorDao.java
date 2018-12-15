package com.cruddemo.springmvc.dao;

import java.util.List;

import com.cruddemo.springmvc.model.Surveyor;

public interface SurveyorDao {

	Surveyor findById(int id);

	void saveSurveyor(Surveyor surveyor);
	
	void deleteSurveyorBySite(String site);
	
	List<Surveyor> findAllSurveyors();

	Surveyor findSurveyorBySite(String site);

}
