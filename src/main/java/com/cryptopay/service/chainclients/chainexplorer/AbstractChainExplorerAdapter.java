package com.cryptopay.service.chainclients.chainexplorer;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.repository.CryptoCurrencyChainJoinRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.BigInteger;

abstract public class AbstractChainExplorerAdapter {

    protected RestTemplate httpClient = new RestTemplate();
    protected String explorerUrl;
    protected String explorerApiKey;

    public AbstractChainExplorerAdapter(String explorerUrl, String explorerApiKey) {
        var restTemplateBuilder = new RestTemplateBuilder();
        this.explorerUrl = explorerUrl;
        this.explorerApiKey = explorerApiKey;
    }

    public abstract BigDecimal getBalance(String walletAddress, String contractAddress, Long contractDecimals);

    public abstract SupportedChain getChain();
}
