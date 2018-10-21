package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.model.Order;
import net.ebuy.apiapp.model.OrderDetail;
/**
 * @author Donald Trieu
 *
 */
@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public Order create(Order entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}

	@Override
	public void update(Order entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(entity);

	}

	@Override
	public void createOrUpdate(Order entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(entity);

	}
	
	@Override
	public void delete(Order entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public List<Order> findAllOrders() {
		// TODO Auto-generated method stub
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Order.class);
		return criteria.list();	
	}

	@Override
	public List<Order> findOrderByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findNewOrder(List<Order> orders, int customerId, String datetime) {
		// TODO Auto-generated method stub
		
		Order order = orders.stream()
				.filter(p -> p.getCreatedAtFormatVN().equals(datetime)&&p.getCustomer_id() == customerId)
				.findFirst()
				.orElse(null);
		
		return order;		
	}
}
