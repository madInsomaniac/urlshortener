package com.url.dao;

import java.util.List;

import com.url.domain.Account;
import com.url.domain.RegisterUrl;
import com.url.rest.response.StatsResponse;

public interface UrlDao {

	boolean addAccount(Account acc);

	boolean registerURL(RegisterUrl reg);

	RegisterUrl getStats(String accid);

	public Account getAccount(String accountId);

	List<RegisterUrl> getRegisterUrl(String userId);
	
	String getUrl(String suffixUrl);
	
	

}
