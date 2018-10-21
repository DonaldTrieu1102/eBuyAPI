package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.ListProduct;
/**
 * @author Donald Trieu
 *
 */
public interface ListProductService {
	
	ListProduct findById(int id);
	
	void saveListProduct(ListProduct listProduct);
	
	void deleteListProductById(int listProduct);
	
	List<ListProduct> findAllListProduct();

	ListProduct findListProductById(int id);
	
}
