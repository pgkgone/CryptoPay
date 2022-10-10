package com.cryptopay.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.cryptopay.model.CryptoCurrencyChainJoin} entity
 */
@Data
public class CryptoCurrencyChainJoinInfoDto implements Serializable {
    private final Long id;
    private final Long decimals;
    private final String contractAddress;
    private final CryptoCurrencyDto cryptoCurrency;
    private final CryptoChainDto cryptoChain;
}