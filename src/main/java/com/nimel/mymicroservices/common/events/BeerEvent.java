package com.nimel.mymicroservices.common.events;

import java.io.Serializable;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BeerEvent implements Serializable{
	
	private BeerDto beerDto;

}
