package com.nimel.mymicroservices.beerservice.events;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
	
	public BrewBeerEvent(BeerDto beerDto) {
		super(beerDto);
	}

}
