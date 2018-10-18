package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.ProductDetail;

public interface ProductDetailDao {

	ProductDetail findById(int id);
	
	void saveProductDetail(ProductDetail productDetail);
	
	void deleteProductDetailById(int productDetailId);
	
	List<ProductDetail> findAllProductDetail();

	ProductDetail findProductDetailById(int id);
	
	List<ProductDetail> findListProductDetailByIdProduct(List<ProductDetail> list,int idProduct);

}
