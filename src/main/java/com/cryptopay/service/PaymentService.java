package com.cryptopay.service;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.dto.CreatePaymentDto;
import com.cryptopay.mapper.CryptoWalletMapper;
import com.cryptopay.model.CryptoWallet;
import com.cryptopay.model.Payment;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.repository.*;
import com.cryptopay.service.chainclients.walletgenerator.WalletFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
@Slf4j
public class PaymentService {
    private PaymentRepository paymentRepository;
    private ServiceRepository serviceRepository;
    private CryptoChainRepository cryptoChainRepository;
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    private CryptoWalletService cryptoWalletService;
    private PaymentStatusService paymentStatusService;

    private CryptoWalletRepository cryptoWalletRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            ServiceRepository serviceRepository,
            CryptoChainRepository cryptoChainRepository,
            CryptoCurrencyRepository cryptoCurrencyRepository,
            CryptoWalletRepository cryptoWalletRepository,
            CryptoWalletService cryptoWalletService,
            PaymentStatusService paymentStatusService
    ) {
        this.paymentRepository = paymentRepository;
        this.serviceRepository = serviceRepository;
        this.cryptoChainRepository = cryptoChainRepository;
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.paymentStatusService = paymentStatusService;
        this.cryptoWalletService = cryptoWalletService;
        this.cryptoWalletRepository = cryptoWalletRepository;
    }

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
        var paymentStatus = this.paymentStatusService.createCreateStatus();
        log.info("payment status: {}", paymentStatus);
        payment.setPaymentStatus(paymentStatus);
        paymentStatus.setPayment(payment);
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
        return CryptoWalletMapper.INSTANCE.generatedWalletToCryptoWallet(generatedWalletInfo);
    }

}
