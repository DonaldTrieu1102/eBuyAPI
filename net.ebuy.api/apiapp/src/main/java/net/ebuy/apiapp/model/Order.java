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
@Table(name="orders")
public class Order extends BaseModel{
	
	
	private static final long serialVersionUID = 1L;
		
	@Id
	private int id;
	
	@Column(name ="customer_id")
	private int customer_id;
	
	@Column(name="id_city")
	private int id_city;
	
	@Column(name="id_district")
	private int id_district;
	
	@Column(name="id_ward")
	private int id_ward;
	
	@Column(name="streetname")
	private String streetname;
	
	@Column(name="address_full_text")
	private String address_full_text;
	
	@Column(name ="fee_tranfer")
	private float fee;
	
	@Column(name ="amount")
	private float amount;
	
	@Column(name ="total_amount")
	private float total_amount;
	
	private Boolean order_status;
	
	public int getId() {
		return id;
	}
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
	
	public String getStreetname() {
		return streetname;
	}
	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}
	public String getAddress_full_text() {
		return address_full_text;
	}
	public void setAddress_full_text(String address_full_text) {
		this.address_full_text = address_full_text;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
	public Boolean getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Boolean order_status) {
		this.order_status = order_status;
	}
	public void setId(int id) {
		this.id = id;
	}

	

}
