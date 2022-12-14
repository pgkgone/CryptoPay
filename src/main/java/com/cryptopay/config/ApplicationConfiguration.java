package com.cryptopay.config;

import com.cryptopay.enums.SupportedChain;
import com.cryptopay.enums.WalletFormat;
import com.cryptopay.service.chainclients.chainexplorer.AbstractChainExplorerAdapter;
import com.cryptopay.service.chainclients.walletgenerator.AbstractWalletGenerator;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableScheduling
@Slf4j
public class ApplicationConfiguration {

    @Bean
    public Map<WalletFormat, AbstractWalletGenerator> configureMapGenerators(
            List<AbstractWalletGenerator> walletGenerators
    ) {
        Map<WalletFormat, AbstractWalletGenerator> map = new HashMap<>();
        for (var generator : walletGenerators) {
            map.put(generator.getWalletFormat(), generator);
        }
        log.info("initialized {} wallet generators", map.size());
        return map;
    }

    @Bean
    public Map<SupportedChain, AbstractChainExplorerAdapter> configureMapExplorers(
            List<AbstractChainExplorerAdapter> chainExplorerAdapters
    ) {
        Map<SupportedChain, AbstractChainExplorerAdapter> map = new HashMap<>();
        for (var chainExplorer : chainExplorerAdapters) {
            map.put(chainExplorer.getChain(), chainExplorer);
        }
        log.info("initialized {} chain explorers", map.size());
        return map;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(2);
        return threadPoolTaskScheduler;
    }

    @Bean
    public AES256TextEncryptor aes256TextEncryptor(
            @Value("${settings.encryptionSeed}") String encryptionSeed
    ) {
        AES256TextEncryptor aes256TextEncryptor = new AES256TextEncryptor();
        aes256TextEncryptor.setPassword(encryptionSeed);
        return aes256TextEncryptor;
    }


}
