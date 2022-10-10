package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoCurrencyChainJoinDto;
import com.cryptopay.model.CryptoCurrencyChainJoin;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CryptoCurrencyChainJoinMapper {

    CryptoCurrencyChainJoinMapper INSTANCE = Mappers.getMapper(CryptoCurrencyChainJoinMapper.class);

    CryptoCurrencyChainJoin cryptoCurrencyChainJoinDtoToCryptoCurrencyChainJoin(CryptoCurrencyChainJoinDto cryptoCurrencyChainJoinDto);

    CryptoCurrencyChainJoinDto cryptoCurrencyChainJoinToCryptoCurrencyChainJoinDto(CryptoCurrencyChainJoin cryptoCurrencyChainJoin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CryptoCurrencyChainJoin updateCryptoCurrencyChainJoinFromCryptoCurrencyChainJoinDto(CryptoCurrencyChainJoinDto cryptoCurrencyChainJoinDto, @MappingTarget CryptoCurrencyChainJoin cryptoCurrencyChainJoin);
}
