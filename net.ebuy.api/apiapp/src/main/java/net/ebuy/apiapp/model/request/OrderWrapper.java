package net.ebuy.apiapp.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Donald Trieu
 *
 */
public class OrderWrapper {
	
	
	@JsonProperty("id_city")
	private int id_city;
	
	@JsonProperty("id_district")
	private int id_district;
	
	@JsonProperty("id_ward")
	private int id_ward;
	
	@JsonProperty("fee")
	private float fee;
	
	@JsonProperty("amount")
	private float amount;
	
	@JsonProperty("total_amount")
	private float total_amount;
	
	@JsonProperty("orderDetail")
	private List<OrderDetailRequest> orderDetails;

	public int getId_city() {
		return id_city;
	}

	public void setId_city(int id_city) {
		this.id_city = id_city;
	}

	public int getId_district() {
		return id_district;
	}

	public void setId_district(int id_district) {
		this.id_district = id_district;
	}

	public int getId_ward() {
		return id_ward;
	}

	public void setId_ward(int id_ward) {
		this.id_ward = id_ward;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}

	public List<OrderDetailRequest> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailRequest> orderDetails) {
		this.orderDetails = orderDetails;
	}

	

	
}
