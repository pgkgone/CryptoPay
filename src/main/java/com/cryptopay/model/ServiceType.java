package com.cryptopay.model;

import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Embeddable
@Data
public class ServiceType {

    @Enumerated(EnumType.STRING)
    @Column(name = "status_value", nullable = false)
    private ServiceTypeValue statusValue;

    @Column(name = "duration_number_of_days", nullable = false)
    private Integer durationNumberOfDays;

}