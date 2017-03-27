package com.url.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.dao.UrlDao;
import com.url.domain.Account;
import com.url.domain.RegisterUrl;
import com.url.rest.response.RegisterResponse;

@Service
public class RegisterUrlService {

	@Autowired
	private UrlDao urldao;

	@Autowired
	private AuthenticationService authService;

	public RegisterResponse registerUrl(RegisterUrl regurl,String authToken){

		RegisterResponse regResp = new RegisterResponse();


		if(authService.authenticate(authToken)){
			Account account=new Account();
			account.setAccountId(authService.getUser(authToken).split(":")[0]);
			regurl.setAccount(account);
			String shortUrlSuffix=getShortUrlSuffix();
			regurl.setShortUrl(shortUrlSuffix);

			boolean success=urldao.registerURL(regurl);

			if(success){
				
				regResp.setShortUrl(createShortUrl(shortUrlSuffix));
			}
			else{
				regResp.setResponseStatus("URL Already Registered");
			}
		}else{
			regResp.setResponseStatus("Invalid Authentication Token...Please try with valid token");
		}
		return regResp;
	}

	private String createShortUrl(String suffix) {

		return "http://localhost:9595/shorturl/"+suffix;
	}

	private String getShortUrlSuffix(){
		Random r = new Random ();
		String shorturl=Integer.toString (Math.abs (r.nextInt ()), 16);
		return shorturl;
	}
}
