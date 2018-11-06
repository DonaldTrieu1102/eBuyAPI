package net.ebuy.apiapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class FeedBack extends BaseModel {
	private static final long serialVersionUID = 1L;

	
	public FeedBack() {
		super();
	}

	@Id
	private int id;
	
	@Column(name="id_customer")
	private int id_customer;
	
	@Column(name="id_product_detail")
	private int id_product_detail;
	
	@Column(name="express")
	private int express;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="feedback")
	private int feedback;
	
	private Boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_customer() {
		return id_customer;
	}

	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
	}

	public int getId_product_detail() {
		return id_product_detail;
	}

	public void setId_product_detail(int id_product_detail) {
		this.id_product_detail = id_product_detail;
	}

	public int getExpress() {
		return express;
	}

	public void setExpress(int express) {
		this.express = express;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	
	
}
