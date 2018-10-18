package net.ebuy.apiapp.model.request;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginWrapper {

	@JsonProperty("username")
	private String username;
	
	
	@JsonProperty("password")
	private String password;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	public String getPasswordBase64Decode() {
		byte[] decodedBytes = Base64.getDecoder().decode(this.password);
		return new String(decodedBytes);
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
