package com.sp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CardWSClient {

	static final String URL_CARD = "http://localhost:8081/card/";
	static final String URL_NO_OWNER = "http://localhost:8081/card/noOwner";
	static final String URL_INIT_CARD = "http://localhost:8081/initCard";
	static final String URL_USER_CARDS= "http://localhost:8081/userCards";

	
	public static List<CardPriceDTO> forSale() {
		RestTemplate restTemplate = new RestTemplate();
		List<CardPriceDTO> res = new ArrayList<CardPriceDTO>();
		CardPriceDTO[] tab = restTemplate.getForObject(URL_NO_OWNER,CardPriceDTO[].class);
		if (tab != null) {
			for (CardPriceDTO c : tab) {
				res.add(c);
			}
		}
		return res;
	}

	public static CardPriceDTO getCard(int idCard) {
		RestTemplate restTemplate = new RestTemplate();
		CardPriceDTO res = restTemplate.getForObject(URL_CARD + idCard, CardPriceDTO.class);
		return res;
	}

	public static void saveCard(CardPriceDTO card) {
		RestTemplate restTemplate = new RestTemplate();
		// Data attached to the request.
		HttpEntity<CardPriceDTO> requestBody = new HttpEntity<>(card);
		// Send request with POST method.
		ResponseEntity<CardPriceDTO> result = restTemplate.postForEntity(URL_CARD, requestBody, CardPriceDTO.class);
		System.out.println(result);
	}
	
	
	public static void initCards(int nb) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Integer> result = restTemplate.postForEntity(URL_CARD,nb,Integer.class);
		System.out.println(result);		
	}

	public static List<CardPriceDTO> userCards() {
		RestTemplate restTemplate = new RestTemplate();
		List<CardPriceDTO> res = new ArrayList<CardPriceDTO>();
		CardPriceDTO[] tab = restTemplate.getForObject(URL_USER_CARDS,CardPriceDTO[].class);
		if (tab != null) {
			for (CardPriceDTO c : tab) {
				res.add(c);
			}
		}
		return res;
	}
}
