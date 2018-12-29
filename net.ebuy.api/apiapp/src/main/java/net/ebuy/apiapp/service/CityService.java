package net.ebuy.apiapp.service;

import java.util.List;


import net.ebuy.apiapp.model.City;
/**
 * @author Donald Trieu
 *
 */
public interface CityService {
	
	City findId(int id);
	
	void saveArea(City city);

	void updateArea(City city);

	void deleteAreaById(int cityId);
	
	List<City> findAllCity();

	City findCityByIdCity(int cityId);

}