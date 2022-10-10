package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoCurrencyChainJoinInfoDto;
import com.cryptopay.model.CryptoCurrencyChainJoin;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CryptoCurrencyChainJoinInfoMapper {

    CryptoCurrencyChainJoin cryptoCurrencyChainJoinInfoDtoToCryptoCurrencyChainJoin(
            CryptoCurrencyChainJoinInfoDto cryptoCurrencyChainJoinInfoDto
    );

    CryptoCurrencyChainJoinInfoDto cryptoCurrencyChainJoinToCryptoCurrencyChainJoinInfoDto(
            CryptoCurrencyChainJoin cryptoCurrencyChainJoin
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CryptoCurrencyChainJoin updateCryptoCurrencyChainJoinFromCryptoCurrencyChainJoinInfoDto(
            CryptoCurrencyChainJoinInfoDto cryptoCurrencyChainJoinInfoDto,
            @MappingTarget CryptoCurrencyChainJoin cryptoCurrencyChainJoin
    );
}
