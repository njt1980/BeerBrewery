	package com.nimel.mymicroservices.beerservice.controllers;

import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.dto.BeerPageList;
import com.nimel.mymicroservices.beerservice.services.BeerService;

import ch.qos.logback.core.joran.conditional.IfAction;

@RestController
@RequestMapping("/api/v1")
public class BeerController {
	
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;
	
	@Autowired
	private BeerService beerService;
	
	
	@GetMapping("beerUpc/{upc}")
	public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable String upc){
		BeerDto beerDto =  beerService.getByUpc(upc);
		return new ResponseEntity<>(beerDto,HttpStatus.OK);
	}
	
	
	@GetMapping("beer/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId,@RequestParam(value="showInventoryAtHand",required = false) Boolean showInventoryAtHand){
		BeerDto beerDto =  beerService.getById(beerId,showInventoryAtHand);
		return new ResponseEntity<>(beerDto,HttpStatus.OK);
	}
	
	@GetMapping("beerpage")
	public BeerPageList getAllBeersPages(@RequestParam(value="pageNumber",required=false)Integer pageNumber,
										  @RequestParam(value="pageSize",required=false)Integer pageSize,
										  @RequestParam(value="beerName",required = false)String beerName,
										  @RequestParam(value="beerStyle",required = false)String beerStyle,
										  @RequestParam(value="showInventoryAtHand",required = false)Boolean showInventoryAtHand) {
		
		if(pageNumber == null || pageNumber<0 ) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		if(pageSize == null || pageSize<0 ) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		
		return beerService.getAllBeersPages(PageRequest.of(pageNumber, pageSize),beerName,beerStyle,showInventoryAtHand);
		
	}
	@GetMapping("beer")
	public ResponseEntity<List<BeerDto>> getAllBeers(){
		return new ResponseEntity<>(beerService.getAllBeers(),HttpStatus.OK);
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
