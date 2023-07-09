package com.techelevator.tenmo.model;

import java.util.List;
import java.util.Map;

public class TransferListResponseDto {

    private int accountId;
    private List<String> otherUsernames;
    private List<Transfer> transfers;

    private String otherUsername;
    private Transfer transfer;

    public TransferListResponseDto() {

    }

    public TransferListResponseDto(int accountId, List<String> otherUsernames, List<Transfer> transfers) {
        this.accountId = accountId;
        this.otherUsernames = otherUsernames;
        this.transfers = transfers;
    }

    public TransferListResponseDto(int accountId, String otherUsername, Transfer transfer) {
        this.accountId = accountId;
        this.otherUsername = otherUsername;
        this.transfer = transfer;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<String> getOtherUsernames() {
        return otherUsernames;
    }

    public void setOtherUsernames(List<String> otherUsernames) {
        this.otherUsernames = otherUsernames;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public String getOtherUsername() {
        return otherUsername;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
}
