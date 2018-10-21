package net.ebuy.apiapp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Donald Trieu
 *
 */
public class TokenCustomerResponse extends BaseResponse {
	

	@JsonProperty("id")
	private int customerId;
	
	@JsonProperty("fullname")
	private String name;
	
	private String username;
	
	private String email;
	
	@JsonProperty("phone_number")
	private String phoneNumber;
	
	private String avatar;
	
	private String access_token;
	
	private String token_type;
	
	@JsonIgnore
	private long expires_in;
	
	@JsonIgnore
	private String scope;
	
	@JsonIgnore
	private String jti;

	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}

	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	/**
	 * @return the token_type
	 */
	public String getToken_type() {
		return token_type;
	}

	/**
	 * @param token_type the token_type to set
	 */
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	/**
	 * @return the expires_in
	 */
	public long getExpires_in() {
		return expires_in;
	}

	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @return the jti
	 */
	public String getJti() {
		return jti;
	}

	/**
	 * @param jti the jti to set
	 */
	public void setJti(String jti) {
		this.jti = jti;
	}
	/**
	 * @return the employeeId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
