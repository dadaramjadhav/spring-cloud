package com.cg.spring.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

@Aspect
@Component
public class RestControllerAspect {

	private final Logger logger = LoggerFactory.getLogger("RestControllerAspect");

	Counter categoryCreatedCounter = Metrics.counter("com.cg.spring.category.created");

	@Before("execution(public * com.cg.spring.controller.*Controller.*(..))")
	public void generalAllMethodAspect() {
 		logger.info("all method call will invoke this general method (Category)");
	}
	@AfterReturning("execution(public * com.cg.spring.controller.*Controller.createCategory(..))")
	public void getCalledOnCategorySave() {
		categoryCreatedCounter.increment();
		logger.info("Aspect fired when save method called... (Category)");
	}
}
