package net.ebuy.apiapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * @author Donald Trieu
 *
 */
@Entity
@Table(name="product")
public class Product extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_list")
	private ListProduct id_list;
	
	@ManyToOne
	@JoinColumn(name="id_type")
	private Type id_type;
	
	@ManyToOne
	@JoinColumn(name="id_type_product")
	private TypeProduct id_type_product;
	
	
	@ManyToOne
	@JoinColumn(name="id_customer")
	private Customer id_customer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ListProduct getId_list() {
		return id_list;
	}

	public void setId_list(ListProduct id_list) {
		this.id_list = id_list;
	}

	public Type getId_type() {
		return id_type;
	}

	public void setId_type(Type id_type) {
		this.id_type = id_type;
	}

	public TypeProduct getId_type_product() {
		return id_type_product;
	}

	public void setId_type_product(TypeProduct id_type_product) {
		this.id_type_product = id_type_product;
	}

	public Customer getId_customer() {
		return id_customer;
	}

	public void setId_customer(Customer id_customer) {
		this.id_customer = id_customer;
	}
	

}
