package com.cryptopay.exception;

public class WalletBalanceParsingException extends RuntimeException {
    public WalletBalanceParsingException(String message) {
        super(message);
    }
}
