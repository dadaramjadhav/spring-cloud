package com.cg.spring.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.cg.spring.exceptions.HTTP400Exception;
import com.cg.spring.exceptions.HTTP404Exception;
import com.cg.spring.exceptions.RestAPIExceptionInfo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

public abstract class AbstractController implements ApplicationEventPublisherAware {
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected ApplicationEventPublisher eventPublisher;
	protected static final String DEFAULT_PAGE_SIZE="20";
	protected static final String DEFAULT_PAGE_NUMBER="0";
	
	Counter http400ExceptionCounter = Metrics.counter("com.cg.spring.ProductController.HTTP400");
	Counter http404ExceptionCounter = Metrics.counter("com.cg.spring.ProductController.HTTP404");
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public @ResponseBody RestAPIExceptionInfo handleDataStoreException(HTTP400Exception ex,
			WebRequest request, HttpServletResponse response) {
		logger.info("Received data store exception"+ ex.getLocalizedMessage());
		http400ExceptionCounter.increment();
		return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "request did not have correct parameters");
	}
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public @ResponseBody RestAPIExceptionInfo handleResourceNotFoundException(HTTP404Exception ex,
			WebRequest request, HttpServletResponse response) {
		logger.info("Received resource not found exception"+ ex.getLocalizedMessage());
		http404ExceptionCounter.increment();
		return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "request resource not found");
	}
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}
	
	public static <T> T checkResourceFound(final T resource) {
		if(resource == null) { 
			throw new  HTTP404Exception("resource not found");
		}
		return resource;
	}
}
