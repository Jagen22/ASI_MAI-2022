package com.sp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.model.Card;
import com.sp.service.CardService;

@RestController
public class CardRestCrt {
	@Autowired
	CardService cService;
	
	@RequestMapping(method=RequestMethod.POST,value="/addCard")
    public void addCard(@RequestBody Card card) {
		cService.addCard(card);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/card")
    public List<Card> listAllCards() {
        List<Card> Lcard = cService.findAllCards();
        return Lcard;
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/card/{id}")
    public Card getCard(@PathVariable String id) {
    	Card c = cService.getCard(Integer.valueOf(id));
        return c;
    }
	
    @RequestMapping(method=RequestMethod.POST, value="/initCards")
    public void initCards(){
    	cService.initCards();
	}
	
}
