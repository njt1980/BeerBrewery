package com.nimel.mymicroservices.beerservice.services;

import java.util.List;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.nimel.mymicroservices.beerservice.config.JmsConfig;
import com.nimel.mymicroservices.beerservice.entities.Beer;
//import com.nimel.mymicroservices.beerservice.events.BrewBeerEvent;
import com.nimel.mymicroservices.beerservice.mappers.BeerMapper;
import com.nimel.mymicroservices.beerservice.repository.BeerRepository;
import com.nimel.mymicroservices.common.events.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class BrewingService {
	
	private BeerRepository beerRepository;
	private InventoryService inventoryService;
	private JmsTemplate jmsTemplate;
	private BeerMapper beerMapper;
	
	@Scheduled(fixedRate = 5000)
	public void checkForLowInventory() {
//		System.out.println("In quantity check module..");
		List<Beer> listOfBeers = beerRepository.findAll();
		listOfBeers.forEach(beer -> {
			int quantityOnHand = inventoryService.getOnHandInventory(beer.getId());
//			System.out.println("Qnty on Hand for " + beer.getId() + "is" + quantityOnHand);
//			log.debug("Beer is {}",beer.getBeerName());
//			log.debug("quantity is {}",quantityOnHand);
//			System.out.println("Beer is " + beer.getBeerName());
//			System.out.println("Beer min is " + beer.getMinOnHand());
//			System.out.println("Beer quantity in Hand " + quantityOnHand);
			
			if(beer.getMinOnHand() > quantityOnHand) {
				System.out.println("Sending brewing request");
				jmsTemplate.convertAndSend(JmsConfig.BREW_QUEUE,new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
//				jmsTemplate.convertAndSend(JmsConfig.BREW_QUEUE,beerMapper.beerToBeerDto(beer));
			}
		});
	}
	


}
