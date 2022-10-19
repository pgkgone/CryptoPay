package com.cryptopay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PaymentDto implements Serializable {
    private final Long id;
    private final PaymentStatusDto paymentStatus;
    private final ServiceDto service;
    private final String email;
    private final String cryptoWalletAddress;
    private final CryptoChainDto cryptoChain;
    private final CryptoCurrencyDto cryptoCurrency;
}