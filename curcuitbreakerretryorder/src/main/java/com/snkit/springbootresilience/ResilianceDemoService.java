package com.snkit.springbootresilience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@Service
public class ResilianceDemoService {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(ResilianceDemoService.class);
	
	@CircuitBreaker(name="getEmploye",fallbackMethod="getCustFallBack")
	@Retry(name="getEmployetretry",fallbackMethod="getRetryCustFallBack")
	public String getCust() {
		logger.info(" Entring into getCust  ResilianceDemoService ");
		
		MultiValueMap<String, String> headers = new HttpHeaders();
		
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		HttpEntity requestEntity = new HttpEntity(headers) ;
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8070/getEmploye",
				HttpMethod.GET,
				requestEntity,
				String.class);
		
		logger.info(" Exist from  getCust  ResilianceDemoService ");
		return response.getBody();

	}
	
	 // this configuration is required when we donot want consider each retry call for curcuit count
	public String getCustFallBack(java.lang.Throwable ex) {
		logger.info("From GetCustFallBack response ");
		System.out.println(" getCustFallBack  "+ex);
	return "Response from getCustFallBack";
	}
	public String getRetryCustFallBack(java.lang.Throwable throwable) {
		logger.info("From getRetryCustFallBack response ");
		System.out.println(" getRetryCustFallBack  "+throwable);
	       throw new RuntimeException("Retry not worked");
	}
	
	
	/*
	 // this configuration is required when we want consider each retry call for Circuit count
	public String getCustFallBack(CallNotPermittedException ex) {
		logger.info("From GetCustFallBack response ");
		System.out.println(" getCustFallBack  "+ex);
	return "Response from getCustFallBack";
	}
	public String getRetryCustFallBack(java.lang.Throwable throwable) {
		logger.info("From getRetryCustFallBack response ");
		System.out.println(" getRetryCustFallBack  "+throwable);
		return "Response from getRetryCustFallBack";
	} 
	*/
	
}
