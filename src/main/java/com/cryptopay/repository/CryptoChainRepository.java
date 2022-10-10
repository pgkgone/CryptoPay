package com.cryptopay.repository;

import com.cryptopay.model.CryptoChain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoChainRepository extends JpaRepository<CryptoChain, Long> {
    Optional<CryptoChain> findByShortcutName(String shortcutName);
}