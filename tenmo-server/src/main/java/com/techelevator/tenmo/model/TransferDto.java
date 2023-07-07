package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDto {

    private int accountToId;
    private BigDecimal amount;

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

    @Override
    public String toString() {
        return "LoginDto{" +
                "account_id='" + accountToId + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
