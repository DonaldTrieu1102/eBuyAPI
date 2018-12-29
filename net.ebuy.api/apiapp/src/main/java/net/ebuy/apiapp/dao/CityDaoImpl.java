package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.City;

/**
 * @author Donald Trieu
 *
 */
@Repository("cityDao")
public class CityDaoImpl extends AbstractDao<Integer, City> implements CityDao {

	@Autowired 
	SessionFactory sessionFactory;

	public City findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	public void saveCity(City city) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(city);
		
	}

	public void deleteCity(Integer cityId) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("DELETE FROM areas WHERE Id = "+ cityId).executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public List<City> findAllCity() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(City.class);
		List<City> city = criteria.list();
		return city;
	}

	@Override
	public City findCityByIdCity(int cityId) {
		// TODO Auto-generated method stub
		return (City) sessionFactory.getCurrentSession().createCriteria(City.class)
				.add(Restrictions.eq("id", cityId)).uniqueResult();
	}


}
