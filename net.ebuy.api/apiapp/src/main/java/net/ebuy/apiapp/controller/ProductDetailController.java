package net.ebuy.apiapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import net.ebuy.apiapp.model.FeedBack;
import net.ebuy.apiapp.model.Product;
import net.ebuy.apiapp.model.ProductDetail;
import net.ebuy.apiapp.service.CustomerService;
import net.ebuy.apiapp.service.FeedBackService;
import net.ebuy.apiapp.service.ProductDetailService;
import net.ebuy.apiapp.service.ProductService;
/**
 * @author Donald Trieu
 *
 */

@RestController
@RequestMapping("/api/productdetail")
public class ProductDetailController {

	@Autowired
	private ProductDetailService productDetailSerVice;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	FeedBackService feedBackService;
	
	@ResponseBody
	@RequestMapping(value = "/getall",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> get(HttpServletRequest request){
		
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			List<ProductDetail> productDetails = productDetailSerVice.findAllProductDetail();
			List<Object> listProductDetailResponse = new ArrayList<>();
			for(ProductDetail productDetail: productDetails) {
					
				List<FeedBack> feedBacks = feedBackService.findListFeedBackByIdProductDetail(productDetail.getId());
				int countStar = 0;
				int countLike = 0;
				float count1;
				int count2;
				if(feedBacks.isEmpty()) {
					count1 = 0f;
					count2 = 0;
				}
				else {
					for(FeedBack feedBack : feedBacks) {
						countStar += feedBack.getFeedback();
						if(feedBack.getExpress()==1) {
							countLike += 1;
						}
					}
					count1 = (float)countStar/feedBacks.size();
					count2 = countLike;
				}
				
				Object data = new Object() {
					public final int id_product_detail = productDetail.getId();
					public final int id_product = productDetail.getId_product().getId();
					public final int id_type = productDetail.getId_product().getId_type().getId();
					public final String name_product_detail = productDetail.getName_product_detail();
					public final String image_product_detail = productDetail.getImage_product_detail();
					public final float price_product_detail = productDetail.getPrice_product_detail();
					public final int quantity_product_detail = productDetail.getQuantity_product_detail();
					public final String color_product_detail = productDetail.getColor_product_detail();
					public final String weigh_product_detail = productDetail.getWeigh_product_detail();
					public final boolean product_status = productDetail.getProduct_status();
					public final String describe_product_detail = productDetail.getDescribe_product_detail();
					public final String material_product_detail = productDetail.getMaterial_product_detail();
					public final String trademark_product_detail = productDetail.getTrademark_product_detail();
					public final String address_from = productDetail.getAddress_from_product_detail();
					public final float countstar = count1;
					public final int countfeedback = feedBacks.size();
					public final float countlike = count2;
				};
				listProductDetailResponse.add(data);
			}
			response.setData(listProductDetailResponse);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}
	// get all product detail of customer buy with id_customer
	@ResponseBody
	@RequestMapping(value = "/getProductDetailOfCustomer",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<BaseResponse> getProductDetailOfCustomer(HttpServletRequest request,
			@RequestParam(value="id_customer") int id_Customer){
		
		BaseResponse response = new BaseResponse();
		response.setStatus(ResponseStatusEnum.SUCCESS);
		response.setMessage(ResponseStatusEnum.SUCCESS);
		response.setData(null);
		try {
			List<Product> products = productService.findAllProduct();
			List<Product> productRespronse = products.stream()
					.filter(p->p.getId_customer().getId() == id_Customer)
					.collect(Collectors.toList());
			List<Object> listProductDetailResponse = new ArrayList<>();
			List<ProductDetail> productDetails = productDetailSerVice.findAllProductDetail();
			for(Product product: productRespronse) {
				
				List<ProductDetail> productDetail = productDetailSerVice.findListProductDetailByIdProduct(productDetails, product.getId());
				
				for(ProductDetail prDetail: productDetail) {
					
					List<FeedBack> feedBacks = feedBackService.findListFeedBackByIdProductDetail(prDetail.getId());
					int countStar = 0;
					int countLike = 0;
					float count1;
					int count2;
					if(feedBacks.isEmpty()) {
						count1 = 0f;
						count2 = 0;
					}
					else {
						for(FeedBack feedBack : feedBacks) {
							countStar += feedBack.getFeedback();
							if(feedBack.getExpress()==1) {
								countLike += 1;
							}
						}
						count1 = (float)countStar/feedBacks.size();
						count2 = countLike;
					}
					
					
					Object data = new Object() {
						public final int id_product_detail = prDetail.getId();
						public final int id_product = prDetail.getId_product().getId();
						public final int id_type = prDetail.getId_product().getId_type().getId();
						public final String name_product_detail = prDetail.getName_product_detail();
						public final String image_product_detail = prDetail.getImage_product_detail();
						public final float price_product_detail = prDetail.getPrice_product_detail();
						public final int quantity_product_detail = prDetail.getQuantity_product_detail();
						public final String color_product_detail = prDetail.getColor_product_detail();
						public final String weigh_product_detail = prDetail.getWeigh_product_detail();
						public final boolean product_status = prDetail.getProduct_status();
						public final String describe_product_detail = prDetail.getDescribe_product_detail();
						public final String material_product_detail = prDetail.getMaterial_product_detail();
						public final String trademark_product_detail = prDetail.getTrademark_product_detail();
						public final String address_from = prDetail.getAddress_from_product_detail();
						public final float countstar = count1;
						public final int countfeedback = feedBacks.size();
						public final float countlike = count2;
					};
					listProductDetailResponse.add(data);
				}
			}
			response.setData(listProductDetailResponse);
		} catch (Exception e) {
			response.setStatus(ResponseStatusEnum.FAIL);
			response.setMessageError(e.getMessage());
			// TODO: handle exception
		}
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}
	// get ProductDetail customer like
	
	
	
	
}
