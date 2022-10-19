package com.cryptopay.converters;

import com.cryptopay.enums.SupportedChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToSupportedChainConverter implements Converter<String, SupportedChain> {
    @Override
    public SupportedChain convert(String source) {
        return SupportedChain.valueOf(source.toLowerCase());
    }
}
