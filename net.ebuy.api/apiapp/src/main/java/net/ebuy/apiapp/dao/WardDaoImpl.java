package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
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
	public List<Ward> findAllCity() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ward.class);
		List<Ward> ward = criteria.list();
		return ward;
	}
	

	
}
