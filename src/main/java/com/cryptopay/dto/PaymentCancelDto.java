package com.cryptopay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentCancelDto {
    private Long id;
    private String message;
}
