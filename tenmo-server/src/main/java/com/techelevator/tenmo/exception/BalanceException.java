package com.techelevator.tenmo.exception;

public class BalanceException extends RuntimeException {
    public BalanceException() {
            super();
    }
    public BalanceException(String message) {
            super(message);
    }
    public BalanceException(String message, Exception cause) {
        super(message, cause);
    }
}
