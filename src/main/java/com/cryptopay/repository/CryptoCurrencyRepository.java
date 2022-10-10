package com.cryptopay.repository;

import com.cryptopay.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    Optional<CryptoCurrency> findByShortcutName(String shortcutName);
}