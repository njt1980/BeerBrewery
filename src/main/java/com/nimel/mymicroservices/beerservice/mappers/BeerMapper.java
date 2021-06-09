package com.nimel.mymicroservices.beerservice.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.entities.Beer;

@Mapper(uses= {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
	
	BeerDto beerToBeerDto(Beer beer);
	Beer beerDtoToBeer(BeerDto beerDto);
	BeerDto beerToBeerDtoWithInventory(Beer beer);

}
