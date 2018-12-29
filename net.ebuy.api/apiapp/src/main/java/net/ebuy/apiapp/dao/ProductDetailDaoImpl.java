package net.ebuy.apiapp.dao;

import java.util.List;
import java.util.stream.Collectors;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.OrderDetail;
import net.ebuy.apiapp.model.Product;
import net.ebuy.apiapp.model.ProductDetail;
/**
 * @author Donald Trieu
 *
 */

@Repository("productDetailDao")
public class ProductDetailDaoImpl extends AbstractDao<Integer, ProductDetail> implements ProductDetailDao {

	@Autowired 
	SessionFactory sessionFactory;
	
	@Override
	public ProductDetail findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public void saveProductDetail(ProductDetail productDetail) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(productDetail);
	}

	@Override
	public void updateProductDetail(ProductDetail productDetail) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(productDetail);
	}
	
	@Override
	public void deleteProductDetailById(int productDetailId) {
		// TODO Auto-generated method stub
		  sessionFactory.getCurrentSession().createQuery("DELETE FROM productdetail WHERE Id = "+ productDetailId).executeUpdate();
		  sessionFactory.getCurrentSession().createQuery("DELETE FROM feeback WHERE Id_ = "+ productDetailId).executeUpdate();

	}

	@Override
	public List<ProductDetail> findAllProductDetail() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProductDetail.class);
		return criteria.list();
	}

	@Override
	public ProductDetail findProductDetailById(int idProductDetail) {
		// TODO Auto-generated method stub
		return (ProductDetail)sessionFactory.getCurrentSession().createCriteria(ProductDetail.class)
				.add(Restrictions.eq("id", idProductDetail))
				.uniqueResult();
	}

	@Override
	public List<ProductDetail> findListProductDetailByIdProduct(List<ProductDetail> list, int idProduct) {
		// TODO Auto-generated method stub
		
		List<ProductDetail> listProductDetail = list.stream()
				.filter(p -> p.getId_product().getId()== idProduct)
				.collect(Collectors.toList());
		
		return listProductDetail;
		
	}

//	@Override
//	public List<ProductDetail> findProductDetailByIdProduct(Product product) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public ProductDetail findProductDetailByIdProduct(Product product) {
//		// TODO Auto-generated method stub
//		return (ProductDetail)sessionFactory.getCurrentSession().createCriteria(ProductDetail.class)
//				.add(Restrictions.eq("id_product", product))
//				.uniqueResult();
//	}

	
}
