package com.cryptopay.service;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.config.SupportedCurrency;
import com.cryptopay.model.CryptoCurrencyChainJoin;
import com.cryptopay.repository.CryptoCurrencyChainJoinRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CryptoCurrencyChainJoinService {

    private CryptoCurrencyChainJoinRepository cryptoCurrencyChainJoinRepository;

    public CryptoCurrencyChainJoinService(CryptoCurrencyChainJoinRepository cryptoCurrencyChainJoinRepository) {
        this.cryptoCurrencyChainJoinRepository = cryptoCurrencyChainJoinRepository;
    }

    @Transactional(readOnly = true)
    public CryptoCurrencyChainJoin getCryptoCurrencyChainJoinByContract(
            SupportedCurrency currency,
            SupportedChain chain
            ) {
        return cryptoCurrencyChainJoinRepository.findByCryptoCurrency_ShortcutNameAndCryptoChain_ShortcutName(
                currency.toString(),
                chain.toString()
        ).get();
    }
}
