package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.Company;
import com.example.model.Graduation;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class IntermediateService {

	private static Integer COMPANY_API_CALL_COUNT = 0;
	private static Integer GRADUATION_API_CALL_COUNT = 0;

	@Autowired
	private RestTemplate restTempalte;

	private Logger LOGGER = LoggerFactory.getLogger(IntermediateService.class);
	
	@Autowired
    private CircuitBreakerFactory circuitBreakerFactory;
	
	@Retry(name = "getGraduationData", fallbackMethod = "getGraduationDataFallback")
	public Graduation getGraduationData(Integer id) {
		GRADUATION_API_CALL_COUNT++;
		
		LOGGER.info("Inside getGraduationData method");
		LOGGER.info("Get Graduation Data method called " + GRADUATION_API_CALL_COUNT);

		ResponseEntity<Graduation> graduationData = restTempalte
				.getForEntity("http://localhost:8081/graduation/getUserGraduationDataById/" + id, Graduation.class);

		LOGGER.info("Status Code: " + graduationData.getStatusCode());

		return graduationData.getBody();
	}
	
	public Graduation getGraduationDataFallback(Integer id, Exception ex) {
		LOGGER.info("getGraduationData method failed for id : " + id + "\n" + "exception " + ex.getMessage());
		return null;
	}

//	@CircuitBreaker(name = "getCompanyData", fallbackMethod = "getCompanyDataFallback")
	public Company getCompanyData(Integer id) {
		COMPANY_API_CALL_COUNT++;

		LOGGER.info("Inside getGraduationData method");
		LOGGER.info("Get Company Data method called " + COMPANY_API_CALL_COUNT);
		
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		
		return circuitBreaker.run(() -> restTempalte
				.getForObject("http://localhost:8082/company/getUserCompanyDataById/" + id, Company.class), 
				throwable -> {
					LOGGER.info("******************************************");
					return null;
				});

//		ResponseEntity<Company> companyData = restTempalte
//				.getForEntity("http://localhost:8082/company/getUserCompanyDataById/" + id, Company.class);

//		LOGGER.info("Status Code: " + companyData.getStatusCode());

//		return companyData.getBody();
	}

	public Company getCompanyDataFallback(Integer id, Exception ex) {
		LOGGER.info("getCompanyData method failed for id : " + id + "\n" + "exception " + ex.getMessage());
		return null;
	}

}
