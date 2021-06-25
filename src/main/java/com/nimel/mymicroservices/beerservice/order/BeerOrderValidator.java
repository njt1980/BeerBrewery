package com.nimel.mymicroservices.beerservice.order;

import java.util.concurrent.atomic.AtomicInteger;

import com.nimel.mymicroservices.beerservice.repository.BeerRepository;
import com.nimel.mymicroservices.common.dtos.BeerOrderDto;


public class BeerOrderValidator {
	
	private BeerRepository beerRepository;
	
	public Boolean validateBeer(BeerOrderDto beerOrder) {
		
		AtomicInteger beersNotFound = new AtomicInteger();
		
		beerOrder.getBeerOrderLines().forEach(beerOrderLine -> {
			if(beerRepository.findByUpc(beerOrderLine.getUpc()) == null) {
				beersNotFound.incrementAndGet();
			}
		});
		return (beersNotFound == null);
	}

}
