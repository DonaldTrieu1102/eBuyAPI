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
@Table(name="type")
public class Type extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_list")
	private ListProduct id_list;
	

	@Column(name = "name")
	private String name_type;
	
	@Column(name ="image")
	private String image_type;
	
	private Boolean status;
	
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

	public String getName_type() {
		return name_type;
	}

	public void setName_type(String name_type) {
		this.name_type = name_type;
	}

	public String getImage_type() {
		return image_type;
	}

	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
