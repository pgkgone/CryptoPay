package com.cryptopay.service.chainclients.chainexplorer.BSCExplorer;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.exception.WalletBalanceParsingException;
import com.cryptopay.service.chainclients.chainexplorer.AbstractChainExplorerAdapter;
import com.cryptopay.service.chainclients.chainexplorer.chainscancommons.AccountBalanceResponse;
import com.cryptopay.service.chainclients.chainexplorer.chainscancommons.GetRequestStringBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class BSCExplorerAdapter extends AbstractChainExplorerAdapter {

    public BSCExplorerAdapter(
            @Value("${explorer.bscscan.url}") String explorerUrl,
            @Value("${explorer.bscscan.apiKey}") String apiKey
    ) {
        super(explorerUrl, apiKey);
    }


    @Override
    public BigDecimal getBalance(String walletAddress, String contractAddress, Long contractDecimals) {
        var rq = new GetRequestStringBuilder()
                .address(walletAddress)
                .contract(contractAddress)
                .apiToken(this.explorerApiKey)
                .build();
        log.info("Make request to {} with address: {}", getChain().toString(), this.explorerUrl + rq);
        var response = this.httpClient.getForObject(
                this.explorerUrl + rq,
                AccountBalanceResponse.class
        );
        if (response == null) {
            throw new WalletBalanceParsingException("Unable to get results from explorer");
        }
        log.info(response.toString());
        return new BigDecimal(response.getResult()).movePointLeft(Math.toIntExact(contractDecimals));
    }
    @Override
    public SupportedChain getChain() {
        return SupportedChain.bsc;
    }
}
