package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.Product;
/**
 * @author Donald Trieu
 *
 */
public interface ProductDao {

	Product findById(int id);
	
	List<Product> findAllProduct();

	Product findProductById(int id);
	
	void savaProduct(Product product);
	
	Product findNewProduct(List<Product> products, int customerId, String created_at);
		
	// Tìm một list id_product bằng id_list_product
	List<Integer> findListIdProductByIdListProduct(List<Product>  listProduct,int idListProduct);

	// Tìm một list id_product bằng id_type
	List<Integer> findListIdProductByIdType(List<Product>  listProduct,int idType);
	
	// Tìm một list id_product bằng id_type_product
	List<Integer> findListIdProductByIdTypeProduct(List<Product>  listProduct,int idTypeProduct);
	
	List<Product> findListIdProductByIdCustomer(List<Product>  listProduct,int idCustomer);

	
}
