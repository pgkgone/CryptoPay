package com.cryptopay.repository;

import com.cryptopay.model.CryptoWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoWalletRepository extends JpaRepository<CryptoWallet, Long> {
}