package com.cryptopay.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "service")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private ServiceType serviceType;

    @Column(name = "cost")
    private Double cost;

    @ManyToMany
    @JoinTable(name = "service_crypto_currencies",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "crypto_currencies_id"))
    @ToString.Exclude
    private Collection<CryptoCurrency> cryptoCurrencies = new ArrayList<>();

    @OneToMany(mappedBy = "service")
    @ToString.Exclude
    private Collection<Payment> payments = new ArrayList<>();

}
