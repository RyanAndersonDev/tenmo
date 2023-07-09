package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

public interface TransferDao {
    void updateRecipientBalance(BigDecimal transferAmount, int accountId);
    void createTransfer(boolean isRequest, int accountFrom, int accountTo, BigDecimal amount);
    void updateSenderBalance(BigDecimal transferAmount, int accountId);
    List<Transfer> getTransferList(int accountId);
    Transfer getTransferById(int transferId);
    int getOtherAccountIdFromTransfer(int accountId, Transfer transfer);

}
