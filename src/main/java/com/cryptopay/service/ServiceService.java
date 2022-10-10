package com.cryptopay.service;

import com.cryptopay.dto.ServiceDto;
import com.cryptopay.dto.ServiceInfoDto;
import com.cryptopay.mapper.ServiceInfoMapper;
import com.cryptopay.mapper.ServiceMapper;
import com.cryptopay.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceInfoMapper serviceInfoMapper;
    private final ServiceMapper serviceMapper;
    @Transactional(readOnly = true)
    public List<ServiceInfoDto> getAllServiceInfoDtos() {
        return serviceRepository
            .findAll()
            .stream()
            .map(serviceInfoMapper::serviceToServiceInfoDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServiceInfoDto getServiceInfoDtoById(Long id) {
        var serviceInfoData = serviceRepository.findById(id);
        return serviceInfoMapper.serviceToServiceInfoDto(
                serviceInfoData.get()
        );
    }

    @Transactional(readOnly = true)
    public List<ServiceDto> getAllServiceDtos() {
        return serviceRepository
                .findAll()
                .stream()
                .map(serviceMapper::serviceToServiceDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServiceDto getServiceDtoById(Long id) {
        var serviceInfoData = serviceRepository.findById(id);
        return serviceMapper.serviceToServiceDto(
                serviceInfoData.get()
        );
    }
}
