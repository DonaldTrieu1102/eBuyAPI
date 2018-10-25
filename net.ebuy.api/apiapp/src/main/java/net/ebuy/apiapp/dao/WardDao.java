package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.Ward;

public interface WardDao {
	Ward findById(int id);
	
	void saveWard(Ward ward);
	
	void deleteWard(Integer wardId);
	
	List<Ward> findAllWard();
	
	List<Ward> findAllWardByIdDistrict(List<Ward> wards,int idDistrict);

}
