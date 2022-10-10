package com.cryptopay.mapper;

import com.cryptopay.dto.ServiceTypeDto;
import com.cryptopay.model.ServiceType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ServiceTypeMapper {

    ServiceType serviceTypeDtoToServiceType(ServiceTypeDto serviceTypeDto);

    ServiceTypeDto serviceTypeToServiceTypeDto(ServiceType serviceType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ServiceType updateServiceTypeFromServiceTypeDto(ServiceTypeDto serviceTypeDto, @MappingTarget ServiceType serviceType);
}
