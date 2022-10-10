package com.cryptopay.service;

import com.cryptopay.enums.SupportedChain;
import com.cryptopay.enums.SupportedCurrency;
import com.cryptopay.model.CryptoCurrencyChainJoin;
import com.cryptopay.repository.CryptoCurrencyChainJoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoCurrencyChainJoinService {

    private final CryptoCurrencyChainJoinRepository cryptoCurrencyChainJoinRepository;

    @Transactional(readOnly = true)
    public CryptoCurrencyChainJoin getCryptoCurrencyChainJoinByContract(
            SupportedCurrency currency,
            SupportedChain chain
    ) {
        return cryptoCurrencyChainJoinRepository.findByCryptoCurrencyShortcutNameAndCryptoChainShortcutName(
                currency.toString(),
                chain.toString()
        ).get();
    }
}
