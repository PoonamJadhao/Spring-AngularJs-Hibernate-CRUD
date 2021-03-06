package com.cruddemo.springmvc.service;

import java.util.List;

import com.cruddemo.springmvc.model.Surveyor;

public interface SurveyorService {

	Surveyor findById(int id);
	
	void saveSurveyor(Surveyor surveyor);
	
	void updateSurveyor(Surveyor surveyor);
	
	void deleteSurveyorBySite(String site);

	List<Surveyor> findAllSurveyors(); 
	
	Surveyor findSurveyorBySite(String site);

	boolean isSurveyorSiteUnique(Integer id, String site);
	
}
