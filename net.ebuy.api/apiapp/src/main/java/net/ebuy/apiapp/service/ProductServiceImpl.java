package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.ProductDao;
import net.ebuy.apiapp.model.Product;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao dao;
	
	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		return dao.findAllProduct();
	}

	@Override
	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Integer> findListIdProductByIdListProduct(List<Product> listProduct, int idListProduct) {
		// TODO Auto-generated method stub
		return dao.findListIdProductByIdListProduct(listProduct, idListProduct);
	}

	@Override
	public List<Integer> findListIdProductByIdType(List<Product> listProduct, int idType) {
		// TODO Auto-generated method stub
		return dao.findListIdProductByIdType(listProduct, idType);
	}

	@Override
	public List<Integer> findListIdProductByIdTypeProduct(List<Product> listProduct, int idTypeProduct) {
		// TODO Auto-generated method stub
		return dao.findListIdProductByIdTypeProduct(listProduct, idTypeProduct);
	}

}
