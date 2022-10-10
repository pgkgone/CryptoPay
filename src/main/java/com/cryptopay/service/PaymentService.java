package com.cryptopay.service;

import com.cryptopay.enums.SupportedChain;
import com.cryptopay.dto.CreatePaymentDto;
import com.cryptopay.mapper.CryptoWalletMapper;
import com.cryptopay.model.CryptoWallet;
import com.cryptopay.model.Payment;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.repository.*;
import com.cryptopay.enums.WalletFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ServiceRepository serviceRepository;
    private final CryptoChainRepository cryptoChainRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CryptoWalletService cryptoWalletService;
    private final PaymentStatusService paymentStatusService;

    private final CryptoWalletMapper cryptoWalletMapper;

    @Transactional
    public Payment createPayment(CreatePaymentDto createPayment) {
        log.info("input createPayment: {}", createPayment);
        var cryptoChain = this.cryptoChainRepository.findByShortcutName(createPayment.getCryptoChainShortcutName()).get();
        var cryptoCurrency = this.cryptoCurrencyRepository.findByShortcutName(createPayment.getCryptoCurrencyShortcutName()).get();
        var service = this.serviceRepository.findById(createPayment.getServiceId()).get();
        log.info("fetched cryptoChain: {}; cryptoCurrency: {}; service: {}", cryptoChain, cryptoCurrency, service);
        var payment = new Payment();
        payment.setCryptoChain(cryptoChain);
        payment.setCryptoCurrency(cryptoCurrency);
        payment.setService(service);
        payment.setEmail(createPayment.getEmail());
        var paymentStatus = this.paymentStatusService.createPaymentStatus(PaymentStatusValue.Created);
        log.info("payment status: {}", paymentStatus);
        payment.setPaymentStatus(paymentStatus);
        var cryptoWallet = createWallet(SupportedChain.valueOf(cryptoChain.getShortcutName()));
        payment.setCryptoWallet(cryptoWallet);
        payment = this.paymentRepository.save(payment);
        return payment;
    }

    @Transactional
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Payment> getPayments(List<PaymentStatusValue> statuses) {
        return paymentRepository.findByPaymentStatuses(statuses);
    }

    private CryptoWallet createWallet(
            SupportedChain chain
    ) {
        var generatedWalletInfo = this.cryptoWalletService
                .generateWallet(WalletFormat.mapChainToWalletFormat(chain));
        return cryptoWalletMapper.generatedWalletToCryptoWallet(generatedWalletInfo);
    }

}
