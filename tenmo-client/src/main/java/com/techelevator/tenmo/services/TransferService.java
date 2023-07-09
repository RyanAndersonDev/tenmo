package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TransferService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = "http://localhost:8080";
    private String authToken = null;


    public void pay(TransferInput transferInput) {
        restTemplate.exchange(baseUrl + "/pay", HttpMethod.POST, makePayEntity(transferInput), TransferInput.class);
    }

    public void listTransfers() {
        TransferListResponseDto transferObject = restTemplate.exchange(baseUrl + "/transfers", HttpMethod.GET, makeAuthEntity(),
                TransferListResponseDto.class).getBody();

        int currentAccountId = transferObject.getAccountId();
        Map<String, Transfer> transfers = transferObject.getUserNameTransferMap();

        System.out.println("-------------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID          From/To                 Amount");
        System.out.println("-------------------------------------------");

        for(Map.Entry<String, Transfer> entry : transfers.entrySet()) {
            String username = entry.getKey();
            Transfer transfer = entry.getValue();

            if(currentAccountId == transfer.getAccountFrom()) {
                System.out.println(transfer.getTransferId() + "          To:" +
                        username + "          $" + transfer.getAmount());
            } else {
                System.out.println(transfer.getTransferId() + "          From:" +
                        username + "          $" + transfer.getAmount());
            }
        }
        System.out.println("---------");
    }

    public void transferDetails(int transferId, AuthenticatedUser currentUser) {
        TransferListResponseDto transferMap = restTemplate.exchange(baseUrl + "/transfers/" + transferId,
                HttpMethod.GET, makeAuthEntity(), TransferListResponseDto.class).getBody();
        Map<String, Transfer> map = transferMap.getUserNameTransferMap();
        Set<String> nameSet = map.keySet();
        String extractedName = nameSet.iterator().next();
        Transfer transfer = map.get(extractedName);
        int currentAccountId = transferMap.getAccountId();

        String type = "";
        String status = "";

        if(transfer.getTransferTypeId() == 2) {
            type = "Send";
            status = "Approved";
        } else {
            type = "Request";
            status = "Pending";
        }

        if(currentAccountId == transfer.getAccountFrom()) {
            System.out.println("--------------------------------------------\n" +
                    "Transfer Details\n" +
                    "--------------------------------------------\n" +
                    "Id: " + transfer.getTransferId() + "\n" +
                    "From: " + currentUser.getUser().getUsername() + "\n" +
                    "To: " + extractedName + "\n" +
                    "Type: " + type + "\n" +
                    "Status: " + status + "\n" +
                    "Amount: $" + transfer.getAmount() + "\n");
        } else {
            System.out.println("--------------------------------------------\n" +
                    "Transfer Details\n" +
                    "--------------------------------------------\n" +
                    "Id: " + transfer.getTransferId() + "\n" +
                    "From: " + extractedName + "\n" +
                    "To: " + currentUser.getUser().getUsername() + "\n" +
                    "Type: " + type + "\n" +
                    "Status: " + status + "\n" +
                    "Amount: $" + transfer.getAmount() + "\n");
        }
    }

    public HttpEntity<TransferInput> makePayEntity(TransferInput transferInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transferInput, headers);
    }

    public HttpEntity<Void> makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

}
