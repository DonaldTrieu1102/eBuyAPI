package net.ebuy.apiapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Donald Trieu
 *
 */
@Entity
@Table(name="product_detail")
public class ProductDetail extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_product")
	private Product id_product;
	
	
	@Column(name = "name_product")
	private String name_product_detail;
	
	@Column(name ="image")
	private String image_product_detail;
	
	
	@Column(name ="price")
	private Float price_product_detail;
	
	@Column(name ="quantity")
	private float quantity_product_detail;
	
	@Column(name ="color")
	private String color_product_detail;
	
	@Column(name ="weigh")
	private String weigh_product_detail;
	
	private Boolean product_status;

	
	@Column(name ="`describe`")
	private String describe_product_detail;
	
	
	@Column(name ="material")
	private String material_product_detail;
	
	@Column(name ="trademark")
	private String trademark_product_detail;
	
	@Column(name ="address_from")
	private String address_from_product_detail;
	
	private Boolean status;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getId_product() {
		return id_product;
	}

	public void setId_product(Product id_product) {
		this.id_product = id_product;
	}

	public String getName_product_detail() {
		return name_product_detail;
	}

	public void setName_product_detail(String name_product_detail) {
		this.name_product_detail = name_product_detail;
	}

	public String getImage_product_detail() {
		return image_product_detail;
	}

	public void setImage_product_detail(String image_product_detail) {
		this.image_product_detail = image_product_detail;
	}

	public Float getPrice_product_detail() {
		return price_product_detail;
	}

	public void setPrice_product_detail(Float price_product_detail) {
		this.price_product_detail = price_product_detail;
	}

	public float getQuantity_product_detail() {
		return quantity_product_detail;
	}

	public void setQuantity_product_detail(float quantity_product_detail) {
		this.quantity_product_detail = quantity_product_detail;
	}

	public String getColor_product_detail() {
		return color_product_detail;
	}

	public void setColor_product_detail(String color_product_detail) {
		this.color_product_detail = color_product_detail;
	}

	public String getWeigh_product_detail() {
		return weigh_product_detail;
	}

	public void setWeigh_product_detail(String weigh_product_detail) {
		this.weigh_product_detail = weigh_product_detail;
	}

	public Boolean getProduct_status() {
		return product_status;
	}

	public void setProduct_status(Boolean product_status) {
		this.product_status = product_status;
	}

	public String getDescribe_product_detail() {
		return describe_product_detail;
	}

	public void setDescribe_product_detail(String describe_product_detail) {
		this.describe_product_detail = describe_product_detail;
	}

	public String getMaterial_product_detail() {
		return material_product_detail;
	}

	public void setMaterial_product_detail(String material_product_detail) {
		this.material_product_detail = material_product_detail;
	}

	public String getTrademark_product_detail() {
		return trademark_product_detail;
	}

	public void setTrademark_product_detail(String trademark_product_detail) {
		this.trademark_product_detail = trademark_product_detail;
	}

	public String getAddress_from_product_detail() {
		return address_from_product_detail;
	}

	public void setAddress_from_product_detail(String address_from_product_detail) {
		this.address_from_product_detail = address_from_product_detail;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	
	
	
	
	
}
