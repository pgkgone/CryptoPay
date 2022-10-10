package com.cryptopay.model;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public enum PaymentStatusValue {
    Created(Set.of()),
    Pending(Set.of(PaymentStatusValue.Created)),
    Paid(Set.of(PaymentStatusValue.Created, PaymentStatusValue.Pending)),
    Finished(Set.of(PaymentStatusValue.Paid)),
    Canceled(Set.of(
            PaymentStatusValue.Created,
            PaymentStatusValue.Pending,
            PaymentStatusValue.Paid,
            PaymentStatusValue.Finished
    ));

    private Set<PaymentStatusValue> possibleTransitionFrom = new HashSet<>();

    public Boolean isPossibleFrom(PaymentStatusValue paymentStatusValue) {
        return this.possibleTransitionFrom.contains(paymentStatusValue);
    }
}
