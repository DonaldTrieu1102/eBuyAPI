package net.ebuy.apiapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterCustomerWrapper {

	@JsonProperty("password")
	private String password;
	
	@JsonProperty("username")
	private String username;
	

	@JsonProperty("phone_number")
	private String phoneNumber;
	

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}
