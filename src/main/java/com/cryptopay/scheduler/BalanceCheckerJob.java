package com.cryptopay.scheduler;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.config.SupportedCurrency;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.service.CryptoWalletService;
import com.cryptopay.service.PaymentService;
import com.cryptopay.service.PaymentStatusService;
import com.cryptopay.service.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@Slf4j
public class BalanceCheckerJob implements Job {

    private final PaymentService paymentService;
    private final PaymentStatusService paymentStatusService;
    private final CryptoWalletService cryptoWalletService;
    private final ServiceService serviceService;

    public BalanceCheckerJob(PaymentService paymentService, PaymentStatusService paymentStatusService, CryptoWalletService cryptoWalletService, ServiceService serviceService) {
        this.paymentService = paymentService;
        this.paymentStatusService = paymentStatusService;
        this.cryptoWalletService = cryptoWalletService;
        this.serviceService = serviceService;
    }

    @Override
    @Transactional
    public void execute() {
        log.info("starts {} job", this.getClass().getName());
        var payments = paymentService.getPayments(
                List.of(PaymentStatusValue.Created, PaymentStatusValue.Pending)
        );
        for (var payment : payments) {
            var walletBalance = this.cryptoWalletService.getWalletBalance(
                    SupportedChain.valueOf(payment.getCryptoChain().getShortcutName()),
                    SupportedCurrency.valueOf(payment.getCryptoCurrency().getShortcutName()),
                    payment.getCryptoWallet().getAddress()
            );
            log.info("account balance is {}", walletBalance);
            if (walletBalance.compareTo(BigDecimal.valueOf(payment.getService().getCost())) >= 0) {
                this.paymentStatusService.setPaidStatus(payment);
            } else {
                log.info("Retrieved latest payment status: {}", payment.getPaymentStatus());
                if (
                        payment.getPaymentStatus()
                                .getTime()
                                .plusMinutes(payment.getPaymentStatus().getExpireInMinutes())
                                .compareTo(LocalDateTime.now(ZoneOffset.UTC)) < 0)
                {
                    paymentStatusService.setCanceledStatus(
                            payment,
                            "Payment canceled due to timeout");
                }
            }
        }
    }

}
