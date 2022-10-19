package com.cryptopay.controller;

import com.cryptopay.enums.SupportedChain;
import com.cryptopay.enums.SupportedCurrency;
import com.cryptopay.enums.WalletFormat;
import com.cryptopay.service.CryptoWalletService;
import com.cryptopay.service.chainclients.walletgenerator.GeneratedWalletInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {

    private final CryptoWalletService cryptoWalletService;

    @GetMapping("/generate/{walletFormat}")
    public GeneratedWalletInfo generateWallet(
            @PathVariable WalletFormat walletFormat
    ) {
        log.info("generating wallet");
        return cryptoWalletService.generateWallet(walletFormat);
    }

    @GetMapping("/balance/{chain}/{currency}")
    public BigDecimal balance(
            @PathVariable SupportedChain chain,
            @PathVariable SupportedCurrency currency,
            @RequestParam String address
    ) {
        return cryptoWalletService.getWalletBalance(chain, currency, address).get();
    }
}