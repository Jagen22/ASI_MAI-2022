package com.sp.Card;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {

	public List<Card> findByPrice(String price);
}
