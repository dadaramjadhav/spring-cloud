package com.cg.spring.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.cg.spring.events.ProductEvent;

@Component
public class ProductEventListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@EventListener
	public void onApplicationEvent(ProductEvent productEvent) {
		logger.info("Received product event:"+ productEvent.getEventType());
		logger.info("Received product from product event:"+ productEvent.getProduct().toString());
		
	}
}