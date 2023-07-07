package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.TransferInput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080";


    public void pay(TransferInput transferInput) {
        restTemplate.exchange(baseUrl + "/pay", HttpMethod.POST, makePayEntity(transferInput), Boolean.class);
    }

    public HttpEntity<TransferInput> makePayEntity(TransferInput transferInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(transferInput, headers);
    }

}
