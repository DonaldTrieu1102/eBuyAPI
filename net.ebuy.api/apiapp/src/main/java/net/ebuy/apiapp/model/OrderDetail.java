package net.ebuy.apiapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Donald Trieu
 *
 */
@Entity
@Table(name="order_detail")
public class OrderDetail extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name="id_product_detail")
	private int id_product_detail;
	
	@Column(name="id_customer")
	private int id_customer;
	
	@Column(name="name")
	private String name;
	
	@Column(name ="price")
	private float price;
	
	@Column(name ="quantity")
	private int quantity;
	
	@Column(name ="amount")
	private float amount;
	
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_product_detail() {
		return id_product_detail;
	}

	public void setId_product_detail(int id_product_detail) {
		this.id_product_detail = id_product_detail;
	}

	public int getId_customer() {
		return id_customer;
	}

	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
}
