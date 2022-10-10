package com.cryptopay.service.chainclients.chainexplorer.chainscancommons;

import lombok.Data;

@Data
public class AccountBalanceResponse {
    private String status;
    private String message;
    private String result;
}
