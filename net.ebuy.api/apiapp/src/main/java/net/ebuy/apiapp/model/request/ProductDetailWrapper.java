package net.ebuy.apiapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Donald Trieu
 *
 */
public class ProductDetailWrapper {

	@JsonProperty("id_product_detail")
	private int id_product_detail;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("price")
	private float price;
	
	@JsonProperty("size")
	private String size;
	
	@JsonProperty("quantity")
	private int quatity;
	
	@JsonProperty("status")
	private boolean status;

	public int getId_product_detail() {
		return id_product_detail;
	}

	public void setId_product_detail(int id_product_detail) {
		this.id_product_detail = id_product_detail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuatity() {
		return quatity;
	}

	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
	
}
