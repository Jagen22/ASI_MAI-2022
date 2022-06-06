package com.sp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
	
	@RestController
	public class AuthRestCrt {
		@Autowired
		AuthService aService;
	
		@RequestMapping(method=RequestMethod.GET,value="/login/{pseudo}/{mdp}")
		public void verifUser(@PathVariable String pseudo, @PathVariable String mdp) {
			if(!aService.verifUser(pseudo,mdp)) {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,HttpStatus.UNAUTHORIZED.getReasonPhrase());
			}
		}

}
