package net.ebuy.apiapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateProfile {

	@JsonProperty("avatar")
	private String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
	

}
