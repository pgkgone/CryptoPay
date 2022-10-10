package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoCurrencyDto;
import com.cryptopay.model.CryptoCurrency;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CryptoCurrencyMapper {

    CryptoCurrency cryptoCurrencyDtoToCryptoCurrency(CryptoCurrencyDto cryptoCurrencyDto);

    CryptoCurrencyDto cryptoCurrencyToCryptoCurrencyDto(CryptoCurrency cryptoCurrency);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCryptoCurrencyFromCryptoCurrencyDto(
            CryptoCurrencyDto cryptoCurrencyDto,
            @MappingTarget CryptoCurrency cryptoCurrency
    );
}
