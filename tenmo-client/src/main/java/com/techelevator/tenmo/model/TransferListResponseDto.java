package com.techelevator.tenmo.model;

import java.util.List;
import java.util.Map;

public class TransferListResponseDto {
    private int accountId;
    private Map<String, Transfer> userNameTransferMap;
    private Transfer transfer;

    public TransferListResponseDto() {

    }

    public TransferListResponseDto(Transfer transfer) {
        this.transfer = transfer;
    }

    public TransferListResponseDto(int accountId, Map<String, Transfer> map) {
        this.accountId = accountId;
        this.userNameTransferMap = map;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Map<String, Transfer> getUserNameTransferMap() {
        return userNameTransferMap;
    }

    public void setUserNameTransferMap(Map<String, Transfer> userNameTransferMap) {
        this.userNameTransferMap = userNameTransferMap;
    }
}
