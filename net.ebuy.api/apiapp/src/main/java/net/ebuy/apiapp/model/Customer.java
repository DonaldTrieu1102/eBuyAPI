package net.ebuy.apiapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author Donald Trieu
 *
 */
@Entity
@Table(name="customers")
public class Customer extends BaseModel {

	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private int id;
	
	@Column(name="username")
	private String username;
	
	private String password;
	
	private String fullname;
	
	private String email;
	
	@Column(name="avatar")
	private String avatar;
	
	private int gender;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birthday", nullable = true)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date birthday;
	
	
	@Column(name="phone_number")
	private String phone_number;
	
	
	@ManyToOne
	@JoinColumn(name="id_city")
	private City id_city;
	
	@ManyToOne
	@JoinColumn(name="id_district")
	private District id_district;
	
	@ManyToOne
	@JoinColumn(name="id_ward")
	private Ward id_ward;
	

	
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

	public City getId_city() {
		return id_city;
	}

	public void setId_city(City id_city) {
		this.id_city = id_city;
	}

	public District getId_district() {
		return id_district;
	}

	public void setId_district(District id_district) {
		this.id_district = id_district;
	}

	public Ward getId_ward() {
		return id_ward;
	}

	public void setId_ward(Ward id_ward) {
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
