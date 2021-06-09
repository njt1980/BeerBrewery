package com.nimel.mymicroservices.beerservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.entities.Beer;
import com.nimel.mymicroservices.beerservice.services.InventoryService;

public abstract class BeerMapperDecorator implements BeerMapper{
	@Autowired
	private BeerMapper beerMapper;
	@Autowired
	private InventoryService inventoryService;
	
	@Override
	public BeerDto beerToBeerDto(Beer beer) {
		return beerMapper.beerToBeerDto(beer);
	}
	
	@Override
	public BeerDto beerToBeerDtoWithInventory(Beer beer) {
		BeerDto beerDto=beerMapper.beerToBeerDto(beer);
		beerDto.setQuantityOnHand(inventoryService.getOnHandInventory(beer.getId()));
		return beerDto;
	}
//	@Override
//	public BeerDto beerToBeerDto(Beer beer) {
//		BeerDto beerDto=beerMapper.beerToBeerDto(beer);
//		beerDto.setQuantityOnHand(inventoryService.getOnHandInventory(beer.getId()));
//		System.out.println("Going to print updated beerDto");
//		return beerDto;
//	}
	
	@Override
	public Beer beerDtoToBeer(BeerDto beerDto) {
		return beerMapper.beerDtoToBeer(beerDto);
	}

}
