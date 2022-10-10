package com.cryptopay.mapper;

import com.cryptopay.dto.PaymentStatusDto;
import com.cryptopay.model.PaymentStatus;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentStatusMapper {

    PaymentStatus paymentStatusDtoToPaymentStatus(PaymentStatusDto paymentStatusDto);

    PaymentStatusDto paymentStatusToPaymentStatusDto(PaymentStatus paymentStatus);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentStatusFromPaymentStatusDto(
            PaymentStatusDto paymentStatusDto,
            @MappingTarget PaymentStatus paymentStatus
    );
}
