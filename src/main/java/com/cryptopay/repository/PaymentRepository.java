package com.cryptopay.repository;

import com.cryptopay.model.Payment;
import com.cryptopay.model.PaymentStatusValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from Payment p where p.paymentStatus.statusValue in ?1")
    List<Payment> findByPaymentStatuses(List<PaymentStatusValue> values);

    @Query("select p from Payment p " +
            "where p.ip = ?1 and p.paymentStatus.statusValue in ?2")
    Optional<Payment> findByIpAndStatuses(
            String ip,
            List<PaymentStatusValue> statuses
    );

}