package com.cryptopay.service;

import com.cryptopay.dto.CryptoChainDto;
import com.cryptopay.mapper.CryptoChainMapper;
import com.cryptopay.repository.CryptoChainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoChainService {

    private final CryptoChainRepository cryptoChainRepository;

    private final CryptoChainMapper cryptoChainMapper;

    @Transactional(readOnly = true)
    public List<CryptoChainDto> getAll() {
        return this.cryptoChainRepository
                .findAll()
                .stream()
                .map(cryptoChainMapper::cryptoChainToCryptoChainDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CryptoChainDto getByShortcutName(String shortcutName) {
        return cryptoChainMapper
                .cryptoChainToCryptoChainDto(
                        this.cryptoChainRepository.findByShortcutName(shortcutName).get()
                );
    }
}
