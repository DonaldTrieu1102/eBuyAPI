package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.Order;
import net.ebuy.apiapp.model.OrderDetail;
/**
 * @author Donald Trieu
 *
 */
@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends AbstractDao<Integer, OrderDetail> implements OrderDetailDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public OrderDetail findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public OrderDetail create(OrderDetail entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}

	@Override
	public void update(OrderDetail entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
	
	@Override
	public void createOrUpdate(OrderDetail entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(OrderDetail entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public OrderDetail findOrderById(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> findAllOrderDetails() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderDetail.class);
		return criteria.list();	
		}
	@Override
	public List<OrderDetail> findOrderDetailsByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetail findOrderDetailById(int orderDetailId) {
		// TODO Auto-generated method stub
		return (OrderDetail)sessionFactory.getCurrentSession().createCriteria(OrderDetail.class)
				.add(Restrictions.eq("id", orderDetailId))
				.uniqueResult();
	}
//	@Override
//	public OrderDetail findExistsOrderDetail(int id_customer, int id_product_detail) {
//		// TODO Auto-generated method stub
//		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderDetail.class)
//		.add(Restrictions.eq("id_customer", id_customer))
//		.add(Restrictions.eq("id_product_detail", id_product_detail));
//		
//
//
//		return null;
//	}



	


}
