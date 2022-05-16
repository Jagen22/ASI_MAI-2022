package com.sp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.model.Card;
import com.sp.model.User;

@Service
public class MarketService {

	@Autowired
	UserService uService;
	@Autowired
	CardService cService;
	public void buy(int idCard, int idBuyer) {
		Card cardToBuy = cService.getCard(idCard);
		User Buyer = uService.getUser(idBuyer);
		if (cardToBuy.getOwner()==null) {
			if (Buyer.getMoney()>=cardToBuy.getPrice()) {
				Buyer.setMoney(Buyer.getMoney()-cardToBuy.getPrice());
				Buyer.addCard(cardToBuy);
				cardToBuy.setOwner(Buyer);
			}
		}
	}
	public void sell(int idCard, int idSeller) {
		Card cardToSell = cService.getCard(idCard);
		User Seller = uService.getUser(idSeller);
		if (cardToSell.getOwner()==Seller) {
			Seller.setMoney(Seller.getMoney()+cardToSell.getPrice());
			Seller.removeCard(cardToSell);
			cardToSell.setOwner(null);
		}		
	}
	public List<Card> forSale() {
		return cService.noOwner();
	}
	
	
}
