package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.ListProduct;

@Repository("listProductDao")
public class ListProductDaoImpl extends AbstractDao<Integer, ListProduct> implements ListProductDao{

	@Autowired SessionFactory sessionFactory;

	@Override
	public ListProduct findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public void saveListProduct(ListProduct listProduct) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(listProduct);
	}

	@Override
	public void deleteListProductById(int listProduct) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ListProduct> findAllListProduct() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ListProduct.class);
		return criteria.list();
	}

	@Override
	public ListProduct findListProductById(int id) {
		// TODO Auto-generated method stub
		return (ListProduct) sessionFactory.getCurrentSession().get(ListProduct.class, id);
	}

	
	
}
