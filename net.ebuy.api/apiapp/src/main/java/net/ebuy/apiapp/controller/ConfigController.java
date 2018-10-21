package net.ebuy.apiapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.ebuy.apiapp.helper.ResponseStatusEnum;
import net.ebuy.apiapp.model.BaseResponse;
/**
 * @author Donald Trieu
 *
 */

@RestController
@RequestMapping("/api")
public class ConfigController extends BaseController {

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
}
