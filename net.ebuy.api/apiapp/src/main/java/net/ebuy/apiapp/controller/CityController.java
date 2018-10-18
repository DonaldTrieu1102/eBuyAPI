package net.ebuy.apiapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ebuy.apiapp.model.City;
import net.ebuy.apiapp.model.BaseResponse;
import net.ebuy.apiapp.service.CityService;
import net.ebuy.apiapp.helper.ResponseStatusEnum;


/**
 * @author Donald Trieu
 *
 */
@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/cities")
public class CityController {
	
	@Autowired
	private CityService cityService;

	@ResponseBody
	@RequestMapping(value = "/getall",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> get(HttpServletRequest request){
		
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			List<Object> data = new ArrayList<Object>();
			List<City> cities = cityService.findAllCity();
			for(City city: cities) {
				data.add(city);
			}
			response.setData(data);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}
	

}
