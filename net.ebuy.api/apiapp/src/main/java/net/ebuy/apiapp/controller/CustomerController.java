package net.ebuy.apiapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.mapper.Mapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.ebuy.apiapp.config.ApplicationProperties;
import net.ebuy.apiapp.helper.ResponseStatusEnum;
import net.ebuy.apiapp.model.BaseResponse;
import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.FeedBack;
import net.ebuy.apiapp.model.Order;
import net.ebuy.apiapp.model.OrderDetail;
import net.ebuy.apiapp.model.OrderMid;
import net.ebuy.apiapp.model.ProductDetail;
import net.ebuy.apiapp.model.TokenModel;
import net.ebuy.apiapp.model.request.CommentWrapper;
import net.ebuy.apiapp.model.request.LoginWrapper;
import net.ebuy.apiapp.model.request.OrderDetailRequest;
import net.ebuy.apiapp.model.request.OrderWrapper;
import net.ebuy.apiapp.model.request.ProductDetailWrapper;
import net.ebuy.apiapp.model.response.TokenCustomerResponse;
import net.ebuy.apiapp.service.CustomerService;
import net.ebuy.apiapp.service.FeedBackService;
import net.ebuy.apiapp.service.OrderDetailService;
import net.ebuy.apiapp.service.OrderMidService;
import net.ebuy.apiapp.service.OrderService;
import net.ebuy.apiapp.service.ProductDetailService;
import net.ebuy.apiapp.utils.Utils;


import org.apache.http.util.EntityUtils;

