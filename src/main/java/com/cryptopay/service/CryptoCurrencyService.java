package com.cryptopay.service;

import com.cryptopay.dto.CryptoCurrencyDto;
import com.cryptopay.mapper.CryptoCurrencyMapper;
import com.cryptopay.repository.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    private final CryptoCurrencyMapper cryptoCurrencyMapper;

    @Transactional(readOnly = true)
    public List<CryptoCurrencyDto> getAll() {
        return this.cryptoCurrencyRepository
                .findAll()
                .stream()
                .map(cryptoCurrencyMapper::cryptoCurrencyToCryptoCurrencyDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CryptoCurrencyDto getByShortcutName(String shortcutName) {
        return cryptoCurrencyMapper.cryptoCurrencyToCryptoCurrencyDto(
                        this.cryptoCurrencyRepository.findByShortcutName(shortcutName).get()
                );
    }
}
