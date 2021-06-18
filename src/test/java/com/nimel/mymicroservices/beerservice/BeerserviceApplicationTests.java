package com.nimel.mymicroservices.beerservice;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.test.context.SpringBootTest;

import com.nimel.mymicroservices.beerservice.services.BeerService;
import com.nimel.mymicroservices.beerservice.services.InventoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@SpringBootTest
class BeerserviceApplicationTests {
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private BeerService beerService;
	
	
//	@Test
	void contextLoads() {
		System.out.println("Am here..");
		log.debug("Going to get quantity..");
		int onHand=inventoryService.getOnHandInventory(UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb"));
		System.out.println(onHand);
		log.debug("On Hand is : {}",onHand);
	}
	
//	@Test
	void Test1() {
		List beerList= beerService.getAllBeers();
		System.out.println(beerList);
	}


}
