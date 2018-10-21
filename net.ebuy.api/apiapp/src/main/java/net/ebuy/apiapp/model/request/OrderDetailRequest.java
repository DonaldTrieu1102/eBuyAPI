package net.ebuy.apiapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Donald Trieu
 *
 */
public class OrderDetailRequest {

	@JsonProperty("id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
