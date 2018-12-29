package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.DistrictDao;
import net.ebuy.apiapp.model.District;


@Transactional
@Service("districtService")
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	DistrictDao dao;
	@Override
	public District findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDistrict(District district) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDistrict(Integer districtId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<District> findAllDistrict() {
		// TODO Auto-generated method stub
		return dao.findAllDistrict();
	}

	@Override
	public List<District> findAllDistrictByIdCity(List<District> districts, int idCity) {
		// TODO Auto-generated method stub
		return dao.findAllDistrictByIdCity(districts, idCity);
	}

	@Override
	public District findDistrictById(int districtId) {
		// TODO Auto-generated method stub
		return dao.findDistrictById(districtId);
	}

}
