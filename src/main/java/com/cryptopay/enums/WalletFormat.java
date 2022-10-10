package com.cryptopay.enums;

import com.cryptopay.exception.WalletGenerationException;

public enum WalletFormat {
    erc, trc;

    public static WalletFormat mapChainToWalletFormat(SupportedChain chain) {
        switch (chain) {
            case eth:
            case bsc:
                return WalletFormat.erc;
            case trc:
                return WalletFormat.trc;
        }
        throw  new WalletGenerationException(String.format("Supported chain <-> Wallet format mapping no set for %s", chain));
    }
}
