package com.nimel.mymicroservices.beerservice.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nimel.mymicroservices.beerservice.entities.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{

}
