package com.cryptopay.scheduler;

import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.service.PaymentService;
import com.cryptopay.service.PaymentStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceExecutionJob implements Job {
    private final PaymentService paymentService;
    private final PaymentStatusService paymentStatusService;

    @Override
    @Transactional
    public void execute() {
        log.info("starts {} job", this.getClass().getName());
        var paidPayments = paymentService.getPayments(List.of(PaymentStatusValue.Paid));
        for (var payment : paidPayments) {
            this.paymentStatusService.setPaymentStatus(PaymentStatusValue.Finished ,payment);
            log.info("Payment: {} was finished", payment);
        }
    }
}
