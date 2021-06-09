package com.nimel.mymicroservices.beerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeerConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
