package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.City;
/**
 * @author Donald Trieu
 *
 */

public interface CityDao {
	City findById(int id);
	
	void saveCity(City city);
	
	void deleteCity(Integer cityId);
	
	List<City> findAllCity();
	
	City findCityByIdCity(int cityId);


}
