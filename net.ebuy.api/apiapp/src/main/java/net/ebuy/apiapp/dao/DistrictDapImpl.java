package net.ebuy.apiapp.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	public void deleteDistrict(Integer districtId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<District> findAllDistrict() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(District.class);
		List<District> districts = criteria.list();
		return districts;
	}

	@Override
	public List<District> findAllDistrictByIdCity(List<District> districts,int idCity) {
		// TODO Auto-generated method stub
		List<District> listDistricts = districts.stream()
				.filter(p-> p.getId_city().getId()==idCity)
				.collect(Collectors.toList());
		return listDistricts;
	}

	@Override
	public District findDistrictById(int districtId) {
		// TODO Auto-generated method stub
		return (District)sessionFactory.getCurrentSession().createCriteria(District.class)
				.add(Restrictions.eq("id", districtId)).uniqueResult();
	}

}
