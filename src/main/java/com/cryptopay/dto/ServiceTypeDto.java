package com.cryptopay.dto;

import com.cryptopay.model.ServiceTypeValue;
import lombok.Data;

import java.io.Serializable;

@Data
public class ServiceTypeDto implements Serializable {
    private final ServiceTypeValue statusValue;
    private final Integer durationNumberOfDays;
}