package com.cryptopay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CryptoWalletDto implements Serializable {
    private final Long id;
    private final String wallet;
}