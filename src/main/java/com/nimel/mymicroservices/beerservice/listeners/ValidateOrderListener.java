package com.nimel.mymicroservices.beerservice.listeners;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private BeerOrderValidator beerOrderValidator;
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
	public void listen(ValidateOrderRequest validateOrderRequest ) {
		
		System.out.println("Validate request dto from queue" +  validateOrderRequest.getBeerOrderDto());
		System.out.println("Beer Order id for request in order validation queue" + validateOrderRequest.getBeerOrderDto().getId());
		
		Boolean isValid = beerOrderValidator.validateBeer(validateOrderRequest.getBeerOrderDto());
		
		jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, 
				ValidateOrderResult.builder()
				.beerOrderId(validateOrderRequest.getBeerOrderDto().getId())
				.isValid(isValid)
				.build());
	}

}
