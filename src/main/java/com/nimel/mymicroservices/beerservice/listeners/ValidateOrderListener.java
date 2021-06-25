package com.nimel.mymicroservices.beerservice.listeners;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.nimel.mymicroservices.beerservice.config.JmsConfig;
import com.nimel.mymicroservices.beerservice.order.BeerOrderValidator;
import com.nimel.mymicroservices.common.dtos.BeerOrderDto;
import com.nimel.mymicroservices.common.dtos.ValidateOrderRequest;
import com.nimel.mymicroservices.common.dtos.ValidateOrderResult;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ValidateOrderListener {
	
	private BeerOrderValidator beerOrderValidator;
	private JmsTemplate jmsTemplate;
	
	@JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
	public void Listen(ValidateOrderRequest validateOrderRequest ) {
		
		Boolean isValid = beerOrderValidator.validateBeer(validateOrderRequest.getBeerOrderDto());
		
		jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
				.beerOrderId(validateOrderRequest.getBeerOrderDto().getId())
				.isValid(isValid)
				.build());
	}

}
