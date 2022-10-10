package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoCurrencyChainJoinInfoDto;
import com.cryptopay.model.CryptoCurrencyChainJoin;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CryptoCurrencyChainJoinInfoMapper {

    CryptoCurrencyChainJoinInfoMapper INSTANCE = Mappers.getMapper(CryptoCurrencyChainJoinInfoMapper.class);

    CryptoCurrencyChainJoin cryptoCurrencyChainJoinInfoDtoToCryptoCurrencyChainJoin(CryptoCurrencyChainJoinInfoDto cryptoCurrencyChainJoinInfoDto);

    CryptoCurrencyChainJoinInfoDto cryptoCurrencyChainJoinToCryptoCurrencyChainJoinInfoDto(CryptoCurrencyChainJoin cryptoCurrencyChainJoin);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CryptoCurrencyChainJoin updateCryptoCurrencyChainJoinFromCryptoCurrencyChainJoinInfoDto(CryptoCurrencyChainJoinInfoDto cryptoCurrencyChainJoinInfoDto, @MappingTarget CryptoCurrencyChainJoin cryptoCurrencyChainJoin);
}
