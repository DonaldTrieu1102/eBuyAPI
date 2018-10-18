package net.ebuy.apiapp.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ebuy.apiapp.config.ApplicationProperties;
import net.ebuy.apiapp.helper.ResponseStatusEnum;
import net.ebuy.apiapp.model.BaseResponse;
import net.ebuy.apiapp.model.Customer;
import net.ebuy.apiapp.model.TokenModel;
import net.ebuy.apiapp.model.request.LoginWrapper;
import net.ebuy.apiapp.model.response.TokenCustomerResponse;
import net.ebuy.apiapp.service.CustomerService;
import net.ebuy.apiapp.utils.Utils;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ApplicationProperties config;
	
	
	@RequestMapping(value = "/configs", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse> configs(HttpServletRequest request) {
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			byte[] encodedBytes = Base64.encodeBase64("adam_clientid:XY7kmzoNzl100".getBytes());
			String apiKey = new String(encodedBytes);
			Object data = new Object() {
				public final String api_key = apiKey;
				public final String type = "Basic";
			};
			response.setData(data);

		} catch (Exception ex) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(ex.getMessage());
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	
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
					.bodyForm(Form.form().add("grant_type", "password").add("username", username + ",customer")
							.add("password", Utils.decodeBase64String(password)).build())//
					.addHeader("Authorization", authHeader).execute().returnResponse();

			ObjectMapper mapper = new ObjectMapper();
			TokenCustomerResponse data = mapper.readValue(EntityUtils.toString(httpResponse.getEntity()),
					TokenCustomerResponse.class);

			TokenModel tokenModel = mapper.readValue(decodeJWTToken(data.getAccess_token()), TokenModel.class);
			Customer customer = customerService.findCustomerByUserName(tokenModel.getUser_name());
			data.setUsername(customer.getUsername());
			data.setCustomerId(customer.getId());
			data.setName(customer.getFullname());
			data.setEmail(customer.getEmail());
			data.setPhoneNumber(customer.getPhone_number());
			data.setAvatar("");
			data.setToken_type("Bearer");
			return data;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}

