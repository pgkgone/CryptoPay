package com.cryptopay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CryptoCurrencyChainJoinDto implements Serializable {
    private final Long id;
    private final Long decimals;
    private final String contractAddress;
}