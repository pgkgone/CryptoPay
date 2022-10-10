package com.cryptopay.mapper;

import com.cryptopay.dto.PaymentDto;
import com.cryptopay.model.Payment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
    @Mapping(source = "cryptoWalletAddress", target = "cryptoWallet.address")
    Payment paymentDtoToPayment(PaymentDto paymentDto);

    @Mapping(source = "cryptoWallet.address", target = "cryptoWalletAddress")
    PaymentDto paymentToPaymentDto(Payment payment);

    @Mapping(source = "cryptoWalletAddress", target = "cryptoWallet.address")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Payment updatePaymentFromPaymentDto(PaymentDto paymentDto, @MappingTarget Payment payment);
}
