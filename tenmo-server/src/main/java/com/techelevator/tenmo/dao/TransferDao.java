package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    void updateRecipientBalance(BigDecimal transferAmount, int accountId);
    void createTransfer(boolean isRequest, int accountFrom, int accountTo, BigDecimal amount);
    void updateSenderBalance(BigDecimal transferAmount, int accountId);
    List<Transfer> getTransferList(int accountId);

}
