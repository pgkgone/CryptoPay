package com.cryptopay.mapper;

import com.cryptopay.dto.CryptoChainDto;
import com.cryptopay.model.CryptoChain;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CryptoChainMapper {

    CryptoChainMapper INSTANCE = Mappers.getMapper(CryptoChainMapper.class);

    CryptoChain cryptoChainDtoToCryptoChain(CryptoChainDto cryptoChainDto);

    CryptoChainDto cryptoChainToCryptoChainDto(CryptoChain cryptoChain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCryptoChainFromCryptoChainDto(CryptoChainDto cryptoChainDto, @MappingTarget CryptoChain cryptoChain);
}
