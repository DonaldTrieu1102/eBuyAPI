package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.ListProduct;
/**
 * @author Donald Trieu
 *
 */
public interface ListProductDao {

	ListProduct findById(int id);
	
	void saveListProduct(ListProduct listProduct);
	
	void deleteListProductById(int listProduct);
	
	List<ListProduct> findAllListProduct();

	ListProduct findListProductById(int id);
	
}
