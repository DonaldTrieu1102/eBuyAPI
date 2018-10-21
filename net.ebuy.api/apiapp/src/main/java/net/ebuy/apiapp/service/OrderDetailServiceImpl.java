package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.AbstractDao;
import net.ebuy.apiapp.dao.OrderDetailDao;
import net.ebuy.apiapp.model.OrderDetail;
/**
 * @author Donald Trieu
 *
 */
@Transactional
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailDao dao;
	
	@Override
	public OrderDetail findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public OrderDetail create(OrderDetail entity) {
		// TODO Auto-generated method stub
		return dao.create(entity);
	}
	@Override
	public void update(OrderDetail entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

	@Override
	public void delete(OrderDetail entity) {
		// TODO Auto-generated method stub
		dao.delete(entity);
	}

	@Override
	public void createOrUpdate(OrderDetail entity) {
		// TODO Auto-generated method stub
		dao.createOrUpdate(entity);
	}
	
	@Override
	public List<OrderDetail> findAllOrderDetails() {
		// TODO Auto-generated method stub
		return dao.findAllOrderDetails();
	}

	@Override
	public List<OrderDetail> findOrderDetailsByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return dao.findOrderDetailsByCustomerId(customerId);
	}

	@Override
	public OrderDetail findOrderById(int orderId) {
		// TODO Auto-generated method stub
		return dao.findOrderById(orderId);
	}

	@Override
	public OrderDetail findOrderDetailById(int orderDetailId) {
		// TODO Auto-generated method stub
		return dao.findOrderDetailById(orderDetailId);
	}



}
