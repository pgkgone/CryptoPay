package com.cryptopay.service.chainclients.walletgenerator;

import com.cryptopay.enums.WalletFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

@Component
@Slf4j
public class ErcWalletGenerator extends AbstractWalletGenerator {
    @Override
    GeneratedWalletInfo generateWalletImpl() throws Exception {
        byte[] seed = new byte[32];
        this.secureRandom.nextBytes(seed);
        ECKeyPair keyPair = Keys.createEcKeyPair();
        WalletFile wallet = Wallet.createStandard(new String(seed), keyPair);
        return new GeneratedWalletInfo(
                keyPair.getPrivateKey().toString(16),
                keyPair.getPublicKey().toString(16),
                "0x" + wallet.getAddress()
        );
    }

    @Override
    public WalletFormat getWalletFormat() {
        return WalletFormat.erc;
    }
}
