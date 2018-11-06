package net.ebuy.apiapp.service;

import java.util.List;

import net.ebuy.apiapp.model.FeedBack;

public interface FeedBackService {
FeedBack findById(int id);
	
	FeedBack saveFeedBack(FeedBack feedBack);
	
	void updateFeedBack(FeedBack feedBack);
	
	void deleteFeedBack(FeedBack feedBack);
	
	List<FeedBack> findAllFeedBack();
	
	List<FeedBack> findListFeedBackByIdProductDetail(int idProductDetail);
	
	FeedBack findFeedBackById(int idFeedBack);
	
	List<FeedBack> findListFeedBackByIdCustomer(int idCustomer);
	
	FeedBack findFeedBackByIdCustomerAndIdProductDetail(int idCustomer, int idProductDetail);

}
