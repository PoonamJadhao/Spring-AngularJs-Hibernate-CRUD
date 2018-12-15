package com.cruddemo.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cruddemo.springmvc.model.Surveyor;

@Repository("surveyorDao")
public class SurveyorDaoImpl extends AbstractDao<Integer, Surveyor> implements SurveyorDao {

	public Surveyor findById(int id) {
		return getByKey(id);
	}

	public void saveSurveyor(Surveyor surveyor) {
		persist(surveyor);
	}

	public void deleteSurveyorBySite(String site) {
		Query query = getSession().createSQLQuery("delete from SURVEYOR where site = :site");
		query.setString("site", site);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Surveyor> findAllSurveyors() {
		Criteria criteria = createEntityCriteria();
		return (List<Surveyor>) criteria.list();
	}

	public Surveyor findSurveyorBySite(String site) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("site", site));
		return (Surveyor) criteria.uniqueResult();
	}
}
