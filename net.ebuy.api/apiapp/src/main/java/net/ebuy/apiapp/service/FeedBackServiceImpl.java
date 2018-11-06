package net.ebuy.apiapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ebuy.apiapp.dao.FeedBackDao;
import net.ebuy.apiapp.model.FeedBack;


@Transactional
@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	FeedBackDao dao;
	
	@Override
	public FeedBack findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public FeedBack saveFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub
		return dao.saveFeedBack(feedBack);
	}

	@Override
	public void updateFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub
		dao.updateFeedBack(feedBack);
	}

	@Override
	public void deleteFeedBack(FeedBack feedBack) {
		// TODO Auto-generated method stub
		dao.deleteFeedBack(feedBack);
	}

	@Override
	public List<FeedBack> findAllFeedBack() {
		// TODO Auto-generated method stub
		return dao.findAllFeedBack();
	}

	@Override
	public List<FeedBack> findListFeedBackByIdProductDetail(int idProductDetail) {
		// TODO Auto-generated method stub
		return dao.findListFeedBackByIdProductDetail(idProductDetail);
	}

	@Override
	public FeedBack findFeedBackById(int idFeedBack) {
		// TODO Auto-generated method stub
		return dao.findFeedBackById(idFeedBack);
	}

	@Override
	public List<FeedBack> findListFeedBackByIdCustomer(int idCustomer) {
		// TODO Auto-generated method stub
		return dao.findListFeedBackByIdCustomer(idCustomer);
	}

	@Override
	public FeedBack findFeedBackByIdCustomerAndIdProductDetail(int idCustomer, int idProductDetail) {
		// TODO Auto-generated method stub
		return dao.findFeedBackByIdCustomerAndIdProductDetail(idCustomer, idProductDetail);
	}
	
	

}
