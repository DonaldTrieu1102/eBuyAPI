package net.ebuy.apiapp.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.District;
import net.ebuy.apiapp.model.Ward;

@Repository("wardDao")
public class WardDaoImpl extends AbstractDao<Integer, Ward> implements WardDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Ward findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWard(Ward ward) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWard(Integer wardId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ward> findAllWard() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ward.class);
		List<Ward> ward = criteria.list();
		return ward;
	}

	@Override
	public List<Ward> findAllWardByIdDistrict(List<Ward> wards, int idDistrict) {
		// TODO Auto-generated method stub
		List<Ward> listWards = wards.stream()
				.filter(p->p.getId_district().getId()==idDistrict)
				.collect(Collectors.toList());
		return listWards;
	}

	@Override
	public Ward findWardByIdWard(int wardId) {
		// TODO Auto-generated method stub
		return (Ward)sessionFactory.getCurrentSession().createCriteria(Ward.class)
				.add(Restrictions.eq("id", wardId)).uniqueResult();
	}
	

	
}
