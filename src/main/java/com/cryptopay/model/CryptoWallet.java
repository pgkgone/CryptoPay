package com.cryptopay.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "crypto_wallet")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CryptoWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "public_key", nullable = false, unique = true)
    private String publicKey;

    @Column(name = "private_key", nullable = false, unique = true)
    private String privateKey;

    @Column(name = "address", nullable = false, unique = true)
    private String address;

    @OneToOne(mappedBy = "cryptoWallet")
    @ToString.Exclude
    private Payment payment;
}