package com.cryptopay.dto;

import lombok.Data;

@Data
public class CreatePaymentDto {
    private Long serviceId;
    private String cryptoChainShortcutName;
    private String cryptoCurrencyShortcutName;
    private String email;
}
