package com.cryptopay.service.chainclients.walletgenerator;

import com.cryptopay.enums.WalletFormat;
import com.cryptopay.exception.WalletGenerationException;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

@Slf4j
abstract public class AbstractWalletGenerator {

    protected final SecureRandom secureRandom = new SecureRandom();

    public GeneratedWalletInfo generateWallet() {
        GeneratedWalletInfo wallet = null;
        try {
            wallet = generateWalletImpl();
        } catch (Exception e) {
            throw new WalletGenerationException(String.format("Unable to generate %s wallet", this.getWalletFormat().name()));
        }
        log.info("{} address: {} generated", this.getWalletFormat(), wallet.getAddress());
        return wallet;
    }

    abstract GeneratedWalletInfo generateWalletImpl() throws Exception;

    public abstract WalletFormat getWalletFormat();
}
