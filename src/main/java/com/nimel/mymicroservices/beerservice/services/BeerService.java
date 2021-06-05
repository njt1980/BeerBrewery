package com.nimel.mymicroservices.beerservice.services;

import java.util.UUID;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;

public interface BeerService {
	
	BeerDto getById(UUID id);
	
	BeerDto saveBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId,BeerDto beerDto);

}
