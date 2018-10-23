package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.District;

@Repository("districtDao")
public class DistrictDapImpl extends AbstractDao<Integer, District> implements DistrictDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public District findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDistrict(District district) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCity(Integer districtId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<District> findAllCity() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(District.class);
		List<District> districts = criteria.list();
		return districts;
	}

}
