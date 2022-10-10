package com.cryptopay.dto;

import com.cryptopay.model.PaymentStatusValue;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PaymentStatusDto implements Serializable {
    private final Long id;
    private final LocalDateTime time;
    private final Long expireInMinutes;
    private final PaymentStatusValue statusValue;
    private final String message;
}
