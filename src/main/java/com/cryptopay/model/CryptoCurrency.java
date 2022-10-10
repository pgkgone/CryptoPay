package com.cryptopay.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "crypto_currency")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CryptoCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "shortcut_name", nullable = false, unique = true)
    private String shortcutName;

    @OneToMany(
            mappedBy = "cryptoCurrency"
    )
    @ToString.Exclude
    private Collection<Payment> payments = new ArrayList<>();

    @ManyToMany(mappedBy = "cryptoCurrencies")
    @ToString.Exclude
    private Collection<Service> services = new ArrayList<>();

    @OneToMany(mappedBy = "cryptoCurrency", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Collection<CryptoCurrencyChainJoin> chainJoin = new ArrayList<>();

}