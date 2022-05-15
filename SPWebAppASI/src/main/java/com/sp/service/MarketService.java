package com.sp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sp.model.Card;

public class MarketService {

	@Autowired
	UserService uService;
	CardService cService;
	public void buy(int idCard, int idBuyer) {
		// TODO Auto-generated method stub
		
	}
	public void sell(int idCard, int idSeller) {
		// TODO Auto-generated method stub
		
	}
	public List<Card> forSale() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
