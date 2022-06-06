package com.sp.market;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.sp.CardPriceDTO;


@RestController
public class MarketRestCrt {

	@Autowired
	MarketService mService;
	
	@RequestMapping(method=RequestMethod.POST,value="/market/buy/{idCard}/{idBuyer}")
	public void buy(@PathVariable int idCard, @PathVariable int idBuyer) {
		if(!mService.buy(idCard,idBuyer)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/market/sell/{idCard}/{idSeller}")
	public void sell(@PathVariable int idCard, @PathVariable int idSeller) {
		if(!mService.sell(idCard,idSeller)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/market/all")
	public List<CardPriceDTO> forSale() {
		return mService.forSale();
	}
	
}
