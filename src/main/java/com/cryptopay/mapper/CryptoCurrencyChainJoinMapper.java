package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoCurrencyChainJoinDto;
import com.cryptopay.model.CryptoCurrencyChainJoin;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CryptoCurrencyChainJoinMapper {

    CryptoCurrencyChainJoin cryptoCurrencyChainJoinDtoToCryptoCurrencyChainJoin(
            CryptoCurrencyChainJoinDto cryptoCurrencyChainJoinDto
    );

    CryptoCurrencyChainJoinDto cryptoCurrencyChainJoinToCryptoCurrencyChainJoinDto(
            CryptoCurrencyChainJoin cryptoCurrencyChainJoin
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CryptoCurrencyChainJoin updateCryptoCurrencyChainJoinFromCryptoCurrencyChainJoinDto(
            CryptoCurrencyChainJoinDto cryptoCurrencyChainJoinDto,
            @MappingTarget CryptoCurrencyChainJoin cryptoCurrencyChainJoin
    );
}
