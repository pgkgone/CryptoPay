package com.cryptopay.converters;

import com.cryptopay.enums.SupportedChain;
import org.springframework.core.convert.converter.Converter;

public class StringToSupportedChainConverter implements Converter<String, SupportedChain> {
    @Override
    public SupportedChain convert(String source) {
        return SupportedChain.valueOf(source.toLowerCase());
    }
}
