package net.ebuy.apiapp.dao;

import java.util.List;

import net.ebuy.apiapp.model.OrderMid;
/**
 * @author Donald Trieu
 *
 */

public interface OrderMidDao {

	OrderMid findById(int id);
	
	OrderMid create(OrderMid entity);
	
	void update(OrderMid entity);
	
	void delete(OrderMid entity);
	
	void createOrUpdate(OrderMid entity);
	
	List<OrderMid> findAllOrderMid();
	
	List<OrderMid> findOrderMidByIdOrderDetail(List<OrderMid> orderMids,int idOrderDetail);

	

}
