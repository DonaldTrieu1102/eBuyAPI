package net.ebuy.apiapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Donald Trieu
 *
 */
public class AddProductDetailWrapper {

  	@JsonProperty("id_list")
    private int id_list;

  	@JsonProperty("id_type")
    private int id_type;

  	@JsonProperty("id_type_product")
    private int id_type_product;

  	@JsonProperty("image")
    private String image;

  	@JsonProperty("name_product")
    private String name_product;

  	@JsonProperty("describe")
    private String describe;

  	@JsonProperty("material")
    private String material;

  	@JsonProperty("trademark")
    private String trademark;

  	@JsonProperty("price")
    private float price;

  	@JsonProperty("quantity")
    private int quantity;

  	@JsonProperty("weight")
    private String weight;

  	@JsonProperty("product_status")
    private Boolean product_status;

	public int getId_list() {
		return id_list;
	}

	public void setId_list(int id_list) {
		this.id_list = id_list;
	}

	public int getId_type() {
		return id_type;
	}

	public void setId_type(int id_type) {
		this.id_type = id_type;
	}

	public int getId_type_product() {
		return id_type_product;
	}

	public void setId_type_product(int id_type_product) {
		this.id_type_product = id_type_product;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName_product() {
		return name_product;
	}

	public void setName_product(String name_product) {
		this.name_product = name_product;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Boolean getProduct_status() {
		return product_status;
	}

	public void setProduct_status(Boolean product_status) {
		this.product_status = product_status;
	}
  	
  	
	
}
