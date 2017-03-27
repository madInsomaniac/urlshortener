package com.url.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.url.dao.UrlDao;
import com.url.domain.Account;
import com.url.rest.response.AccountResponse;

@Service
public class AccountService {

	@Autowired
	private UrlDao urldao;
	
	@Autowired
	private AuthenticationService authService;

	public AccountResponse addAccount(Account account) {

		AccountResponse accountResp=new AccountResponse();

		String randomPass=getRandomPassword();
		account.setPassword(randomPass);
		
		boolean success=urldao.addAccount(account);
		
		if(success){
			accountResp.setDescription("Account Created Successfully");
			accountResp.setPassword(randomPass);
			
		}else{
			accountResp.setDescription("Account with that ID "+account.getAccountId()+" already exists");
		}
		accountResp.setSuccess(success);
		

		return accountResp;
	}



	public String getRandomPassword(){
		Random r = new Random ();
		String pass=Integer.toString (Math.abs (r.nextInt ()), 16);
		return pass;
	}
}
