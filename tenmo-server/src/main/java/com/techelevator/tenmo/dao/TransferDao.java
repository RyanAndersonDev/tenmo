package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface TransferDao {
    void updateRecipientBalance(BigDecimal transferAmount, int accountId);
    boolean createTransfer(boolean isRequest, int accountFrom, int accountTo, BigDecimal amount);
    void updateSenderBalance(BigDecimal transferAmount, int accountId);

}
