package com.cryptopay.service;

import com.cryptopay.exception.PaymentStatusTransitionException;
import com.cryptopay.model.Payment;
import com.cryptopay.model.PaymentStatus;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentStatusService {
    @Value("${settings.payment.expireInMinutes}")
    private Long expireInMinutes;
    private final PaymentStatusRepository paymentStatusRepository;

    @Transactional
    public void setPaymentStatus(PaymentStatusValue paymentStatusValue, Payment payment) {
        setPaymentStatus(paymentStatusValue, payment, getPaymentMessage(paymentStatusValue));
    }

    @Transactional
    public void setPaymentStatus(PaymentStatusValue paymentStatusValue, Payment payment, String message) {
        setPaymentStatus(paymentStatusValue, payment, message, getExpireInMinutes(paymentStatusValue));
    }

    @Transactional
    public PaymentStatus createPaymentStatus(PaymentStatusValue paymentStatusValue) {
        return createPaymentStatus(paymentStatusValue, getPaymentMessage(paymentStatusValue));
    }

    @Transactional
    public PaymentStatus createPaymentStatus(PaymentStatusValue paymentStatusValue, String message) {
        return createPaymentStatus(paymentStatusValue, message, getExpireInMinutes(paymentStatusValue));
    }

    private void setPaymentStatus(
            PaymentStatusValue paymentStatusValue,
            Payment payment,
            String message,
            Optional<Long> expireInMinutes
    ) {
        if (!paymentStatusValue.isPossibleFrom(payment.getPaymentStatus().getStatusValue())) {
            throw PaymentStatusTransitionException.of(
                    payment.getPaymentStatus().getStatusValue(),
                    paymentStatusValue
            );
        }
        var paymentStatus = createPaymentStatus(
                paymentStatusValue,
                message,
                expireInMinutes
        );
        paymentStatus.setPayment(payment);
        payment.setPaymentStatus(paymentStatus);
        paymentStatusRepository.save(paymentStatus);
    }

    private PaymentStatus createPaymentStatus(
            PaymentStatusValue paymentStatusValue,
            String message,
            Optional<Long> expireInMinutes
    ) {
        var paymentStatusBuilder = PaymentStatus
                .builder()
                .statusValue(paymentStatusValue)
                .message(message)
                .time(LocalDateTime.now(ZoneOffset.UTC));
        expireInMinutes.ifPresent(paymentStatusBuilder::expireInMinutes);
        return paymentStatusBuilder.build();
    }

    private String getPaymentMessage(PaymentStatusValue paymentStatusValue) {
        switch (paymentStatusValue) {
            case Created:
                return "Payment created.";
            case Pending:
                return "Pending transfer.";
            case Paid:
                return "Payment accepted. Waiting for the service to be completed.";
            case Finished:
                return "Payment successfully completed.";
            case Canceled:
                return "Payment was cancelled by unknown reason.";
            default:
                return "Unknown payment status.";
        }
    }

    private Optional<Long> getExpireInMinutes(PaymentStatusValue paymentStatusValue) {
        switch (paymentStatusValue) {
            case Created:
            case Pending:
                return Optional.of(this.expireInMinutes);
            default:
                return Optional.empty();
        }
    }
}
