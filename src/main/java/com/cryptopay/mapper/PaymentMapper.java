package com.cryptopay.mapper;

import com.cryptopay.dto.PaymentDto;
import com.cryptopay.model.Payment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

    @Mapping(source = "cryptoWalletAddress", target = "cryptoWallet.address")
    Payment paymentDtoToPayment(PaymentDto paymentDto);

    @Mapping(source = "cryptoWallet.address", target = "cryptoWalletAddress")
    PaymentDto paymentToPaymentDto(Payment payment);

    @Mapping(source = "cryptoWalletAddress", target = "cryptoWallet.address")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment updatePaymentFromPaymentDto(PaymentDto paymentDto, @MappingTarget Payment payment);
}
