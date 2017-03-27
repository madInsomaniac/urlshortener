package com.url.rest.response;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AccountResponse {

	private boolean success;
	public boolean getSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public AccountResponse(boolean success, String description, String password) {
		this.success = success;
		this.description = description;
		this.password = password;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	private String description;

	@JsonInclude(Include.NON_NULL)
	private String password;


	public AccountResponse() {
		// TODO Auto-generated constructor stub
	}

}
