package com.url.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;


@Entity
@Table(name="registeredurl")
public class RegisterUrl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "urlId")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int urlid;
	
	public int getUrlid() {
		return urlid;
	}

	public void setUrlid(int urlid) {
		this.urlid = urlid;
	}

	@OneToOne
	@JoinColumn(name="account_id")
	private Account account;
	
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	@Column
	private String url;
	
	@Transient
	private int redirectType;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column
	private String shortUrl;
	@Column
	private int hits;

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(int redirectType) {
		this.redirectType = redirectType;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	
}
