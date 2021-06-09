package com.nimel.mymicroservices.beerservice.services;

import java.util.UUID;

public interface InventoryService {
	
	public int getOnHandInventory(UUID beerId);

}
