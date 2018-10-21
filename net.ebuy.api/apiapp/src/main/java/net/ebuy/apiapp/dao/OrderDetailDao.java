package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.OrderDetail;
/**
 * @author Donald Trieu
 *
 */
public interface OrderDetailDao {

	OrderDetail findById(int id);

	OrderDetail create(OrderDetail entity);

	void update(OrderDetail entity);
	
	void delete(OrderDetail entity);
	
	void createOrUpdate(OrderDetail entity);
	
	List<OrderDetail> findAllOrderDetails();

	List<OrderDetail> findOrderDetailsByCustomerId(int customerId);
	
	OrderDetail findOrderById(int orderId);
	
	OrderDetail findOrderDetailById(int orderDetailId);

	

}
