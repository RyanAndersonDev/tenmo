package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    private String authToken = null;
    private final String baseUrl = "http://localhost:8080/";

    private final RestTemplate restTemplate = new RestTemplate();

    public BigDecimal getBalance(){
        BigDecimal balance = new BigDecimal("0");
        ResponseEntity<BigDecimal> response = restTemplate.exchange(baseUrl + "balance", HttpMethod.GET,makeAuthEntity(), BigDecimal.class);
        balance = response.getBody();
        return balance;
    }


    public HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

}
