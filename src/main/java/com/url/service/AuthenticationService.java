package com.url.service;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.url.dao.UrlDao;
import com.url.domain.Account;

@Service
public class AuthenticationService {

	@Autowired
	private UrlDao urldao;

	public boolean authenticate(String authCredentials) {
		// TODO Auto-generated method stub

		

		boolean authstatus = false;

		String usr=getUser(authCredentials).split(":")[0];
		String pwd=getUser(authCredentials).split(":")[1];
		
		Account account = urldao.getAccount(usr);
		if (account != null && account.getPassword().equals(pwd)) {
			authstatus = true;
		}

		return authstatus;

	}
	
	public String getUser(String authCredentials){
		

		final String encodedUsrPwd = authCredentials.replaceFirst("Basic" + " ", "");
		byte[] bytes = encodedUsrPwd.getBytes();
		String usrpwd = null;

		try {
			byte[] b = Base64Utils.decode(bytes);
			usrpwd = new String(b, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		final StringTokenizer strtoken = new StringTokenizer(usrpwd, ":");
		final String usr = strtoken.nextToken();
		final String pwd = strtoken.nextToken();
		final String userPass=usr+":"+pwd;
		
		return userPass;
	}
}
