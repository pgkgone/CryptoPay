package com.cryptopay.mapper;

import com.cryptopay.dto.ServiceInfoDto;
import com.cryptopay.model.Service;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ServiceInfoMapper {

    ServiceInfoMapper INSTANCE = Mappers.getMapper(ServiceInfoMapper.class);
    Service serviceInfoDtoToService(ServiceInfoDto serviceInfoDto);
    ServiceInfoDto serviceToServiceInfoDto(Service service);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Service updateServiceFromServiceInfoDto(ServiceInfoDto serviceInfoDto, @MappingTarget Service service);
}
