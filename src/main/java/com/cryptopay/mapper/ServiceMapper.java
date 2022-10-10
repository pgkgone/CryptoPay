package com.cryptopay.mapper;

import com.cryptopay.dto.ServiceDto;
import com.cryptopay.model.Service;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {

    Service serviceDtoToService(ServiceDto serviceDto);

    ServiceDto serviceToServiceDto(Service service);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Service updateServiceFromServiceDto(ServiceDto serviceDto, @MappingTarget Service service);
}
