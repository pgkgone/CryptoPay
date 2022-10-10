package com.cryptopay.exception;

import com.cryptopay.model.PaymentStatusValue;

public class PaymentStatusTransitionException extends RuntimeException {
    public PaymentStatusTransitionException(String message) {
        super(message);
    }

    public static PaymentStatusTransitionException of(PaymentStatusValue from, PaymentStatusValue to) {
        return new PaymentStatusTransitionException(
                String.format("Unable to transit from %s status to %s status", from.toString(), to.toString())
        );
    }
}
