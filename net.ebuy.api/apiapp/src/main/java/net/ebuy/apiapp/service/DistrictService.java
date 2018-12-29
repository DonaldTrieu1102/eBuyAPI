package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.District;

public interface DistrictService {

	District findById(int id);
	
	void saveDistrict(District district);
	
	void deleteDistrict(Integer districtId);
	
	List<District> findAllDistrict();
	
	List<District> findAllDistrictByIdCity(List<District> districts,int idCity);

	District findDistrictById(int districtId);

}
