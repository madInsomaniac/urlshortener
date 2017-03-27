package com.url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.dao.UrlDao;

@Service
public class RedirectUrlService {

	@Autowired
	private UrlDao urldao;

	public String getRedirectUrl(String urlSuffix) {

		return urldao.getUrl(urlSuffix);

	}
}
