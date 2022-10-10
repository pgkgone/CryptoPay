package com.cryptopay.service.chainclients.walletgenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tron.trident.core.key.KeyPair;

@Component
@Slf4j
public class TrcWalletGenerator extends AbstractWalletGenerator {

    @Override
    GeneratedWalletInfo generateWalletImpl() {
        KeyPair keyPair = KeyPair.generate();
        return new GeneratedWalletInfo(
                keyPair.toPrivateKey(),
                keyPair.toPublicKey(),
                keyPair.toBase58CheckAddress()
        );
    }

    @Override
    public WalletFormat getWalletFormat() {
        return WalletFormat.TRC;
    }
}
