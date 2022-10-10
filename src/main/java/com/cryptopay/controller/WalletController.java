package com.cryptopay.controller;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.config.SupportedCurrency;
import com.cryptopay.service.CryptoWalletService;
import com.cryptopay.service.chainclients.walletgenerator.GeneratedWalletInfo;
import com.cryptopay.service.chainclients.walletgenerator.WalletFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Slf4j
@RequestMapping("/wallet")
public class WalletController {

    private final CryptoWalletService cryptoWalletService;

    public WalletController(
            CryptoWalletService cryptoWalletService
    ) {
        this.cryptoWalletService = cryptoWalletService;
    }

    @GetMapping("/generate/{walletFormat}")
    public GeneratedWalletInfo generateWallet(@PathVariable WalletFormat walletFormat) {
        return cryptoWalletService.generateWallet(walletFormat);
    }

    @GetMapping("/balance/{chain}/{currency}")
    public BigDecimal balance(
            @PathVariable SupportedChain chain,
            @PathVariable SupportedCurrency currency,
            @RequestParam String address
    ) {
        return cryptoWalletService.getWalletBalance(chain,currency,address);
    }
}