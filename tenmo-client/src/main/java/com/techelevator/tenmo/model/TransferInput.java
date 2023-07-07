package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferInput {
    private int accountToId;
    private BigDecimal amount;

    public TransferInput(int accountToId, BigDecimal amount) {
        this.accountToId = accountToId;
        this.amount = amount;
    }

    public int getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(int accountToId) {
        this.accountToId = accountToId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
