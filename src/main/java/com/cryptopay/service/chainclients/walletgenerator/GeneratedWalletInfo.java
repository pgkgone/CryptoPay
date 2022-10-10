package com.cryptopay.service.chainclients.walletgenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratedWalletInfo {
    private String privateKey;
    private String publicKey;
    private String address;
}
