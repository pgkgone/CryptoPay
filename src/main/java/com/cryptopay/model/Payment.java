package com.cryptopay.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "payment_status_id", nullable = false)
    private PaymentStatus paymentStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "crypto_wallet_id")
    private CryptoWallet cryptoWallet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "crypto_chain_id", nullable = false)
    private CryptoChain cryptoChain;

    @ManyToOne(optional = false)
    @JoinColumn(name = "crypto_currency_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

}