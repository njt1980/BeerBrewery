package com.nimel.mymicroservices.beerservice.events;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;


public class InventoryEvent extends BeerEvent{
	
	public InventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}

}
