package com.nimel.mymicroservices.beerservice.order;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nimel.mymicroservices.beerservice.repository.BeerRepository;
import com.nimel.mymicroservices.common.dtos.BeerOrderDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BeerOrderValidator {
	@Autowired
	private BeerRepository beerRepository;
	
	public Boolean validateBeer(BeerOrderDto beerOrder) {
		
		AtomicInteger beersNotFound = new AtomicInteger();
		
		beerOrder.getBeerOrderLines().forEach(beerOrderLine -> {
			System.out.println(beerOrderLine.getUpc());
			if(beerRepository.findByUpc(beerOrderLine.getUpc()) == null) {
				System.out.println("Upc" + beerOrderLine.getUpc() + "not found");
				beersNotFound.incrementAndGet();
			}
		});
		System.out.println("Value of beersNotFound after validation : " + beersNotFound);
		return (beersNotFound.get() == 0);
	}

}
