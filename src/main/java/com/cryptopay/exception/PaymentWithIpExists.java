package com.cryptopay.exception;

import lombok.Data;

@Data
public class PaymentWithIpExists extends Exception {

    private Long id;
    public PaymentWithIpExists(Long id, String message) {
        super(message);
        this.id = id;
    }
}
