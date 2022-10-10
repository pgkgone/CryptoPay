package com.cryptopay.controller;

import com.cryptopay.dto.ServiceDto;
import com.cryptopay.dto.ServiceInfoDto;
import com.cryptopay.service.ServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping("/info/all")
    public List<ServiceInfoDto> getAllInfo() {
        return this.serviceService.getAllServiceInfoDtos();
    }

    @GetMapping("/info/{id}")
    public ServiceInfoDto getInfo(@PathVariable Long id) {
        return this.serviceService.getServiceInfoDtoById(id);
    }

    @GetMapping("/all")
    public List<ServiceDto> getAll() {
        return this.serviceService.getAllServiceDtos();
    }

    @GetMapping("/{id}")
    public ServiceDto get(@PathVariable Long id) {
        return this.serviceService.getServiceDtoById(id);
    }

}
