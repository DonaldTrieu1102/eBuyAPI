package net.ebuy.apiapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.ebuy.apiapp.model.FeedBack;


@Repository("feedBackDao")
public class FeedBackDaoImpl extends AbstractDao<Integer, FeedBack> implements FeedBackDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public FeedBack findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public FeedBack saveFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(feedBack);
		return feedBack;
	}

	@Override
	public void updateFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(feedBack);

	}

	@Override
	public void deleteFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(feedBack);

	}

	@Override
	public List<FeedBack> findAllFeedBack() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FeedBack.class);
		return criteria.list();
	}

	@Override
	public List<FeedBack> findListFeedBackByIdProductDetail(int idProductDetail) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FeedBack.class)
				.add(Restrictions.eq("id_product_detail", idProductDetail));
		return criteria.list();
	}

	@Override
	public FeedBack findFeedBackById(int idFeedBack) {
		// TODO Auto-generated method stub
		return (FeedBack)sessionFactory.getCurrentSession().get(FeedBack.class, idFeedBack);
	}

	@Override
	public List<FeedBack> findListFeedBackByIdCustomer(int idCustomer) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FeedBack.class)
				.add(Restrictions.eq("id_customer", idCustomer));
		return criteria.list();	
	}

	@Override
	public FeedBack findFeedBackByIdCustomerAndIdProductDetail(int idCustomer, int idProductDetail) {
		// TODO Auto-generated method stub
		return (FeedBack) sessionFactory.getCurrentSession().createCriteria(FeedBack.class)
				.add(Restrictions.eq("id_customer", idCustomer))
				.add(Restrictions.eq("id_product_detail", idProductDetail))
				.uniqueResult();
	}

	

}
