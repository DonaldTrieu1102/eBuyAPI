package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.OrderMid;
/**
 * @author Donald Trieu
 *
 */
public interface OrderMidService {

	OrderMid findById(int id);
	
	OrderMid create(OrderMid entity);
	
	void update(OrderMid entity);
	
	void delete(OrderMid entity);
	
	void createOrUpdate(OrderMid entity);
	
	List<OrderMid> findAllOrderMid();
	
}
