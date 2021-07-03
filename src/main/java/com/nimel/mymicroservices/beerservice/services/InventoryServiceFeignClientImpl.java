package com.nimel.mymicroservices.beerservice.services;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nimel.mymicroservices.beerservice.dto.InventoryDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class InventoryServiceFeignClientImpl implements InventoryService{
	
	private InventoryServiceFeignClient inventoryServiceFeignClient;
	
	@Override
	public int getOnHandInventory(UUID beerId) {
		ResponseEntity<List<InventoryDto>> responseEntity=inventoryServiceFeignClient.getOnHandInventory(beerId);
		int onHand=Objects.requireNonNull(responseEntity.getBody())
				.stream()
				.mapToInt(InventoryDto::getQuantityOnHand)
				.sum();
		return onHand;
	}
}
