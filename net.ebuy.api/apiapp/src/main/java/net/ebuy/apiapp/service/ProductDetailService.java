package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.ProductDetail;
/**
 * @author Donald Trieu
 *
 */
public interface ProductDetailService {

	ProductDetail findById(int id);
	
	void saveProductDetail(ProductDetail productDetail);
	
	void updateProductDetail(ProductDetail productDetail);

	
	void deleteProductDetailById(int productDetailId);
	
	List<ProductDetail> findAllProductDetail();

	ProductDetail findProductDetailById(int idProductDetail);
	
	List<ProductDetail> findListProductDetailByIdProduct(List<ProductDetail> list,int idProduct);
	


}
