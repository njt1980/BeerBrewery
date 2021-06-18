package com.nimel.mymicroservices.beerservice.listeners;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.nimel.mymicroservices.beerservice.config.JmsConfig;
import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.entities.Beer;
//import com.nimel.mymicroservices.beerservice.events.BrewBeerEvent;
//import com.nimel.mymicroservices.beerservice.events.InventoryEvent;
import com.nimel.mymicroservices.beerservice.repository.BeerRepository;
import com.nimel.mymicroservices.common.events.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BeerEventListener {
	
	private BeerRepository beerRepository;
	private JmsTemplate jmsTemplate;
	
	@Transactional
	@JmsListener(destination = JmsConfig.BREW_QUEUE)
	public void listenForBeerBrewEvents(BrewBeerEvent brewEvent) {
		
		BeerDto beerDto = brewEvent.getBeerDto();
		System.out.println("Received brewing request for " + beerDto.getId());
		Beer beer = beerRepository.getById(beerDto.getId());	
//		Optional<Beer> beer = beerRepository.findById(beerDto.getId());
//		Beer beer = beerRepository.findByUpc(beerDto.getUpc());
//		System.out.println(beer);
		
		beerDto.setQuantityOnHand(beer.getQuantityToBrew());
		System.out.println("Beer brewed for " + beer.getId() + "to the amount of" + beer.getQuantityToBrew());
		InventoryEvent inventoryEvent = new InventoryEvent(beerDto);
		System.out.println("Sending Inventory Update event..");
		System.out.println("Sending to Inventory queue.." + inventoryEvent);
		jmsTemplate.convertAndSend(JmsConfig.INVENTORY_QUEUE, inventoryEvent);
	
		
		
	}

}
