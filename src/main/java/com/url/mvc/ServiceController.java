package com.url.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.url.domain.Account;
import com.url.domain.RegisterUrl;
import com.url.rest.response.AccountResponse;
import com.url.rest.response.RegisterResponse;
import com.url.service.AccountService;
import com.url.service.AuthenticationService;
import com.url.service.RedirectUrlService;
import com.url.service.RegisterUrlService;
import com.url.service.StatsService;

@Controller
public class ServiceController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RegisterUrlService regurlservice;
	
	@Autowired
	private StatsService statsService;
	
	@Autowired
	private RedirectUrlService redirectUrlService;
	
	
	@Autowired
	private AuthenticationService authService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String urlshortener() {
		return "index";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AccountResponse addAccount(@RequestBody Account account) {
		
		return accountService.addAccount(account);

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody RegisterResponse registerUrl(@RequestBody RegisterUrl reg,@RequestHeader("Authorization")String authToken) {

		return regurlservice.registerUrl(reg,authToken);
	}

	
	@RequestMapping(value = "/statistic/{accountId}", method = RequestMethod.GET)
	public @ResponseBody String registerUrl(@PathVariable("accountId")String accountId,@RequestHeader("Authorization")String authToken) {

		return statsService.getStats(accountId, authToken);
	}
	
	@RequestMapping(value = "/{shortUrlSuffix}", method = RequestMethod.GET)
	public String redirectUrl(@PathVariable("shortUrlSuffix")String shortUrlSuffix) {

		return "redirect:"+redirectUrlService.getRedirectUrl(shortUrlSuffix);
	}
	
}
