package net.ebuy.apiapp.service;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.OrderMidDao;
import net.ebuy.apiapp.model.OrderMid;
/**
 * @author Donald Trieu
 *
 */

@Transactional
@Service("orderMidService")
public class OrderMidServiceImpl implements OrderMidService {

	@Autowired
	private OrderMidDao dao;
	
	@Override
	public OrderMid findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public OrderMid create(OrderMid entity) {
		// TODO Auto-generated method stub
		return dao.create(entity);
	}

	@Override
	public void update(OrderMid entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

	@Override
	public void delete(OrderMid entity) {
		// TODO Auto-generated method stub
		dao.delete(entity);
	}

	@Override
	public void createOrUpdate(OrderMid entity) {
		// TODO Auto-generated method stub

		dao.createOrUpdate(entity);
	}

	@Override
	public List<OrderMid> findAllOrderMid() {
		// TODO Auto-generated method stub
		return dao.findAllOrderMid();
	}

	@Override
	public List<OrderMid> findOrderMidByIdOrderDetail(List<OrderMid> orderMids, int idOrderDetail) {
		// TODO Auto-generated method stub
		return dao.findOrderMidByIdOrderDetail(orderMids, idOrderDetail);
	}

	

}
