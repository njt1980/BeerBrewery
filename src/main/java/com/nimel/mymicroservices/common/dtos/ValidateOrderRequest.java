package com.nimel.mymicroservices.common.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateOrderRequest {
	
	private BeerOrderDto beerOrderDto;

}
