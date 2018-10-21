package net.ebuy.apiapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.OrderDao;
import net.ebuy.apiapp.model.Order;
/**
 * @author Donald Trieu
 *
 */
@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;
	
	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public Order create(Order entity) {
		// TODO Auto-generated method stub
		return dao.create(entity);
	}

	@Override
	public void update(Order entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

	@Override
	public void delete(Order entity) {
		// TODO Auto-generated method stub
		dao.delete(entity);
	}

	@Override
	public void createOrUpdate(Order entity) {
		// TODO Auto-generated method stub
		dao.createOrUpdate(entity);
	}

	@Override
	public List<Order> findAllOrders() {
		// TODO Auto-generated method stub
		return dao.findAllOrders();
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
	public Order findNewOrder(List<Order> orders, int customerId, String created_at) {
		// TODO Auto-generated method stub
		return dao.findNewOrder(orders, customerId, created_at);
	}

}
