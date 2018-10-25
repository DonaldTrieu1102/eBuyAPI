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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.ebuy.apiapp.helper.ResponseStatusEnum;
import net.ebuy.apiapp.model.BaseResponse;
import net.ebuy.apiapp.model.Ward;
import net.ebuy.apiapp.service.WardService;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/wards")
public class WardController {

	@Autowired
	private WardService wardService;

	@ResponseBody
	@RequestMapping(value = "/getall",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> get(HttpServletRequest request,
			@RequestParam(value="id_district") int idDistrict){
		
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			List<Object> data = new ArrayList<Object>();
			List<Ward> wards = wardService.findAllWard();
			List<Ward> wardsResponse = wardService.findAllWardByIdDistrict(wards, idDistrict);
			for(Ward ward: wardsResponse) {
				
				Object object = new  Object() {
					public final int id = ward.getId();
					public final String name_ward = ward.getName();
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
}
