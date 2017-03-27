package com.url.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String accountId;
	
	@Transient
	private String success;
	@Transient
	private String description;
	
	@Column
	private String password;
	
	

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}



	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	

	
	
	
	
	
	
}
