package com.url.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.url.domain.Account;
import com.url.domain.RegisterUrl;
import com.url.rest.response.RegisterResponse;
import com.url.rest.response.StatsResponse;

public class UrlDaoImpl implements UrlDao {

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Transactional
	public boolean addAccount(Account acc) {

		boolean success = false;
		Account account = getAccount(acc.getAccountId());
		String id = "";
		if (account == null)
			id = (String) template.save(acc);

		if (id.isEmpty()) {
			success = false;
		} else {
			success = true;
		}

		return success;
	}

	@Transactional
	public boolean registerURL(RegisterUrl regurl) {

		boolean success = false;
		ArrayList<RegisterUrl> urlList = (ArrayList) template.findByNamedParam("from RegisterUrl r where r.url = :url",
				"url", regurl.getUrl());
		List<String> urls=getUrl(urlList);
		boolean urlstatus = urls.contains(regurl.getUrl());
		//RegisterUrl reg = template.get(RegisterUrl.class, regurl.getUrlid());

		Integer id = null;
		if (urlstatus == false)
			id =  (Integer) template.save(regurl);

		if (id == null) {
			success = false;
		} else {
			success = true;
		}

		return success;
	}

	public RegisterUrl getStats(String accid) {
		RegisterUrl rUrl = template.get(RegisterUrl.class, accid);
		return rUrl;
	}

	public Account getAccount(String accountId) {
		Account account = template.get(Account.class, accountId);
		return account;
	}

	public List<String> getUrl(List<RegisterUrl> regUrlList) {

		List<String> urlList = new ArrayList<String>();
		for (RegisterUrl regUrl : regUrlList) {

			urlList.add(regUrl.getUrl());

		}
		return urlList;
		
	}
	
	public List<RegisterUrl> getRegisterUrl(String accId){
		List<RegisterUrl> registerUrlList = (List) template.findByNamedParam("from RegisterUrl r where r.account.accountId = :accountId",
				"accountId", accId);
		
		return registerUrlList;
	}

	@Transactional
	public String getUrl(String suffixUrl) {
		List<RegisterUrl> registerUrlList = (List) template.findByNamedParam("from RegisterUrl r where r.shortUrl = :shortUrl",
				"shortUrl", suffixUrl);
		RegisterUrl registerUrl=registerUrlList.get(0);
		registerUrl.setHits(registerUrl.getHits()+1);
		template.update(registerUrl);
		return registerUrl.getUrl();
	}
}
