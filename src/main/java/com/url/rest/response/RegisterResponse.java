package com.url.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class RegisterResponse {

	@JsonInclude(Include.NON_NULL)
	String shortUrl;
	
	@JsonInclude(Include.NON_NULL)
	String responseStatus;
	
	public String getResponseStatus() {
		return responseStatus;
	}


	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}


	public String getShortUrl() {
		return shortUrl;
	}


	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public RegisterResponse() {
		// TODO Auto-generated constructor stub
	}

}
