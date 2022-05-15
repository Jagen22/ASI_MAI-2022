package com.sp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.model.Card;
import com.sp.service.MarketService;

@RestController
public class MarketRestCrt {

	@Autowired
	
	MarketService mService;
	
	@RequestMapping(method=RequestMethod.POST,value="/market/buy")
	public void buy(@PathVariable int idCard, @PathVariable int idBuyer) {
		mService.buy(idCard,idBuyer);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/market/sell/{idCard}/{idSeller}")
	public void sell(@PathVariable int idCard, @PathVariable int idSeller) {
		mService.sell(idCard,idSeller);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/market/all")
	public List<Card> forSale() {
		List<Card> Lcard = mService.forSale();
		return Lcard;
	}
}
