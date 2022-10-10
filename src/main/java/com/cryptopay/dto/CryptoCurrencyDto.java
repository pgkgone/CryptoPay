package com.cryptopay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CryptoCurrencyDto implements Serializable {
    private final Long id;
    private final String name;
    private final String shortcutName;
}
