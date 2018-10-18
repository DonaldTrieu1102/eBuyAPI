package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.Product;

public interface ProductService {
	
	Product findById(int id);
	
	List<Product> findAllProduct();

	Product findProductById(int id);
	
	List<Integer> findListIdProductByIdListProduct(List<Product>  listProduct,int idListProduct);

	List<Integer> findListIdProductByIdType(List<Product>  listProduct,int idType);
	
	List<Integer> findListIdProductByIdTypeProduct(List<Product>  listProduct,int idTypeProduct);
}
