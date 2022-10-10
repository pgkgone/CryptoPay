package com.cryptopay.service;

import com.cryptopay.dto.CryptoCurrencyDto;
import com.cryptopay.mapper.CryptoCurrencyMapper;
import com.cryptopay.model.CryptoCurrency;
import com.cryptopay.repository.CryptoCurrencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    CryptoCurrencyService(
            CryptoCurrencyRepository cryptoCurrencyRepository
    ) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }

    @Transactional(readOnly = true)
    public List<CryptoCurrencyDto> getAll() {
        return this.cryptoCurrencyRepository
                .findAll()
                .stream()
                .map(CryptoCurrencyMapper.INSTANCE::cryptoCurrencyToCryptoCurrencyDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CryptoCurrencyDto getByShortcutName(String shortcutName) {
        return CryptoCurrencyMapper.INSTANCE
                .cryptoCurrencyToCryptoCurrencyDto(
                        this.cryptoCurrencyRepository.findByShortcutName(shortcutName).get()
                );
    }
}
