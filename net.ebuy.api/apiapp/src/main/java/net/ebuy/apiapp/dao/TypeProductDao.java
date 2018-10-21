package net.ebuy.apiapp.dao;

import java.util.List;
import net.ebuy.apiapp.model.TypeProduct;
/**
 * @author Donald Trieu
 *
 */
public interface TypeProductDao {

	TypeProduct findById(int id);
	
	void saveTypeProduct(TypeProduct typeProduct);
	
	void deleteTypeProductById(int id_typeProduct);
	
	List<TypeProduct> findAllType();

	TypeProduct findTypeProductById(int id);
	
	List<Object>findListTypeProductByIdType(List<TypeProduct> listTypeProduct, int idListTypeProduct);
	
}
