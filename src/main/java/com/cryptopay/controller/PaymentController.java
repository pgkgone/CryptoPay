package com.cryptopay.controller;

import com.cryptopay.dto.CreatePaymentDto;
import com.cryptopay.dto.PaymentCancelDto;
import com.cryptopay.dto.PaymentDto;
import com.cryptopay.exception.PaymentWithIpExists;
import com.cryptopay.mapper.PaymentMapper;
import com.cryptopay.model.PaymentStatusValue;
import com.cryptopay.service.PaymentService;
import com.cryptopay.service.PaymentStatusService;
import com.cryptopay.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentStatusService paymentStatusService;
    private final PaymentMapper paymentMapper;

    @PostMapping("/create")
    public PaymentDto createPayment(
            @RequestBody CreatePaymentDto createPaymentDto,
            HttpServletRequest request
    ) throws PaymentWithIpExists {
        log.info("Received request for payment creation");
        var payment = this.paymentService.createPayment(
                HttpUtils.getRequestIP(request),
                createPaymentDto
        );
        log.info("payment: {}", payment);
        log.info("payment dto: {}", paymentMapper.paymentToPaymentDto(payment));
        return paymentMapper.paymentToPaymentDto(payment);
    }

    @PostMapping("/cancel/{id}")
    public PaymentCancelDto cancelPayment(
            @PathVariable("id") Long id
    ) {
        this.paymentService.updatePaymentStatus(id, PaymentStatusValue.Canceled);
        return new PaymentCancelDto(id, "Status successfully changed");
    }

    @PostMapping("/pay/{paymentId}")
    public PaymentDto paymentConfirmedByUser(@PathVariable Long paymentId) {
        log.info("Received request payment confirmed by user");
        var payment = this.paymentService.getPaymentById(paymentId);
        this.paymentStatusService.setPaymentStatus(PaymentStatusValue.Pending, payment);
        return paymentMapper.paymentToPaymentDto(payment);
    }

}
