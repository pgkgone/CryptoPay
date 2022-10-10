package com.cryptopay.mapper;

import com.cryptopay.dto.ServiceInfoDto;
import com.cryptopay.model.Service;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceInfoMapper {

    Service serviceInfoDtoToService(ServiceInfoDto serviceInfoDto);

    ServiceInfoDto serviceToServiceInfoDto(Service service);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Service updateServiceFromServiceInfoDto(ServiceInfoDto serviceInfoDto, @MappingTarget Service service);
}
