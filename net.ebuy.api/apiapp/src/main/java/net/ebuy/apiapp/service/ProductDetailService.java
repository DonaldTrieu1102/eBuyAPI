package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.ProductDetail;

public interface ProductDetailService {

ProductDetail findById(int id);
	
	void saveProductDetail(ProductDetail productDetail);
	
	void deleteProductDetailById(int productDetailId);
	
	List<ProductDetail> findAllProductDetail();

	ProductDetail findProductDetailrById(int id);
		
	List<ProductDetail> findListProductDetailByIdProduct(List<ProductDetail> list,int idProduct);

}
