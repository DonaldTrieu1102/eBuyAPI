package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.Ward;

public interface WardService {
	Ward findById(int id);
	
	void saveWard(Ward ward);
	
	void deleteWard(Integer wardId);
	
	List<Ward> findAllCity();
}
