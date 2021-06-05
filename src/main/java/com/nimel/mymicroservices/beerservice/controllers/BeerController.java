package com.nimel.mymicroservices.beerservice.controllers;

import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.services.BeerService;

@RestController
@RequestMapping("/api/v1")
public class BeerController {
	
	@Autowired
	private BeerService beerService;
	
	@GetMapping("beer/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){
		BeerDto beerDto =  beerService.getById(beerId);
		return new ResponseEntity<>(beerDto,HttpStatus.OK);
	}
	@PostMapping("beer")
	public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(beerService.saveBeer(beerDto),HttpStatus.CREATED);
	}
	@PutMapping("beer/{beerId}")
	public ResponseEntity updateBeer(@PathVariable UUID beerId,@RequestBody BeerDto beerDto) {
		return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto),HttpStatus.NO_CONTENT);
	}

}
