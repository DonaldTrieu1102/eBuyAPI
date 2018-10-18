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
import net.ebuy.apiapp.model.Product;
import net.ebuy.apiapp.model.ProductDetail;
import net.ebuy.apiapp.model.Type;
import net.ebuy.apiapp.service.ProductDetailService;
import net.ebuy.apiapp.service.ProductService;
import net.ebuy.apiapp.service.TypeService;

@RestController
@RequestMapping("/api/type")
public class TypeController {
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@ResponseBody
	@RequestMapping(value = "/gettype",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> getType(HttpServletRequest request,
			@RequestParam(value ="id_ListProduct") int id_ListProduct){
		
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			List<Type> listType = typeService.findAllType();
			List<Object> data = typeService.findListTypeByIdListProduct(listType, id_ListProduct);
		
			response.setData(data);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}

	// get list product detail with id_list_product
		@ResponseBody
		@RequestMapping(value = "/getProductDetailByIdType",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
		public ResponseEntity<BaseResponse> getProducDetailByIdType(HttpServletRequest request,
				@RequestParam(value ="id_Type") int id_Type){
			BaseResponse response = new BaseResponse();
			response.setStatus(ResponseStatusEnum.SUCCESS);
			response.setMessage(ResponseStatusEnum.SUCCESS);
			response.setData(null);
			try {
				List<Product> listProducts = productService.findAllProduct();
				
				List<ProductDetail> list = productDetailService.findAllProductDetail();
				
				List<Integer> listObject = productService.findListIdProductByIdType(listProducts, id_Type);
				
				List<ProductDetail> listProductDetails = new ArrayList<ProductDetail>();
				List<ProductDetail> listProductDetailResponse = new ArrayList<ProductDetail>();

				for(int i = 0 ;i < listObject.size();i++) {
					listProductDetails = productDetailService.findListProductDetailByIdProduct(list, listObject.get(i));
					for(ProductDetail productDetail : listProductDetails) {
						listProductDetailResponse.add(productDetail);
					}
				}
				List<Object> listProductResponse = new ArrayList<>();
				for(ProductDetail productDetail : listProductDetailResponse) {
					
					Object data = new Object() {
						public final int id_product_detail = productDetail.getId();
						public final int id_product = productDetail.getId_product().getId();
						public final String name_product_detail = productDetail.getName_product_detail();
						public final String image_product_detail = productDetail.getImage_product_detail();
						public final String size_product_detail = productDetail.getSize_product_detail();
						public final float price_product_detail = productDetail.getPrice_product_detail();
						public final String quantity_product_detail = productDetail.getQuantity_product_detail();
						public final String color_product_detail = productDetail.getColor_product_detail();
						public final String weigh_product_detail = productDetail.getWeigh_product_detail();
						public final boolean product_status = productDetail.getProduct_status();
						public final String describe_product_detail = productDetail.getDescribe_product_detail();
						public final String material_product_detail = productDetail.getMaterial_product_detail();
						public final String trademark_product_detail = productDetail.getTrademark_product_detail();
						public final String address_from = productDetail.getAddress_from_product_detail();
					};
					listProductResponse.add(data);
				}
				response.setData(listProductResponse);
			} catch (Exception e) {
				response.setStatus(ResponseStatusEnum.FAIL);
				response.setMessageError(e.getMessage());
				// TODO: handle exception
			}
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

		}
		
}
