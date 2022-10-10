package com.cryptopay.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * A DTO for the {@link com.cryptopay.model.Service} entity
 */
@Data
public class ServiceInfoDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final ServiceTypeDto serviceType;
    private final Double cost;
    private final Collection<CryptoCurrencyInfoDto> cryptoCurrencies;

    /**
     * A DTO for the {@link com.cryptopay.model.CryptoCurrency} entity
     */
    @Data
    public static class CryptoCurrencyInfoDto implements Serializable {
        private final Long id;
        private final String name;
        private final String shortcutName;
        private final Set<CryptoChainDto> cryptoChains;
    }
}