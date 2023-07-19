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
        List<String> otherUserNames = transferObject.getOtherUsernames();
        List<Transfer> transfers = transferObject.getTransfers();

        System.out.println("-------------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID          From/To                 Amount");
        System.out.println("-------------------------------------------");

        for(int i =  0; i < transfers.size(); i++) {
            String username = otherUserNames.get(i);
            Transfer transfer = transfers.get(i);

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
        TransferListResponseDto transferObject = restTemplate.exchange(baseUrl + "/transfers/" + transferId,
                HttpMethod.GET, makeAuthEntity(), TransferListResponseDto.class).getBody();

        int currentAccountId = transferObject.getAccountId();
        String otherName = transferObject.getOtherUsername();
        Transfer transfer = transferObject.getTransfer();

        String type = "";
        String status = "";
        String from = "";
        String to = "";

        if(transfer.getTransferTypeId() == 2) {
            type = "Send";
            status = "Approved";
        } else {
            type = "Request";
            status = "Pending";
        }

        if(currentAccountId == transfer.getAccountFrom()) {
            from = currentUser.getUser().getUsername();
            to = otherName;
        } else {
            from = otherName;
            to = currentUser.getUser().getUsername();
        }

        System.out.println("--------------------------------------------\n" +
                "Transfer Details\n" +
                "--------------------------------------------\n" +
                "Id: " + transfer.getTransferId() + "\n" +
                "From: " + from + "\n" +
                "To: " + to + "\n" +
                "Type: " + type + "\n" +
                "Status: " + status + "\n" +
                "Amount: $" + transfer.getAmount() + "\n");
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
