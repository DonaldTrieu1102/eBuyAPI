package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.TypeDao;
import net.ebuy.apiapp.dao.TypeProductDao;
import net.ebuy.apiapp.model.TypeProduct;

@Transactional
@Service("typeProductService")
public class TypeProductServiceImpl implements TypeProductService {

	@Autowired
	private TypeProductDao dao;
	@Override
	public TypeProduct findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void saveTypeProduct(TypeProduct typeProduct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTypeProductById(int id_typeProduct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TypeProduct> findAllType() {
		// TODO Auto-generated method stub
		return dao.findAllType();
	}

	@Override
	public TypeProduct findTypeProductById(int id) {
		// TODO Auto-generated method stub
		return dao.findTypeProductById(id);
	}

	@Override
	public List<Object> findListTypeProductByIdType(List<TypeProduct> listTypeProduct, int idListTypeProduct) {
		// TODO Auto-generated method stub
		return dao.findListTypeProductByIdType(listTypeProduct, idListTypeProduct);
	}

}
