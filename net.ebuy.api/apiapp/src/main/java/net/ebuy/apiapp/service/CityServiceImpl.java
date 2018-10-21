package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.CityDao;
import net.ebuy.apiapp.model.City;

/**
 * @author Donald Trieu
 *
 */
@Transactional
@Service("cityService")
public class CityServiceImpl implements CityService {
	
	@Autowired private CityDao dao;

	public City findId(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	public void saveArea(City city) {
		// TODO Auto-generated method stub
		dao.saveCity(city);
	}

	public void updateArea(City city) {
		// TODO Auto-generated method stub
		City entity = dao.findById(city.getId());
		if(entity!=null) {
			entity.setId(city.getId());
		}
		dao.saveCity(entity);
	}

	public void deleteAreaById(int cityId) {
		// TODO Auto-generated method stub
		dao.deleteCity(cityId);
		
	}

	public List<City> findAllCity() {
		// TODO Auto-generated method stub
		return dao.findAllCity();
	}

}
