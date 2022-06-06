package com.sp.market;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.CardPriceDTO;
import com.sp.CardWSClient;
import com.sp.UserCardDTO;
import com.sp.UserWSClient;



@Service
public class MarketService {
	
	public boolean buy(int idCard, int idBuyer) {
		boolean res = false;
		CardPriceDTO cardToBuy = CardWSClient.getCard(idCard);
		UserCardDTO Buyer = UserWSClient.getUser(idBuyer);
		if (cardToBuy.getUserID()==-1) {
			if (Buyer.getMoney()>=cardToBuy.getPrice()) {
				Buyer.setMoney(Buyer.getMoney()-cardToBuy.getPrice());
				Buyer.addCard(cardToBuy.getCardID());
				cardToBuy.setUserID(Buyer.getUserID());
				CardWSClient.saveCard(cardToBuy);
				UserWSClient.saveUser(Buyer);
				res = true;
			}
		}
		return res;
	}
	public boolean sell(int idCard, int idSeller) {
		boolean res = false;
		CardPriceDTO cardToSell = CardWSClient.getCard(idCard);
		UserCardDTO Seller = UserWSClient.getUser(idSeller);
		if (cardToSell.getUserID()==Seller.getUserID()) {
			Seller.setMoney(Seller.getMoney()+cardToSell.getPrice());
			Seller.removeCard(cardToSell.getCardID());
			cardToSell.setUserID(-1);
			CardWSClient.saveCard(cardToSell);
			UserWSClient.saveUser(Seller);
			res = true;
		}	
		return res;
	}
	
	public List<CardPriceDTO> forSale() {
		return CardWSClient.forSale();
	}

	
	
}
