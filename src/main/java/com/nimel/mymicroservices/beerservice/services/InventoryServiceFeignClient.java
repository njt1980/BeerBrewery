package com.nimel.mymicroservices.beerservice.services;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nimel.mymicroservices.beerservice.dto.InventoryDto;

@FeignClient(name="inventoryservice")
public interface InventoryServiceFeignClient {
	
	@RequestMapping(method = RequestMethod.GET,value = InventoryServiceImpl.INVENTORY_PATH)
	ResponseEntity<List<InventoryDto>> getOnHandInventory(@PathVariable UUID beerId);


}
