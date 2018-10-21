package net.ebuy.apiapp.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ebuy.apiapp.service.CustomerService;
import net.ebuy.apiapp.helper.ResponseStatusEnum;
import net.ebuy.apiapp.model.BaseResponse;
import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.TokenModel;


@RestController
public class BaseController {

	@SuppressWarnings("unused")
	@Autowired
	private CustomerService customerService;
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<BaseResponse> handleMissingParams(MissingServletRequestParameterException ex) {
	    // Actual exception handling
	    BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.MISSING_PARAMS);
		response.setMessageError(String.format("%s is required!", ex.getParameterName()));
		response.setData(null);
		
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	
	public Customer getCustomer(HttpServletRequest request) {
		try {
			String authorizeHeader = request.getHeader("Authorization");
			String token = authorizeHeader.replace("Bearer ", "");;
			ObjectMapper mapper = new ObjectMapper();
			TokenModel data = mapper.readValue(decodeJWTToken(token), TokenModel.class);
			Customer customer = customerService.findCustomerByUserName(data.getUser_name());
			return customer;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
		
	public String decodeJWTToken(String token){
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        return body;        
    }
}
