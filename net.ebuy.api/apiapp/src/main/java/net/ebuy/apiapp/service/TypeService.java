package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.Type;

public interface TypeService {

	Type findById(int id);
	
	void saveType(Type type);
	
	void deleteTypeById(int id_type);
	
	List<Type> findAllType();

	Type findTypeById(int id);
	
	List<Object>findListTypeByIdListProduct(List<Type> listType, int idListProduct);
}
