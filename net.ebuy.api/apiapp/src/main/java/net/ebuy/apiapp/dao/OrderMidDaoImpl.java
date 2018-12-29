package net.ebuy.apiapp.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.OrderMid;
/**
 * @author Donald Trieu
 *
 */
@Repository("orderMidDao")
public class OrderMidDaoImpl extends AbstractDao<Integer, OrderMid> implements OrderMidDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public OrderMid findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public OrderMid create(OrderMid entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(entity);
		return null;
	}

	@Override
	public void update(OrderMid entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(entity);

	}

	@Override
	public void createOrUpdate(OrderMid entity) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(entity);

	}

	@Override
	public List<OrderMid> findAllOrderMid() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderMid.class);
		return criteria.list();
	}

	@Override
	public List<OrderMid> findOrderMidByIdOrderDetail(List<OrderMid> orderMids, int idOrderDetail) {
		// TODO Auto-generated method stub
		List<OrderMid> orderMidResponse = orderMids.stream().filter(p->p.getId_order_detail()== idOrderDetail)
				.collect(Collectors.toList());
		return orderMidResponse;
	}

	

}
