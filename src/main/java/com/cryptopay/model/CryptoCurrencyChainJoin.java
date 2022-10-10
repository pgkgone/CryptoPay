package com.cryptopay.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "crypto_currency_chain_join")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CryptoCurrencyChainJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "decimals", nullable = false)
    private Long decimals;

    @Column(name = "contract_address", nullable = false)
    private String contractAddress;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "crypto_currency_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "crypto_chain_id", nullable = false)
    private CryptoChain cryptoChain;

}