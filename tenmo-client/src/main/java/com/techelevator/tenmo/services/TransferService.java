package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.TransferInput;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080";
    private String authToken = null;


    public void pay(TransferInput transferInput) {
        restTemplate.exchange(baseUrl + "/pay", HttpMethod.POST, makePayEntity(transferInput), TransferInput.class);
    }

    public HttpEntity<TransferInput> makePayEntity(TransferInput transferInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transferInput, headers);
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

}
