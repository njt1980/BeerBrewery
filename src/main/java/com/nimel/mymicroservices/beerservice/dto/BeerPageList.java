package com.nimel.mymicroservices.beerservice.dto;


import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPageList extends PageImpl<BeerDto>{

	public BeerPageList(List<BeerDto> content) {
		super(content);
	}
	
	public BeerPageList(List<BeerDto> content, Pageable pageable, long total) {
		super(content,pageable,total);
	}
	

}
