package net.ebuy.apiapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ebuy.apiapp.helper.ResponseStatusEnum;
import net.ebuy.apiapp.model.BaseResponse;
import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.Product;
import net.ebuy.apiapp.service.CustomerService;
import net.ebuy.apiapp.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private CustomerService customerService;
	
	
	// get information customer by IdProduct
	@ResponseBody
	@RequestMapping(value = "/getCustomerByIdProduct",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> getInformationCustomer(HttpServletRequest request,
			@RequestParam(value="id_Product") int Id_Product){
		
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			Product product = productService.findProductById(Id_Product);
			Customer customer = customerService.findCustomerById(product.getId_customer().getId());

				Object data = new Object() {
					public final int id_customer = customer.getId();
					public final String avatar = customer.getAvatar();
					public final String usename = customer.getUsername();
					public final String address = customer.getAddress_full_text() +" " + customer.getStreetname() +
							", " + customer.getId_ward().getName() + ", " + customer.getId_district().getName()+
							", " + customer.getId_city().getName();

				};
			response.setData(data);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}
	
	
}
