package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoCurrencyDto;
import com.cryptopay.model.CryptoCurrency;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CryptoCurrencyMapper {

    CryptoCurrencyMapper INSTANCE = Mappers.getMapper(CryptoCurrencyMapper.class);

    CryptoCurrency cryptoCurrencyDtoToCryptoCurrency(CryptoCurrencyDto cryptoCurrencyDto);

    CryptoCurrencyDto cryptoCurrencyToCryptoCurrencyDto(CryptoCurrency cryptoCurrency);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCryptoCurrencyFromCryptoCurrencyDto(CryptoCurrencyDto cryptoCurrencyDto, @MappingTarget CryptoCurrency cryptoCurrency);
}
