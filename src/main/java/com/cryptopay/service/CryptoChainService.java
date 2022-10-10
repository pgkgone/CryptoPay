package com.cryptopay.service;

import com.cryptopay.dto.CryptoChainDto;
import com.cryptopay.mapper.CryptoChainMapper;
import com.cryptopay.repository.CryptoChainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoChainService {

    private final CryptoChainRepository cryptoChainRepository;

    CryptoChainService(
            CryptoChainRepository cryptoChainRepository
    ) {
        this.cryptoChainRepository = cryptoChainRepository;
    }

    @Transactional(readOnly = true)
    public List<CryptoChainDto> getAll() {
        return this.cryptoChainRepository
                .findAll()
                .stream()
                .map(CryptoChainMapper.INSTANCE::cryptoChainToCryptoChainDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CryptoChainDto getByShortcutName(String shortcutName) {
        return CryptoChainMapper.INSTANCE
                .cryptoChainToCryptoChainDto(
                        this.cryptoChainRepository.findByShortcutName(shortcutName).get()
                );
    }
}
