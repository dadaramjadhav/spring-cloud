package com.cg.spring.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.cg.spring.events.CategoryEvent;

@Component
public class CategoryEventListener {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@EventListener
	public void onApplicationEvent(CategoryEvent categoryEvent) {
		logger.info("Received category event:"+ categoryEvent.getEventType());
		logger.info("Received category from category event:"+ categoryEvent.getCategory().toString());
		
	}
}