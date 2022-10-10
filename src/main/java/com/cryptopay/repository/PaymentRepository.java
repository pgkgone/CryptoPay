package com.cryptopay.repository;

import com.cryptopay.model.Payment;
import com.cryptopay.model.PaymentStatusValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from Payment p where p.paymentStatus.statusValue in :values")
    List<Payment> findByPaymentStatuses(@Param("values") List<PaymentStatusValue> values);

}