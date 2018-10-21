package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.Type;
/**
 * @author Donald Trieu
 *
 */
public interface TypeDao {

	Type findById(int id);
	
	void saveType(Type type);
	
	void deleteTypeById(int id_type);
	
	List<Type> findAllType();

	Type findTypeById(int id);
	
	List<Object>findListTypeByIdListProduct(List<Type> listType, int idListProduct);
	
}
