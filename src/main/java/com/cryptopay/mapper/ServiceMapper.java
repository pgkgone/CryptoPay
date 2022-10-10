package com.cryptopay.mapper;

import com.cryptopay.dto.ServiceDto;
import com.cryptopay.model.Service;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ServiceMapper {

    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    Service serviceDtoToService(ServiceDto serviceDto);

    ServiceDto serviceToServiceDto(Service service);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Service updateServiceFromServiceDto(ServiceDto serviceDto, @MappingTarget Service service);
}
