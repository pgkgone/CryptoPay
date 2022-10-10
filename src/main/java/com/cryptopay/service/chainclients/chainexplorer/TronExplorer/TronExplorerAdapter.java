package com.cryptopay.service.chainclients.chainexplorer.TronExplorer;

import com.cryptopay.config.SupportedChain;
import com.cryptopay.exception.WalletBalanceParsingException;
import com.cryptopay.service.chainclients.chainexplorer.AbstractChainExplorerAdapter;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
@Slf4j
public class TronExplorerAdapter extends AbstractChainExplorerAdapter {

    public TronExplorerAdapter(
            @Value("${explorer.trongrid.url}") String explorerUrl,
            @Value("${explorer.trongrid.apiKey}") String apiKey
    ) {
        super(explorerUrl, null);
    }

    @Override
    public BigDecimal getBalance(String walletAddress, String contractAddress, Long contractDecimals) {
        log.info("Make request to trongrid");
        var response = this.httpClient.getForObject(
                String.format(this.explorerUrl + "/v1/accounts/%s", walletAddress),
                JsonNode.class
        );
        if (response == null) {
            throw new WalletBalanceParsingException("Unable to get results from explorer");
        }
        log.info(response.toString());
        return parseBalance(walletAddress, contractAddress, response)
                .movePointLeft(Math.toIntExact(contractDecimals));
    }

    @Override
    public SupportedChain getChain() {
        return SupportedChain.trc;
    }

    private BigDecimal parseBalance(String walletAddress, String targetContractAddress, JsonNode jsonNode) {
        var tokensArray = jsonNode.get("data").get(0).get("trc20");
        for (int i =0; i < tokensArray.size(); i++) {
            var tokenEntry = tokensArray.get(i);
            var tokenContract = tokenEntry.fieldNames().next();
            if (tokenContract.equals(targetContractAddress)) {
                return new BigDecimal(
                        tokenEntry.get(tokenContract).asText()
                );
            }
        }
        throw new WalletBalanceParsingException(
                String.format("Contract %s for wallet %s not found!", targetContractAddress, walletAddress)
        );
    }
}
