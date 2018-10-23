package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.District;

public interface DistrictDao {

	District findById(int id);
	
	void saveDistrict(District district);
	
	void deleteCity(Integer districtId);
	
	List<District> findAllCity();
}
