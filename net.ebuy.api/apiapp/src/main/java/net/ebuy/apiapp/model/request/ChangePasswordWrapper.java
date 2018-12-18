package net.ebuy.apiapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordWrapper {

	@JsonProperty("old_password")
	private String oldPassword;

	@JsonProperty("new_password")
	private String newPassword;
	
	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
