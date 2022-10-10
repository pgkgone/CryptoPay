package com.cryptopay.scheduler;

import com.cryptopay.enums.SupportedChain;
import com.cryptopay.enums.SupportedCurrency;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.service.CryptoWalletService;
import com.cryptopay.service.PaymentService;
import com.cryptopay.service.PaymentStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BalanceCheckerJob implements Job {
    private final PaymentService paymentService;
    private final PaymentStatusService paymentStatusService;
    private final CryptoWalletService cryptoWalletService;

    @Override
    @Transactional
    public void execute() {
        log.info("starts {} job", this.getClass().getName());
        var payments = paymentService.getPayments(List.of(PaymentStatusValue.Created, PaymentStatusValue.Pending));
        for (var payment : payments) {
            var walletBalance = this.cryptoWalletService.getWalletBalance(SupportedChain.valueOf(payment.getCryptoChain().getShortcutName()), SupportedCurrency.valueOf(payment.getCryptoCurrency().getShortcutName()), payment.getCryptoWallet().getAddress());
            log.info("account balance is {}", walletBalance);
            if (
                    walletBalance.isPresent() &&
                    walletBalance.get().compareTo(BigDecimal.valueOf(payment.getService().getCost())) >= 0
            ) {
                this.paymentStatusService.setPaymentStatus(PaymentStatusValue.Paid, payment);
            } else {
                log.info("Retrieved latest payment status: {}", payment.getPaymentStatus());
                if (payment.getPaymentStatus().getTime().plusMinutes(payment.getPaymentStatus().getExpireInMinutes()).compareTo(LocalDateTime.now(ZoneOffset.UTC)) < 0) {
                    paymentStatusService.setPaymentStatus(PaymentStatusValue.Canceled, payment, "Payment canceled due to timeout");
                }
            }
        }
    }

}
