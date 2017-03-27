package com.url.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.url.dao.UrlDao;
import com.url.domain.Account;
import com.url.domain.RegisterUrl;

@Service
public class StatsService {
	
	
	@Autowired
	private UrlDao urldao;

	@Autowired
	private AuthenticationService authService;
	
	private static Map<String,Integer> stats=new HashMap();
	
	
	
	public String getStats(String userId,String authToken){
		JsonArray jsonArray=new JsonArray();
		
		if(authService.authenticate(authToken)){
			
			
			List<RegisterUrl> registeredUrl=urldao.getRegisterUrl(userId);
			for(RegisterUrl registerUrl:registeredUrl){
				
				JsonObject jsonObject=new JsonObject();
				jsonObject.addProperty(registerUrl.getUrl(), registerUrl.getHits());
				jsonArray.add(jsonObject);
			}
		}
		String json=new Gson().toJson(jsonArray);
		return json.substring(1,json.length()-1);
	}
	
	
	
	

}
