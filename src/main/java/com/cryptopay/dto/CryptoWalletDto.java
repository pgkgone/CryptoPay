package com.cryptopay.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cryptopay.model.CryptoWallet} entity
 */
@Data
public class CryptoWalletDto implements Serializable {
    private final Long id;
    private final String wallet;
}