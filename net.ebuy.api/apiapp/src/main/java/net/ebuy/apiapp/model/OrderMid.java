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
@Table(name="order_mid")
public class OrderMid {

	@Id
	private int id;
	
	@Column(name = "id_order")
	private int id_order;
	
	@Column(name="id_order_detail")
	private int id_order_detail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_order() {
		return id_order;
	}

	public void setId_order(int id_order) {
		this.id_order = id_order;
	}

	public int getId_order_detail() {
		return id_order_detail;
	}

	public void setId_order_detail(int id_order_detail) {
		this.id_order_detail = id_order_detail;
	}
	
}
