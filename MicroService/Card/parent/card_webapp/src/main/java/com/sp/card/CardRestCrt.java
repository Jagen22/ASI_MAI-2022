package com.sp.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardRestCrt {
	@Autowired
	CardService cService;
	
	@RequestMapping(method=RequestMethod.POST,value="/card")
    public void addCard(@RequestBody Card card) {
		cService.saveCard(card);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/card")
    public List<Card> listAllCards() {
        return cService.findAllCards();
    }
    
    @RequestMapping(method=RequestMethod.GET,value="/card/{id}")
    public Card getCard(@PathVariable String id) {
        return cService.getCard(Integer.valueOf(id));
    }	
    
    @RequestMapping(method = RequestMethod.GET, value = "/card/noOwner")
    public List<Card> noOwner() {
        return cService.noOwner();
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/initCard")
    public void initCard(@RequestBody int nb) {
    	cService.initCards(nb);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/userCards")
    public List<Card> userCards() {
        return cService.get5Card();
    }

}
