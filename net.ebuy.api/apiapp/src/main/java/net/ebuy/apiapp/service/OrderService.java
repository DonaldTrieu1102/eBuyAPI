package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.Order;
/**
 * @author Donald Trieu
 *
 */
public interface OrderService {


	Order findById(int id);

	Order create(Order entity);

	void update(Order entity);
	
	void delete(Order entity);
	
	void createOrUpdate(Order entity);
	
	List<Order> findAllOrders();
	
	Order findNewOrder(List<Order> orders,int customerId, String created_at);

	List<Order> findOrderByCustomerId(int customerId);
	
	Order findOrderById(int orderId);
	
}
