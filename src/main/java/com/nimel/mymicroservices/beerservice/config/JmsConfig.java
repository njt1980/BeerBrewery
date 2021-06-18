package com.nimel.mymicroservices.beerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.core.instrument.Statistic;

@Configuration
public class JmsConfig {
	
	public static final String BREW_QUEUE = "brewing-request";
	public static final String INVENTORY_QUEUE = "inventory-request";
	
	@Bean
	public MessageConverter messageConverter(ObjectMapper objectMapper) {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		converter.setObjectMapper(objectMapper);
		return converter;
	}

}