/**
 * @author Donald Trieu
 *
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired 
	private OrderDetailService orderDetailService;
	

	@Autowired 
	private OrderService orderService;
	
	@Autowired 
	private OrderMidService orderMidService;
	
	@Autowired 
	private ProductDetailService productDetailService;
	
	@Autowired
	private ApplicationProperties config;
	
	
	@Autowired
	private FeedBackService feedBackService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> login(HttpServletRequest request, @RequestBody LoginWrapper wrapper) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			try {

				TokenCustomerResponse data = LoginCustomer(wrapper.getUsername(), wrapper.getPassword(),
						request.getHeader("Authorization"));
				if (data == null) {
					response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
					response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
				} else {
					response.setData(data);
				}
			} catch (Exception ex) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}


	private TokenCustomerResponse LoginCustomer(String username, String password, String authHeader) {
		try {
			HttpResponse httpResponse = Request.Post(config.getOauthEndpoint())
					.bodyForm(Form.form().add("grant_type", "password").add("username", username)
							.add("password", Utils.decodeBase64String(password)).build())//
					.addHeader("Authorization", authHeader).execute().returnResponse();

			// readvalue lấy key:value của json 
			// Lấy TokenCustomerResponse từ key EntityUtils.toString(httpResponse.getEntity()
			ObjectMapper mapper = new ObjectMapper();
			TokenCustomerResponse data = mapper.readValue(EntityUtils.toString(httpResponse.getEntity()),
					TokenCustomerResponse.class);

			// Lấy TokenModel từ access_token
			// Lấy username trong token model
			TokenModel tokenModel = mapper.readValue(decodeJWTToken(data.getAccess_token()), TokenModel.class);
			Customer customer = customerService.findCustomerByUserName(tokenModel.getUser_name());
			
			TokenCustomerResponse tokenCustomerResponse = new TokenCustomerResponse();			
			tokenCustomerResponse.setUsername(customer.getUsername());
			tokenCustomerResponse.setCustomerId(customer.getId());
			tokenCustomerResponse.setName(customer.getFullname());
			tokenCustomerResponse.setEmail(customer.getEmail());
			tokenCustomerResponse.setPhoneNumber(customer.getPhone_number());
			tokenCustomerResponse.setAvatar("");
			tokenCustomerResponse.setAccess_token(data.getAccess_token());
			tokenCustomerResponse.setToken_type("Bearer");
			
			return tokenCustomerResponse;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	// get all customer
	@RequestMapping(value = "/{id}/getall", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> addOderDetail(HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			
			List<Customer> customers = customerService.findAllCustomers();
			List<Object> customerResponse = new ArrayList<>();
			for(Customer customer: customers) {
				Object object = new Object() {
					public final int id_customer = customer.getId();
					public final String name = customer.getUsername();
					public final String avatar = customer.getAvatar();
				};
				customerResponse.add(object);
			}
			response.setData(customerResponse);			
		}catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}
	// order detail with status = 0
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/add_orderDetail", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> addOderDetail(HttpServletRequest request,
			@RequestBody ProductDetailWrapper wrapper) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			Customer customer = getCustomer(request);
			if(customer == null) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			}else {
				OrderDetail existsOrderDetail = orderDetailService.findAllOrderDetails()
						.stream().
						filter(x-> x.getId_customer()==customer.getId() 
						&& x.getId_product_detail() == wrapper.getId_product_detail()
						&& x.getStatus() == false)
						.findAny()
						.orElse(null);
				OrderDetail orderDetail = new OrderDetail();

				if(existsOrderDetail == null) {
					orderDetail.setId_product_detail(wrapper.getId_product_detail());
					orderDetail.setName(wrapper.getName());
					orderDetail.setPrice(wrapper.getPrice());
					orderDetail.setQuantity(wrapper.getQuatity());
					orderDetail.setAmount(wrapper.getPrice()*wrapper.getQuatity());
					orderDetail.setId_customer(customer.getId());
					orderDetail.setStatus(false);
					orderDetailService.create(orderDetail);
				}else {					
					existsOrderDetail.setQuantity(wrapper.getQuatity()+existsOrderDetail.getQuantity());
					existsOrderDetail.setAmount(wrapper.getPrice()*wrapper.getQuatity() + existsOrderDetail.getAmount());
					orderDetailService.update(existsOrderDetail);
				}
				
				response.setMessage(ResponseStatusEnum.SUCCESS);
			}
			
		}catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}
	// order detail with status = 0
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/create_order", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> addOder(HttpServletRequest request,
				@RequestBody OrderWrapper wrapper) {
			BaseResponse response = new BaseResponse();
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setMessage(ResponseStatusEnum.SUCCESS);
			response.setData(null);
			try {
				Customer customer = getCustomer(request);
				if(customer == null) {
					response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
					response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
				}else {

					Order order = new Order();
					order.setId_city(wrapper.getId_city());
					order.setId_district(wrapper.getId_district());
					order.setId_ward(wrapper.getId_ward());
					order.setCustomer_id(customer.getId());
					order.setFee(wrapper.getFee());
					order.setAmount(wrapper.getAmount());
					order.setTotal_amount(wrapper.getTotal_amount());
					order.setOrder_status(true);
					orderService.create(order);

					List<Order> orders = orderService.findAllOrders();
					
					Order orderNew = orderService.findNewOrder(orders, customer.getId(), order.getCreatedAtFormatVN());
								
					for (OrderDetailRequest orderDetailRequest : wrapper.getOrderDetails()) {
						
						OrderMid orderMid = new OrderMid();
						orderMid.setId_order(orderNew.getId());
						orderMid.setId_order_detail(orderDetailRequest.getId());
						orderMidService.create(orderMid);
						
						OrderDetail orderDetail = orderDetailService.findOrderDetailById(orderDetailRequest.getId());
						orderDetail.setStatus(true);
						orderDetailService.update(orderDetail);
						
						ProductDetail productDetail = productDetailService.findProductDetailById(orderDetail.getId_product_detail());
						productDetail.setQuantity_product_detail(productDetail.getQuantity_product_detail() - orderDetail.getQuantity());
						productDetailService.updateProductDetail(productDetail);
						
					}
					response.setMessage(ResponseStatusEnum.SUCCESS);
				}
				
			}catch(Exception ex) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError(ex.getMessage());
				ex.printStackTrace();
			}
			
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

		}
	// customer add product detail
	
	// customer feedback product detail
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/addFeedBackExpress", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> addFeedBackExperss(HttpServletRequest request,
			@RequestParam (value="id_product_detail") int id_product_detail,
			@RequestParam (value="express") int express){
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		
		try {
			Customer customer = getCustomer(request);
			if(customer == null) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			}else {
				
				FeedBack feedBack = feedBackService.findFeedBackByIdCustomerAndIdProductDetail(customer.getId(), id_product_detail);		
				if(feedBack== null) {
					FeedBack newFeedBack = new FeedBack();
					newFeedBack.setExpress(express);
					newFeedBack.setId_product_detail(id_product_detail);
					newFeedBack.setId_customer(customer.getId());
					newFeedBack.setFeedback(0);
					newFeedBack.setComment("");
					newFeedBack.setStatus(true);
					feedBackService.saveFeedBack(newFeedBack);
				}else {
					feedBack.setExpress(express);
					feedBackService.updateFeedBack(feedBack);
				}
			}
			
			response.setMessage(ResponseStatusEnum.SUCCESS);
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/addFeedBack", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> addFeedBack(HttpServletRequest request,
			@RequestParam (value="id_product_detail") int id_product_detail,
			@RequestParam (value="feedback") int feedback){
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		
		try {
			Customer customer = getCustomer(request);
			if(customer == null) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			}else {
				
				FeedBack feedBack = feedBackService.findFeedBackByIdCustomerAndIdProductDetail(customer.getId(), id_product_detail);		
				if(feedBack== null) {
					FeedBack newFeedBack = new FeedBack();
					newFeedBack.setFeedback(feedback);
					newFeedBack.setExpress(0);
					newFeedBack.setComment("");
					newFeedBack.setId_product_detail(id_product_detail);
					newFeedBack.setId_customer(customer.getId());
					newFeedBack.setStatus(true);
					feedBackService.saveFeedBack(newFeedBack);
				}else {
					feedBack.setFeedback(feedback);
					feedBackService.updateFeedBack(feedBack);
				}
			}
			
			response.setMessage(ResponseStatusEnum.SUCCESS);
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/addComment", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> addComment(HttpServletRequest request,
			@RequestParam (value="id_product_detail") int id_product_detail,
			@RequestBody CommentWrapper commentWrapper){
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		
		try {
			Customer customer = getCustomer(request);
			if(customer == null) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			}else {
				
				FeedBack feedBack = feedBackService.findFeedBackByIdCustomerAndIdProductDetail(customer.getId(), id_product_detail);		
				if(feedBack== null) {
					FeedBack newFeedBack = new FeedBack();
					newFeedBack.setComment(commentWrapper.getComment());
					newFeedBack.setExpress(0);
					newFeedBack.setFeedback(0);
					newFeedBack.setId_product_detail(id_product_detail);
					newFeedBack.setId_customer(customer.getId());
					newFeedBack.setStatus(true);
					feedBackService.saveFeedBack(newFeedBack);
				}else {
					feedBack.setComment(commentWrapper.getComment());
					feedBackService.updateFeedBack(feedBack);
				}
			}
			
			response.setMessage(ResponseStatusEnum.SUCCESS);
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	// get express of customer
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/getLike", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> getLike(HttpServletRequest request,
			@RequestParam (value="id_product_detail") int id_product_detail){
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		
		try {
			Customer customer = getCustomer(request);
			int var = 0;
			if(customer == null) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			}else {
				FeedBack feedBack = feedBackService.findFeedBackByIdCustomerAndIdProductDetail(customer.getId(), id_product_detail);		
				if(feedBack== null) {
					var = 0;
				}else {
					var = feedBack.getExpress();
				}
			}
			int a = var;
			Object object = new  Object() {
				public final float type = a;
			};
			response.setData(object);
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
		
}

