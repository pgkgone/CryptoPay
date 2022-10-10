package com.cryptopay.service;

import com.cryptopay.dto.ServiceDto;
import com.cryptopay.dto.ServiceInfoDto;
import com.cryptopay.mapper.ServiceInfoMapper;
import com.cryptopay.mapper.ServiceMapper;
import com.cryptopay.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    ServiceService(
            ServiceRepository serviceRepository
    ) {
        this.serviceRepository = serviceRepository;
    }

    @Transactional(readOnly = true)
    public List<ServiceInfoDto> getAllServiceInfoDtos() {
        return serviceRepository
            .findAll()
            .stream()
            .map(ServiceInfoMapper.INSTANCE::serviceToServiceInfoDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServiceInfoDto getServiceInfoDtoById(Long id) {
        var serviceInfoData = serviceRepository.findById(id);
        return ServiceInfoMapper.INSTANCE.serviceToServiceInfoDto(
                serviceInfoData.get()
        );
    }

    @Transactional(readOnly = true)
    public List<ServiceDto> getAllServiceDtos() {
        return serviceRepository
                .findAll()
                .stream()
                .map(ServiceMapper.INSTANCE::serviceToServiceDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServiceDto getServiceDtoById(Long id) {
        var serviceInfoData = serviceRepository.findById(id);
        return ServiceMapper.INSTANCE.serviceToServiceDto(
                serviceInfoData.get()
        );
    }
}
