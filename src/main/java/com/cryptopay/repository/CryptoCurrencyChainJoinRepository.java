package com.cryptopay.repository;

import com.cryptopay.model.CryptoCurrencyChainJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CryptoCurrencyChainJoinRepository extends JpaRepository<CryptoCurrencyChainJoin, Long> {

    @Query(
           "select c from CryptoCurrencyChainJoin c " +
           "where c.cryptoCurrency.shortcutName = ?1 and c.cryptoChain.shortcutName = ?2"
    )
    Optional<CryptoCurrencyChainJoin> findByCryptoCurrencyShortcutNameAndCryptoChainShortcutName(
            String cryptoCurrencyShortcut,
            String chainShortcut
    );

}