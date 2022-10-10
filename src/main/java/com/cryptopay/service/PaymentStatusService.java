package com.cryptopay.service;

import com.cryptopay.model.Payment;
import com.cryptopay.model.PaymentStatus;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class PaymentStatusService {
    private final Long expireInMinutes;

    private final PaymentStatusRepository paymentStatusRepository;
    public PaymentStatusService(
            @Value("${application.payment.expireInMinutes}") Long expireInMinutes,
            PaymentStatusRepository paymentStatusRepository
    ) {
        this.expireInMinutes = expireInMinutes;
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Transactional
    public void setCreateStatus(Payment payment) {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Created);
        paymentStatus.setMessage("Payment created.");
        setTime(paymentStatus);
        setExpire(paymentStatus);
        paymentStatus.setPayment(payment);
        payment.setPaymentStatus(paymentStatus);
        this.paymentStatusRepository.save(paymentStatus);
    }

    @Transactional
    public void setPendingStatus(Payment payment) {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Pending);
        paymentStatus.setMessage("Pending transfer.");
        setTime(paymentStatus);
        setExpire(paymentStatus);
        paymentStatus.setPayment(payment);
        payment.setPaymentStatus(paymentStatus);
        this.paymentStatusRepository.save(paymentStatus);
    }

    @Transactional
    public void setPaidStatus(Payment payment) {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Paid);
        paymentStatus.setMessage("Payment accepted. Waiting for the service to be completed.");
        setTime(paymentStatus);
        paymentStatus.setPayment(payment);
        payment.setPaymentStatus(paymentStatus);
        this.paymentStatusRepository.save(paymentStatus);
    }

    @Transactional
    public void setFinishedStatus(Payment payment) {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Finished);
        paymentStatus.setMessage("Payment successfully completed.");
        setTime(paymentStatus);
        paymentStatus.setPayment(payment);
        payment.setPaymentStatus(paymentStatus);
        this.paymentStatusRepository.save(paymentStatus);
    }

    @Transactional
    public void setCanceledStatus(Payment payment, String message) {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Canceled);
        paymentStatus.setMessage(message);
        setTime(paymentStatus);
        paymentStatus.setPayment(payment);
        payment.setPaymentStatus(paymentStatus);
        this.paymentStatusRepository.save(paymentStatus);
    }

    @Transactional
    public PaymentStatus createCreateStatus() {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Created);
        paymentStatus.setMessage("Payment created.");
        setTime(paymentStatus);
        setExpire(paymentStatus);
        return paymentStatus;
    }

    @Transactional
    public PaymentStatus createPendingStatus() {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Pending);
        paymentStatus.setMessage("Pending transfer.");
        setTime(paymentStatus);
        setExpire(paymentStatus);
        return paymentStatus;
    }

    @Transactional
    public PaymentStatus createPaidStatus() {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Paid);
        paymentStatus.setMessage("Payment accepted. Waiting for the service to be completed.");
        setTime(paymentStatus);
        return paymentStatus;
    }

    @Transactional
    public PaymentStatus createFinishedStatus() {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Finished);
        paymentStatus.setMessage("Payment successfully completed.");
        setTime(paymentStatus);
        return paymentStatus;
    }

    @Transactional
    public PaymentStatus createCanceledStatus(String message) {
        var paymentStatus = new PaymentStatus();
        paymentStatus.setStatusValue(PaymentStatusValue.Canceled);
        paymentStatus.setMessage(message);
        setTime(paymentStatus);
        return paymentStatus;
    }

    private void setTime(PaymentStatus paymentStatus) {
        paymentStatus.setTime(LocalDateTime.now(ZoneOffset.UTC));
    }

    private void setExpire(PaymentStatus paymentStatus) {
        paymentStatus.setExpireInMinutes(this.expireInMinutes);
    }

}
