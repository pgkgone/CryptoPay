package com.cryptopay.service.chainclients.chainexplorer.chainscancommons;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class GetRequestStringBuilder {

    private String address;

    private String contract;

    private String apiToken;

    public GetRequestStringBuilder address(String address) {
        this.address = address;
        return this;
    }

    public GetRequestStringBuilder contract(String contract) {
        this.contract = contract;
        return this;
    }

    public GetRequestStringBuilder apiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    public String build() {
        return String.format(
                "/api" +
                "?module=account" +
                "&action=tokenbalance" +
                "&contractaddress=%s" +
                "&address=%s" +
                "&tag=latest" +
                "&apikey=%s", this.contract, this.address, this.apiToken
        );
    }
}
