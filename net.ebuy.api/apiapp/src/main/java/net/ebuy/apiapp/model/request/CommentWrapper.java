package net.ebuy.apiapp.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentWrapper {

	@JsonProperty("comment")
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
