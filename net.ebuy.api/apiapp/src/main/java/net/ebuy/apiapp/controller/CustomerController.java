package net.ebuy.apiapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import net.ebuy.apiapp.model.ListProduct;
import net.ebuy.apiapp.model.Order;
import net.ebuy.apiapp.model.OrderDetail;
import net.ebuy.apiapp.model.OrderMid;
import net.ebuy.apiapp.model.Product;
import net.ebuy.apiapp.model.ProductDetail;
import net.ebuy.apiapp.model.TokenModel;
import net.ebuy.apiapp.model.Type;
import net.ebuy.apiapp.model.TypeProduct;
import net.ebuy.apiapp.model.request.AddProductDetailWrapper;
import net.ebuy.apiapp.model.request.ChangePasswordWrapper;
import net.ebuy.apiapp.model.request.CommentWrapper;
import net.ebuy.apiapp.model.request.LoginWrapper;
import net.ebuy.apiapp.model.request.OrderDetailRequest;
import net.ebuy.apiapp.model.request.OrderWrapper;
import net.ebuy.apiapp.model.request.ProductDetailWrapper;
import net.ebuy.apiapp.model.request.RegisterCustomerWrapper;
import net.ebuy.apiapp.model.request.UpdateProfile;
import net.ebuy.apiapp.model.response.TokenCustomerResponse;
import net.ebuy.apiapp.service.CustomerService;
import net.ebuy.apiapp.service.FeedBackService;
import net.ebuy.apiapp.service.ListProductService;
import net.ebuy.apiapp.service.OrderDetailService;
import net.ebuy.apiapp.service.OrderMidService;
import net.ebuy.apiapp.service.OrderService;
import net.ebuy.apiapp.service.ProductDetailService;
import net.ebuy.apiapp.service.ProductService;
import net.ebuy.apiapp.service.TypeProductService;
import net.ebuy.apiapp.service.TypeService;
import net.ebuy.apiapp.utils.Utils;

