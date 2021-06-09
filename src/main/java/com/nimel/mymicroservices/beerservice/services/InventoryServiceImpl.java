package com.nimel.mymicroservices.beerservice.services;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nimel.mymicroservices.beerservice.dto.InventoryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ConfigurationProperties(prefix="nim.brewery",ignoreUnknownFields = true)
@Service
public class InventoryServiceImpl implements InventoryService {
	
	public final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
	@Autowired
	private RestTemplate restTemplate;
	
	private String beerInventoryServiceHost;

	public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
		this.beerInventoryServiceHost = beerInventoryServiceHost;
	}
	//	public void setRestTemplate(RestTemplate restTemplate) {
//		this.restTemplate = restTemplate;
//	}
//	private String inventorySerLink="http://localhost:8082/api/v1/beer/{beerId}/inventory";
	@Override
	public int getOnHandInventory(UUID beerId) {
		
		log.debug("Going to access Inventory Service");
		System.out.println("Going to access Inventory Service");
		System.out.println(restTemplate);
		ResponseEntity<List<InventoryDto>> responseEntity=restTemplate
				.exchange(beerInventoryServiceHost + INVENTORY_PATH,HttpMethod.GET,null,new ParameterizedTypeReference<List<InventoryDto>>(){},(Object) beerId);
		
		int onHand=Objects.requireNonNull(responseEntity.getBody())
				.stream()
				.mapToInt(InventoryDto::getQuantityOnHand)
				.sum();
		return onHand;
	
	}	

}
