package com.cryptopay.service;

import com.cryptopay.dto.CreatePaymentDto;
import com.cryptopay.enums.SupportedChain;
import com.cryptopay.enums.WalletFormat;
import com.cryptopay.exception.PaymentWithIpExists;
import com.cryptopay.mapper.CryptoWalletMapper;
import com.cryptopay.model.CryptoWallet;
import com.cryptopay.model.Payment;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.repository.CryptoChainRepository;
import com.cryptopay.repository.CryptoCurrencyRepository;
import com.cryptopay.repository.PaymentRepository;
import com.cryptopay.repository.ServiceRepository;
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
    public Payment createPayment(String ip, CreatePaymentDto createPayment) throws PaymentWithIpExists {
        this.throwIfPaymentWithIpExists(ip);
        log.info("input createPayment: {}", createPayment);
        var cryptoChain = this.cryptoChainRepository.findByShortcutName(createPayment.getCryptoChainShortcutName()).get();
        var cryptoCurrency = this.cryptoCurrencyRepository.findByShortcutName(createPayment.getCryptoCurrencyShortcutName()).get();
        var service = this.serviceRepository.findById(createPayment.getServiceId()).get();
        log.info("fetched cryptoChain: {}; cryptoCurrency: {}; service: {}", cryptoChain, cryptoCurrency, service);
        var payment = new Payment();
        payment.setIp(ip);
        payment.setCryptoChain(cryptoChain);
        payment.setCryptoCurrency(cryptoCurrency);
        payment.setService(service);
        payment.setEmail(createPayment.getEmail());
        var paymentStatus = this.paymentStatusService.createPaymentStatus(PaymentStatusValue.Created);
        log.info("payment status: {}", paymentStatus);
        payment.setPaymentStatus(paymentStatus);
        var cryptoWallet = createWallet(SupportedChain.valueOf(cryptoChain.getShortcutName()));
        payment.setCryptoWallet(cryptoWallet);
        payment = this.paymentRepository.saveAndFlush(payment);
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

    @Transactional
    public Payment updatePaymentStatus(Long id, PaymentStatusValue paymentStatus) {
        var payment = this.paymentRepository.findById(id).get();
        this.paymentStatusService.setPaymentStatus(paymentStatus, payment);
        return payment;
    }

    private CryptoWallet createWallet(
            SupportedChain chain
    ) {
        var generatedWalletInfo = this.cryptoWalletService
                .generateWallet(WalletFormat.mapChainToWalletFormat(chain));
        return cryptoWalletMapper.generatedWalletToCryptoWallet(generatedWalletInfo);
    }

    private void throwIfPaymentWithIpExists(String ip) throws PaymentWithIpExists {
        var payment = this.paymentRepository.findByIpAndStatuses(
                ip,
                List.of(
                        PaymentStatusValue.Created,
                        PaymentStatusValue.Pending
                )
        );
        if (payment.isPresent()) {
            throw new PaymentWithIpExists(
                    payment.get().getId(),
                    String.format("Active payment with ip: %s already exists", ip)
            );
        }
    }

}
