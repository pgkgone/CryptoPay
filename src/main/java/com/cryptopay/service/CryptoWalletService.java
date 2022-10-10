package com.cryptopay.service;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.config.SupportedCurrency;
import com.cryptopay.service.chainclients.chainexplorer.AbstractChainExplorerAdapter;
import com.cryptopay.service.chainclients.walletgenerator.AbstractWalletGenerator;
import com.cryptopay.service.chainclients.walletgenerator.GeneratedWalletInfo;
import com.cryptopay.service.chainclients.walletgenerator.WalletFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CryptoWalletService {

    private final Map<WalletFormat, AbstractWalletGenerator> walletGenerators;
    private final Map<SupportedChain, AbstractChainExplorerAdapter> chainExplorers;
    private final CryptoCurrencyChainJoinService cryptoCurrencyChainJoinService;

    public CryptoWalletService(
            Map<WalletFormat, AbstractWalletGenerator> walletGenerators,
            Map<SupportedChain, AbstractChainExplorerAdapter> chainExplorers,
            CryptoCurrencyChainJoinService cryptoCurrencyChainJoinService
    ) {
        this.walletGenerators = walletGenerators;
        this.chainExplorers = chainExplorers;
        this.cryptoCurrencyChainJoinService = cryptoCurrencyChainJoinService;
    }

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
