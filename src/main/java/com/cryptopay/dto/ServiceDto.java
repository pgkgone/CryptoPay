package com.cryptopay.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceDto implements Serializable {
    private final Long id;
    private final String name;
    private final String description;
    private final ServiceTypeDto serviceType;
    private final Double cost;
}
