package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.OrderDetail;
/**
 * @author Donald Trieu
 *
 */
public interface OrderDetailService {

	OrderDetail findById(int id);

	OrderDetail create(OrderDetail entity);

	void update(OrderDetail entity);
	
	void delete(OrderDetail entity);
	
	void createOrUpdate(OrderDetail entity);
	
	List<OrderDetail> findAllOrderDetails();

	List<OrderDetail> findOrderDetailsByCustomerId(int customerId);
	
	OrderDetail findOrderById(int orderId);
	
	OrderDetail findOrderDetailById(int orderDetailId);
	
	List<OrderDetail> findOrderDetailsByIdProductDetail(List<OrderDetail> orderDetails,int idproductDetail);

}
