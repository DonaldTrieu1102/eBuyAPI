package net.ebuy.apiapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="customer")
public class Customer extends BaseModel {

	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private int id;
	
	private String username;
	
	private String password;
	
	private String fullname;
	
	private String email;
	
	private String avatar;
	
	private int gender;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birthday", nullable = true)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date birthday;
	
	
	@Column(name="phone_number")
	private String phone_number;
	
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
	
	private Boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


}
