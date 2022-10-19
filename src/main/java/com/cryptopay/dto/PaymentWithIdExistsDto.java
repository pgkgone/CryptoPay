package com.cryptopay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentWithIdExistsDto {
        private Long id;
        private String message;
}
