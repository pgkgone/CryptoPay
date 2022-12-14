package com.cryptopay.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "crypto_chain")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CryptoChain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "shortcut_name", nullable = false, unique = true)
    private String shortcutName;

    @OneToMany(mappedBy = "cryptoChain", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Collection<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "cryptoChain", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Collection<CryptoCurrencyChainJoin> cryptoCurrencyJoin = new ArrayList<>();

}