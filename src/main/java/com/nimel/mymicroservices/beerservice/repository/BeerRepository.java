package com.nimel.mymicroservices.beerservice.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.entities.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{
	
	Page<Beer> findAllByBeerNameAndBeerStyle(Pageable pageable,String beerName,String beerStyle);
	
	Page<Beer> findAllByBeerName(Pageable pageable,String beerName);
	
	Page<Beer> findAllByBeerStyle(Pageable pageable,String beerStyle);
	
	Beer findByUpc(String upc);
	
	
	
	
	

}
