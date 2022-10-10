package com.cryptopay.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_status")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private PaymentStatusValue statusValue;

    @Column(name = "expire_in_minutes")
    private Long expireInMinutes;

    @Column(name = "message")
    private String message;


    @OneToOne(mappedBy = "paymentStatus")
    @ToString.Exclude
    private Payment payment;

}