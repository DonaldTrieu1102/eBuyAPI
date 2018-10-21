package net.ebuy.apiapp.model;

import javax.persistence.Column;
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
@Table(name="type_product")
public class TypeProduct extends BaseModel {

	private static final long serialVersionUID = 1L;

	
	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_type")
	private Type id_type;
	

	@Column(name = "name")
	private String name_type_product;
	
	@Column(name ="image")
	private String image_type_product;
	
	
	private Boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Type getId_type() {
		return id_type;
	}

	public void setId_type(Type id_type) {
		this.id_type = id_type;
	}

	public String getName_type_product() {
		return name_type_product;
	}

	public void setName_type_product(String name_type_product) {
		this.name_type_product = name_type_product;
	}

	public String getImage_type_product() {
		return image_type_product;
	}

	public void setImage_type_product(String image_type_product) {
		this.image_type_product = image_type_product;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
