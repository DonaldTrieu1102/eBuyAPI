package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.WardDao;
import net.ebuy.apiapp.model.Ward;

@Transactional
@Service("wardService")
public class WardServiceImpl implements WardService{

	@Autowired
	WardDao dao;
	@Override
	public Ward findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveWard(Ward ward) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteWard(Integer wardId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Ward> findAllCity() {
		// TODO Auto-generated method stub
		return dao.findAllCity();
	}

}
