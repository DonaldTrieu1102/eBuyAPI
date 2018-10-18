package net.ebuy.apiapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="list_product")
public class ListProduct extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@Column(name = "name")
	private String name_item_list;
	
	@Column(name ="image")
	private String image_item_list;
	
	private Boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_item_list() {
		return name_item_list;
	}

	public void setName_item_list(String name_item_list) {
		this.name_item_list = name_item_list;
	}

	public String getImage_item_list() {
		return image_item_list;
	}

	public void setImage_item_list(String image_item_list) {
		this.image_item_list = image_item_list;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
