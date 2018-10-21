package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.ListProductDao;
import net.ebuy.apiapp.model.ListProduct;
/**
 * @author Donald Trieu
 *
 */

@Transactional
@Service("listProductService")
public class ListProductServiceImpl implements ListProductService {

	@Autowired
	private ListProductDao dao;

	@Override
	public ListProduct findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public void saveListProduct(ListProduct listProduct) {
		// TODO Auto-generated method stub
		dao.saveListProduct(listProduct);
	}

	@Override
	public void deleteListProductById(int listProduct) {
		// TODO Auto-generated method stub
		dao.deleteListProductById(listProduct);
	}

	@Override
	public List<ListProduct> findAllListProduct() {
		// TODO Auto-generated method stub
		return dao.findAllListProduct();
	}

	@Override
	public ListProduct findListProductById(int id) {
		// TODO Auto-generated method stub
		return dao.findListProductById(id);
	}
	
	

}
