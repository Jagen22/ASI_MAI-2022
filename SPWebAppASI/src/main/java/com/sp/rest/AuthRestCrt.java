package com.sp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.service.AuthService;

@RestController
public class AuthRestCrt {
	@Autowired
	AuthService aservice;
	
	@RequestMapping(method=RequestMethod.GET,value="/login/{pseudo}/{mdp}")
	public boolean verifUser(@PathVariable String pseudo, @PathVariable String mdp) {
		return aservice.verifUser(pseudo,mdp);
	}
}
