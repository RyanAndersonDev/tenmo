package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class AccountService {

    private String authToken = null;
    private final String baseUrl = "http://localhost:8080";
    private final RestTemplate restTemplate = new RestTemplate();

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public BigDecimal getBalance(){
        BigDecimal balance = new BigDecimal("0");
        ResponseEntity<BigDecimal> response = restTemplate.exchange(baseUrl + "/balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class);
        balance = response.getBody();
        return balance;
    }


    public HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    public void listUsers() {
        User[] users = restTemplate.exchange(baseUrl + "/accounts", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();

        System.out.println("-------------------------------------------");
        System.out.println("User ID        User Name");
        System.out.println("-------------------------------------------");
        for(User user : users) {
            System.out.println(user.getId() + "           " + user.getUsername());
        }
        System.out.println("---------");
    }

}
