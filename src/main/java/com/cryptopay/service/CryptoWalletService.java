package com.cryptopay.service;

import com.cryptopay.enums.SupportedChain;
import com.cryptopay.enums.SupportedCurrency;
import com.cryptopay.service.chainclients.chainexplorer.AbstractChainExplorerAdapter;
import com.cryptopay.service.chainclients.walletgenerator.AbstractWalletGenerator;
import com.cryptopay.service.chainclients.walletgenerator.GeneratedWalletInfo;
import com.cryptopay.enums.WalletFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoWalletService {

    private final Map<WalletFormat, AbstractWalletGenerator> walletGenerators;
    private final Map<SupportedChain, AbstractChainExplorerAdapter> chainExplorers;
    private final CryptoCurrencyChainJoinService cryptoCurrencyChainJoinService;

    @Transactional(readOnly = true)
    public BigDecimal getWalletBalance(
            SupportedChain chain,
            SupportedCurrency currency,
            String address
    ) {
        var cryptoCurrencyChainJoin = this
                .cryptoCurrencyChainJoinService
                .getCryptoCurrencyChainJoinByContract(currency, chain);
        return this.chainExplorers.get(chain).getBalance(
                address,
                cryptoCurrencyChainJoin.getContractAddress(),
                cryptoCurrencyChainJoin.getDecimals()
        );
    }

    public GeneratedWalletInfo generateWallet(WalletFormat walletFormat) {
        return this.walletGenerators.get(walletFormat).generateWallet();
    }
}