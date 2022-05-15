package com.sp.service;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {
	
	@Autowired
	UserService uService;

	public boolean verifUser(String pseudo, String mdp) {
		return uService.verifUser(pseudo,mdp);
	}

}