import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ListProductService listProductService;
	
	@Autowired
	private TypeProductService typeProductService;
	
	@Autowired
	private TypeService typeService;
		
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
	// register account
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> register(HttpServletRequest request,
			@RequestBody RegisterCustomerWrapper wrapper) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			if (wrapper.getPassword().length() < 6) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("Mật khẩu phải lớn hơn 6 ký tự");
			} else if (wrapper.getUsername().isEmpty()) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("Tên đăng nhập không được bỏ trống");
			} else if (wrapper.getPhoneNumber().isEmpty()) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError("Số điện thoại không hợp lệ");
			} else {
				Customer customer = customerService.findCustomerByUserName(wrapper.getUsername());
				if (customer != null) {
					response.setStatus(ResponseStatusEnum.FAIL);
					response.setMessageError("Username đã được sử dụng");
				} else {
					customer = customerService.findCustomerByPhoneNumber(wrapper.getPhoneNumber());
					if (customer != null) {
						response.setStatus(ResponseStatusEnum.FAIL);
						response.setMessageError("Số điện thoại đã được sử dụng");
					} else {

						customer = new Customer();
						customer.setPassword(Utils.encodePassword(wrapper.getPassword()));
						customer.setUsername(wrapper.getUsername());
						customer.setPhone_number(wrapper.getPhoneNumber());
						customer.setStatus(true);
						customerService.saveCustomer(customer);
					}
				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	// change pass word
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/change-password", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> changePassword(HttpServletRequest request,
			@RequestBody ChangePasswordWrapper wrapper) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			Customer customer = getCustomer(request);
			if (customer == null) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			} else {
				if (wrapper.getNewPassword().length() < 6) {
					response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
					response.setMessageError("Mật khẩu mới phải lớn hơn 6 ký tự");
				} else if (!customer.getPassword().equals(Utils.encodePassword(wrapper.getOldPassword()))) {
					response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
					response.setMessageError("Mật khẩu cũ không chính xác");
				} else {
					customer.setPassword(Utils.encodePassword(wrapper.getNewPassword()));
					customer.setUpdatedAt(new Date());
					customerService.saveCustomer(customer);
				}
			}
		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	
	// update_profile
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/update_profile", method = RequestMethod.POST, consumes = {
			MediaType.ALL_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> updateProfile(HttpServletRequest request,
			@RequestBody UpdateProfile wrapper) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			Customer customer = getCustomer(request);
			if (customer == null) {
				response.setStatus(ResponseStatusEnum.UNAUTHORIZED);
				response.setMessage(ResponseStatusEnum.UNAUTHORIZED);
			} else {
					customer.setAvatar(wrapper.getAvatar());
					customer.setUpdatedAt(new Date());
					customerService.saveCustomer(customer);
				
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
			tokenCustomerResponse.setAvatar(customer.getAvatar());
			tokenCustomerResponse.setAccess_token(data.getAccess_token());
			tokenCustomerResponse.setToken_type("Bearer");
			
			return tokenCustomerResponse;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	// get all customer
	@RequestMapping(value = "/getall", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> getAllCustomer(HttpServletRequest request) {
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
					public final String avatar = customer.getAvatar();
					public final String usename = customer.getUsername();
					public final String address = customer.getAddress_full_text() +" " + customer.getStreetname() +
							", " + customer.getId_ward().getName() + ", " + customer.getId_district().getName()+
							", " + customer.getId_city().getName();
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
	// get all customer
		@RequestMapping(value = "/getCustomerById", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
		@ResponseBody
		public ResponseEntity<BaseResponse> getCustomerById(HttpServletRequest request, @RequestParam (value="id") int id) {
			BaseResponse response = new BaseResponse();
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setMessage(ResponseStatusEnum.SUCCESS);
			response.setData(null);
			try {
				
				Customer customer = customerService.findCustomerById(id);
				Object object = new Object() {
					public final int id_customer = customer.getId();
					public final String avatar = customer.getAvatar();
					public final String usename = customer.getUsername();
					public final String address = customer.getAddress_full_text() +" " + customer.getStreetname() +
							", " + customer.getId_ward().getName() + ", " + customer.getId_district().getName()+
							", " + customer.getId_city().getName();
				};
				response.setData(object);			
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
	// get orderdetail with status = 0
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/getAllOrderDetail", method = RequestMethod.GET, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> getOrderDetail(HttpServletRequest request) {
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
				
				List<OrderDetail> orderDetails = orderDetailService.findAllOrderDetails()
						.stream()
						.filter(x-> x.getId_customer()==customer.getId() 
						&& x.getStatus() == false)
						.collect(Collectors.toList());
				List<Object> orderDetailResponse = new ArrayList<>();
				for(OrderDetail orderDetail: orderDetails) {
					ProductDetail productDetail = productDetailService.findProductDetailById(orderDetail.getId_product_detail());
					Object object = new Object() {
						public final int id = orderDetail.getId();
						public final int id_product_detail = orderDetail.getId_product_detail();
						public final int id_customer = orderDetail.getId_customer();
						public final String name = orderDetail.getName();
						public final String avatar = productDetail.getImage_product_detail();
						public final float price = orderDetail.getPrice();
						public final float quantity = orderDetail.getPrice();
						public final float amount = orderDetail.getAmount();
						public final Boolean status = orderDetail.getStatus();
					};
					orderDetailResponse.add(object);
				}
				response.setData(orderDetailResponse);	
			}
		}catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	
	
	// add order with status = 0 and status order detail = 1
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
					order.setStreetname(wrapper.getStreetname());
					order.setAddress_full_text(wrapper.getAddress_full_text());
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
	@PreAuthorize("hasRole('CUSTOMER')")
	@RequestMapping(value = "/{id}/add_product_detail", method = RequestMethod.POST, consumes = {MediaType.ALL_VALUE }, produces = {MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseEntity<BaseResponse> addproductdetail(HttpServletRequest request,
			@RequestBody AddProductDetailWrapper wrapper){
		
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
				Product product = new Product();
				ListProduct listProduct = listProductService.findListProductById(wrapper.getId_list());
				Type type = typeService.findTypeById(wrapper.getId_type());
				TypeProduct typeProduct = typeProductService.findTypeProductById(wrapper.getId_type_product());
				
				product.setId_list(listProduct);
				product.setId_type(type);
				product.setId_type_product(typeProduct);
				product.setId_customer(customer);
				product.setStatus(true);
				
				productService.saveProduct(product);
				
				List<Product> products = productService.findAllProduct();
				
				Product newProduct = productService.findNewProduct(products, customer.getId(), product.getCreatedAtFormatVN());
				
				
				ProductDetail productDetail = new ProductDetail();
				productDetail.setId_product(newProduct);
				productDetail.setName_product_detail(wrapper.getName_product());
				productDetail.setImage_product_detail(wrapper.getImage());
				productDetail.setPrice_product_detail(wrapper.getPrice());
				productDetail.setQuantity_product_detail(wrapper.getQuantity());
				productDetail.setWeigh_product_detail(wrapper.getWeight());
				productDetail.setProduct_status(wrapper.getProduct_status());
				productDetail.setDescribe_product_detail(wrapper.getDescribe());
				productDetail.setMaterial_product_detail(wrapper.getMaterial());
				productDetail.setTrademark_product_detail(wrapper.getTrademark());
				productDetail.setStatus(true);
				
				productDetailService.saveProductDetail(productDetail);
				
			}
			
		}catch(Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
			ex.printStackTrace();
		}
		
		
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		
	}
	
	
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
	// get ordered of customer
	
		
}

