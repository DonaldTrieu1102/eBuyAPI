package net.ebuy.apiapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ebuy.apiapp.helper.ResponseStatusEnum;
import net.ebuy.apiapp.model.BaseResponse;
import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.District;
import net.ebuy.apiapp.model.FeedBack;
import net.ebuy.apiapp.service.CustomerService;
import net.ebuy.apiapp.service.FeedBackService;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedBackController {
	
	@Autowired
	FeedBackService feedBackService;
	
	@Autowired
	CustomerService customerService;
	
	@ResponseBody
	@RequestMapping(value="/getAllByIdProductDetail", method = org.springframework.web.bind.annotation.RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> get(HttpServletRequest request, 
			@RequestParam(value="id_product_detail") int id_product_detail){
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		
		try {
			List<Object> data = new ArrayList<Object>();
			List<FeedBack> feedBacks = feedBackService.findListFeedBackByIdProductDetail(id_product_detail);
			for(FeedBack feedBack : feedBacks) {
				int id_customer = feedBack.getId_customer();
				Customer customer = customerService.findCustomerById(id_customer);
				Object object = new  Object() {
					public final int id = feedBack.getId();
					public final String name_customer = customer.getUsername();
					public final int id_product_detail = feedBack.getId_product_detail();
					public final int express = feedBack.getExpress();
					public final int feedback = feedBack.getFeedback();
					public final String comment = feedBack.getComment();
				};
				data.add(object);
			}
			response.setData(data);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="/getStar", method = org.springframework.web.bind.annotation.RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> getStar(HttpServletRequest request, 
			@RequestParam(value="id_product_detail") int id_product_detail){
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		
		try {
			List<FeedBack> feedBacks = feedBackService.findListFeedBackByIdProductDetail(id_product_detail);
			int countStar = 0;
			for(FeedBack feedBack : feedBacks) {
				countStar += feedBack.getFeedback();
			}
			float count = (float)countStar/feedBacks.size();
			Object object = new  Object() {
				public final float countstar = count;
				public final int countfeedback = feedBacks.size();
			};
			response.setData(object);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value="/getCountLike", method = org.springframework.web.bind.annotation.RequestMethod.GET,produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> getCountLike(HttpServletRequest request, 
			@RequestParam(value="id_product_detail") int id_product_detail){
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		
		try {
			List<FeedBack> feedBacks = feedBackService.findListFeedBackByIdProductDetail(id_product_detail);
			int countLike = 0;
			for(FeedBack feedBack : feedBacks) {
				if(feedBack.getExpress()==1) {
					countLike += 1;
				}
			}
			int count = countLike;
			Object object = new  Object() {
				public final float countlike = count;
			};
			response.setData(object);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

}
