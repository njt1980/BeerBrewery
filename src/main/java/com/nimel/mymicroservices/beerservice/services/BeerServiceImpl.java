package com.nimel.mymicroservices.beerservice.services;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nimel.mymicroservices.beerservice.dto.BeerDto;
import com.nimel.mymicroservices.beerservice.dto.BeerPageList;
import com.nimel.mymicroservices.beerservice.entities.Beer;
import com.nimel.mymicroservices.beerservice.exceptions.NotFoundException;
import com.nimel.mymicroservices.beerservice.mappers.BeerMapper;
import com.nimel.mymicroservices.beerservice.repository.BeerRepository;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//@RequiredArgsConstructor
@Slf4j
@Service
public class BeerServiceImpl implements BeerService{
	@Autowired
	private BeerRepository beerRepository;
	@Autowired
	private BeerMapper beerMapper;
	
	
//	private final BeerMapper beerMapper;
	@Cacheable(cacheNames = "beerCache",key = "#beerId" ,condition = "#showInventoryAtHand == false")
	@Override
	public BeerDto getById(UUID id, Boolean showInventoryAtHand) {
		System.out.println("Getting data from database - SingleBeer");
		if(showInventoryAtHand) {
		return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(id).orElseThrow(NotFoundException::new));
		}else {
		return beerMapper.beerToBeerDto(beerRepository.findById(id).orElseThrow(NotFoundException::new));	
		}
	}

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
		log.info("BeerDto -> {}",beerDto.toString());
		Beer beer = beerMapper.beerDtoToBeer(beerDto);
		log.info("beer -> {}",beer.toString());
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
//		return null;
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer= beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle());
		beer.setPrice(beerDto.getPrice());
		beer.setMinOnHand(beerDto.getMinOnHand());
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
//		return null;
	}

	@Override
	public List<BeerDto> getAllBeers() {
		List<Beer> listOfBeers= new ArrayList<Beer>();
		beerRepository.findAll().forEach(listOfBeers::add);;
		return listOfBeers.stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList());

	}

//	@Override
//	public BeerPageList getAllBeersPages(Pageable pageable) {
//		Page<Beer> beerPage = beerRepository.findAll(pageable);
//		return new BeerPageList(beerPage
//				.stream()
//				.map(beerMapper::beerToBeerDto)
//				.collect(Collectors.toList()),
//						PageRequest.of(beerPage.getPageable().getPageNumber(),
//						beerPage.getPageable().getPageSize()),
//						beerPage.getTotalElements());
//	}

	@Cacheable(cacheNames = "beerListCache" ,condition = "#showInventoryAtHand == false ")
	@Override
	public BeerPageList getAllBeersPages(Pageable pageable, String beerName, String beerStyle,
			Boolean showInventoryAtHand) {
		System.out.println("Getting data from database");
		Page<Beer> beerPage = null;
		if(showInventoryAtHand == null) {
			showInventoryAtHand = false;
		}
		if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(pageable,beerName,beerStyle);
		}else if(!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerName(pageable,beerName);
		}else if(StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerStyle(pageable,beerStyle);
		}else {
			beerPage = beerRepository.findAll(pageable);
		}
		System.out.println(showInventoryAtHand);
		if(showInventoryAtHand) {
			return new BeerPageList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDtoWithInventory)
					.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(),beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		} else {
			return new BeerPageList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDto)
					.collect(Collectors.toList()),
					PageRequest.of(beerPage.getPageable().getPageNumber(),beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}
				
	}
	
	@Cacheable(cacheNames = "beerUpcCache",key="#upc")
	@Override
	public BeerDto getByUpc(String upc) {
		System.out.println("Getting data from DB");
		Beer beer=beerRepository.findByUpc(upc);
		return beerMapper.beerToBeerDto(beer);
	}

	

}
