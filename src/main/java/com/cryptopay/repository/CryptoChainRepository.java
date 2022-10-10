package com.cryptopay.repository;

import com.cryptopay.model.CryptoChain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CryptoChainRepository extends JpaRepository<CryptoChain, Long> {
    Optional<CryptoChain> findByShortcutName(String shortcutName);
}