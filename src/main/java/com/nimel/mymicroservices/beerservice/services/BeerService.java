package com.nimel.mymicroservices.beerservice.services;


import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.dto.BeerPageList;

public interface BeerService {
	
	BeerDto getById(UUID id);
	
	BeerDto saveBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId,BeerDto beerDto);
	
	List<BeerDto> getAllBeers();
	
	BeerPageList getAllBeersPages(Pageable pageable);

}
