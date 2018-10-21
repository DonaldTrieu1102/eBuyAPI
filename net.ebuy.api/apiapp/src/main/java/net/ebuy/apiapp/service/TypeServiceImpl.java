package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.TypeDao;
import net.ebuy.apiapp.model.Type;
/**
 * @author Donald Trieu
 *
 */
@Transactional
@Service("typeService")
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeDao dao;

	@Override
	public Type findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void saveType(Type type) {
		// TODO Auto-generated method stub
		dao.saveType(type);
	}

	@Override
	public void deleteTypeById(int id_type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Type> findAllType() {
		// TODO Auto-generated method stub
		return dao.findAllType();
	}

	@Override
	public Type findTypeById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Object> findListTypeByIdListProduct(List<Type> listType, int idListProduct) {
		// TODO Auto-generated method stub
		return dao.findListTypeByIdListProduct(listType, idListProduct);
	}
	
	

}
