package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.model.ProductDetail;
import net.ebuy.apiapp.dao.ProductDetailDao;


@Transactional
@Service("productDetailService")
public class ProductDetailServiceImpl implements ProductDetailService {

	@Autowired
	private ProductDetailDao dao;
	@Override
	public ProductDetail findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void saveProductDetail(ProductDetail productDetail) {
		// TODO Auto-generated method stub
		dao.saveProductDetail(productDetail);
	}

	@Override
	public void deleteProductDetailById(int productDetailId) {
		// TODO Auto-generated method stub
		dao.deleteProductDetailById(productDetailId);
	}

	@Override
	public List<ProductDetail> findAllProductDetail() {
		// TODO Auto-generated method stub
		return dao.findAllProductDetail();
	}

	@Override
	public ProductDetail findProductDetailrById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<ProductDetail> findListProductDetailByIdProduct(List<ProductDetail> list,int idProduct) {
		// TODO Auto-generated method stub
		return dao.findListProductDetailByIdProduct(list,idProduct);
	}

}
