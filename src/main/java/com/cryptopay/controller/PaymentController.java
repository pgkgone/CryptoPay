package com.cryptopay.controller;

import com.cryptopay.dto.CreatePaymentDto;
import com.cryptopay.dto.PaymentDto;
import com.cryptopay.mapper.PaymentMapper;
import com.cryptopay.service.PaymentService;
import com.cryptopay.service.PaymentStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentStatusService paymentStatusService;

    PaymentController(
            PaymentService paymentService,
            PaymentStatusService paymentStatusService) {
        this.paymentService = paymentService;
        this.paymentStatusService = paymentStatusService;
    }

    @PostMapping("/create")
    public PaymentDto createPayment(@RequestBody CreatePaymentDto createPaymentDto) {
        log.info("Received request for payment creation");
        var payment = this.paymentService.createPayment(createPaymentDto);
        return PaymentMapper.INSTANCE.paymentToPaymentDto(payment);
    }

    @PostMapping("/pay/{paymentId}")
    public PaymentDto paymentConfirmedByUser(@PathVariable Long paymentId) {
        log.info("Received request payment confirmed by user");
        var payment = this.paymentService.getPaymentById(paymentId);
        this.paymentStatusService.setPendingStatus(payment);
        return PaymentMapper.INSTANCE.paymentToPaymentDto(payment);
    }

}
