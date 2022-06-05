package market_webapp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sp.CardPriceDTO;
import com.sp.CardWSClient;
//import com.sp.userDTO;


@Service
public class MarketService {
	
	public boolean buy(int idCard, int idBuyer) {
		boolean res = false;
		CardPriceDTO cardToBuy = CardWSClient.getCard(idCard);
		User Buyer = uService.getUser(idBuyer);
		if (cardToBuy.getUserID()==-1) {
			if (Buyer.getMoney()>=cardToBuy.getPrice()) {
				Buyer.setMoney(Buyer.getMoney()-cardToBuy.getPrice());
				Buyer.addCard(cardToBuy);
				cardToBuy.setOwner(Buyer);
				CardWSClient.saveCard(cardToBuy);
				uService.saveUser(Buyer);
				res = true;
			}
		}
		return res;
	}
	public boolean sell(int idCard, int idSeller) {
		boolean res = false;
		CardPriceDTO cardToSell = CardWSClient.getCard(idCard);
		User Seller = uService.getUser(idSeller);
		if (cardToSell.getUserID()==Seller) {
			Seller.setMoney(Seller.getMoney()+cardToSell.getPrice());
			Seller.removeCard(cardToSell);
			cardToSell.setUserID(-1);
			CardWSClient.saveCard(cardToSell);
			uService.saveUser(Seller);
			res = true;
		}	
		return res;
	}
	public List<CardPriceDTO> forSale() {
		return CardWSClient.noOwner();
	}

	
	
}
