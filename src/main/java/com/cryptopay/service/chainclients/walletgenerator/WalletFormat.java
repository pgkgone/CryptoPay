package com.cryptopay.service.chainclients.walletgenerator;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.exception.WalletGenerationException;

public enum WalletFormat {
    ERC,
    TRC;

    public static WalletFormat mapChainToWalletFormat(SupportedChain chain) {
        switch (chain) {
            case eth:
            case bsc:
                return WalletFormat.ERC;
            case trc:
                return WalletFormat.TRC;
        }
        throw  new WalletGenerationException(String.format("Supported chain <-> Wallet format mapping no set for %s", chain.toString()));
    }
}
