package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.District;

public interface DistrictService {

	District findById(int id);
	
	void saveDistrict(District district);
	
	void deleteCity(Integer districtId);
	
	List<District> findAllCity();
}
