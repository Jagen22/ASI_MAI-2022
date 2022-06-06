package com.sp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserWSClient {
	
	static final String URL_USER = "http://localhost:8082/user/";
	static final String URL_ADD_USER = "http://localhost:8082/user/add";
	static final String URL_VERIF_USER = "http://localhost:8082/user/verif";
	

	
	public static UserCardDTO getUser(int idUser) {
		RestTemplate restTemplate = new RestTemplate();
		UserCardDTO res = restTemplate.getForObject(URL_USER + idUser, UserCardDTO.class);
		return res;
	}
	
	public static List<UserCardDTO> listAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		List<UserCardDTO> res = new ArrayList<UserCardDTO>();
		UserCardDTO[] tab = restTemplate.getForObject(URL_USER, UserCardDTO[].class);
		if (tab != null) {
			for (UserCardDTO u : tab) {
				res.add(u);
			}
		}		
		return res;
	}


	public static void saveUser(UserCardDTO user) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<UserCardDTO> requestBody = new HttpEntity<>(user);
		ResponseEntity<UserCardDTO> result = restTemplate.postForEntity(URL_USER, requestBody, UserCardDTO.class);
		System.out.println(result);
	}
	
	
	public static void addUser(UserCardDTO user) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<UserCardDTO> requestBody = new HttpEntity<>(user);
		ResponseEntity<UserCardDTO> result = restTemplate.postForEntity(URL_ADD_USER, requestBody, UserCardDTO.class);
		System.out.println(result);
	}

	public static boolean verifUser(String pseudo, String mdp) {
		RestTemplate restTemplate = new RestTemplate();
		boolean res = restTemplate.getForObject(URL_VERIF_USER + "?pseudo=" + pseudo + "&mdp=" + mdp, boolean.class);
		return res;
	}
	
	



}
