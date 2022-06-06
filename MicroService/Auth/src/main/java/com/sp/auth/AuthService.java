package com.sp.auth;

import org.springframework.stereotype.Service;

import com.sp.UserWSClient;

@Service
public class AuthService {

	public boolean verifUser(String pseudo, String mdp) {
		return UserWSClient.verifUser(pseudo,mdp);
	}
	
}
